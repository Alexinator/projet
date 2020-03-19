
/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 * 
 */

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {

	private Document doc;
	private Graph graph;
	private Map<String, String> mapStations;
	private Ligne ligne;

	public DOMParser(Document doc) {
		this.doc = doc;
		this.graph = new Graph();
		this.mapStations = new HashMap<>();
		parcours();
	}

	public Graph getGraph() {
		return this.graph;
	}

	private void parcours() {
		NodeList lignes = doc.getElementsByTagName("ligne");
		NodeList stations = doc.getElementsByTagName("station");
		
		for (int i = 0; i < stations.getLength(); i++) {
			Node nStation = stations.item(i);
			Element eStation = (Element) nStation;	
			NodeList stops = eStation.getElementsByTagName("stop");
			for (int j = 0; j < stops.getLength(); j++) {				
				Node nStops = stops.item(j);
				Element eStops = (Element) nStops;
				mapStations.put(eStops.getTextContent(), eStation.getAttribute("nom"));
			}
		}
		for (int i = 0; i < lignes.getLength(); i++) {
			Node nLigne = lignes.item(i);
			Element eLigne = (Element) nLigne;		
			ligne = new Ligne(eLigne.getAttribute("nom"), eLigne.getAttribute("source"), eLigne.getAttribute("destination"), eLigne.getAttribute("type"), Integer.parseInt(eLigne.getAttribute("attenteMoyenne")));
			NodeList troncons = eLigne.getElementsByTagName("troncon");
			for(int j = 0; j < troncons.getLength(); j++) {
				Node nTroncon = troncons.item(j);
				Element eTroncon = (Element) nTroncon;
				this.graph.ajouterTroncon(new Troncon(ligne, mapStations.get(eTroncon.getAttribute("depart")), mapStations.get(eTroncon.getAttribute("arrivee")), Integer.parseInt(eTroncon.getAttribute("duree"))));
			}
		}
	}
}
