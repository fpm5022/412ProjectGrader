public class MakeChange
{
	public static void main(String[]args)
	{
		final int penniesPerDollar = 100;
		final int penniesPerQuarter = 25;
		final int penniesPerDime = 10;
		final int penniesPerNickle = 5;	
		
		double amountDue = Double.parseDouble(args[0]);                         
		double amountGiven = Double.parseDouble(args[1]);
		
		int dueTotal = (int) Math.round(amountDue * penniesPerDollar);          
		int dueGiven = (int) Math.round(amountGiven * penniesPerDollar);
		
		int changeDue = dueGiven - dueTotal;                                    
		int dollarsDue = dueTotal / penniesPerDollar;
		int centsDue = dueTotal % penniesPerDollar;
		int dollarsGiven = dueGiven / penniesPerDollar;
		int centsGiven = dueGiven % penniesPerDollar;
		int changeDollarsDue = changeDue / penniesPerDollar;
		int changeCentsDue = changeDue % penniesPerDollar;
		int quarters = changeCentsDue / penniesPerQuarter;
		int dimes = (changeCentsDue - (penniesPerQuarter * quarters)) / penniesPerDime;
		int nickles = (changeCentsDue - ((penniesPerDime * dimes) + (penniesPerQuarter * quarters))) / penniesPerNickle;
		int pennies = (changeCentsDue - ((penniesPerDime * dimes) + (penniesPerQuarter * quarters) + (penniesPerNickle * nickles))) % penniesPerDollar;
		

		System.out.println();
		System.out.println("Amount Due From Customer = " + dollarsDue + " dollars and " + centsDue + " cents");
		System.out.println("Amount Given by Customer = " + dollarsGiven + " dollars and " + centsGiven + " cents");
		System.out.println("Amount of Change due to Customer = " + changeDollarsDue + " dollars and " + changeCentsDue + " cents");
		System.out.println("Dollars change = " + changeDollarsDue);
		System.out.println("Quarters change = " + quarters);
		System.out.println("Dimes change = " + dimes);
		System.out.println("Nickels change = " + nickles);
		System.out.println("Pennies change = " + pennies);
	}
}