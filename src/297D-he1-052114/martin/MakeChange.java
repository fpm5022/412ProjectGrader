public class Change
{
	public static void main(String [] args)
	{

		double amountDue = Math.round(67.21*100.00) / 100.00;

		
		
		double amountGiven = Math.round(100.00*100.00) / 100.00;

		double changeDue = Math.round((amountGiven - amountDue)*100.00) / 100.00;
 		
 		int dollChange = 32;
 		int quartChange = 3;
 		int dimeChange = 0;
 		int nickChange = 0;
 		int penChange = 4;

System.out.println("Amount Due From Customer =" + amountDue);
System.out.println("Amount Given by Customer =" + amountGiven);
System.out.println("Amount of Change Due to Customer =" + changeDue);
System.out.println("Dollars Change =" + dollChange);
System.out.println("Quarters Change =" + quartChange);
System.out.println("Dimes Change =" + dimeChange);
System.out.println("Nickels Change =" + nickChange);
System.out.println("Pennies Change =" + penChange);



	}
}