import java.lang.Math;
import java.lang.Object;

public class SwapLetters
{
	public static void main(String[] args)
	{
		String str = (args[0]);    //string from which everything is taken
		int position1 = Integer.parseInt(args[1]);   //first letter position
		int position2 = Integer.parseInt(args[2]);   //second letter position
		{
			
			int stringLength = String.valueOf(str).length();  //length of string
			String sub1 = str.substring(0, position1 - 1);
			String sub2 = str.substring(position2 - 1, position2);
			String sub3 = str.substring(position1, position2 - 1);
			String sub4 = str.substring(position1 - 1, position1);
			String sub5 = str.substring(position2);
			


			System.out.print("Original String: " + str + " New String: ");
			System.out.println(sub1 + sub2 + sub3 + sub4 + sub5);
		}
	}
}