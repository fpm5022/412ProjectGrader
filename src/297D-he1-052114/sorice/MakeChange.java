public class MakeChange
{
	public static void main (String [] args)
	{
			double amountDue = Double.parseDouble (args [0]);
			double amountGiven = Double.parseDouble (args[1]);

			int PENNIES_PER_DOLLAR = 100;
			int PENNIES_PER_QUARTER = 25;
			int PENNIES_PER_DIME = 10;
			int PENNIES_PER_NICKEL = 5;
			int PENNIES_PER_PENNY = 1;

			int changeDue = (int) Math.floor(PENNIES_PER_DOLLAR * (amountGiven - amountDue));
			int dueDollars = (int) amountDue;
			int a = (int) amountDue %100;
			double b = Math.round((amountDue - a)*100);
			int dueCents = (int) b;

			int givenDollars = (int) amountGiven;
			int givenCents = (int) amountGiven %100;

			int dollarCoins = changeDue / PENNIES_PER_DOLLAR;
			changeDue = changeDue % PENNIES_PER_DOLLAR;
			int quarterCoins = changeDue / PENNIES_PER_QUARTER;
			changeDue = changeDue % PENNIES_PER_QUARTER;
			int dimeCoins = changeDue / PENNIES_PER_DIME;
			changeDue = changeDue % PENNIES_PER_DIME;
			int nickelCoins = changeDue / PENNIES_PER_NICKEL;
			changeDue = changeDue % PENNIES_PER_NICKEL;
			int pennyCoins = changeDue / PENNIES_PER_PENNY;
			changeDue = changeDue % PENNIES_PER_PENNY;

			int totalCents = (quarterCoins*PENNIES_PER_QUARTER + dimeCoins*PENNIES_PER_DIME + nickelCoins*PENNIES_PER_NICKEL + pennyCoins*PENNIES_PER_PENNY);

			System.out.println("Amount Due From Customer = " + dueDollars + " dollars and " + dueCents + " cents ");
			System.out.println("Amount Given by Customer = " + givenDollars + " dollars and " + givenCents + " cents ");
			System.out.println("Amount of Change Due to Customer = " + dollarCoins + " dollars and " + totalCents + " cents");
			System.out.println("Dollars change = " + dollarCoins );
			System.out.println("Quarters change = " + quarterCoins);
			System.out.println("Dimes change = " + dimeCoins);
			System.out.println("Nickels change = " + nickelCoins);
			System.out.println("Pennies change = " + pennyCoins);


	}
}