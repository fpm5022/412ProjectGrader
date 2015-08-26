public class SwapLetters
{
	public static void main(String[] args)
	{
	  String str = new String(args[0]);
	  
	  char[] charArray = str.toCharArray();

	  int Swap1 = Integer.parseInt(args[1]);

	  int Swap2 = Integer.parseInt(args[2]);
	  
	  char temp = charArray[Swap1];
     
      charArray[Swap1] = charArray[Swap2];
      charArray[Swap2] = temp;

	  System.out.println("Original String = " + str);
	  System.out.println("Swapped String = " + new String(charArray));
	}
}