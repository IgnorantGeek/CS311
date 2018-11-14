import java.lang.*;
import java.util.*;
import java.awt.PrintGraphics;
import java.io.*;

/**
 * Class for getting the shortest path for various types of input types
 *
 * @author Nick Heisler
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

  private class PathNode
      {
        Node vertex;
        int shortest;
        PathNode[] connections;
        Node previous; //for now, not sure if we need this.
        PathNode(Node n, int dist)
        {
          vertex = n;
          shortest = dist;
          for (int i = 0; i < n.edges.length; i++)
          {
            
          }
        }
      }

  /**
   * position format (x,y) = [0,1]
   */
  private class Node
  {
    ArrayList<Edge> edges;
    int position[] = new int[2];

    Node(int x, int y)
    {
      position[0] = x;
      position[1] = y;
      edges = new ArrayList<Edge>();
    }
    
    /**
     * Checks if a node with this position exists
     * @return  the Node, if it exists. Null otherwise
     */
    private Node isVertex()
    {
      for (int i = 0; i < numVert; i++)
      {
        if (this.position[0] == vertices[i].position[0] && this.position[1] == vertices[i].position[1])
        {
          return vertices[i];
        }
      }
      return null;
    }
    /**
     * Prints this node's position, and the positions of any connected nodes, 
     * as well as the associated edge weight, used for testing
     */
    public void printNode()
    {
      // print Node info
      System.out.print(this.position[0]);
      System.out.print(' ');
      System.out.print(this.position[1]);
      System.out.print(' ');
      int numconnections = this.edges.size();
      if (numconnections != 0)
      {
        //print all the  Nodesconnected to this node, followed by the edge weight
        int i = 0;
        while (i != numconnections)
        {
          if (i != 0)
          {
            System.out.print("    ");
          }
          System.out.print(this.edges.get(i).end.position[0]);
          System.out.print(' ');
          System.out.print(this.edges.get(i).end.position[1]);
          System.out.print(' ');
          System.out.print(this.edges.get(i).weight);
          i++;
          if (i != numconnections)
          {
            System.out.println();
          }
        }
      }
      System.out.println();
    }

    /**
     * Gets the shortest path from the source node to the target node, n.
     * @param n target node for path
     * @return ArrayList of integers, containing the path to the target node.
     * The path is in sequential order of each nodes x and y position, respectively.
     */
    private ArrayList<Integer> get_shortest_as_int(Node n)
    {
      // how can I store info about distance from vertex? What about previous vertex info, is that important 
      // for my application? 
      int i = 0;
      int sourcemark = 0;
      ArrayList<PathNode> visited = new ArrayList<PathNode>();
      ArrayList<PathNode> unvisited = new ArrayList<PathNode>();
      ArrayList<Integer> path_to_target = new ArrayList<Integer>();// what we are returning, array of positions
      ArrayList<Node> temp_path = new ArrayList<Node>();
      
      // this loop should initialize all the Nodes in the graph as PathNodes, and set the source node to 0 distance
      // sourcemark stores the index of the source node in the unvisited array
      while (i != numVert)
      {
        int distance = Integer.MAX_VALUE;
        if (vertices[i] == this) 
        {
          sourcemark = i;
          distance = 0;
        }
        PathNode pn = new PathNode(vertices[i], distance);
        unvisited.add(i, pn);
        i++;
      }
      // now we want to "look at" the source vertex, unvisited[sourcemark]
      PathNode looking = unvisited.get(sourcemark);
      Node lookingn = looking.vertex;
      while (unvisited.size() != 0)
      {
        for (int j = 0; j < lookingn.edges.length; j++)
        {

        }
      }
      return path_to_target;
    }
  }
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
        if (start.isVertex() != null) 
        {
          start = start.isVertex();
          newStart = true;
        }
        if (end.isVertex() != null)
        {
          end = end.isVertex();
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
        if (numVert != vertices.length | !newEdge) // Bug: seems to be working now
        {
          edges[numEdges] = e;
          numEdges++;
        }
        scan.nextLine(); // thats annoying
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
    if (source.isVertex() == null) 
    {
      System.out.println("One or more of the entered positions is not in the Graph");
      return null;
    }
    if (target.isVertex() == null)
    {
      System.out.println("One or more of the entered positions is not in the Graph");
      return null;
    }
    source = source.isVertex();
    target = target.isVertex();
    return source.get_shortest_as_int(target);
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

  public static void main(String[] args)
  {
    //here is where main stuff will go
    WGraph graph = new WGraph("D:\\Documents\\Workspaces\\CS311\\nheisler-PA2\\src\\GraphData.txt");
    System.out.println("Total number of vertices in graph: " + graph.numVert);
    System.out.println("Max number of vertices: " + graph.vertices.length);
    System.out.println("Total number of edges in graph: " + graph.numEdges);
    System.out.println("Max number of edges: " + graph.edges.length);
    System.out.println();
    //graph.printGraph();
    System.out.println();

    int i = 0;
    while (i < graph.numVert)
    {
      graph.vertices[i].printNode();
      i++;
    }
  }
}
