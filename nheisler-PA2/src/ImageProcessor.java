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
}
