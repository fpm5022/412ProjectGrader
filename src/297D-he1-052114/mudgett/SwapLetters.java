public class SwapLetters {
	public static void main(String [] args) {
	String str = args[0];
	int index1 = Integer.parseInt(args[1])-1;
	int index2 = Integer.parseInt(args[2])-1;
	String first = str.substring(0,index1);
	String swap1 = str.substring(index1,index1+1);
	String middle = str.substring(index1+1,index2);
	String swap2 = str.substring(index2,index2+1);
	String last = str.substring(index2+1,str.length());
	String newString = first + swap2 + middle + swap1 + last;
	System.out.println("Original String: " + str + "; New String: " + newString);
	}
}