public class SwapLetters
{
	public static void main (String [] args)
	{
		String string1 = "123456";
		int position1 = Integer.parseInt (args [0]);
		int position2 = Integer.parseInt (args [1]);
		
		
		String piece1 = string1.substring(0,position1);
		String piece2 = string1.substring(position1,position1+1);
		String piece3 = string1.substring(position1+1,position2);
		String piece4 = string1.substring(position2,position2+1);
		String piece5 = string1.substring(position2+1,string1.length());
		
		
		String string2 = piece1 + piece4 + piece3 + piece2 + piece5;
		
		System.out.println(string2); 
	}
}
		
		
		