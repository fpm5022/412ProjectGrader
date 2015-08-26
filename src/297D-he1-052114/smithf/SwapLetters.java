public class SwapLetters
{
  public static void main(String [] args)
  {
    int startInput = Integer.parseInt(args[0]);
    int position1 = Integer.parseInt(args[1]);
    int position2 = Integer.parseInt(args[2]);
    String str = Integer.toString(startInput);
    char firstPosition = str.charAt(position1+1);
    char secondPosition = str.charAt(position2+1);
    String begin = str.substring(0, position1);
    String middle = str.substring(position1+1, position2);
    String end = str.substring(position2+1, str.length()-1);
    System.out.print(begin+secondPosition+middle+firstPosition+end);
  }
}