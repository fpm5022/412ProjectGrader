public class SwapLetters
{
		public static void main(String []args)
		{
			String str = (args[0]);
			int position1 = Integer.parseInt(args[1]);
			int position2 = Integer.parseInt(args[2]);

			String a = str.substring(0, position1);
			String b = str.substring(position1, position1+1);
			String c = str.substring(position1+1, position2);
			String d = str.substring(position2, position2+1);
			String e = str.substring(position2+1, str.length());

			

			System.out.println("Original String: " + str + "  New String:" + a + d + c + b + e ); 
		}
}