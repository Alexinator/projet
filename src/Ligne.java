
public class Ligne {

	private String nom;
	private String source;
	private String destination;
	private String type;
	private int attenteMoyenne;
	
	public Ligne(String nom, String source, String destination, String type, int attenteMoyenne) {
		super();
		this.nom = nom;
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.attenteMoyenne = attenteMoyenne;
	}
	public String getNom() {
		return nom;
	}
	public String getSource() {
		return source;
	}
	public String getDestination() {
		return destination;
	}
	public String getType() {
		return type;
	}
	public int getAttenteMoyenne() {
		return attenteMoyenne;
	}
}
