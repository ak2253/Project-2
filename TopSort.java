import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
public class TopSort {
	public ArrayList<Node> Kahns(final DirectedGraph graph) { //traverse through graph through Kahns Algorithm
		ArrayList<Node> arlst=new ArrayList<Node>();
		HashMap<Node,Integer> Hmap=new HashMap<Node,Integer>();
		Queue<Node> q=new LinkedList<Node>();
		for(Node node:graph.DGnodeSet) //inserting each node indegree
			Hmap.put(node, node.indegree);
		Iterator<Entry<Node, Integer>> iter=Hmap.entrySet().iterator();
		while(iter.hasNext()) { //put all nodes with 0 indegree into queue
			Map.Entry<Node,Integer> pair=(Map.Entry<Node, Integer>)iter.next();
			if(pair.getValue()==0)
				q.add(pair.getKey());
		}
		while(!q.isEmpty()) {
			Node node=q.poll();
			arlst.add(node);
			Hmap.replace(node,-1); //mark as visited
			for(Node edge:node.nodeSet) { //decrement indegree for depended nodes
				Hmap.replace(edge, Hmap.get(edge)-1);
				if(Hmap.get(edge)==0) { //if not longer dependant add to queue
					q.add(edge);
				}
			}
		}
		return arlst;
	}
	
	public ArrayList<Node> mDFS(final DirectedGraph graph) { //traverse through graph using mdfs
		ArrayList<Node> arlst=new ArrayList<Node>();
		Stack<Node> order=new Stack<Node>();
		Stack<Node> stack=new Stack <Node>();
		for(Node node:graph.DGnodeSet) { //Go through all nodes to check for disconnected graph
			if(node.visited==false) { //add to stack if not visited
				node.visited=true;
				stack.push(node);
			}
			while(!stack.isEmpty()) { //Go through all nodes connected to the respective node
				Node curnode=stack.pop();
				for(Node edge:curnode.nodeSet) {
					if(edge.visited==false) {
						edge.visited=true;
						stack.push(edge);
					}
				}
				order.push(curnode);
			}
		}
		//order is backwards so the stack needs to reverse itself
		while(!order.isEmpty())
			stack.push(order.pop());
		while(!stack.isEmpty())
			arlst.add(stack.pop());
		return arlst;
	}
}
