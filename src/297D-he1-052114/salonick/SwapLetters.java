public class SwapLetters
{
	public static void main(String[] args)
	{
		String str = args[0];
		int a = Integer.parseInt(args[1]);
		int b = Integer.parseInt(args[2]);

		char[] charArray = str.toCharArray();
		char firstChar = charArray[a];
		char secondChar = charArray[b];

		charArray[a] = secondChar;
		charArray[b] = firstChar;

		String finalString = new String(charArray);

		System.out.println(str);
		System.out.println(finalString);

	}
}