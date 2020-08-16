// This class represent a node of the graph.

public class Node {

	private int Name;
	private boolean Mark;
	
	/* The constructor for the class. Creates a node with the given name. The name of a
	 * node is an integer value between 0 and n - 1, where n is the number of vertices in the graph to which
	 * the node belongs. */
	public Node(int name) {
		this.Name = name;
		this.Mark = false;
	}
	
	// Marks this node with the specified value.
	public void setMark(boolean mark) {
		this.Mark = mark;
	}
	
	// Returns the value with which this node has been marked.
	public boolean getMark() {
		return Mark;
	}
	
	// Returns the name of this Node;
	public int getName() {
		return Name;
	}
	
}
