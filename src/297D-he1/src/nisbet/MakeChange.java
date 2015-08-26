import java.util.Scanner;
public class MakeChange
{
	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Amount due from the customer = ");
		double due = in.nextDouble();
		System.out.println("Dollar amount given = ");
		double given = in.nextDouble();
		
		final int dollar = 100;
		final int quarter = 25;
		final int dime = 10;
		final int nickel = 5;
		final int penny = 1;
		
		int dollarsDue = (int)due;
		double centsDue = due - (int)due;
		int roundDue = (int)Math.round(centsDue * 100.0);
		int dollarsGiven = (int)given;
		double centsGiven = given - (int)given;
		int roundGiven = (int)Math.round(centsGiven * 100.0);
		double dollarsChange = (dollarsGiven - dollarsDue);
		double centsChange = (roundGiven - centsDue);
		
		if (centsGiven == 0 && centsDue > 0)
		{
			dollarsChange = dollarsChange - .5;
			centsChange = (100 - roundDue); 
		}
		
		if (centsGiven < centsDue)
		{
			dollarsChange = dollarsChange - .5;
			centsChange = (100 - roundDue); 
		}
		
		int quartersReturned = ((int)centsChange / quarter);
		int dimesReturned = (((int)centsChange - (quartersReturned * quarter)) / dime);
		int nickelsReturned = ((((int)centsChange - (quartersReturned * quarter)) - (dimesReturned * dime)) / nickel);
		int penniesReturned = (((int)centsChange - (quartersReturned * quarter)) - (dimesReturned * dime) - (nickelsReturned * nickel));
		
		System.out.println(" Amount Due From Customer = " + dollarsDue + " dollars and " + roundDue + " cents ");
		System.out.println(" Amount Given by Customer = " + dollarsGiven + " dollars and " + roundGiven + " cents ");
		System.out.println(" Amount of Change Due to Customer = " + (int)dollarsChange + " dollars and " + (int)centsChange + " cents ");
		System.out.println(" Dollars Returned = " + (int)dollarsChange);
		System.out.println(" Quarters Returned = " + quartersReturned);
		System.out.println(" Dimes Returned = " + dimesReturned);
		System.out.println(" Nickels Returned = " + nickelsReturned);
		System.out.println(" Pennies Returned = " + penniesReturned);
	}

}