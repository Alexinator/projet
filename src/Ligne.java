/**
 * 
 * @author Chapelle Alexandre && De Vos Olivier
 *
 */

public class Ligne {
	private String nom;
	private String source;
	private String destination;
	private String type;
	private int attenteMoyenne;

	/**
	 * Constructeur
	 * 
	 * @param nom
	 *            le num�ro de la ligne
	 * @param source
	 *            station de d�part de la ligne
	 * @param destination
	 *            station d'arriv�e de la ligne
	 * @param type
	 *            bus, tram, m�tro
	 * @param attenteMoyenne
	 *            dur�e moyenne pass�e en station (une seule fois par ligne)
	 */
	public Ligne(String nom, String source, String destination, String type, int attenteMoyenne) {
		super();
		this.nom = nom;
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.attenteMoyenne = attenteMoyenne;
	}

	/**
	 * getter du num�ro de la ligne
	 * 
	 * @return this.nom (String)
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * getter de la sation de d�part de la ligne
	 * 
	 * @return this.source (String)
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * getter de la station d'arriv�e de la ligne
	 * 
	 * @return this.destination (String)
	 */
	public String getDestination() {
		return this.destination;
	}

	/**
	 * getter du type de la ligne (bus, tram, metro)
	 * 
	 * @return this.type (String)
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * getter de l'attente moyenne sur la ligne
	 * 
	 * @return this.attenteMoyenne (int)
	 */
	public int getAttenteMoyenne() {
		return this.attenteMoyenne;
	}

	@Override
	public String toString() {
		return "Ligne [nom=" + nom + ", source=" + source + ", destination=" + destination + ", type=" + type
				+ ", attenteMoyenne=" + attenteMoyenne + "]";
	}
}
