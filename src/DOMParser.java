
/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 * 
 */

import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParser {

	private Document doc;
	private Graph graph;
	private Map<String, String> stations;
	private String nomStation;
	private Ligne ligne;

	public DOMParser(Document doc) {
		this.doc = doc;
		this.graph = new Graph();
		parcours();
	}

	public Graph getGraph() {
		return this.graph;
	}

	private void parcours() {
		NodeList lignes = doc.getElementsByTagName("ligne");
		NodeList stations = doc.getElementsByTagName("station");
		NodeList troncons = doc.getElementsByTagName("troncon");
		NodeList stops = doc.getElementsByTagName("stop");
		
		for (int i = 0; i < lignes.getLength(); i++) {
			Node nLigne = lignes.item(i);
			Element eLigne = (Element) nLigne;			
		}
		for (int i = 0; i < stations.getLength(); i++) {
			Node nStation = stations.item(i);
			Element eStation = (Element) nStation;			
		}
		for (int i = 0; i < troncons.getLength(); i++) {
			Node nTroncon = troncons.item(i);
			Element eTroncon = (Element) nTroncon;			
		}
		for (int i = 0; i < stops.getLength(); i++) {
			Node nStops = stations.item(i);
			Element eStops = (Element) nStops;			
		}
	}

}
