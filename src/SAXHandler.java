/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private Graph graph;
	private Map<String, String> stations;
	private boolean estUneStation = false;
	private String nomStation;
	private Ligne ligne;

	/**
	 * Sert de constructeur
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		this.graph = new Graph();
		this.stations = new HashMap<String, String>();
	}

	/**
	 * Si l'element est une station, enregistre le nom de la station
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (estUneStation) {
			estUneStation = false;
			this.stations.put(new String(ch, start, length), this.nomStation);
		}
	}

	/**
	 * Remplit les structures de donnees avec les elements du XML
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equalsIgnoreCase("ligne")) {
			this.ligne = new Ligne(attributes.getValue("nom"), attributes.getValue("source"),
					attributes.getValue("destination"), attributes.getValue("type"),
					Integer.parseInt(attributes.getValue("attenteMoyenne")));
		} else if (qName.equalsIgnoreCase("station")) {
			this.nomStation = attributes.getValue("nom");
		} else if (qName.equalsIgnoreCase("troncon")) {
			this.graph.ajouterTroncon(new Troncon(this.ligne, this.stations.get(attributes.getValue("depart")),
					this.stations.get(attributes.getValue("arrivee")), Integer.parseInt(attributes.getValue("duree"))));
		} else if (qName.equalsIgnoreCase("stop")) {
			estUneStation = true;
		}
	}

	/**
	 * Getteur de graph
	 * 
	 * @return this.graph (Graph)
	 */
	public Graph getGraph() {
		return this.graph;
	}

}
