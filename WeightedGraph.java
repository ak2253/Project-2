import java.util.HashSet;

public class WeightedGraph {
	public HashSet<Node> Set=new HashSet<Node>();
	public void addNode(final String nodeVal) { //add node to Set
		Node node=new Node(nodeVal);
		Set.add(node);
	}
	
	public void addWeightedEdge(final Node first, final Node second, final int edgeWeight) { //creates directed weighted edge from node first to node second
		Node node1 = null;
		Node node2 = null;
		for(Node node:Set) { //process to check if both nodes exist in graph
			if(node.value.compareTo(first.value)==0)
				node1=node;
			if(node.value.compareTo(second.value)==0) {
				node2=node;
			}
		}
		if(node1!=null&&node2!=null) { //check if both nodes exist
			if(!node1.wEdge.containsKey(node2)) {
				node1.wEdge.put(node2,edgeWeight);
			}
		}
	}
	
	public void removeDirectedEdge(final Node first, final Node second) { //remove directed weighted edge from node first to node second
		if(Set.contains(first)&&Set.contains(second)) { //check if both nodes exist in graph
			for(Node node:Set) {
				if(node.value.compareTo(first.value)==0&&node.elst.contains(second)) { //check if node first has edge to node second
					int index=node.elst.indexOf(second);
					node.elst.remove(index);
					node.wEdge.remove(second);
				}
			}
		}
	}
	
	public HashSet<Node> getAllNodes() { //returns Set
		return Set;
	}
	
	public Node findNode(final String nodeVal) { //find node in graph
		for(Node node:Set) {
			if(node.value.compareTo(nodeVal)==0)
				return node;
		} 
		return null;
	}
}
