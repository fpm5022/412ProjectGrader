public class MakeChange {
	public static void main(String [] args) {
	int amountDue = (int) (Math.round(100.0*Double.parseDouble(args[0])));
	int amountGiven = (int) (Math.round(100.0*Double.parseDouble(args[1])));

/*	These are just debugging statements to diagnose rounding issue when
	converting from double dollar amounts to integer cents amounts
*/
	System.out.println(args[0] + " " + args[1]);
	System.out.println(amountDue + " " + amountGiven);

	int changeDue = amountGiven-amountDue;
	if(changeDue < 0) {
		System.out.println("Customer Owes More Money - Note Change Due is NEGATIVE!");
	}
	int dollars = changeDue/100;
	int cents = changeDue%100;
	int remainder = cents;
	int quarters = remainder/25;
	remainder = remainder%25;
	int dimes = remainder/10;
	remainder = remainder%10;
	int nickels = remainder/5; 
	int pennies1 = remainder%5;
	int pennies2 = changeDue%5;
	System.out.println("Amount Due From Customer = " + amountDue/100 + " dollars and " + amountDue%100 + " cents");
	System.out.println("Amount Given by Customer = " + amountGiven/100 + " dollars and " + amountGiven%100 + " cents");
	System.out.println("Amount of Change Due to Customer = " + dollars + " dollars and " + cents + " cents");
	System.out.println("Dollars change = " + dollars);
	System.out.println("Quarters change = " + quarters);
	System.out.println("Dimes change = " + dimes);
	System.out.println("Nickels change = " + nickels);
	System.out.println("First Pennies Change Calculation = " + pennies1);
	System.out.println("Second Pennies Change Calculation = " + pennies2);
	}
}