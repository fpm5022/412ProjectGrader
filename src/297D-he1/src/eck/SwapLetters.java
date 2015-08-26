import java.util.Scanner;
public class SwapLetters
{
	public static void main(String args[])
	{
		String swapLettersString="123456789";
		char data[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
		String str = new String(data);
		System.out.println(swapLettersString);

		Scanner in = new Scanner(System.in);

		System.out.print("Please enter the two letters you would like to swap: ");
		int letters = in.nextInt();

		int[] position1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] position2 = {letters}; {int bash$ =  1%2%3%4%5%6%7%8%9;}; 

		/*
		I thought this way would work to make the letters able to swap..I tried
		 adding in different commands but couldn't get it figured out.
		 */

		System.out.println(swapLettersString);
		
	}
}