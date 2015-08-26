public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args [0]);
		double amountGiven = Double.parseDouble(args [1]);
		int amDue = (int) amountDue;
		int amGiven = (int) amountGiven;
		double centsDue = Math.round(100 * (amountDue - amDue));
		double centsGiven = Math.round(100 * (amountGiven - amGiven));
		double changeDue = (amountGiven - amountDue);
		int chDue = (int) changeDue;
		double centsChange = Math.round(100 * (changeDue - chDue));

		final int QUARTER = 25;
		final int DIME = 10;
		final int NICKLE = 5;
		final int PENNY = 1;

		int remaining;
		int quarter = ((int) centsChange / QUARTER);
		remaining = ((int) centsChange - (quarter * QUARTER));
		int dimes = (remaining / DIME);
		remaining = (remaining - (dimes * DIME));
		int nickles = (remaining / NICKLE);
		remaining = (remaining - (nickles * NICKLE));
		int pennies = (remaining / PENNY);
		remaining = (remaining - (pennies * PENNY));


		System.out.println("Amount Due from Customer: " + amDue + " dollars and " + centsDue + " cents ");
		System.out.println("Amount Given by Customer: " + amGiven + " dollars and " + centsGiven + " cents ");
		System.out.println("Amount of Change Due to Customer: " + chDue + " dollars and " + centsChange + " cents ");
		System.out.println("Dollars change: " + chDue);
		System.out.println("Quarters change: " + quarter);
		System.out.println("Dimes change: " + dimes);
		System.out.println("Nickles change: " + nickles);
		System.out.println("Pennies change: " + pennies);
	}
}