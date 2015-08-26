public class MakeChange
{
	public static void main (String [] args)
	{
		double amountDue = Double.parseDouble (args [0]);
		double amountGiven = Double.parseDouble (args [1]);
		
		//Amount Due From Customer
		int dollars_due = (int) amountDue;
		double cents_due = (amountDue - dollars_due);
		double centsDue = (cents_due * 100.0);
		int round_centsDue = Math.round((float)centsDue);
		
		//Amount Given by Customer
		int dollars_given = (int) amountGiven;
		double cents_given = (amountGiven - dollars_given);
		double centsGiven = (cents_given * 100.0);
		int round_centsGiven = Math.round((float)centsGiven);
		
		//Amount of Change Due to Customer
		double amountReturned = (amountGiven - amountDue);
		int dollars_returned = (int) amountReturned;
		double cents_returned = (amountReturned - dollars_returned);
		double centsReturned = (cents_returned * 100.0);
		int round_centsReturned = Math.round((float)centsReturned);
		
		//Change Broken down to Coins
		
		//Quarters
		double quarters = ((cents_returned)/0.25);
		int quarters_returned = (int) quarters;
		int round_quartersReturned = Math.round((float)quarters_returned);
		
		//Leftover Round 1
		double leftover1 = ((cents_returned) - ((round_quartersReturned) * 0.25));
		
		//Dimes
		double dimes = ((leftover1)/0.1);
		int dimes_returned = (int) dimes;
		int round_dimesReturned = Math.round((float)dimes_returned);
		
		//Leftover Round 2
		double leftover2 = ((cents_returned) - ((round_quartersReturned) * 0.25) - ((round_dimesReturned) * 0.1));
		
		//Nickels
		double nickels = ((leftover2)/0.05);
		int nickels_returned = (int) nickels;
		int round_nickelsReturned = Math.round((float)nickels_returned);
		
		//Leftover Round 3
		double leftover3 = ((cents_returned) - ((round_quartersReturned) * 0.25) - ((round_dimesReturned) * 0.1) - ((round_nickelsReturned) * 0.05));
		
		//Pennies
		double pennies = ((leftover3)/0.01);
		int pennies_returned = (int) pennies;
		int round_penniesReturned = Math.round((float)pennies_returned);
		
		
		System.out.println("Amount Due From Customer: " + dollars_due + " dollars and " + round_centsDue + " cents" );
		System.out.println("Amount Given by Customer: " + dollars_given + " dollars and " + round_centsGiven + " cents");
		System.out.println("Amount of Change Due to Customer: " + dollars_returned + " dollars and " + round_centsReturned + " cents");
		System.out.println("Dollars Change: " + dollars_returned);
		System.out.println("Quarters: " + round_quartersReturned);
		System.out.println("Dimes: " + round_dimesReturned);
		System.out.println("Nickels: " + round_nickelsReturned);
		System.out.println("Pennies: " + round_penniesReturned);
		
		
		
	}
}
		
		
		
		