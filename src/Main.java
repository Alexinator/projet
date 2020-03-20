/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;

public class Main {
	public static void main(String[] args) {
		try {
			File inputFile = new File("stib.xml");
			//File inputFile = new File("stibattente0.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			SAXHandler userhandler = new SAXHandler();
			saxParser.parse(inputFile, userhandler);
			Graph g = userhandler.getGraph();
			g.calculerCheminMinimisantNombreTroncons("MALIBRAN", "ALMA", "output.xml");
			g.calculerCheminMinimisantTempsTransport("MALIBRAN", "ALMA", "output2.xml");

			//BONUS : DOMParser
			File xmlFile = new File("stib.xml");
			//File xmlFile = new File("stibattente0.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			DOMParser parser = new DOMParser(doc);
			Graph graph = parser.getGraph();
			graph.calculerCheminMinimisantNombreTroncons("MALIBRAN", "ALMA", "output.dom.xml");
			graph.calculerCheminMinimisantTempsTransport("MALIBRAN", "ALMA", "output2.dom.xml");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}