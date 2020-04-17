import java.util.HashMap;

public class GridNode {
	int x;
	int y;
	HashMap<GridNode,Integer> neighbors=new HashMap<GridNode,Integer>();
	String value;
	boolean visited=false;
	public GridNode(int x, int y, String value) {
		this.x=x;
		this.y=y;
		this.value=value;
	}
}
