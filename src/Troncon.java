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
	 * @param depart = le sommet de d�part
	 * @param arrivee = le sommet d'arriv�e
	 * @param duree = la dur�e pass�e sur le troncon
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
	 * getter du sommet de d�part
	 * @return this.depart (String)
	 */
	public String getDepart() {
		return this.depart;
	}
	/**
	 * getter du sommet d'arriv�e
	 * @return this.arrivee (String)
	 */
	public String getArrivee() {
		return this.arrivee;
	}
	/**
	 * getter de la dur�e
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
