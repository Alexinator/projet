/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class Graph {

	private Map<String, Set<Troncon>> arcs;

	/**
	 * Constructeur qui génère une map (arcs) avec comme valeur le point de départ des tronçons
	 * @param troncons un set de troncons
	 */
	public Graph() {
		super();
		this.arcs = new HashMap<String, Set<Troncon>>();
	}
	
	public void ajouterTroncon(Set<Troncon> troncons) {
		if (troncons == null)
			throw new IllegalArgumentException();
		for (Troncon troncon : troncons) {
			if (!arcs.containsKey(troncon.getDepart())) {
				arcs.put(troncon.getDepart(), new HashSet<Troncon>());
			}
			arcs.get(troncon.getDepart()).add(troncon);
		}
	}

	/**
	 * Calcule le chemin minimisant le nombre de troncons en fonction d'un point de
	 * depart et d'une arrivee.
	 * @param stationDepart:  la station de depart
	 * @param stationArrivee: la station d'arrivee
	 * @param fichier:        le chemin du fichier XML
	 */
	public void calculerCheminMinimisantNombreTroncons(String stationDepart, String stationArrivee, String fichier) {
		Deque<String> fileStations = new ArrayDeque<String>();
		Set<String> sommets = new HashSet<String>();
		Map<String, Troncon> chemin = new HashMap<String, Troncon>();
		Deque<Troncon> cheminDefinitif = new ArrayDeque<Troncon>();
		fileStations.add(stationDepart);
		sommets.add(stationDepart);
		while (!fileStations.isEmpty()) {
			String station = fileStations.remove();
			for (Troncon troncon : arcs.get(station)) {
				if (!sommets.contains(troncon.getArrivee())) {
					sommets.add(troncon.getArrivee());
					fileStations.add(troncon.getArrivee());
					chemin.put(troncon.getArrivee(), troncon);
				}
				if (troncon.getArrivee().equals(stationArrivee)) {
					Troncon dernier = chemin.get(stationArrivee);
					while (dernier != null) {
						cheminDefinitif.push(dernier);
						dernier = chemin.get(dernier.getDepart());
					}
					ecrireXML(cheminDefinitif, fichier);
					return;
				}
			}
		}
		throw new IllegalArgumentException("Le chemin entre les deux stations est impossible");
	}

	/**
	 * Calcule le chemin minimisant au maximum le temps passe dans les transports en
	 * fonction d'un point de depart et d'un point d'arrivee. 
	 * @param stationDepart:  la station de depart
	 * @param stationArrivee: la station d'arrivee
	 * @param fichier:        le chemin du fichier XML
	 */
	public void calculerCheminMinimisantTempsTransport(String stationDepart, String stationArrivee, String fichier) {
		Map<String, Integer> dureesDefinitives = new HashMap<String, Integer>();
		Map<String, Integer> dureePossible = new HashMap<String, Integer>();
		Map<String, List<Troncon>> chemin = new HashMap<String, List<Troncon>>();
		Deque<Troncon> cheminDefinitif = new ArrayDeque<Troncon>();

		dureePossible.put(stationDepart, 0);
		String positionActuelle = stationDepart;
		chemin.put(stationDepart, new ArrayList<Troncon>());

		while (!positionActuelle.equals(stationArrivee)) {
			int dureeTroncon = dureePossible.get(positionActuelle);
			dureesDefinitives.put(positionActuelle, dureePossible.remove(positionActuelle));
			
			for (Troncon troncon : arcs.get(positionActuelle)) {
				List<Troncon> tronconsParcourus = new ArrayList<Troncon>();
				for(Troncon tr : chemin.get(positionActuelle)) {
					tronconsParcourus.add(tr);
				}
				tronconsParcourus.add(troncon);
				if (!dureePossible.containsKey((troncon.getArrivee()))) {
					dureePossible.put(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
					chemin.put(troncon.getArrivee(), tronconsParcourus);
				}
				else if(dureePossible.get(troncon.getArrivee()) > dureeTroncon+troncon.getDuree()) {
					dureePossible.replace(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
					chemin.replace(troncon.getArrivee(), tronconsParcourus);
				}
			}			
			int dureeMin = Integer.MAX_VALUE;
			for(String sommet : dureePossible.keySet()) {
				if(dureePossible.get(sommet) < dureeMin) {
					dureeMin = dureePossible.get(sommet);
					positionActuelle = sommet;
				}
			}
			if(positionActuelle.equals(stationArrivee)) {
				dureeTroncon = dureePossible.remove(positionActuelle);
				dureesDefinitives.put(positionActuelle, dureeTroncon);
				break;
			}
		}
		for(Troncon troncon : chemin.get(stationArrivee)) {
			cheminDefinitif.add(troncon);
		}
		ecrireXML(cheminDefinitif, fichier);
	}

	/**
	 * Ecrit le fichier XML en fonction d'une pile de troncons reçue. 
	 * @param pile:    l'ensemble des troncons
	 * @param fichier: le chemin du fichier XML
	 */
	private void ecrireXML(Deque<Troncon> pile, String fichier) {
		String depart = pile.peekFirst().getDepart();
		String arrivee = pile.peekLast().getArrivee();
		String debut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

		int duree = 0;
		int taillePile = pile.size();
		int nombreTroncons = 1;
		String body = "";
		Troncon troncon = pile.pop();

		while (true) {
			String tronconDepart = troncon.getDepart();
			String tronconArrivee = troncon.getArrivee();
			while(troncon.getLigne().equals(pile.peek().getLigne())) {
				duree+=troncon.getDuree();
				nombreTroncons++;
				troncon = pile.pop();
				if(pile.isEmpty()) {
					break;
				}
			}
			duree+=troncon.getDuree();
			tronconArrivee = troncon.getArrivee();
			body += "<deplacement arrivee=\"" + tronconArrivee + "\" attenteMoyenne=\""
					+ troncon.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + tronconDepart
					+ "\" direction=\"" + troncon.getLigne().getDestination() + "\" duree=\"" + duree
					+ "\" nbTroncon=\"" + nombreTroncons + "\">" + troncon.getLigne().getNom() + "</deplacement>\n";
			if(pile.isEmpty()) {
				break;
			}
			nombreTroncons = 1;
			duree = 0;
			troncon = pile.pop();
		}

		String header = "<trajet depart=\"" + depart + "\" destination=\"" + arrivee + "\" duree=\"" + duree
				+ "\" nbTroncons=\"" + taillePile + "\">\n";
		String fin = "</trajet>";
		try {
			PrintWriter writer = new PrintWriter(fichier);
			writer.write(debut + header + body + fin);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
