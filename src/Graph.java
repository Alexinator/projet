
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

import sun.dc.DuctusRenderingEngine;

public class Graph {

	private Map<String, Set<Troncon>> arcs;

	/**
	 * Constructeur
	 */
	public Graph() {
		super();
		this.arcs = new HashMap<String, Set<Troncon>>();
	}

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
	 * @param stationDepart:
	 *            la station de depart
	 * @param stationArrivee:
	 *            la station d'arrivee
	 * @param fichier:
	 *            le chemin du fichier XML
	 * @throws Exception
	 *             : lance une exception s'il n'existe pas de chemin entre les deux
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
	 * @param stationDepart:
	 *            la station de depart
	 * @param stationArrivee:
	 *            la station d'arrivee
	 * @param fichier:
	 *            le chemin du fichier XML
	 * @throws Exception
	 *             : lance une exception s'il n'existe pas de chemin entre les deux
	 *             stations
	 */
	public void calculerCheminMinimisantTempsTransport(String stationDepart, String stationArrivee, String fichier)
			throws Exception {
		/*
		 * Map<String, Integer> dureesDefinitives = new HashMap<String, Integer>();
		 * Map<String, Integer> dureePossible = new HashMap<String, Integer>();
		 * Map<String, Troncon> chemin = new HashMap<String, Troncon>();
		 * 
		 * dureePossible.put(stationDepart, 0); String positionActuelle = stationDepart;
		 * chemin.put(stationDepart, null);
		 * 
		 * while (positionActuelle != null) { int dureeTroncon =
		 * dureePossible.get(positionActuelle); dureesDefinitives.put(positionActuelle,
		 * dureePossible.remove(positionActuelle));
		 * 
		 * for (Troncon troncon : arcs.get(positionActuelle)) { List<Troncon>
		 * tronconsParcourus = new ArrayList<Troncon>(); tronconsParcourus.add(troncon);
		 * if (!dureePossible.containsKey((troncon.getArrivee()))) {
		 * dureePossible.put(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
		 * chemin.put(troncon.getArrivee(), tronconsParcourus.get(0)); // a voir pour le
		 * 0 } else if(dureePossible.get(troncon.getArrivee()) >
		 * dureeTroncon+troncon.getDuree()) {
		 * dureePossible.replace(troncon.getArrivee(), dureeTroncon+troncon.getDuree());
		 * chemin.put(troncon.getArrivee(), tronconsParcourus.get(0)); // a voir pour le
		 * 0 } } int dureeMin = Integer.MAX_VALUE; for(String sommet :
		 * dureePossible.keySet()) { if(dureePossible.get(sommet) < dureeMin) { dureeMin
		 * = dureePossible.get(sommet); positionActuelle = sommet; } }
		 * if(positionActuelle.equals(stationArrivee)) { dureeTroncon =
		 * dureePossible.remove(positionActuelle);
		 * dureesDefinitives.put(positionActuelle, dureeTroncon);
		 * ecrireXML(stationDepart, stationArrivee, chemin, fichier); return; } } throw
		 * new Exception("Le chemin entre les deux stations est impossible");
		 */
	}

	/**
	 * Ecrit le fichier XML en fonction d'une pile de troncons reçue.
	 * 
	 * @param pile:
	 *            l'ensemble des troncons
	 * @param fichier:
	 *            le chemin du fichier XML
	 */
	private void ecrireXML(String stationDepart, String stationArrivee, Map<String, Troncon> chemin, String fichier) {
		String depart = stationDepart;
		String arrivee = stationArrivee;
		String debut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";

		int dureeTronconsMMLigne = 0;
		int dureeTroncon = 0;
		int dureeTotale = 0;
		int tailleChemin = 0;
		int nombreTroncons = 1;
		String body = "";
		Troncon troncon = chemin.get(stationArrivee);
		Troncon precedent = chemin.get(stationArrivee);
		while (troncon != null) {
			while (troncon.getLigne().equals(precedent.getLigne())) {
				dureeTronconsMMLigne += troncon.getDuree();
				nombreTroncons++;
				troncon = chemin.get(troncon.getDepart());
			}
			dureeTroncon += troncon.getDuree() + dureeTronconsMMLigne;
			tailleChemin += nombreTroncons;
			String texte = "<deplacement arrivee=\"" + precedent.getArrivee() + "\" attenteMoyenne=\""
					+ precedent.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + troncon.getArrivee()
					+ "\" direction=\"" + precedent.getLigne().getDestination() + "\" duree=\"" + dureeTroncon
					+ "\" nbTroncon=\"" + nombreTroncons + "\" type=\"" + precedent.getLigne().getType() + "\">"
					+ precedent.getLigne().getNom() + "</deplacement>\n";
			body = texte + body;
			dureeTotale += dureeTroncon + dureeTronconsMMLigne + troncon.getLigne().getAttenteMoyenne();
			nombreTroncons = 1;
			dureeTronconsMMLigne = 0;
			dureeTroncon = 0;
			precedent = troncon;
			troncon = chemin.get(troncon.getDepart());
		}
		String texte = "<deplacement arrivee=\"" + precedent.getArrivee() + "\" attenteMoyenne=\""
				+ precedent.getLigne().getAttenteMoyenne() + "\"" + " depart=\"" + precedent.getDepart()
				+ "\" direction=\"" + precedent.getLigne().getDestination() + "\" duree=\"" + precedent.getDuree()
				+ "\" nbTroncon=\"" + nombreTroncons + "\" type=\"" + precedent.getLigne().getType() + "\">"
				+ precedent.getLigne().getNom() + "</deplacement>\n";
		body = texte + body;
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
