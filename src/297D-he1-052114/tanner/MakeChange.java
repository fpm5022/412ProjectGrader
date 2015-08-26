public class MakeChange
{
	public static void main(String [] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		double amountGiven = Double.parseDouble(args[1]);

		int centsDue = (int) Math.round(amountDue * 100);
		int centsGiven = (int) Math.round(amountGiven * 100);
		int centsOwed = centsGiven - centsDue;

		int dollars = 0;
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		int pennies = 0;

		while(centsOwed >= 100)
		{
			dollars++;
			centsOwed = centsOwed - 100;
		}

		while(centsOwed >= 25)
		{
			quarters++;
			centsOwed = centsOwed - 25;
		}

		while(25 > centsOwed & centsOwed >= 10)
		{
			dimes++;
			centsOwed = centsOwed - 10;
		}

		while(10 > centsOwed & centsOwed >= 5)
		{
			nickels++;
			centsOwed = centsOwed - 5;
		}

		while(5 > centsOwed & centsOwed >= 1)
		{
			pennies++;
			centsOwed = centsOwed - 1;
		}

		int dollarsDue = (int) centsDue / 100;
		int newCentsDue = (int) centsDue - (dollarsDue * 100);

		int dollarsGiven = (int) centsGiven / 100;
		int newCentsGiven = (int) centsGiven - (dollarsGiven * 100);

		int dollarsOwed = (int) (centsGiven - centsDue) / 100;
		int newCentsOwed = (int) (centsGiven - centsDue) - (dollarsOwed * 100);



		System.out.println("Amount Due From Customer: " + dollarsDue + " dollars and " + newCentsDue + " cents");
		System.out.println("Amount Given by Customer: " + dollarsGiven + " dollars and " + newCentsGiven + " cents");
		System.out.println("Amount of Change Due to Customer: " +  dollarsOwed + " dollars and " + newCentsOwed + " cents");
		System.out.println("Dollars change: " + dollars);
		System.out.println("Quarters change: " + quarters);
		System.out.println("Dimes change: " + dimes);
		System.out.println("Nickels change: " + nickels);
		System.out.println("Pennies change: " + pennies);

	}
}