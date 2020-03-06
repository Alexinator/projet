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
	
	//TODO rajouter des m�thodes pour ajouter des troncons et stations (appell�es via le saxhandler)

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
					ecrireXML(pile,fichier);
					return;
				}
			}
		}
	}

	public void calculerCheminMinimisantTempsTransport(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}
	
	
	private void ecrireXML(Deque<Troncon> pile,String fichier) {
		String string = "";		
		String depart = pile.peekFirst().getDepart();
		String arrivee = pile.peekLast().getArrivee();
		String debut = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
				//+ 
		
		int duree = 0;
		int nbTroncons = pile.size();
		Set<Ligne> lignes = new HashSet<Ligne>();
		Ligne lignePrecedente = null;
		int nbr = 1;
		String body = "";
		while(!pile.isEmpty()){
			Troncon troncon = pile.pop(); 
			duree += troncon.getDuree();
			if(!lignes.contains(troncon.getLigne())) {
				lignes.add(troncon.getLigne());
				duree += troncon.getLigne().getAttenteMoyenne();
			}
			if(!troncon.getLigne().equals(lignePrecedente)) {
				body += "<deplacement arrivee=\""+troncon.getArrivee()+"\" attenteMoyenne=\""+troncon.getLigne().getAttenteMoyenne()+"\""
						+ " depart=\""+troncon.getDepart()+"\" direction=\""+troncon.getLigne().getDestination()+"\" duree=\""+troncon.getDuree()+"\" nbTroncon=\""+nbr+"\">"+troncon.getLigne().getNom()+"</deplacement>\n";
			}
			nbr++;
			lignePrecedente = troncon.getLigne();
		}
			
		String header = "<trajet depart=\""+depart+"\" destination=\""+arrivee+"\" duree=\""+duree+"\" nbTroncons=\""+nbTroncons+"\">\n";
		String fin = "</trajet>";
		try {
			PrintWriter writer = new PrintWriter(fichier);
			writer.write(debut+header+body+fin);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
