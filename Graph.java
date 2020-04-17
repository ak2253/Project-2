import java.util.HashSet;

public class Graph {
	public HashSet<Node> nodeSet;
	public Graph() {
		nodeSet=new HashSet<Node>();
	}
	public void addNode(final String nodeVal) {
		Node node=new Node(nodeVal);
		nodeSet.add(node);
	}
	
	public void addUndirectedEdge(final Node first, final Node second) { //create edge from node first to node second both ways
		if(nodeSet.contains(first)&&nodeSet.contains(second)) { //check if both nodes exists
			if(!first.nodeSet.contains(second)) //check for repeats
				first.nodeSet.add(second);
			if(!second.nodeSet.contains(first))
				second.nodeSet.add(first);
		}
	}
	
	public void removeUndirectedEdge(final Node first, final Node second) { //delete edge from node first to node second both ways
		if(nodeSet.contains(first)&&nodeSet.contains(second)) { //check if both nodes exist
			if(first.nodeSet.contains(second))
				first.nodeSet.remove(second);
			if(second.nodeSet.contains(first))
				second.nodeSet.remove(first);
		}
	}
	
	public HashSet<Node> getAllNodes() {
		return nodeSet;
	}
}
