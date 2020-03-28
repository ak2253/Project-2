import java.util.ArrayList;
import java.util.List;

class Node {
	String value;
	boolean visited;
	List<Node> elst;
	Node(String value) {
		this.value=value;
		elst=new ArrayList<Node>();
		visited=false;
	}
	public boolean isVisited() { //return visited
		return visited;
	}
	public List<Node> getEdges() { //return list of edges
		return elst;
	}
}
