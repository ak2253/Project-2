import java.util.ArrayList;
import java.util.Random;

public class MainGraph {
	public static Graph createRandomUnweightedGraphIter(int n) { //create random Graph
		Graph graph=new Graph();
		Random rand=new Random();
		int tempn=n;
		while(tempn>0) {
			graph.addNode(String.valueOf(tempn));
			if(graph.Set.size()>1) { //check if there only 0 or 1 node in graph
				Node ognode=null;
				for(Node node:graph.Set) { //find node that was just added
					if(node.value.compareTo(String.valueOf(tempn))==0)
							ognode=node;
				}
				for(Node node:graph.Set) { //go though all current nodes to potentially add an edge 
					if(node.value.compareTo(String.valueOf(tempn))!=0) {
						int randnum=rand.nextInt(2);
						if(randnum==0) //50/50 to add edge
							graph.addUndirectedEdge(ognode, node);
					}
				}
			}
			tempn-=1;
		}
		return graph;
	}
	public static Graph createLinkedList(int n) { //create graph that looks like SLL
		Graph graph=new Graph();
		Node tempnode=null; //previous node
		int tempn=n;
		while(tempn>0) {
			Node node=new Node(String.valueOf(tempn));
			if(tempnode!=null) { //check for first iteration
				tempnode.elst.add(node);
				graph.Set.add(tempnode);
			}
			tempnode=node;
			tempn-=1;
		}
		graph.Set.add(tempnode);
		return graph;
	}
	public static ArrayList<Node> BFTRecLinkedList(final Graph graph) {
		GraphSearch gs=new GraphSearch();
		return gs.BFTRec(graph);
	}
	public static ArrayList<Node> BFTIterLinkedList(final Graph graph) {
		GraphSearch gs=new GraphSearch();
		return gs.BFTIter(graph);
	}
	public static void main(String[] args) {
		Graph graph1=createLinkedList(10000);
		System.out.println(BFTRecLinkedList(graph1).size());
		Graph graph2=createLinkedList(10000);
		System.out.println(BFTIterLinkedList(graph2).size());
	}
}
