public class SwapLetters
{
	
	public static void main(String[] args)
	
	{
		
		String str = args[0];
		
		int position1 = Integer.parseInt(args[1]);
		
		int position2 = Integer.parseInt(args[2]);
		
		position1--;
		
		position2--;
		
		char x = str.charAt(position1);
		
		char y = str.charAt(position2);
		
		int length = str.length();
		
		System.out.println("Original String: " + str);
		
		String holder = str.substring(0, position1);
		
		String otherHolder = str.substring((position1 + 1), position2);
		
		String lastHolder = str.substring((position2 + 1), length); 
		
		str = holder + y + otherHolder + x + lastHolder;
		
		System.out.print("New String: " + str);
		
		System.out.println();
		
	}
	
}
		
