public class SwapLetters
{
  public static void main(String[] args)
	{
		String str = args[0];
		int x = Integer.parseInt(args[1]); 
		int y = Integer.parseInt(args[2]);

		char[] charArray = str.toCharArray();
		char firstChar = charArray[x];
		char secondChar = charArray[y];

		charArray[x] = secondChar;
		charArray[y] = firstChar;

		String finalString = new String(charArray);

		System.out.println(str);
		System.out.println(finalString);

	}
}