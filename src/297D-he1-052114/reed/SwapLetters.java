public class SwapLetters{
	public static void main (String[] args) {
		String str = "789";
		int position1 = Integer.parseInt(str);
		int position2 = Integer.parseInt(str);
		String New1 = str.substring(0, 1);
		String New2 = str.substring(1, 2);
		String New3 = str.substring(2, 3);
		String Swapped = New3 + New2 + New1;
 		System.out.println("Original String:" + str);
		System.out.println("New String:" + Swapped);
  }
} 