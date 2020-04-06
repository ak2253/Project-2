import java.util.HashSet;

public class DirectedGraph {
	public HashSet<Node> Set=new HashSet<Node>();
	public void addNode(final String nodeVal) { //add node to Set
		Node node=new Node(nodeVal);
		Set.add(node);
	}
	
	public void addDirectedEdge(final Node first, final Node second) { //create edge from node first to node second
		Node node1 = null;
		Node node2 = null;
		for(Node node:Set) { //used to check if both nodes are in graph
			if(node.value.compareTo(first.value)==0) //node first found
				node1=node;
			if(node.value.compareTo(second.value)==0) //node second found
				node2=node;
		}
		if(node1!=null&&node2!=null) { //check if both nodes are in graph
			if(!node2.elst.contains(node1)&&!node1.elst.contains(node2)) {
				node1.elst.add(node2);
				node2.indegree+=1;
			}
		}
	}
	
	public void removeDirectedEdge(final Node first, final Node second) { //remove edge from node first to node second
		if(Set.contains(first)&&Set.contains(second)) { //check if nodes exist in graph
			for(Node node:Set) {
				if(node.value.compareTo(first.value)==0&&node.elst.contains(second)) { //first node is found and has edge to node second
					int index=node.elst.indexOf(second);
					node.elst.remove(index);
					second.indegree-=1;
				}
			}
		}
	}
	public HashSet<Node> getAllNodes() { //returns Set
		return Set;
	}
	
	public Node findNode(final String nodeVal) { //return node in Set if exist
		for(Node node:Set) {
			if(node.value.compareTo(nodeVal)==0)
				return node;
		} 
		return null;
	}
}
