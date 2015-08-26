public class SwapLetters
{
	public static void main(String[] args)
	{
		
		String str = (args[0]) ;
		int position1 = Integer.parseInt(args[1]) ;
		int position2 = Integer.parseInt(args[2]) ;
		int position1a = position1 - 1 ;
		int position2a = position2 - 1 ;
		
		String x = str.substring(position1a,position1) ;
		String y = str.substring(position2a,position2) ;
		String a = str.substring(0, position1a) ;
		String b = str.substring(position1, position2a) ;
		String c = str.substring(position2) ;
		String strnew = a + y + b + x + c ;
		
		System.out.println("Original String: " + str + " New String: " + strnew) ;
	}
}