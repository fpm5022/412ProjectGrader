public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		double amountGiven = Double.parseDouble(args[1]);
		int a = (int) amountDue;
		int b = (int) amountGiven;
		double changeDue = Math.round((amountDue - a)*(100));
		int change1 = (int) changeDue;
		double changeGiven = Math.round((amountGiven - b)*(100));
		int change2 = (int) changeGiven;
		double changeDollars = (amountGiven - amountDue);
		int dollars = (int) changeDollars;
		double changeCents = Math.round((changeDollars - dollars)* (100));
		int cents = (int) changeCents;
		int quarters = cents/25;
		int dimes = (cents - (quarters*25))/10;
		int nickels = (cents - (quarters*25) - (dimes*10))/5;
		int pennies = (cents - (quarters*25) - (dimes*10) - (nickels*5));

		System.out.println("Amount Due From Customer= " + a + " dollars " + change1 + " cents");
		System.out.println("Amount Given by Customer= " + b + " dollars " + change2 + " cents");
		System.out.println("Amount of Change Due to Customer= " + dollars + " dollars " + cents + " cents");
		System.out.println("Dollars change= " + dollars);
		System.out.println("Quarters change= " + quarters);
		System.out.println("Dimes change= " + dimes);
		System.out.println("Nickels change= " + nickels);
		System.out.println("Pennies change= " + pennies);
	}
}