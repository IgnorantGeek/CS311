import java.lang.*;
import java.util.*;
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
  String filepath;

  /**
   * Constructor for a new WGraph with some read file, the semantic of the read file is as follows:
   * (a) First line contains a number indicating the number of vertices in the graph 
   * (b) Second line contains a number indicating the number of edges in the graph 
   * (c) 
   *
   * @param fName The file to be read
   */
  WGraph(String fName)
  {
    this.filepath = fName;
    File file = new File(this.filepath);
    try {
    Scanner scan = new Scanner(file);
    while (scan.hasNextLine())
    {
      //read the file, scanning for integers
      this.numVert = scan.nextInt(); //sets the number of vertices 
      scan.nextLine(); //move the line down
      this.numEdges = scan.nextInt();
    }
    scan.close();
    } 
    catch (FileNotFoundException e)
    {
      System.out.println("No file with the name " + this.filepath + " could be found.");
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
  public ArrayList<Integer> V2V(int ux, int uy, int vs, int vy)
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
    System.out.println("testing testing 1, 2, 3");
  }
}