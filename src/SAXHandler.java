import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private Set<Troncon> troncons;
	private Map<String, String> stops;
	private Set<Ligne> lignes;

	private boolean isStop = false;

	private String stationName;
	private Ligne ligne;

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		this.troncons = new HashSet<Troncon>();
		this.stops = new HashMap<String, String>();
		this.lignes = new HashSet<Ligne>();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (isStop) {
			isStop = false;
			this.stops.put(new String(ch, start, length), this.stationName);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equalsIgnoreCase("ligne")) {
			this.ligne = new Ligne(attributes.getValue("nom"), attributes.getValue("source"),
					attributes.getValue("destination"), attributes.getValue("type"),
					Integer.parseInt(attributes.getValue("attenteMoyenne")));
			this.lignes.add(ligne);
		} else if (qName.equalsIgnoreCase("station")) {
			this.stationName = attributes.getValue("nom");
		} else if (qName.equalsIgnoreCase("troncon")) {
			this.troncons.add(new Troncon(this.ligne, this.stops.get(attributes.getValue("depart")),
					this.stops.get(attributes.getValue("arrivee")), Integer.parseInt(attributes.getValue("duree"))));
		} else if (qName.equalsIgnoreCase("stop")) {
			isStop = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	public Graph getGraph() {
		return new Graph(troncons);
	}

}
