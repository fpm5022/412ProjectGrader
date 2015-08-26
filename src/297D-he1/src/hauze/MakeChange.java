public class MakeChange
{
	public static void  main(String[] args)
	{
	 	double amountDue = Double.parseDouble(args[0]);
	  	double amountGiven = Double.parseDouble(args[1]);
	  	amountDue = (int) Math.round(amountDue * 100);
	  	amountGiven = (int) Math.round(amountGiven * 100);

	  	double changeDue = amountGiven - amountDue;
	  	double dollar = changeDue / 100;
	  	int dollars = (int) dollar;
	  	double quarter = (changeDue % 100) / 25;
	  	int quarters = (int) quarter;
	  	double dime = ((changeDue % 100) % 25) / 10;
      	int dimes = (int) dime;
	  	double nickel = (((changeDue % 100) % 25) % 10) / 5;
	  	int nickels = (int) nickel;
	  	double penny = ((((changeDue % 100) % 25) % 10) % 5) / 1;
	  	int pennies = (int) penny;

	   	int amountDueDollars =((int) amountDue / 100);
	   	int amountDueCents = ((int) amountDue % 100); 
	   	int amountGivenDollars = ((int)amountGiven / 100);
	   	int amountGivenCents = ((int)amountGiven % 100);  



	  	System.out.println("Amount Due From Customer= " + amountDueDollars + " dollars and " + amountDueCents + " cents"); 
	  	System.out.println("Amount Given by Customer= " + amountGivenDollars + " dollars and " + amountGivenCents + " cents");  
	  	System.out.println("Amount of Change due to Customer= " + dollars + " dollars and " + pennies + " cents"); 
	  	System.out.println("Dolllars change= " + dollars);
	  	System.out.println("Quarters change= " + quarters);
	  	System.out.println("Dimes change= " + dimes);
	  	System.out.println("Nickels change= " + nickels);
	  	System.out.println("Pennies change= " + pennies);
	}
}