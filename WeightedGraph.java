import java.util.HashSet;

public class WeightedGraph {
	public HashSet<Node> nodeSet=new HashSet<Node>();
	public void addNode(final String nodeVal) {
		Node node=new Node(nodeVal);
		nodeSet.add(node);
	}
	
	public void addWeightedEdge(final Node first, final Node second, final int edgeWeight) { //creates directed weighted edge from node first to node second
		if(nodeSet.contains(first)&&nodeSet.contains(second))
			first.wEdge.put(second,edgeWeight);
	}
	
	public void removeDirectedEdge(final Node first, final Node second) { //remove directed weighted edge from node first to node second
		if(nodeSet.contains(first)&&nodeSet.contains(second)) { //check if both nodes exist in graph
			if(first.wEdge.containsKey(second))
				first.wEdge.remove(second);
		}
	}
	
	public HashSet<Node> getAllNodes() {
		return nodeSet;
	}
	
	public Node findNode(final String nodeVal) {
		for(Node node:nodeSet) {
			if(node.value.compareTo(nodeVal)==0)
				return node;
		} 
		return null;
	}
}
