
// This class represents the Labyrinth. 
// A graph will be used to store the labyrinth and to find a solution for it.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Labyrinth {
	
	private int entrance;
	private int exit;
	private Graph graph;
	private Stack<Node> Path = new Stack<Node>();
	private int BBombs, ABombs;
	
	//  constructor for building a labyrinth from the contents of the input file. 
	// If the input file does not exist, this method should throw a LabyrinthException.
	Labyrinth(String inputFile) throws LabyrinthException {
		
		BufferedReader in;
		int Scale, Width, Length;
		char line[];
		char labyrinth[][];
		
		try {
			
			// Reads in the first few lines of the file and assigns them to variables.
			in = new BufferedReader(new FileReader(inputFile));
			Scale = Integer.parseInt(in.readLine()); // Scale
			Width = Integer.parseInt(in.readLine()); // Width 
			Length = Integer.parseInt(in.readLine()); // Length
			BBombs = Integer.parseInt(in.readLine()); // # Brick Bombs
			ABombs = Integer.parseInt(in.readLine()); // # Acid Bombs
			int W = Width * 2 - 1;
			int L = Length * 2 - 1;
			labyrinth = new char[L][W];
			int size = Width * Length;
			this.graph = new Graph(size);
			int count = 0;
			
			// Reads the labyrinth into a 2d array.
			for (int i = 0; i < L; i++) {
				line = in.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					labyrinth[i][j] = line[j];
				}
			}
			
			// Creates a second 2d array of all the nodes in the labyrinth.
			int[][] nodelist = new int[Length][Width];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < W; j++) {
					
					// Counts the number of nodes and finds the place of the entrance and exit node.
					int mark = i / 2;
					int mark2 = j / 2;
					char CurrentChar = labyrinth[i][j];
					if (CurrentChar == 'b') { 
						entrance = count;
						nodelist[mark][mark2] = entrance;
						count++;
					}
					else if (CurrentChar == 'x') {
						exit = count;
						nodelist[mark][mark2] = exit;
						count++;
					}
					else if (CurrentChar == '+') {
						nodelist[mark][mark2] = count;
						count++;
					}
					
				}
			}

			
			// Adds all the edges in the labyrinth into the graph.
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < W; j++) {
					
					char read = labyrinth[i][j];
					int mark = i / 2;
					int mark2 = j / 2;
					
					// Inserting horizontal edges into the graph.
					if (read == 'h') {
						String type = "wall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark][mark2 + 1]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if (read == '-') {
						String type = "corridor";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark][mark2 + 1]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if(read == 'H') {
						String type = "thinkWall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark][mark2 + 1]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if(read == 'm') {
						String type = "metalWall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark][mark2 + 1]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					
					// Inserting vertical edges into the graph.
					else if(read == 'v') {
						String type = "wall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark + 1][mark2]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if(read == 'V') {
						String type = "thinkWall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark + 1][mark2]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if(read == 'M') {
						String type = "metalWall";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark + 1][mark2]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
					else if (read == '|') {
						String type = "corridor";
						
						try {
							Node u = graph.getNode(nodelist[mark][mark2]);
							Node v = graph.getNode(nodelist[mark + 1][mark2]);
							graph.insertEdge(u, v, type);
						}
						catch (GraphException e) {
							// Prints the error message to the screen.
							System.out.println(e.getMessage());
						}
					}
				}
			}
			
		}
		
		// If the file can't be read or doesn't exist, throws an exception.
		catch(IOException e) {
			throw new LabyrinthException("Error reading input file: " + inputFile);
		}
	}
	
	// returns a reference to the graph representing the labyrinth. 
	// Throws a LabyrinthException if the graph is not defined.
	Graph getGraph() throws LabyrinthException {
		if (graph == null) {
			throw new LabyrinthException("The graph is not defined.");
		}
		return graph;
	}
	
	
	// Returns a java Iterator containing the nodes along the path from the entrance to the exit of the labyrinth, if such a path exists. 
	// If the path does not exist, this method returns the value null.
	Iterator solve() {
		
		try {
			Node Start = graph.getNode(entrance);
			Node Exit = graph.getNode(exit);
			
			// If the path function returns true then it will return an iterator of the stack of nodes that make up the path to the exit.
			if (path(Start, Exit) == true) {
				Iterator<Node> iter = Path.iterator();
				return iter;
			}
			
			// If no path can be found then returns null.
			else {
				return null;
			}
		}
		
		// Catches any graph exceptions and prints them to the screen and returns null.
		catch (GraphException e) {
			// Prints the error message to the screen.
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	// This method will return true if it can find a path to the exit, and return false if it can't.
	private boolean path(Node u, Node d) {
		
		int acid = ABombs;
		int bombs = BBombs;
		
		// Marks u, pushes u onto the stack, and then sees if node u equals node d and returns true if it does.
		u.setMark(true);
		Path.push(u);
		if (u.equals(d)) {
			return true;
		}
		
		try {
			
			// Creates an iterator of all the edges incident on u, then runs through them all.
			Iterator<Edge> edges = graph.incidentEdges(u); 
			while(edges.hasNext() == true) {
				Edge current = edges.next();
				Node v = current.secondEndpoint();
				
				// If the second end point is the same as the node u, set v to the first end point.
				if (v.getName() == u.getName()) {
					v = current.firstEndpoint();
				}
				
				// If the type of edge being looked at is a wall and there are enough bombs to break through it then do the following.
				if(current.getType() == "wall" && BBombs > 0) {
					
					// If the node is not marked, reduce the number of brick bombs by one, and then check if path(v, d) returns true.
					if(v.getMark() == false) {
						BBombs--;
						// If it does, return true.
						if(path(v, d) == true) {
							return true;
						}
					}
				}
				
				// If the type of edge being looked at is a thick wall and there are enough bombs to break through it then do the following.
				else if (current.getType() == "thinkWall" && BBombs > 1) {
					
					// If the node is not marked, reduce the number of brick bombs by two, and then check if path(v, d) returns true.
					if(v.getMark() == false) {
						BBombs = BBombs - 2;
						if(path(v, d) == true) {
							return true;
						}
					}
				}
				
				// If the type of edge being looked at is a metal wall and there are enough bombs to break through it then do the following.
				else if (current.getType() == "metalWall" && ABombs > 0) {
					
					// If the node is not marked, reduce the number of acid bombs by bombs, and then check if path(v, d) returns true.
					if(v.getMark() == false) {
						ABombs--;
						if(path(v, d) == true) {
							return true;
						}
					}
				}
				
				// If the type of edge being looked at is a corridor then do the following.
				else if (current.getType() == "corridor") {
					
					// If the node is not marked, then check if path(v, d) returns true.
					if(v.getMark() == false) {
						if(path(v, d) == true) {
							return true;
						}
					}
				}
				
				// Resets the numbers of acid bombs and brick bombs back to what they where before path was ran.
				ABombs = acid;
				BBombs = bombs;
			}
			
			// Removes a node from the stack and unmarks it.
			Path.pop().setMark(false);
			return false;
		} 
		
		// Catches any exceptions and prints them, and returns false.
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
