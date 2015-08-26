import java.io.*;
import java.util.*;
public class ReadTest
{
  public static void readFile()
  {
    try
    {
      File inputFile = new File("input.txt");
      Scanner in = new Scanner(inputFile);
      while (in.hasNextLine()) 
      {
        String line = in.nextLine();
	      System.out.println(line);
      }
      in.close();
    }catch(IOException e)
    {
      System.out.println("IO Exception Encountered");
    }
  }
  
  public static void main(String [] args)
  {
    readFile();
  }   
    
}