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

public class Graph {

	private Map<String, Set<Troncon>> arcs;

	// TODO rajouter des methodes pour ajouter des troncons et stations
	// (appellees via le saxhandler)

	public Graph(Set<Troncon> troncons) {
		super();
		this.arcs = new HashMap<String, Set<Troncon>>();
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
	 * 
	 * @param stationDepart:  la station de depart
	 * @param stationArrivee: la station d'arrivee
	 * @param fichier:        le chemin du fichier XML
	 */
	public void calculerCheminMinimisantNombreTroncons(String stationDepart, String stationArrivee, String fichier) {
		Deque<String> bfsFile = new ArrayDeque<String>();
		Set<String> sommets = new HashSet<String>();
		Map<String, Troncon> chemin = new HashMap<String, Troncon>();
		Deque<Troncon> pile = new ArrayDeque<Troncon>();
		bfsFile.add(stationDepart);
		sommets.add(stationDepart);
		while (!bfsFile.isEmpty()) {
			String station = bfsFile.remove();
			for (Troncon troncon : arcs.get(station)) {
				if (!sommets.contains(troncon.getArrivee())) {
					sommets.add(troncon.getArrivee());
					bfsFile.add(troncon.getArrivee());
					chemin.put(troncon.getArrivee(), troncon);
				}
				if (troncon.getArrivee().equals(stationArrivee)) {
					Troncon dernier = chemin.get(stationArrivee);
					while (dernier != null) {
						pile.push(dernier);
						dernier = chemin.get(dernier.getDepart());
					}
					ecrireXML(pile, fichier);
					return;
				}
			}
		}
		throw new IllegalArgumentException("Le chemin entre les deux stations est impossible");
	}

	/**
	 * Calcule le chemin minimisant au maximum le temps passe dans les transports en
	 * fonction d'un point de depart et d'un point d'arrivee.
	 * 
	 * @param stationDepart:  la station de depart
	 * @param stationArrivee: la station d'arrivee
	 * @param fichier:        le chemin du fichier XML
	 */
	public void calculerCheminMinimisantTempsTransport(String stationDepart, String stationArrivee, String fichier) {
		Map<String, Integer> dureesdefinitives = new HashMap<String, Integer>();
		Map<String, Integer> dureestemps = new HashMap<String, Integer>();
		Map<String, List<Troncon>> chemin = new HashMap<String, List<Troncon>>();

		dureestemps.put(stationDepart, 0);
		String positionActuelle = stationDepart;
		chemin.put(stationDepart, new ArrayList<Troncon>());

		while (!positionActuelle.equals(stationArrivee)) {
			int dureeTroncon = dureestemps.get(positionActuelle);
			dureesdefinitives.put(positionActuelle, dureestemps.remove(positionActuelle));
			
			for (Troncon troncon : arcs.get(positionActuelle)) {
				List<Troncon> tronconsParcourus = new ArrayList<Troncon>();
				for(Troncon tr : chemin.get(positionActuelle)) {
					tronconsParcourus.add(tr);
				}
				tronconsParcourus.add(troncon);
				if (!dureestemps.containsKey((troncon.getArrivee()))) {
					dureestemps.put(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
					chemin.put(troncon.getArrivee(), tronconsParcourus);
				}
				else if(dureestemps.get(troncon.getArrivee()) > dureeTroncon+troncon.getDuree()) {
					dureestemps.replace(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
					chemin.replace(troncon.getArrivee(), tronconsParcourus);
				}
			}
			
			int dureeMin = Integer.MAX_VALUE;
			for(String sommet : dureestemps.keySet()) {
				if(dureestemps.get(sommet) < dureeMin) {
					dureeMin = dureestemps.get(sommet);
					positionActuelle = sommet;
				}
			}
			if(positionActuelle.equals(stationArrivee)) {
				dureeTroncon = dureestemps.remove(positionActuelle);
				dureesdefinitives.put(positionActuelle, dureeTroncon);
				break;
			}
		}

		int duree = 0;
		for(Troncon troncon : chemin.get(stationArrivee)) {
			System.out.println(troncon);
			duree += troncon.getDuree();
		}
		
		System.out.println("duree pour aller jusqu'a alma: "+duree);
		// ecrireXML(troncons, fichier);

	}

	/**
	 * Ecrit le fichier XML en fonction des troncons recus.
	 * 
	 * @param pile:    l'ensemble des troncons
	 * @param fichier: le chemin du fichier XML
	 */
	private void ecrireXML(Deque<Troncon> pile, String fichier) {
		String depart = pile.peekFirst().getDepart();
		String arrivee = pile.peekLast().getArrivee();
		String debut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

		int duree = 0;
		int nbTroncons = pile.size();
		Set<Ligne> lignes = new HashSet<Ligne>();
		Ligne lignePrecedente = null;
		int nbr = 1;
		String body = "";
		Troncon troncon = null;

		while (!pile.isEmpty()) {
			troncon = pile.pop();
			duree += troncon.getDuree();
			if (!lignes.contains(troncon.getLigne())) {
				lignes.add(troncon.getLigne());
				duree += troncon.getLigne().getAttenteMoyenne();
			}
			if (!troncon.getLigne().equals(lignePrecedente)) {
				body += "<deplacement arrivee=\"" + troncon.getArrivee() + "\" attenteMoyenne=\""
						+ troncon.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + troncon.getDepart()
						+ "\" direction=\"" + troncon.getLigne().getDestination() + "\" duree=\"" + troncon.getDuree()
						+ "\" nbTroncon=\"" + nbr + "\">" + troncon.getLigne().getNom() + "</deplacement>\n";
			}
			nbr++;
			lignePrecedente = troncon.getLigne();
		}

		String header = "<trajet depart=\"" + depart + "\" destination=\"" + arrivee + "\" duree=\"" + duree
				+ "\" nbTroncons=\"" + nbTroncons + "\">\n";
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
