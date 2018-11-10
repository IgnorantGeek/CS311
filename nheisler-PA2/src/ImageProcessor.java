<<<<<<< HEAD
import java.lang.String;

public class ImageProcessor
{
  //class stuff
=======
import java.lang.*;
import java.util.*;
import java.io.*;

public class ImageProcessor
{
  String filepath;
  int height;
  int width;

  public ImageProcessor(String fname)
  {
    this.filepath = fname;
    try 
    {
      File file = new File(this.filepath);
      Scanner scan = new Scanner(file);
      this.height = scan.nextInt();
      this.width = scan.nextInt();
      while (scan.hasNextLine())
      {
        //read the info for pixel stuff
      }
      scan.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("Could not open the specified file");
    }
  }

  public static void main(String[] args)
  {
    //main method stuff
  }

  ArrayList<ArrayList<Integer>> getImportance()
  {
    return null;
  }

  void writeReduced(int k, String fname)
  {
    //do the thing and write to file
  }
>>>>>>> 5bb3c5cc2ff4b65849ab1a67c34c2e29e9894df2
}
