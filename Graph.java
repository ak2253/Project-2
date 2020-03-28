import java.util.HashSet;

class Graph {
	public HashSet<Node> Set=new HashSet<Node>();
	public void addNode(final String nodeVal) { //add node to Set
		Node node=new Node(nodeVal);
		Set.add(node);
	}
	public void addUndirectedEdge(final Node first, final Node second) { //create edge from node first to node second both ways
		if(!Set.isEmpty()) { //check for empty set
			if(Set.contains(first)&&Set.contains(second)) { //check if both nodes exists
				for(Node node:Set) {
					if(node==first) { //node first found
						if(node.elst.indexOf(second)==-1) //check for repeats
							node.elst.add(second);
					}
					if(node==second) { //node second found
						if(node.elst.indexOf(first)==-1) //check for repeats
							node.elst.add(first);
					}
				}
			}
		}
	}
	public void removeUndirectedEdge(final Node first, final Node second) { //delete edge from node first to node second both ways
		if(!Set.isEmpty()) { //check for empty set
			if(Set.contains(first)&&Set.contains(second)) { //check if both nodes exist
				for(Node node:Set) {
					if(node==first) { //node first is found
						int index=node.elst.indexOf(second);
						if(index!=-1) //check if edge exist
							node.elst.remove(index);
					}
					if(node==second) { //node second is found
						int index=node.elst.indexOf(first);
						if(index!=-1) //check if edge exist
							node.elst.remove(index);
					}
				}
			}
		}
	}
	public HashSet<Node> getAllNodes() { //returns Set
		return Set;
	}
}
