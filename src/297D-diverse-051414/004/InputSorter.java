public class InputSorter
{
public static void main(String [] args)
  {
    String input1 = args[0];
    String input2 = args[1];

    if(input1.length() != 2 | input2.length() != 2)  // problem with input lengths
    {
      System.out.println("Your inputs must both be positive integers of length 2.");
      return;
    }

    boolean isDigit0 = Character.isDigit(input1.charAt(0));
    boolean isDigit1 = Character.isDigit(input1.charAt(1));
    
    boolean isDigit2 = Character.isDigit(input2.charAt(0));
    boolean isDigit3 = Character.isDigit(input2.charAt(1));
    

    if(isDigit0 == false | isDigit1 == false | isDigit2 == false | isDigit3 == false)
    {
      System.out.println("Your inputs must be positive integers of length 2.");
      return;
    }
      
    int x1 = Integer.parseInt(input1);
    int x2 = Integer.parseInt(input2);
    
    if(x1 <= x2)
    {
      System.out.println(x1);
      System.out.println(x2);
    }
    else
    {
      System.out.println(x2);
      System.out.println(x1);
    }
        
  }
}

