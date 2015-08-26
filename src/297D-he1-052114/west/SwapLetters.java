public class SwapLetters{
	public static void main(String[] args){
		//User entered information
		String num = (args[0]);
		int pos1 = Integer.parseInt(args[1]);
		int pos2 = Integer.parseInt(args[2]);
		//Swappin'
		char[] c = num.toCharArray();
		char temp = c[pos1 - 1];
		c[pos1 - 1] = c[pos2 - 1];
		c[pos2 - 1] = temp;
		String newNum = new String(c);
		//Output
		System.out.println("Old string: " + num);
		System.out.println("New string: " + newNum);
	}
}