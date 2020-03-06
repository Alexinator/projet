import java.util.Map;
import java.util.Set;

public class Stop {

	private String nom;
	private Map<Stop, String> stations;
	private Map<String, Set<Stop>> stops;
	
	public Stop(String nom, Map<Stop, String> stations, Map<String, Set<Stop>> stops) {
		super();
		this.nom = nom;
		this.stations = stations;
		this.stops = stops;
	}
	public String getNom() {
		return nom;
	}
	public Map<Stop, String> getStations() {
		return stations;
	}
	public Map<String, Set<Stop>> getStops() {
		return stops;
	}
}
