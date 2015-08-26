class SwapLetters {
	public static void main(String[] args) {
		String num = args[0]; //User inputs a number as a string
		int position1 = Integer.parseInt(args[1]); //Get first integer location
		int position2 = Integer.parseInt(args[2]); //Get 2nd integer location
		String num1 = num.substring (position1-1, position1); //Get the first inputted numbers position
		String num2 = num.substring (position2-1, position2); //Get the 2nd inputted numbers position
		System.out.println("Original String: " + num + " New String: " + num.substring(0, position1-1) + num2 + 		num.substring(position1, position2-1) + num1 + num.substring(position2)); 
		/*In the ouput I had the original string first(obviously) and then I used substrings to the number go from the position before the first integer to the position of the first inputted number, then I inserted the 2nd number as a string.  I then continued on string from the number after the position of the new inputted number until the location of the next inputted position.  At this position I inputed the first number string and then continued as I did before until the end of the string. */  
	}
}