public class SwapLetters
{
	public static void main (String [] args)
	{
		int a = Integer.parseInt(args [0]);

		String str1 = a + "";
		char z = str1.charAt(0);
		char position1 = str1.charAt(1);
		char x = str1.charAt(2);
		char w = str1.charAt(3);
		char position2 = str1.charAt(4);
		char u = str1.charAt(5);

		System.out.println("Original String: " + z + position1 + x + w + position2 + u + "  " + "New String: " + z + position2 + x + w + position1 + u);
	}
}