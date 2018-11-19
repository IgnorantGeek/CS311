import java.lang.*;
import java.util.*;
import java.io.*;

/**
 * Class for getting the shortest path for various types of input types
 *
 * @author Nick Heisler
 */
public class WGraph
{
  Node vertices[];
  Edge edges[];
  int numVert = 0;
  int numEdges = 0;
  String pathtofile;


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
        boolean newStart, newEnd, newEdge;
        newStart = newEnd = newEdge = false;
        int startx, starty, endx, endy;
        startx = scan.nextInt();
        starty = scan.nextInt();
        endx = scan.nextInt();
        endy = scan.nextInt();
        Node start = new Node(startx, starty);
        Node end = new Node(endx, endy);
        Edge e = new Edge();
        e.weight = scan.nextInt();
        if (start.isVertex(this) != null) 
        {
          start = start.isVertex(this);
          newStart = true;
        }
        if (end.isVertex(this) != null)
        {
          end = end.isVertex(this);
          newEnd = true;
        }
        e.start = start;
        e.end = end;
        start.edges.add(e);
        if (!newStart && numVert != vertices.length)
        {
          vertices[numVert] = start;
          numVert++;
          newEdge = true;
        }
        if (!newEnd && numVert != vertices.length)
        {
          vertices[numVert] = end;
          numVert++;
          newEdge = true;
        }
        if (numVert != vertices.length | !newEdge)
        {
          edges[numEdges] = e;
          numEdges++;
        }
        scan.nextLine();
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
   * @param uy y position of first vertex
   * @param vs x position of second vertex
   * @param vy y position of second vertex
   * @return
   */
  public ArrayList<Integer> V2V(int ux, int uy, int vx, int vy)
  {
    Node source = new Node(ux, uy);
    Node target = new Node(vx, vy);
    if (source.isVertex(this) == null) 
    {
      System.out.println("One or more of the entered positions is not in the Graph");
      return null;
    }
    if (target.isVertex(this) == null)
    {
      System.out.println("One or more of the entered positions is not in the Graph");
      return null;
    }
    source = source.isVertex(this);
    target = target.isVertex(this);
    return source.get_shortest_as_int(this, target);
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
    Node source = new Node(ux, uy);
    if (source.isVertex(this) == null)
    {
      System.out.println("One or more of the entered positions is not in the Graph");
      return null;
    }
    for (int i = 0; i < S.size(); i+=2)
    {
      // need to make sure we are scanning two at a time. It is understood that S is even
      Node target = new Node(S.get(i), S.get(i+1));
      // check if the node exists. If it does check for a path from source
      if (target.isVertex(this) != null)
      {
        if (source.get_shortest_as_int(this, target) != null);
      }
    }
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


    /**
     * Helper method to print the graph, features a try-catch block that attempts to open a scanner on the input
     * file from the constructor. Doesn't print if the input file is invalid.
     */
    private void printGraph()
    {
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
      System.out.println();
    }

    /**
     * Helper method that prints general info about the Graph.
     */
    private void printInfo()
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
      System.out.println();
    }

  public static void main(String[] args)
  {
    //here is where main stuff will go
    WGraph graph = new WGraph("/home/nick/Documents/Workspaces/CS311/nheisler-PA2/src/GraphData.txt");
    int i = 0;
    graph.printInfo();
    graph.printGraph();
    while (i < graph.numVert)
    {
      graph.vertices[i].printNode();
      i++;
    }
    System.out.println();
    ArrayList<Integer> path = graph.V2V(1,2,3,4);
    i = 0;
    while (i < path.size())
    {
      System.out.println(path.get(i));
      i++;
    }
  }
}
