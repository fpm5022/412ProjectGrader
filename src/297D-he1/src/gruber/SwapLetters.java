public class SwapLetters
{
  public static void main(String [] args)
  {
    int a = Integer.parseInt(args [0]);
    String str1 = a + "";
    char A = str1.charAt(0);
    char position1 = str1.charAt(1);
    char B = str1.charAt(2);
    char C = str1.charAt(3);
    char D = str1.charAt(4);
    char E = str1.charAt(5);
    char F = str1.charAt(6);
    char position2 = str1.charAt(7);
    char G = str1.charAt(8);
    
    System.out.println("Original String: " + A + position1 + B + C + D + E + F + position2 + G + " New String : " + A + position2 + B + C + D + E + F + position1 + G);
  }
}
    
    