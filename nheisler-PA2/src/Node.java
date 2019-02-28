import java.lang.*;
import java.util.*;
import java.io.*;

public class Node
{
    ArrayList<Edge> edges;
    int position[] = new int[2];
    int distance;
    ArrayList<Node> path;

    Node(int x, int y)
    {
      position[0] = x;
      position[1] = y;
      edges = new ArrayList<Edge>();
      path = new ArrayList<Node>();
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
      int targetmark = 0; // index of visited and unvisited are different, need to mark the target node when we find it and add it to visited.
      ArrayList<Node> visited = new ArrayList<Node>();
      ArrayList<Node> unvisited = new ArrayList<Node>();
      ArrayList<Integer> path_to_target = new ArrayList<Integer>();// what we are returning, array of positions
      ArrayList<Node> temp_path = new ArrayList<Node>();// Need to implement this path. Literally just add to the path until the target node is reached.
      
      // this loop should initialize the unvisited array and set all the node distances
      while (i != g.numVert)
      {
        int dist = Integer.MAX_VALUE;
        if (g.vertices[i] == this) 
        {
          sourcemark = i;
          dist = 0;
          g.vertices[i].path.add(this);
        }
        g.vertices[i].distance = dist;
        unvisited.add(g.vertices[i]);
        i++;
      }

      // now we want to "look at" the source vertex, unvisited[sourcemark]
      Node looking = unvisited.get(sourcemark);
      while (unvisited.size() != 0)
      {
        temp_path = looking.path; // set the current temp path to match the looking path
        for (i = 0; i < looking.edges.size(); i++)
        {
          Edge examine = looking.edges.get(i);
          Node neighbor = examine.end;
          if (unvisited.contains(neighbor)) // we don't want to examine visited members, their distances are finalized
          {
            int neighborIndex = unvisited.indexOf(neighbor);
            int lookIndex = unvisited.indexOf(looking);
            int newDist = unvisited.get(lookIndex).distance + examine.weight;
            if (newDist < unvisited.get(neighborIndex).distance) // when a new, shorter, distance to a node is found we update the distance 
            {
              unvisited.get(neighborIndex).distance = newDist;
              temp_path.add(neighbor);
              unvisited.get(neighborIndex).path = new ArrayList<Node>(temp_path);
              temp_path.remove(neighbor); // we want to re-use this path so we need to remove any neighbor nodes added here. 
            }
          }
        }
        unvisited.remove(looking);
        if (looking == n) targetmark = visited.size();
        visited.add(looking);
        // looking = next smallest Node in unvisited. So I need to find the index of the next smallest number in distance.
        int smallest = Integer.MAX_VALUE;
        int index = 0;
        for (i = 0; i < unvisited.size(); i++)
        {
          if (unvisited.get(i).distance < smallest)
          {
            smallest = unvisited.get(i).distance;
            index = i;
          }
        }
        if (unvisited.size() != 0) looking = unvisited.get(index);
      }
      // End Dijkstra Block

      for (i = 0; i < visited.get(targetmark).path.size(); i++)
      {
        path_to_target.add(visited.get(targetmark).path.get(i).position[0]);
        path_to_target.add(visited.get(targetmark).path.get(i).position[1]);
      }
      // find a way to store the path from source of each Node. Best practice will probably be to create a data structure for storing dijkstra results.
      // Data structure will feature an int for that node's distance from the source and a string that stores the path from source. Whenever distance gets update, we 
      // want to update the path as well. Or I could use a string array to store the path in the same way I did distances. Gonna be a bitch tho

      return path_to_target;
    }
}