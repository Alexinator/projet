import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Set<Troncon> troncons;
	private Set<Ligne> lignes;
	private Map<String, Set<Troncon>> arcs;

	public Graph(Set<Troncon> troncons, Set<Ligne> lignes, Map<String, Set<Troncon>> map) {
		super();
		this.troncons = troncons;
		this.lignes = lignes;
		this.arcs = map;
	}

	public void calculerCheminMinimisantNombreTroncons(String stationDepart, String stationArrivee, String fichier) {
		Deque<String> sommets = new ArrayDeque<String>();
		bfsNombreTroncons(stationDepart, stationArrivee, sommets);
	}
	
	public void bfsNombreTroncons(String stationDepart, String stationArrivee, Deque<String> sommets) {
		for(Troncon arc : arcs.get(stationDepart)) {
			//sommets.add(arc.getArrivee());
			if(arc.getArrivee().equals(stationArrivee)) {
				System.out.println("arrivé à " + arc.getArrivee());
			}
			bfsNombreTroncons(arc.getArrivee(), stationArrivee, sommets);
		}
	}

	public void calculerCheminMinimisantTempsTransport(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}

}
