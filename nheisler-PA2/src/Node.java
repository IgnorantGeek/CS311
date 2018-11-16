import java.lang.*;
import java.util.*;
import java.io.*;

public class Node
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
    protected Node isVertex(WGraph g)
    {
      for (int i = 0; i < g.numVert; i++)
      {
        if (this.position[0] == g.vertices[i].position[0] && this.position[1] == g.vertices[i].position[1])
        {
          return g.vertices[i];
        }
      }
      return null;
    }
    /**
     * Prints this node's position, and the positions of any connected nodes, 
     * as well as the associated edge weight, used for testing
     */
    protected void printNode()
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
    protected ArrayList<Integer> get_shortest_as_int(WGraph g, Node n)
    {
      // Begin Dijkstra Block: This guy is done. Well I hope
      int i = 0;
      int sourcemark = 0;
      ArrayList<Node> visited = new ArrayList<Node>();
      ArrayList<Node> unvisited = new ArrayList<Node>();
      ArrayList<Integer> path_to_target = new ArrayList<Integer>();// what we are returning, array of positions
      //ArrayList<Node> temp_path = new ArrayList<Node>();
      ArrayList<Integer> distance = new ArrayList<Integer>();
      String s[] = new String[g.numVert]; // stores the path of each node
      
      // this loop should initialize the unvisited array and distance arrays
      // sourcemark stores the index of the source node in the unvisited array
      while (i != g.numVert)
      {
        int dist = Integer.MAX_VALUE;
        if (g.vertices[i] == this) 
        {
          sourcemark = i;
          dist = 0;
        }
        unvisited.add(g.vertices[i]);
        distance.add(dist);
        i++;
      }

      // now we want to "look at" the source vertex, unvisited[sourcemark]
      Node looking = unvisited.get(sourcemark);
      while (unvisited.size() != 0)
      {
        for (int j = 0; j < looking.edges.size(); j++)
        {
          Edge examine = looking.edges.get(i);
          Node neighbor = examine.end;
          if (unvisited.contains(neighbor)) // we don't want to examine visited members, their distances are finalized
          {
            int neighborIndex = unvisited.indexOf(neighbor);
            int lookIndex = unvisited.indexOf(looking);
            int newDist = distance.get(lookIndex) + examine.weight;
            if (newDist < distance.get(neighborIndex))
            {
              distance.set(neighborIndex, newDist);
            }
          }
        }
        unvisited.remove(looking);
        visited.add(looking);
        // looking = next smallest Node in unvisited. So I need to find the index of the next smallest number in distance.
        int smallest = distance.get(0);
        for (int x = 0; x < unvisited.size(); x++)
        {
          if (distance.get(x) < smallest)
          {
            smallest = distance.get(x);
          }
        }
        looking = unvisited.get(smallest);
      }
      // End Dijkstra Block

      // TODO: Path storage and data structure conversion
      // find a way to store the path from source of each Node. Best practice will probably be to create a data structure for storing dijkstra results.
      // Data structure will feature an int for that node's distance from the source and a string that stores the path from source. Whenever distance gets update, we 
      // want to update the path as well. Or I could use a string array to store the path in the same way I did distances. Gonna be a bitch tho

      return path_to_target;
    }
}