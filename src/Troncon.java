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
	 * 
	 * @param ligne
	 *            la ligne dont le troncon fait partie
	 * @param depart
	 *            le sommet de depart
	 * @param arrivee
	 *            le sommet d'arrivee
	 * @param duree
	 *            la duree passee sur le troncon
	 */
	public Troncon(Ligne ligne, String depart, String arrivee, int duree) {
		super();
		this.ligne = ligne;
		this.depart = depart;
		this.arrivee = arrivee;
		this.duree = duree;
	}

	/**
	 * Getter de la ligne
	 * 
	 * @return this.ligne (Ligne)
	 */
	public Ligne getLigne() {
		return this.ligne;
	}

	/**
	 * Getter du sommet de depart
	 * 
	 * @return this.depart (String)
	 */
	public String getDepart() {
		return this.depart;
	}

	/**
	 * Getter du sommet d'arrivee
	 * 
	 * @return this.arrivee (String)
	 */
	public String getArrivee() {
		return this.arrivee;
	}

	/**
	 * Getter de la duree
	 * 
	 * @return this.duree (int)
	 */
	public int getDuree() {
		return duree;
	}

	@Override
	public String toString() {
		return "Troncon [ligne=" + ligne.toString() + ", depart=" + depart + ", arrivee=" + arrivee + ", duree=" + duree
				+ "]";
	}

}
