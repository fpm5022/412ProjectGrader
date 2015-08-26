import java.io.*;
import java.util.*;
public class ReadWriteTest
{
  public static String [] readFile()
  {
    String [] stringArray = new String[100];
    int index = 1;
    try
    {
      File inputFile = new File("input.txt");
      Scanner in = new Scanner(inputFile);
      while (in.hasNextLine()) 
      {
        String line = in.nextLine();
	      System.out.println(line);
        stringArray[index] = line; 
        index++;
      }
      in.close();
    }catch(IOException e)
    {
      System.out.println("IO Exception Encountered");
    }
    stringArray[0] = Integer.toString(index);
    return stringArray;
  }
  
  public static void writeFile(String [] stringArray)
  {
    int arrayLength = Integer.parseInt(stringArray[0]);
    try
    {
      PrintWriter out = new PrintWriter("output.txt");
      for(int k = 1; k < arrayLength; k++)
      {      
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
    String [] stringArray = readFile();
    writeFile(stringArray);
  }   
    
}