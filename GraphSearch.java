import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphSearch {
	public ArrayList<Node> DFSRec(final Node start, final Node end) { //create DFS from node start to node end
		ArrayList<Node> arlst=new ArrayList<Node>();
		if(DFSRecHelper(start,end,arlst)==true) //check if solution exist
			return arlst;
		return null;
	}
	
	private Boolean DFSRecHelper(Node node,final Node end,ArrayList<Node> arlst) { //DFS for solution
		if(node.isVisited()==false) { //check if visited
			node.visited=true;
			arlst.add(node);
			if(node==end) //base case
				return true;
			for(Node edge:node.nodeSet) {
				if(DFSRecHelper(edge,end,arlst)==true) //check if current path is solution
					return true;
			}
		}
		//repeat occurred
		return false;
	}
	
	public ArrayList<Node> DFSIter(final Node start,final Node end) { //create DFS from node start to node end
		ArrayList<Node> arlst=new ArrayList<Node>();
		Stack<Node> st=new Stack<Node>();
		st.push(start);
		while(!st.isEmpty()) {
			Node curnode=st.pop();
			if(curnode.isVisited()==false) { //check if visited
				curnode.visited=true;
				arlst.add(curnode);
				if(curnode==end) //base case
					return arlst;
				for(Node edge:curnode.nodeSet) { //traverse through edges
					st.push(edge);
				}
			}
		}
		return null;
	}
	
	public ArrayList<Node> BFTRec(final Graph graph) { //create BFT of graph
		ArrayList<Node> arlst=new ArrayList<Node>();
		Queue<Node> q=new LinkedList<Node>();
		for(Node node:graph.nodeSet) { //check for nodes with no edges
			q.add(node);
			BFTRecHelper(q,arlst);
		}
		return arlst;
	}
	
	private void BFTRecHelper(Queue<Node> q,ArrayList<Node> arlst) { //BFT through graph
		if(!q.isEmpty()) {
			Node curnode=q.poll();
			if(curnode.isVisited()==false) { //check if visited
				curnode.visited=true;
				arlst.add(curnode);
				for(Node edge:curnode.nodeSet) { //add edges to queue
					q.add(edge);
				}
			}
			BFTRecHelper(q,arlst);
		}
	}
	
	public ArrayList<Node> BFTIter(final Graph graph) { //create BFT of graph
		ArrayList<Node> arlst=new ArrayList<Node>();
		Graph tempgraph=graph;
		Queue<Node> q=new LinkedList<Node>();
		for(Node node:tempgraph.nodeSet) { //check for nodes with no edges
			if(node.isVisited()==false)
				q.add(node);
			while(!q.isEmpty()) {
				Node curnode=q.poll();
				if(curnode.isVisited()==false) { //check if visited
					curnode.visited=true;
					arlst.add(curnode);
					for(Node edge:curnode.nodeSet) //add edges to queue
						q.add(edge);
				}
			}
		}
		return arlst;
	}
}
