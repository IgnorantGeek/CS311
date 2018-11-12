import java.lang.*;
import java.util.*;
import java.awt.PrintGraphics;
import java.io.*;

/**
 * Class for getting the shortest path for various types of input types
 * 
 * @Aauthor Nick Heisler
 */
public class WGraph
{
  //These are subclasses for graph elements
  private class Edge
  {
    Node start;
    Node end;
    int weight;
  }
  
  /**
   * position format (x,y) = [0,1]
   */
  private class Node
  {
    ArrayList<Node> edges;
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
  
  /**
   * Helper method to print the graph, features a try-catch block that attempts to open a scanner on the input 
   * file from the constructor. Doesn't print if the input file is invalid. 
   */
  private void printGraph()
  {
    try
    {
      File file = new File(this.pathtofile);
      Scanner scan = new Scanner(file);
      scan.close();
    }
    catch (FileNotFoundException e)
    {
      //don't do anything, the constructor handles error printing, this only makes sure 
      //we are not printing when there is no file.
      return;
    }
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
      }
      scan.close();
    } 
    catch (FileNotFoundException e)
    {
      System.out.println("No file with the name " + pathtofile + " could be found.");
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
  public ArrayList<Integer> V2V(int ux, int uy, int vx, int vy)
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

  public static void main(String[] args)
  {
    //here is where main stuff will go
    WGraph graph = new WGraph("GraphData.txt");
    graph.printGraph();
  }
}