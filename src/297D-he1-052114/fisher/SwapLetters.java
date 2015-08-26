public class SwapLetters
{
	public static void main(String[] args)
	{

		String str = args[0];
		int position1 = Integer.parseInt(args [1]);
		int position2 = Integer.parseInt(args [2]);
		char[] letters = str.toCharArray();
		char temporary = letters[position1];
		letters[position1] = letters[position2];
		letters[position2] = temporary;

		String str2 = new String(letters);

		System.out.println("Original String: " + str);
		System.out.println("New String: " + str2);
		


	}
}