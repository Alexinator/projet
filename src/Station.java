import java.util.List;
import java.util.Map;

public class Station {

	private String nom;
	private Map<String, List<String>> stops;

	public Station(String nom, Map<String, List<String>> stops) {
		super();
		this.nom = nom;
		this.stops = stops;
	}

	public String getNom() {
		return nom;
	}

	public Map<String, List<String>> getStops() {
		return stops;
	}
}
