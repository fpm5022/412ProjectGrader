import java.util.Scanner;
public class SwapLetters
{
	public static void main(String [] args)
	{
		//Asking for Input
		Scanner in = new Scanner(System.in);
	
		//Assigning data types to input
		String str = args[0];
		int position1 = Integer.parseInt(args[1]);
		int position2 = Integer.parseInt(args[2]);
		
		//Picking Characters
		char [] A = str.toCharArray();
		
		//Swapping the Letters
		char B = A[position1];
		A[position1] = A[position2];
		A[position2] = B;
		
		String newString = new String(A);
		
		//Printing out the Swapped String
		System.out.println(str);
		System.out.println(newString);
		
	}
}