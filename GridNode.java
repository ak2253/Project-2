import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GridNode {
	List<Integer> coord=new ArrayList<Integer>(Arrays.asList(null,null));
	HashMap<GridNode,Integer> neighbors=new HashMap<GridNode,Integer>();
	String value;
	boolean visited=false;
	public GridNode(int x, int y, String value) {
		coord.set(0,x);
		coord.set(1,y);
		this.value=value;
	}
}
