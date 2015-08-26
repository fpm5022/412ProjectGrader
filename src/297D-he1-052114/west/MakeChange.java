public class MakeChange{
	public static void main(String[] args){
		//User entered information
		double amountDue = Double.parseDouble(args[0]);
		double amountGiven = Double.parseDouble(args[1]);
		//Converting values
		int centsDue = (int)Math.round(amountDue * 100);
		int centsGiven = (int)Math.round(amountGiven * 100);
		int change = centsGiven - centsDue;
		int changeCustomer = change;
		//Compute
		int dollars = change / 100;
		change %= 100;
		int quarters = change / 25;
		change %= 25;
		int dimes = change / 10;
		change %= 10;
		int nickels = change / 5;
		change %= 5;
		int pennies = change / 1;
		//Output
		System.out.println("");
		System.out.println("Amount due from customer: " + centsDue + " pennies");
		System.out.println("Amount given by customer: " + centsGiven + " pennies");
		System.out.println("Amount of change due to customer: " + changeCustomer + " pennies");
		System.out.println("Dollars change = " + dollars);
		System.out.println("Quarters change = " + quarters);
		System.out.println("Dimes change = " + dimes);
		System.out.println("Nickels change = " + nickels);
		System.out.println("Pennies change = " + pennies);
	}
}