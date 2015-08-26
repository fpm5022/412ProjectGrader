public class SwapLetters {

   
    public static void main(String[] args) {
        int a = Integer.parseInt (args [0]);
        String str1 = "" + a;  
        String str2 = str1.substring(0,3);
        String str3 = str1.substring(4,7);
        char b = str1.charAt(3);
        char c = str1.charAt(7);
        System.out.println(str2 + c + str3 + b);
  }
}
