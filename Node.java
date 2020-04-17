import java.util.HashMap;
import java.util.HashSet;

class Node {
	String value;
	boolean visited;
	HashSet<Node> nodeSet=new HashSet<Node>();
	HashMap<Node,Integer> wEdge=new HashMap<Node,Integer>(); //for dijkstras
	int indegree=0; //for topological sorting
	Node(String value) {
		this.value=value;
		visited=false;
	}
	public boolean isVisited() {
		return visited;
	}
	public HashSet<Node> getEdges() {
		return nodeSet;
	}
}
