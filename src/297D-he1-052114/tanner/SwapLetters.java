public class SwapLetters
{
	public static void main(String [] args)
	{
		String str = args[0];
		int position1 = Integer.parseInt(args[1]);
		int position2 = Integer.parseInt(args[2]);

		int pos1 = position1 - 1;
		int pos2 = position2 - 1;

		if(position1 < position2)
		{
			String str1 = str.substring(0, (pos1));
			String str2 = str.substring((pos1 + 1), (pos2));
			String str3 = str.substring((pos2 + 1));

			String x = str.substring(pos1, pos1 + 1);
			String y = str.substring(pos2, pos2 + 1);

			System.out.printf("Original String: " + str + " ");
			System.out.println("New String: " + str1 + y + str2 + x + str3);
		}

		else
		{
			System.out.println("Your second number must be greater than your first number.");
		}

	}
}