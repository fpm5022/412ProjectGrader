public class SwapLetters
{
 public static void main(String [] args)
 {
  int a = Integer.parseInt(args[0]);

  String str1 = a + "";

  char z = str1.charAt(0);
  char position1 = str1.charAt(1);
  char y = str1.charAt(2);
  char x = str1.charAt(3);
  char w = str1.charAt(4);
  char v = str1.charAt(5);
  char u = str1.charAt(6);
  char position2 = str1.charAt(7);
  char t = str1.charAt(8);
  System.out.println("Original String: " + z + position1 + y + x + w + v + u + position2 + t + " New Position: " + z + position2 + y + x + w + v + u + position1 + t);
 }
}