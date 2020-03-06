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

					System.out.println(pile.size());
					while(!pile.isEmpty())
							System.out.println(pile.pop());
					return;
				}
			}
		}
	}

	public void calculerCheminMinimisantTempsTransport(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}

}
