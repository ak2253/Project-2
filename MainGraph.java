import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class MainGraph {
	// problem 3
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
	
	// problem 4
	public static DirectedGraph createRandomDAGIter(final int n) { //creates a random DAG
		DirectedGraph DGraph=new DirectedGraph();
		Random rand=new Random();
		Node[] colnodes=new Node[n];
		int[][] matrix=new int[n][n]; //row/col
		for(int i=0;i<n;i++) { //putting all nodes into matrix
			DGraph.addNode(String.valueOf(i+1));
			Node node=DGraph.findNode(String.valueOf(i+1));
			colnodes[i]=node;
		}
		for(int row=0;row<n;row++) { //creating a lower bounded triangular matrix
			for(int col=0;col<n;col++) {
				if(row==col)
					continue;
				if(row>col) {
					int randnum=rand.nextInt(2);
					if(randnum==0)
						matrix[row][col]=1;
				}
			}
		}
		for(int row=0;row<n;row++) { //take data from matrix and applying it to the graph
			Node node1=colnodes[row];
			for(int col=0;col<n;col++) {
				if(matrix[row][col]==1)
					DGraph.addDirectedEdge(node1, colnodes[col]);
			}
		}
		return DGraph;
	}
	
	//problem 5
	public static WeightedGraph createRandomCompleteWeightedGraph(final int n) { //creates randomized weighted graph
		WeightedGraph wg=new WeightedGraph();
		Random rand=new Random();
		for(int i=1;i<=n;i++) { //initializing all nodes into graph
			wg.addNode(String.valueOf(i));
			if(wg.Set.size()<1)
				continue;
			Node ognode=wg.findNode(String.valueOf(i));
			for(Node node:wg.Set) { //connecting most recent node to all previously made nodes except itself
				if(ognode.value.compareTo(node.value)==0)
					continue;
				wg.addWeightedEdge(ognode, node, rand.nextInt(10)+1);
				wg.addWeightedEdge(node, ognode, rand.nextInt(10)+1);
			}
		}
		return wg;
	}
	
	public static WeightedGraph createWeightedLinkedList(final int n) { //create a weighted graph in the form of a linked list
		WeightedGraph wg=new WeightedGraph();
		Node tempnode=null; //keep track of previous node
		for(int i=1;i<=n;i++) {
			Node curnode=new Node(String.valueOf(i));
			if(tempnode!=null) { //connect previous node to most recent node
				tempnode.wEdge.put(curnode,1);
				wg.Set.add(tempnode);
			}
			tempnode=curnode;
		}
		wg.Set.add(tempnode);
		return wg;
	}
	
	public static HashMap<Node,Integer> dijkstras(final Node start) { //Traverse through weighted graph with dijkstras algorithm
		HashMap<Node,Integer> map=new HashMap<Node,Integer>();
		map.put(start,0);
		while(true) {
			Node node=null;
			Integer d=null;
			Iterator<Entry<Node, Integer>> iter=map.entrySet().iterator();
			while(iter.hasNext()) { //find node with the shortest path that was not visited
				Entry<Node,Integer> pair=iter.next();
				if(pair.getKey().visited==false) {
					if(node==null) {
						pair.getKey().visited=true;
						node=pair.getKey();
						d=pair.getValue();
					}
					else if(pair.getValue()<d) {
						pair.getKey().visited=true;
						node=pair.getKey();
						d=pair.getValue();
					}
				}
			}
			if(node==null) //base case: all nodes has been visited
				break;
			iter=node.wEdge.entrySet().iterator();
			while(iter.hasNext()) { //take current node and check if new/shorter paths were made
				Entry<Node,Integer> pair=iter.next();
				Integer curvalue=map.get(node)+pair.getValue();
				Node edge=pair.getKey();
				if(!map.containsKey(edge)) //node does not exist yet
					map.put(edge,curvalue);
				else if(curvalue<map.get(edge)) //shorter path is found
					map.replace(edge,curvalue);
			}
		}
		return map;
	}
	
	//problem 6
	public static GridGraph createRandomGridGraph(int n) { //create randomized gridgraph
		GridGraph grid=new GridGraph();
		for(int row=0;row<=n;row++) { //initialized all nodes into graph
			for(int col=0;col<=n;col++)
				grid.addGridNode(row,col,String.valueOf(row)+String.valueOf(col));
		}
		for(GridNode node:grid.Set) { //initialized all neighbors for each node
			GridNode neighbor=null;
			for(int i=0;i<4;i++) {
				if(i==0)
					neighbor=grid.findGridNode(node.coord.get(0)+1,node.coord.get(1));
				else if(i==1)
					neighbor=grid.findGridNode(node.coord.get(0)-1,node.coord.get(1));
				else if(i==2)
					neighbor=grid.findGridNode(node.coord.get(0),node.coord.get(1)+1);
				else if(i==3)					
					neighbor=grid.findGridNode(node.coord.get(0),node.coord.get(1)-1);
				if(neighbor!=null) //check if node exist in graph
					node.neighbors.put(neighbor,0);
			}
		}
		Random rand=new Random();
		for(GridNode node:grid.Set) { //create randomized edge to its neighbors for each node
			Iterator<Entry<GridNode, Integer>> iter=node.neighbors.entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry<GridNode,Integer> pair=(Map.Entry<GridNode, Integer>)iter.next();
				if(pair.getValue()==1)
					continue;
				int fifty=rand.nextInt(2);
				GridNode findNode=grid.findGridNode(pair.getKey().coord.get(0),pair.getKey().coord.get(1));
				if(fifty==0&&findNode!=null) //50/50 chance to connect
					grid.addUndirectedEdge(node,findNode);
			}
		}
		return grid;
	}
	
	public static ArrayList<GridNode> astar(final GridNode sourceNode,final GridNode destNode) { //Traverse through grid graph using A* algorithm
		ArrayList<GridNode> arlst=new ArrayList<GridNode>();
		HashMap<GridNode,ArrayList<GridNode>> path=new HashMap<GridNode,ArrayList<GridNode>>();
		HashMap<GridNode,int[]> map=new HashMap<GridNode,int[]>();
		int d=distance(sourceNode,destNode); //heuristic is the distance between two nodes by adjacent nodes 
		int[] srtar={0,d};
		map.put(sourceNode,srtar); //store current distance
		path.put(sourceNode,arlst); //store current path
		GridNode curNode=sourceNode;
		while((curNode.coord.get(0)!=destNode.coord.get(0))||(curNode.coord.get(1)!=destNode.coord.get(1))) { //find path from starting node to destination node
			curNode.visited=true; //mark current node as visited
			arlst=(ArrayList<GridNode>)path.get(curNode).clone(); //get path that node took
			arlst.add(curNode);
			Iterator<Entry<GridNode, Integer>> iter=curNode.neighbors.entrySet().iterator();
			while(iter.hasNext()) { //go through neighbors and add in new/better paths
				Map.Entry<GridNode,Integer> pair=(Map.Entry<GridNode,Integer>)iter.next();
				GridNode neighbor=pair.getKey();
				int[] data={arlst.size(),distance(neighbor,destNode)};
				if(!map.containsKey(neighbor)&&pair.getValue()==1) { //add new node in map
					map.put(neighbor,data);
					path.put(neighbor,arlst);
				}
				else if(map.containsKey(neighbor)&&pair.getValue()==0){
					int[] ogar=map.get(neighbor);
					if(data[0]+data[1]<ogar[0]+ogar[1]) { //add better path in path
						map.replace(neighbor,data);
						if(path.containsKey(neighbor))
							path.replace(neighbor,arlst);
						else
							path.put(neighbor,arlst);
					}
				}
			}
			boolean noNode=true;
			int min=Integer.MAX_VALUE;
			Iterator<Entry<GridNode,int[]>> nextCand=map.entrySet().iterator();
			while(nextCand.hasNext()) { //finding the next node with shortest path to traverse with
				Map.Entry<GridNode,int[]> potent=(Map.Entry<GridNode,int[]>)nextCand.next();
				int[] huer=potent.getValue();
				if(huer[0]+huer[1]<min&&potent.getKey().visited==false) { //make node new current node if better than current node
					noNode=false;
					curNode=potent.getKey();
					min=huer[0]+huer[1];
				}
			}
			if(noNode) {//no more possible paths before destnode is found
				return null;
			}
		}
		arlst=path.get(curNode); 
		arlst.add(curNode); //add destnode to arlst
		return arlst;
	}
	
	public static int distance(GridNode first,GridNode second) { //finding the distance in terms of adjacent nodes
		int d1=second.coord.get(0)-first.coord.get(0);
		int d2=second.coord.get(1)-first.coord.get(1);
		if(d1<0)
			d1*=(-1);
		if(d2<0)
			d2*=(-1);
		return (d1+d2);
	}
	
	public static void main(String[] args) {
		//problem 3
		Graph graph1=createLinkedList(10000);
		BFTRecLinkedList(graph1);
		Graph graph2=createLinkedList(10000);
		BFTIterLinkedList(graph2);
		
		//problem 4
		DirectedGraph DGraph1=createRandomDAGIter(1000);
		DirectedGraph DGraph2=createRandomDAGIter(1000);
		TopSort ts=new TopSort();
		ts.mDFS(DGraph1);
		ts.Kahns(DGraph2);
		
		//problem 5
		WeightedGraph ll=createWeightedLinkedList(1000);
		for(Node node:ll.Set) { 
			if(node.value.compareTo("1")==0) {
				dijkstras(node);
				break;
			}
		}
		
		//problem 6
		int n=100;
		GridGraph grid=createRandomGridGraph(n);
		GridNode start=grid.findGridNode(0, 0);
		GridNode end=grid.findGridNode(n, n);
		ArrayList<GridNode> arlst=astar(start,end);
		if(arlst!=null)
			System.out.println("A path has been found");
		else
			System.out.println("No path has been found");
	}
}
