
public class Troncon {

	private Ligne ligne;
	private Stop depart;
	private Stop arrivee;
	private int duree;
	
	public Troncon(Ligne ligne, Stop depart, Stop arrivee, int duree) {
		super();
		this.ligne = ligne;
		this.depart = depart;
		this.arrivee = arrivee;
		this.duree = duree;
	}
	public Ligne getLigne() {
		return ligne;
	}
	public Stop getDepart() {
		return depart;
	}
	public Stop getArrivee() {
		return arrivee;
	}
	public int getDuree() {
		return duree;
	}
	
	
}
