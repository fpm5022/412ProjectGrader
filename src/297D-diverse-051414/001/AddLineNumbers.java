import java.io.*;
import java.util.*;
public class AddLineNumbers
{  
  public static void readWriteFile()
  {
    String inLine;
    String outLine;
    int k=0;
    try
    {
      File inputFile = new File("holmes.txt");
      Scanner in = new Scanner(inputFile);
      PrintWriter out = new PrintWriter("output.txt");
      while (in.hasNextLine()) 
      {
        inLine = in.nextLine();
        k++;
        outLine = "/* " + Integer.toString(k) + " */ " + inLine;
	      out.println(outLine);
      }
      in.close();
      out.close();
    }catch(IOException e)
    {
      System.out.println("IO Exception Encountered");
    }
  }
  
  public static void main(String [] args)
  {
    readWriteFile();
  }   
    
}