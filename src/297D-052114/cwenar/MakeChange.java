public class MakeChange
{
	
	public static void main(String [] args)
	
	{
		
		double amountDue = Double.parseDouble(args[0]);
		
		double amountGiven = Double.parseDouble(args[1]);
		
		int changeDue = (int) ((amountDue - (int) amountDue) * 100);
		
		int changeGiven = (int) ((amountGiven - (int) amountGiven) * 100);
		
		int dollarChange = (int) amountGiven - (int) amountDue;
		
		int changeOwed = 0;
		
		if(changeDue < changeGiven)
			
		{
			
			changeOwed = changeGiven - changeDue;
			
		}
		
		if(changeDue > changeGiven)
			
		{
			
			changeOwed = changeDue - changeGiven;
			
		}
		
		if(changeGiven == 0)
			
		{
			
			changeOwed = 100 - changeDue;
			
		}
		
		int quarterChange, dimeChange, nickelChange;
		
		if(changeOwed >= 25)
		
		{
			
			quarterChange = changeOwed / 25;
			
		}
		
		else
		
		{
			
			quarterChange = 0;
			
		}
		
		int remainingChange = changeOwed - (quarterChange * 25);
		
		if(remainingChange >= 10)
			
		{
			
			dimeChange = remainingChange / 10;
			
		}
		
		else 
			
		{
			
			dimeChange = 0;
			
		}
		
		int moreChange = remainingChange - (dimeChange * 10);
		
		if(moreChange >= 5)
			
		{
			
			nickelChange = moreChange / 5;
			
		}
		
		else
			
		{
			
			nickelChange = 0;
			
		}
		
		int pennyChange = moreChange - (nickelChange * 5);
		
		System.out.println("Customer owes " + amountDue);
		
		System.out.println("Customer gave " + amountGiven);
		
		System.out.println("Amount of change due to the customer: " + dollarChange + " dollars and " + changeOwed + " cents");
		
		System.out.println("Dollar change " + dollarChange);
		
		System.out.println("Quarter change " + quarterChange);
		
		System.out.println("Dime change " + dimeChange);
		
		System.out.println("Nickel change " + nickelChange);
		
		System.out.println("Penny change " + pennyChange);
		
	}
}
