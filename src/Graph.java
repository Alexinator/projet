import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Troncon> troncons;
	private Set<Ligne> lignes;

	// private Map<String, Set<Stop>> stops;

	public Graph(Set<Troncon> troncons, Set<Ligne> lignes) {
		super();
		this.troncons = troncons;
		this.lignes = lignes;
	}

	public void calculerCheminMinimisantNombreTroncons(String stationDepart, String stationArrivee, String fichier) {
		// TODO Auto-generated method stub
		Troncon sommet = null;
		for (Troncon troncon : troncons) {
			if(troncon.getDepart().equals(stationDepart)){
				cheminMinimisantNombreTroncons(troncon.getDepart(), stationArrivee);
				return;
			}
		}
	}

	private void cheminMinimisantNombreTroncons(String sommetDeDepart, String stationArrivee) {		
		Deque<String> sommets = new ArrayDeque<String>();
		Set<Troncon> tronconsdejavisite = new HashSet<Troncon>();
		sommets.add(sommetDeDepart);
		
		while(!sommets.isEmpty()) {
			String sommet = sommets.remove();
			System.out.println(sommet);
			//System.out.println(sommets.size());
			//System.out.println(troncon.getDepart());
			for(Troncon tr : troncons) {
				if(sommet.equals(tr.getDepart()) && !tronconsdejavisite.contains(tr)) {
					sommets.add(tr.getArrivee());
					//System.out.println(tr.getDepart()+ " "+tr.getArrivee());
				}
				tronconsdejavisite.add(tr);
			}
		}
		
	}

	public void calculerCheminMinimisantTempsTransport(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}

}
