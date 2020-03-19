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
	 *            le numéro de la ligne
	 * @param source
	 *            station de départ de la ligne
	 * @param destination
	 *            station d'arrivée de la ligne
	 * @param type
	 *            bus, tram, métro
	 * @param attenteMoyenne
	 *            durée moyenne passée en station (une seule fois par ligne)
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
	 * getter du numéro de la ligne
	 * 
	 * @return this.nom (String)
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * getter de la sation de départ de la ligne
	 * 
	 * @return this.source (String)
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * getter de la station d'arrivée de la ligne
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
