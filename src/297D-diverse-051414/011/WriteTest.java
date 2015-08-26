import java.io.*;
import java.util.*;
public class WriteTest
{  
  public static void writeFile(String [] stringArray)
  {
    try
    {
      PrintWriter out = new PrintWriter("output.txt");
      for(int k = 0; k < stringArray.length; k++) 
      {
        stringArray[k] = "/* " + Integer.toString(k) + " */ " + stringArray[k];
        out.println(stringArray[k]);
      }
      out.close();
    }catch(IOException e)
    {
      System.out.println("IO Exception Encountered");
    }
  }
  
  public static void main(String [] args)
  {
    String [] stringArray = {"Line 1", "Line 2", "Line 3", "Line 4", "Line 5"};
    writeFile(stringArray);
  }   
    
}