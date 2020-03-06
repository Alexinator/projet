
public class Graph {

	private Graph INSTANCE;
	
	public Graph getGraph() {
		if(INSTANCE == null) {
			INSTANCE = new Graph();
		}
		return INSTANCE;
	}
	
}
