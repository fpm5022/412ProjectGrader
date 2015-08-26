import java.util.Scanner;
public class SwapLetters
{
	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter String: ");
		String str = in.nextLine();
		
		System.out.println("Enter 1st Position: ");
		int position1 = in.nextInt();
		
		System.out.println("Enter 2nd Position: ");
		int position2 = in.nextInt();
		
		char [] A = str.toCharArray();
		char B = A[position1];
		
		A[position1] = A[position2];
		A[position2] = B;
		
		String newString = new String(A);
		
		System.out.println(str);
		System.out.println(newString);
	}
}