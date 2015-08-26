
public class MakeChange
{
	public static void main(String [] args)
	{
		
		
		//Assigning data types to input
		double due = Double.parseDouble(args[0]);
		double given = Double.parseDouble(args [1]);
		
		//Assigning Values to Change Variables
		final int dollar = 100;
		final int quarter = 25;
		final int dime = 10;
		final int nickel = 5;
		final int pennie = 1;
		
		//Seperating dollars and cents
		int dollarsDue = (int)due;
		double centsDue = due-(int)due;
		int roundDue = (int)Math.round(centsDue * 100);
		
		int dollarsGiven = (int)given;
		double centsGiven = given - (int)given;
		int roundGiven = (int)Math.round(centsGiven * 100);
		
		//Calculating change returned
		double dollarsChange = (dollarsGiven - dollarsDue);
		double centsChange = (roundGiven - centsDue);
		
		//Troubleshooting Potential Scenarios
		if (centsGiven == 0 && centsDue > 0)
		{
			dollarsChange = dollarsChange - 1;
			centsChange = (100 - roundDue); 
		}
		
	
		//Dividing up the Change Between Coins
		
		int quartersReturned = ((int)centsChange / quarter);
		int dimesReturned = (((int)centsChange - (quartersReturned * quarter)) / dime);
		int nickelsReturned = ((((int)centsChange - (quartersReturned * quarter)) - (dimesReturned * dime)) / nickel);
		int penniesReturned = (((int)centsChange - (quartersReturned * quarter)) - (dimesReturned * dime) - (nickelsReturned * nickel));
		
		//Printing out Results
		System.out.println("Amount Due From Customer= " + dollarsDue + " dollars and " + roundDue + " cents");
		System.out.println("Amount Given by Customer= " + dollarsGiven + " dollars and " + roundGiven + " cents");
		System.out.println("Amount of Change Due to Customer= " + (int)dollarsChange + " dollars and " + (int)centsChange + " cents");
		System.out.println("Dollars Returned= " + (int)dollarsChange);
		System.out.println("Quarters Returned= " + quartersReturned);
		System.out.println("Dimes Returned= " + dimesReturned);
		System.out.println("Nickels Returned= " + nickelsReturned);
		System.out.println("Pennies Returned= " + penniesReturned);
	}

}