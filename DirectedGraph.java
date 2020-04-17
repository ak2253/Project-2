import java.util.HashSet;

public class DirectedGraph {
	public HashSet<Node> DGnodeSet=new HashSet<Node>();
	public void addNode(final String nodeVal) {
		Node node=new Node(nodeVal);
		DGnodeSet.add(node);
	}
	
	public void addDirectedEdge(final Node first, final Node second) { //create edge from node first to node second
		if(DGnodeSet.contains(first)&&DGnodeSet.contains(second)) {
			if(!first.nodeSet.contains(second)) {
				first.nodeSet.add(second);
				second.indegree+=1;
			}
		}
	}
	
	public void removeDirectedEdge(final Node first, final Node second) { //remove edge from node first to node second
		if(DGnodeSet.contains(first)&&DGnodeSet.contains(second)) {
			if(first.nodeSet.contains(second)) {
				first.nodeSet.remove(second);
				second.indegree-=1;
			}
		}
	}
	public HashSet<Node> getAllNodes() {
		return DGnodeSet;
	}
	
	public Node findNode(final String nodeVal) {
		for(Node node:DGnodeSet) {
			if(node.value.compareTo(nodeVal)==0)
				return node;
		} 
		return null;
	}
}
