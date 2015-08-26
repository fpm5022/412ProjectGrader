class MakeChange {
	public static void main(String[] args) {
		double amtDue = Double.parseDouble(args[0]); //Get user input
		double amtGiven = Double.parseDouble(args[1]); 
		int cents1 = (int)Math.round(amtDue*100); //Change cents amt using Math.round multiplied by 100 so no decimals
		int dollarsChange; //General ints that will be used later on in program
		int changeGiven;
		cents1 = cents1%100; //Using modulus we can get the acutal cents rather than say 10000 for 100.00 dollars
		int cents2 = (int)Math.round(amtGiven*100); //Same as cents one but with the given money
		cents2 = cents2%100; //same as finding cents1 from before
		System.out.println("The amount due from customer = "+(int)amtDue + " Dollars and "+ cents1 + " cents.");
		System.out.println("Amount given by customer = " + (int)amtGiven + " Dollars and " + cents2 + " cents.");
		if (cents2 < cents1) { //need an if then statement depending one whether the given cents is < amt cents
		  dollarsChange=(int)amtGiven - (int)amtDue-1; //if it is less we need to take a dollar off the amtdue
		  changeGiven = 100 -(Math.abs(cents2 - cents1)); //basically transfering the dollar to 100cts and calc
		} else {
		    dollarsChange=(int)amtGiven - (int)amtDue; //if it is cents2 >= cents1 we don't need to subtract a doll
		    changeGiven = (cents2 - cents1); //then easily calculate the remaining cents from transaction
		}
		
		System.out.println("Amount of change due to customer = " + dollarsChange + " Dollars and " + changeGiven + 
		" cents."); //output the change amt to the user 
		int quartersChange = changeGiven/25; //Divide changegiven by coinamt to find cointot amt
		changeGiven %= 25; //mod change by coinamt to figure out how much change is left
		int dimeChange = changeGiven/10; //rinse and repeat for every coinamt
		changeGiven %= 10;
		int nickelChange = changeGiven/5;
		changeGiven %= 5;
		int pennyChange = changeGiven/1;
		changeGiven %= 1;
		System.out.println("Dollars change = " + dollarsChange); //Output each change amt
		System.out.println("Quarters change = " + quartersChange);
		System.out.println("Dimes change = " + dimeChange);
		System.out.println("Nickels change = " + nickelChange);
		System.out.println("Penny change = " + pennyChange);
	}
}