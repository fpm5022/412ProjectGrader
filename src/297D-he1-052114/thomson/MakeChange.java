public class MakeChange
{
	public static void  main(String[] args)
	{
	  double amountDue = Double.parseDouble(args[0]);
	  amountDue = amountDue * 100;
	  double amountGiven = Double.parseDouble(args[1]);
	  amountGiven = amountGiven * 100;

	  double changeDue = amountGiven - amountDue;
	  System.out.println("Amount Due From Customer= " + amountDue);
	  System.out.println("Amount Given by Customer= " + amountGiven);
	  System.out.println("Amount of Change due to Customer= " + changeDue);

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
	  System.out.println("Dolllars change= " + dollars);
	  System.out.println("Quarters change= " + quarters);
	  System.out.println("Dimes change= " + dimes);
	  System.out.println("Nickels change= " + nickels);
	  System.out.println("Pennies change= " + pennies);
	}
}