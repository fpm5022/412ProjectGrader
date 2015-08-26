public class SwapLetters
{
	public static void main(String [] args)
	{
		String str = args[0];
		int position1 = Integer.parseInt(args[1]);
		int position2 = Integer.parseInt(args[2]);
		String newstr1 = str.substring(0,(position1-1));
		String letter1 = str.substring((position1-1),position1);
		String newstr2 = str.substring(position1,(position2-1));
		String letter2 = str.substring((position2-1),position2);
		String newstr3 = str.substring(position2,str.length());
		System.out.println("Original String: " + str + " New String: " + newstr1 + letter2 + newstr2 + letter1 + newstr3);
	}
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        