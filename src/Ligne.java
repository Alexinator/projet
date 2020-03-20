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
	 *            le numero de la ligne
	 * @param source
	 *            station de depart de la ligne
	 * @param destination
	 *            station d'arrivee de la ligne
	 * @param type
	 *            bus, tram, metro
	 * @param attenteMoyenne
	 *            duree moyenne passee en station (une seule fois par ligne)
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
	 * Getter du numero de la ligne
	 * 
	 * @return this.nom (String)
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de la station de depart de la ligne
	 * 
	 * @return this.source (String)
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * Getter de la station d'arrivee de la ligne
	 * 
	 * @return this.destination (String)
	 */
	public String getDestination() {
		return this.destination;
	}

	/**
	 * Getter du type de la ligne (bus, tram, metro)
	 * 
	 * @return this.type (String)
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Getter de l'attente moyenne sur la ligne
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
