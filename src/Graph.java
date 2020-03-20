
/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<String, Set<Troncon>> arcs;

	/**
	 * Constructeur
	 */
	public Graph() {
		super();
		this.arcs = new HashMap<String, Set<Troncon>>();
	}

	/**
	 * Ajoute un troncon dans la map d'arcs
	 * 
	 * @param troncon
	 *            le troncon a ajouter
	 */
	public void ajouterTroncon(Troncon troncon) {
		if (troncon == null)
			throw new IllegalArgumentException();
		if (!arcs.containsKey(troncon.getDepart()))
			arcs.put(troncon.getDepart(), new HashSet<Troncon>());
		arcs.get(troncon.getDepart()).add(troncon);
	}

	/**
	 * Calcule le chemin minimisant le nombre de troncons en fonction d'un point de
	 * depart et d'une arrivee.
	 * 
	 * @param stationDepart
	 *            la station de depart
	 * @param stationArrivee
	 *            la station d'arrivee
	 * @param fichier
	 *            le chemin du fichier XML
	 * @throws Exception
	 *             lance une exception s'il n'existe pas de chemin entre les deux
	 *             stations
	 */
	public void calculerCheminMinimisantNombreTroncons(String stationDepart, String stationArrivee, String fichier)
			throws Exception {
		Deque<String> fileStations = new ArrayDeque<String>();
		Set<String> sommets = new HashSet<String>();
		Map<String, Troncon> chemin = new HashMap<String, Troncon>();

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
					ecrireXML(stationDepart, stationArrivee, chemin, fichier);
					return;
				}
			}
		}
		throw new Exception("Le chemin entre les deux stations est impossible");
	}

	/**
	 * Calcule le chemin minimisant au maximum le temps passe dans les transports en
	 * fonction d'un point de depart et d'un point d'arrivee.
	 * 
	 * @param stationDepart
	 *            la station de depart
	 * @param stationArrivee
	 *            la station d'arrivee
	 * @param fichier
	 *            le chemin du fichier XML
	 * @throws Exception
	 *             lance une exception s'il n'existe pas de chemin entre les deux
	 *             stations
	 */
	public void calculerCheminMinimisantTempsTransport(String stationDepart, String stationArrivee, String fichier)
			throws Exception {

		Map<String, Integer> dureesDefinitives = new HashMap<String, Integer>();
		Map<String, Integer> dureesPossibles = new HashMap<String, Integer>();
		Map<String, Troncon> chemin = new HashMap<String, Troncon>();

		dureesPossibles.put(stationDepart, 0);
		String positionActuelle = stationDepart;

		while (!dureesDefinitives.containsKey(positionActuelle)) {
			int dureeTroncon = dureesPossibles.get(positionActuelle);
			dureesDefinitives.put(positionActuelle, dureesPossibles.remove(positionActuelle));
			for (Troncon troncon : arcs.get(positionActuelle)) {
				if (!dureesDefinitives.containsKey(troncon.getArrivee())) {
					if (!dureesPossibles.containsKey((troncon.getArrivee()))) {
						dureesPossibles.put(troncon.getArrivee(), dureeTroncon + troncon.getDuree());
						chemin.put(troncon.getArrivee(), troncon);
					} else if (dureesPossibles.get(troncon.getArrivee()) > dureeTroncon + troncon.getDuree()) {
						dureesPossibles.replace(troncon.getArrivee(), dureeTroncon + troncon.getDuree());
						chemin.replace(troncon.getArrivee(), troncon);
					}
				}
			}
			int dureeMin = Integer.MAX_VALUE;
			for (String sommet : dureesPossibles.keySet()) {
				if (dureesPossibles.get(sommet) < dureeMin) {
					dureeMin = dureesPossibles.get(sommet);
					positionActuelle = sommet;
				}
			}
			if (positionActuelle.equals(stationArrivee)) {
				dureeTroncon = dureesPossibles.remove(positionActuelle);
				dureesDefinitives.put(positionActuelle, dureeTroncon);
				ecrireXML(stationDepart, stationArrivee, chemin, fichier);
				return;
			}
			if(dureesDefinitives.size() == dureesPossibles.size()) {
				throw new Exception("Le chemin entre les deux stations est impossible");
			}
		}

	}

	/**
	 * Ecrit le fichier XML en fonction d'une map de troncons recus.
	 * 
	 * @param pile
	 *            l'ensemble des troncons
	 * @param fichier
	 *            le chemin du fichier XML
	 */
	private void ecrireXML(String stationDepart, String stationArrivee, Map<String, Troncon> chemin, String fichier) {
		String depart = stationDepart;
		String arrivee = stationArrivee;
		String debut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

		int dureeTroncon = 0;
		int dureeTotale = 0;
		int tailleChemin = 0;
		int nombreTroncons = 0;
		String body = "";
		String texte = "";
		Troncon troncon = chemin.get(stationArrivee);
		Troncon precedent = chemin.get(stationArrivee);
		while (troncon != null) {
			if(troncon.getLigne().equals(chemin.get(troncon.getDepart()).getLigne())) {
				nombreTroncons++;
				dureeTroncon+=troncon.getDuree();
				troncon = chemin.get(troncon.getDepart());
				continue;
			}
			else {
				nombreTroncons++;
				dureeTroncon+=troncon.getDuree();
			}			
			texte = "<deplacement arrivee=\"" + precedent.getArrivee() + "\" attenteMoyenne=\""
					+ precedent.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + troncon.getDepart()
					+ "\" direction=\"" + precedent.getLigne().getDestination() + "\" duree=\"" + dureeTroncon
					+ "\" nbTroncon=\"" + nombreTroncons + "\" type=\"" + precedent.getLigne().getType() + "\">"
					+ precedent.getLigne().getNom() + "</deplacement>\n";
			body = texte + body;
			
			dureeTotale+=dureeTroncon+troncon.getLigne().getAttenteMoyenne();
			tailleChemin+=nombreTroncons;
			
			dureeTroncon = 0;
			nombreTroncons = 0;
			troncon = chemin.get(troncon.getDepart());
			precedent = troncon;
			if(chemin.get(troncon.getDepart()) == null) {
				dureeTroncon+=troncon.getDuree();
				nombreTroncons++;
				dureeTotale += troncon.getDuree()+troncon.getLigne().getAttenteMoyenne();
				tailleChemin++;
				texte = "<deplacement arrivee=\"" + precedent.getArrivee() + "\" attenteMoyenne=\""
						+ precedent.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + troncon.getDepart()
						+ "\" direction=\"" + precedent.getLigne().getDestination() + "\" duree=\"" + dureeTroncon
						+ "\" nbTroncon=\"" + nombreTroncons + "\" type=\"" + precedent.getLigne().getType() + "\">"
						+ precedent.getLigne().getNom() + "</deplacement>\n";
				body = texte + body;
				break;
			}
		}
		String header = "<trajet depart=\"" + depart + "\" destination=\"" + arrivee + "\" duree=\"" + dureeTotale
				+ "\" nbTroncons=\"" + tailleChemin + "\">\n";
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
