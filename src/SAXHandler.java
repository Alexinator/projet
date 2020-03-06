import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXHandler extends DefaultHandler{
	
	private Graph graph;
	private Set<Troncon> troncons;
	private Set<Station> stations;
	private Set<Ligne> lignes;

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}


	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}


	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}


	public Graph getGraph() {
		return new Graph(troncons, stations, lignes);
	}


}
