// This class represents an edge of the graph.

public class Edge {
	
	private Node FirstEndpoint;
	private Node SecondEndpoint;
	private String Type;

	// The constructor for the class. The first two parameters are the endpoints of the edge.
	// The last parameter is the type of the edge, which for this project can be 
	// either “corridor”, “wall”, ”thickWall”, or ”metalWall”.
	Edge(Node u, Node v, String type){
		this.FirstEndpoint = u;
		this.SecondEndpoint = v;
		this.Type = type;
	}
	
	// Returns the first endpoint of this edge.
	public Node firstEndpoint() {
		 return FirstEndpoint;
	}
	
	// Returns the second endpoint of this edge.
	public Node secondEndpoint() {
		return SecondEndpoint;
	}
	
	// Returns the type of this edge.
	public String getType() {
		return Type;
	}
	
	// Sets the type of the edge to the specified value.
	public void setType(String type) {
		this.Type = type;
	}
	
}
