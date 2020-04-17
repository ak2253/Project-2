import java.util.HashSet;

public class GridGraph {
	HashSet<GridNode> GridSet=new HashSet<GridNode>();
	void addGridNode(final int x,final int y, final String nodeVal) {
		GridNode node=new GridNode(x,y,nodeVal);
		GridSet.add(node);
	}
	
	void addUndirectedEdge(final GridNode first, final GridNode second) { //connect node first to node second both way
		if(isNeighbor(first,second)&&GridSet.contains(first)&&GridSet.contains(second)) { //check if neighbors
			first.neighbors.replace(second,1);
			second.neighbors.replace(first,1);
		}
	}
	
	void removeUndirectedEdge(final GridNode first, final GridNode second) { //remove edge from node first to node second both ways
		if(isNeighbor(first,second)&&GridSet.contains(first)&&GridSet.contains(second)) { //check if neighbors
			first.neighbors.replace(second,0);
			second.neighbors.replace(first,0);
		}
	}
	
	HashSet<GridNode> getAllNodes() {
		return GridSet;
	}
	
	public GridNode findGridNode(int x, int y) {
		for(GridNode node:GridSet) {
			if(node.x==x&&node.y==y)
				return node;
		}
		return null;
	}
	private boolean isNeighbor(GridNode node1,GridNode node2) {
		if((Math.abs(node1.x-node2.x)==0&&Math.abs(node1.y-node2.y)==1)||(Math.abs(node1.x-node2.x)==1&&Math.abs(node1.y-node2.y)==0))
			return true;
		return false;
	}
}
