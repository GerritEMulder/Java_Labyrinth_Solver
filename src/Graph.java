/* This class represents an undirected graph. 
 * Implements the GraphADT class. 
 */

import java.util.*;

public class Graph implements GraphADT{
	
	private int Size;
	private LinkedList<Edge>[] Graph;
	private Node[] NodeList;

	// Creates an empty graph with n nodes and no edges. This is the constructor for the class.
	public Graph(int n) {
		
		this.Size = n;
		this.Graph = new LinkedList[Size];
		this.NodeList = new Node[Size];
		
		// Fills the graph with empty linked lists.
		// Fills the List of nodes with nodes.
		for (int i = 0; i < Size; i++) {
			Graph[i] = new LinkedList<Edge>();
			Node temp = new Node(i);
			NodeList[i] = temp;
		}
		
	}
	
	/* Adds to the graph an edge connecting u and v. The type for this new edge is as indicated by the last parameters. 
	 * This method throws a GraphException if either node does not exist or if there is already an edge connecting the given vertices. 
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException {
		int indexU = u.getName();
		int indexV = v.getName();
		Edge edge = new Edge(u, v, edgeType);
		
		
		// Checks to see if the nodes exist in the graph and throws and exception if they don't.
		try {
			Node nodeU = getNode(indexU);
			Node nodeV = getNode(indexV);
		}
		// Catches any exceptions thrown.
		catch(GraphException e) {
			// Prints the error message to the screen.
			System.out.println(e.getMessage());
			throw new GraphException("");
		}
		
		// Checks to see if there is already an edge with the nodes u and v.
		try {
			Edge test = getEdge(u, v);
		}
		
		// If the edge isn't already in the graph, it adds them into the graph.
		catch (GraphException e) {
			// Adds the edge to the two nodes.
			Graph[indexU].addFirst(edge);
			Graph[indexV].addFirst(edge);
			return;
		}
		
		// Throws an exception if an edge between u and v already exists.
		throw new GraphException("An edge conecting the node " + u.getName() + " and the node " + v.getName() + " already exists in the Graph.");
		
	}
	
	// Returns the node with the specified name. If no node with this name
	// exists, the method throws a GraphException.
	public Node getNode(int name) throws GraphException {
		
		if (name < Size) {
			return NodeList[name];
		}
		else {
			throw new GraphException("The node " + name + " does not exist in the Graph.");
		}
	}
	
	// Returns a Java Iterator storing all the edges incident on node u. It returns null if u does not have any edges incident on it.
	// Throws a GraphException if node u is not in the graph.
	public Iterator incidentEdges(Node u) throws GraphException {
		
		// Checks to see if the nodes exist in the graph and throws and exception if they don't.
		try {
			Node nodeU = getNode(u.getName());
		}
		// Catches any exceptions thrown.
		catch(GraphException e) {
			// Prints the error message to the screen.
			System.out.println(e.getMessage());
			throw new GraphException("");
		}
		
		// If node u has no edges incident on it, returns null.
		if(Graph[u.getName()].isEmpty() == true) {
			return null;
		}
		
		// Returns a Java Iterator storing all the edges incident on node u.
		Iterator<Edge> iter = Graph[u.getName()].iterator();
		return iter;
		
	}
	
	// Returns the edge connecting nodes u and v.
	// Throws a GraphException if u or v are not nodes in the graph or there is no edge connecting u and v.
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		// Checks to see if the nodes exist in the graph and throws and exception if they don't.
		try {
			Node nodeU = getNode(u.getName());
			Node nodeV = getNode(v.getName());
		}
		// Catches any exceptions thrown.
		catch(GraphException e) {
			// Prints the error message to the screen.
			System.out.println(e.getMessage());
			throw new GraphException("");
		}
		
		int count = 0;
		int index = u.getName();
		int size = Graph[index].size();
		
		// If node u has no edges incident on it, throw an exception.
		if (size == 0) {
			throw new GraphException("An edge conecting the node " + u.getName() + " and the node " + v.getName() + " does not exist in the Graph.");
		}
		
		Edge temp = Graph[index].get(count);
		boolean check = isEqual(temp, u, v);
		
		// Runs through all the edges incident on node u looking to see if it can find an existing edge of node u & v.
		while ((count < size) && check != true) {
			temp = Graph[index].get(count);
			check = isEqual(temp, u, v);
			count++;
		}
		
		// If there is an existing edge in the graph from node u to node v, returns true.
		if (check == true) {
			return temp;
		}
		
		// Else throws and exception.
		else {
			throw new GraphException("An edge conecting the node " + u.getName() + " and the node " + v.getName() + " does not exist in the Graph.");
		}
	}
	
	// Returns true if and only if nodes u and v are adjacent. 
	// Throws a GraphException if u or v are not nodes in the graph.
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		// Checks to see if the nodes exist in the graph and throws and exception if they don't.
		try {
			Node nodeU = getNode(u.getName());
			Node nodeV = getNode(v.getName());
		}
		// Catches any exceptions thrown.
		catch(GraphException e) {
			// Prints the error message to the screen.
			System.out.println(e.getMessage());
			throw new GraphException("");
		}
		
		// Checks to see if there is an edge in the graph with the nodes u and v.
		try {
			Edge test = getEdge(u, v);
		}
		// If there is no such edge in the graph, return false because then the nodes u & v are not adjacent.
		catch (GraphException e) {
			return false;
		}
		
		// Returns true if they are adjacent.
		return true;
		
	}
	
	// Helper function to see if two edges are equal.
	private boolean isEqual(Edge e, Node u, Node v) {
		
		int IndexU = e.firstEndpoint().getName();
		int IndexV = e.secondEndpoint().getName();
		int Uindex = u.getName();
		int Vindex = v.getName();
		
		if (IndexU == Uindex && IndexV == Vindex) {
			return true;
		}
		else if(IndexU == Vindex && IndexV == Uindex) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
