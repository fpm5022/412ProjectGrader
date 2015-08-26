/** 
   Program transposes two letters in a given string
   Three variable are required on the command line:
   string, position 1 and position 2
*/

public class SwapLetters {
  public static void main(String[] args) {

    String str = args[0];
    int position1 = Integer.parseInt(args[1]);
    int position2 = Integer.parseInt(args[2]);

    String first = str.substring(0, position1 - 1);
    String second = str.substring(position1, position2 - 1);
    String third = str.substring(position2, str.length());
    
    System.out.println("Original String: " + str
        + " New String: " + first + position2 + second + position1 + third);

  }

}
