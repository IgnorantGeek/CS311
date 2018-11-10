import java.lang.*;
import java.util.*;
<<<<<<< HEAD
=======
import java.awt.PrintGraphics;
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
import java.io.*;

/**
 * Class for getting the shortest path for various types of input types
 * 
 * @Aauthor Nick Heisler
 */
public class WGraph
{
  //These are subclasses for graph elements
  class Edge
  {
    Node start;
    Node end;
<<<<<<< HEAD
    double weight;
  }
  
  class Node
  {
    String name;
    ArrayList<Edge> edges;
  }

  //all the nodes in this graph
  ArrayList<Node> nodes;
  int numVert = 0;
  int numEdges = 0;
  String pathtofile;

  //TODO: WGraph constructor needs testing
=======
    int weight;
  }
  
  /**
   * position format (x,y) = [0,1]
   */
  class Node
  {
    ArrayList<Edge> edges;
    int position[] = new int[2];
    private boolean isVertex()
    {
      for (int i = 0; i < numVert; i++)
      {
        if (this.position[0] == vertices[i].position[0] && this.position[1] == vertices[i].position[1]) return true;
      }
      return false;
    }
  }

  Node vertices[];
  Edge edges[];
  int numVert = 0; 
  int numEdges = 0;
  String pathtofile;

>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
  /**
   * Constructor for a new WGraph with some read file, the semantic of the read file is as follows:
   * (1) First line contains a number indicating the number of vertices in the graph 
   * (2) Second line contains a number indicating the number of edges in the graph 
   * (3) Remaining lines contain 5 integers per line, x and y positions of the source vertex, x and y positions of the destination vertex
   *     and an integer to denote the edge weight
   *
   * @param fName The file to be read
   */
  WGraph(String fName)
  {
<<<<<<< HEAD
    this.pathtofile = fName;
    File file = new File(this.pathtofile);
    try 
    {
      Scanner scan = new Scanner(file);
      while (scan.hasNextLine())
      {
        //read the file, scanning for integers
        this.numVert = scan.nextInt(); //sets the number of vertices 
        scan.nextLine(); //move the line down
        this.numEdges = scan.nextInt();
=======
    pathtofile = fName;
    File file = new File(pathtofile);
    try 
    {
      Scanner scan = new Scanner(file);
      vertices = new Node[scan.nextInt()];
      edges = new Edge[scan.nextInt()];
      while (scan.hasNextLine() && numEdges != edges.length) //scans until max number of edges is reached
      {
        //When scanning edges, if the edge adds a new node and the number of nodes is already maxed out
        //we want to throw out that edge and keep scanning. There may be more edges following that don't contain 
        //new nodes
        Node start = new Node();
        Node end = new Node();
        Edge e = new Edge();
        boolean newEdge = false;
        start.position[0] = scan.nextInt();
        start.position[1] = scan.nextInt();
        end.position[0] = scan.nextInt();
        end.position[1] = scan.nextInt();
        e.weight = scan.nextInt();
        e.start = start;
        e.end = end;
        //only add to edges if max number of nodes has not been reached
        if (!start.isVertex() && numVert != vertices.length) 
        {
          vertices[numVert] = start;
          numVert++;
          newEdge = true;
        }
        if (!end.isVertex() && numVert != vertices.length)
        {
          vertices[numVert] = end;
          numVert++;
          newEdge = true;
        }
        if (numVert != vertices.length | !newEdge) //Bug: seems to be working now
        {
          edges[numEdges] = e;
          numEdges++;
        }
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
      }
      scan.close();
    } 
    catch (FileNotFoundException e)
    {
<<<<<<< HEAD
      System.out.println("No file with the name " + this.pathtofile + " could be found.");
=======
      System.out.println("No file with the name " + pathtofile + " could be found.");
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
    }
  }

  /**
   * Finds shortest path bewtween two vertices given two vertices
   * @param ux x position of first vertex
   * @param uy y position of first vertext
   * @param vs x position of second vertex
   * @param vy y position of second vertex
   * @return
   */
<<<<<<< HEAD
  public ArrayList<Integer> V2V(int ux, int uy, int vs, int vy)
=======
  public ArrayList<Integer> V2V(int ux, int uy, int vx, int vy)
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
  {
    return null;
  }

  /**
   * Finds shortest path between two vertices given a vertex and a set of vertices
   * @param ux x position of given vertex
   * @param uy y position of given vertex
   * @param S set of vertices
   * @return
   */
  public ArrayList<Integer> V2S(int ux, int uy, ArrayList<Integer> S)
  {
    return null;
  }

  /**
   * Finds shortest path between two vertices given two sets of vertices
   * @param S1 first set of vertices 
   * @param S2 second set of vertices
   * @return
   */
  public ArrayList<Integer> S2S(ArrayList<Integer> S1, ArrayList<Integer> S2)
  {
    return null;
  }
<<<<<<< HEAD
=======

  /**
   * Helper method to print the graph 
   */
  private void printGraph()
  {
    
    System.out.println("Total number of vertices in graph: " + this.numVert);
    System.out.println("Max number of vertices: " + this.vertices.length);
    System.out.println("Total number of edges in graph: " + this.numEdges);
    System.out.println("Max number of edges: " + this.edges.length);
    for (int i = 0; i < numEdges; i++) 
    {
      Node from = edges[i].start;
      Node to = edges[i].end;
      int cost = edges[i].weight;
      System.out.print(from.position[0]); 
      System.out.print(' '); System.out.print(from.position[1]);
      System.out.print(' ');
      System.out.print(to.position[0]);
      System.out.print(' ');
      System.out.print(to.position[1]);
      System.out.print(' ');
      System.out.print(cost);
      System.out.println();
    }
  }
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
  
  public static void main(String[] args)
  {
    //here is where main stuff will go
<<<<<<< HEAD
    System.out.println("testing testing 1, 2, 3");
=======
    WGraph graph = new WGraph("readMe.txt");
    graph.printGraph();
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
  }
}