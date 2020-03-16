/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */
public class Troncon {

	private Ligne ligne;
	private String depart;
	private String arrivee;
	private int duree;
	
	/**
	 * Constructeur
	 * @param ligne = la ligne dont le troncon fait partie
	 * @param depart = le sommet de départ
	 * @param arrivee = le sommet d'arrivée
	 * @param duree = la durée passée sur le troncon
	 */
	public Troncon(Ligne ligne, String depart, String arrivee, int duree) {
		super();
		this.ligne = ligne;
		this.depart = depart;
		this.arrivee = arrivee;
		this.duree = duree;
	}
	/**
	 * getter de la ligne
	 * @return this.ligne (Ligne)
	 */
	public Ligne getLigne() {
		return this.ligne;
	}
	/**
	 * getter du sommet de départ
	 * @return this.depart (String)
	 */
	public String getDepart() {
		return this.depart;
	}
	/**
	 * getter du sommet d'arrivée
	 * @return this.arrivee (String)
	 */
	public String getArrivee() {
		return this.arrivee;
	}
	/**
	 * getter de la durée
	 * @return this.duree (int)
	 */
	public int getDuree() {
		return duree;
	}
	@Override
	public String toString() {
		return "Troncon [ligne=" + ligne.toString() + ", depart=" + depart + ", arrivee=" + arrivee + ", duree=" + duree + "]";
	}
	
	
}
