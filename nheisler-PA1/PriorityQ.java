import java.lang.String;
import java.util.ArrayList;

public class PriorityQ
{
  int numItems;

  private class tuple
  {
    String v;
    int p;
  }

  private ArrayList<tuple> heap;
  PriorityQ()
  {
    //constructs priority queue
    heap = new ArrayList<tuple>();
    heap.add(null);
    numItems = 0;
  }

  public void add(String s, int p)
  {
    tuple t = new tuple();
    t.v = s;
    t.p = p;
    numItems++;
    heap.add(t);
    percUp(t);
  }

  public String returnMax()
  {
    if (numItems != 0) return heap.get(1).v;
    else return null;
  }

  public String extractMax()
  {
    if (numItems != 0)
    {
      tuple t = heap.get(1);
      tuple end = heap.get(numItems);
      numItems--;
      heap.set(1, end);//sets root to last node
      percDown(end);
      heap.trimToSize();
      return t.v;
    }
    else return null;
  }

  public void remove(int i)
  {
    tuple last = heap.get(numItems);
    numItems--;
    heap.set(i, last);
    percDown(last);
    heap.trimToSize();
  }

  public void decrementPriority(int i, int k)
  {
    //decrements the priority of the ith element by k, and reheapifies
    heap.get(i).p -= k;
    percDown(heap.get(i));
  }

  public int[] prorityArray()
  {
    //returns an array B with B[i] = key(A[i]) where A is the array of the priority queue
    int[] B = new int[numItems];
    for (int i = 0; i < numItems; i++)
    {
      B[i] = getKey(i+1);
    }
    return B;
  }

  public int getKey(int i)
  {
    return heap.get(i).p;
  }

  public String getValue(int i)
  {
    return heap.get(i).v;
  }

  public boolean isEmpty()
  {
    return numItems == 0;
  }

  public static void main(String args[])
  {
    PriorityQ queue = new PriorityQ();
    queue.add("A", 1);
    queue.add("B", 2);
    queue.add("C", 3);
    queue.add("D", 4);
    queue.add("E", 5);
    queue.add("F", 6);
    int i = 0;
    while (i != 6)
    {
      System.out.println(queue.extractMax());
      i++;
    }
    System.out.println("end");
  }

  //percolates up recursively until t is in correct position
  private void percUp(tuple t)
  {
    if (numItems == 0) return;
    tuple root = heap.get(1);
    if (root == t)
    {
      //do nothing
    }
    else
    {
      tuple current = t;
      int i = heap.indexOf(t); //"heap" position in array
      tuple parent = heap.get(i/2);
      if (current.p > parent.p)
      {
        heap.set(i/2, current);
        heap.set(i, parent);
        percUp(current);
      }
      else
      {
        //do nothing, end recursive
      }
    }
  }

  //percolates down recursively until t is in correct position
  private void percDown(tuple t)
  {
    if (numItems == 0) return;
    tuple current = t;
    int i = heap.indexOf(t);//"heap" position in array
    if (i == numItems || 2*i > numItems)
    {
      //do nothing, end of list
    }
    else
    {
      tuple LeftChild = heap.get(2*i);
      tuple RightChild = heap.get((2*i) + 1);
      if (LeftChild.p > current.p && LeftChild.p > RightChild.p)
      {
        heap.set(i, LeftChild);
        heap.set(2*i, current);
        percDown(current);
      }
      else if (RightChild.p > current.p && RightChild.p > LeftChild.p)
      {
        heap.set(i, RightChild);
        heap.set(2*i+1, current);
        percDown(current);
      }
      else if (LeftChild.p > current.p && RightChild.p == LeftChild.p)
      {
        heap.set(i, LeftChild);
        heap.set(2*i, current);
        percDown(current);
      }
      else
      {
        //dont do anything, current is not smaller than children
      }
    }
  }

  private void heapify()
  {
    //do stuff
  }
 }
