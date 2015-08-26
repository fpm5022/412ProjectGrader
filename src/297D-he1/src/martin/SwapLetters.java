public class SwapLetters

{
  public static void main(String [] args)
   {
     String str = args[0];
     char[] c = str.toCharArray();

     char temp = c[1];
     c[1] = c[7];
     c[7] = temp;

     String switchstring = new String(c);

     System.out.println("Original:" + str);
     System.out.println("Switched:" + switchstring);
    }
}