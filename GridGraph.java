import java.util.HashSet;

public class GridGraph {
	HashSet<GridNode> Set=new HashSet<GridNode>();
	void addGridNode(final int x,final int y, final String nodeVal) { //add node to graph
		GridNode node=new GridNode(x,y,nodeVal);
		Set.add(node);
	}
	
	void addUndirectedEdge(final GridNode first, final GridNode second) { //connect node first to node second both way
		if(first.neighbors.containsKey(second)) { //check if neighbors
			GridNode node1=null;
			GridNode node2=null;
			for(GridNode node:Set) { //check if both nodes exist in graph
				if(node.value.compareTo(first.value)==0)
					node1=node;
				if(node.value.compareTo(second.value)==0)
					node2=node;
			}
			if(node1!=null&&node2!=null) { //connect nodes if both nodes exist
				node1.neighbors.replace(node2,1);
				node2.neighbors.replace(node1,1);
			}
		}
	}
	
	void removeUndirectedEdge(final GridNode first, final GridNode second) { //remove edge from node first to node second both ways
		if(first.neighbors.containsKey(second)) { //check if neighbors
			GridNode node1=null;
			GridNode node2=null;
			for(GridNode node:Set) { //check if both nodes exist in graph
				if(node.value.compareTo(first.value)==0)
					node1=node;
				if(node.value.compareTo(second.value)==0)
					node2=node;
			}
			if(node1!=null&&node2!=null) { //remove edge if both nodes exist
				node1.neighbors.replace(node2,0);
				node2.neighbors.replace(node1,0);
			}
		}
	}
	
	HashSet<GridNode> getAllNodes() { //return set
		return Set;
	}
	
	public GridNode findGridNode(int x, int y) { //find gridnode in graph
		for(GridNode node:Set) {
			if(node.coord.get(0)==x&&node.coord.get(1)==y)
				return node;
		}
		return null;
	}
}
