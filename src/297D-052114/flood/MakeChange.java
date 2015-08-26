public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args[0]) ;
		double amountGiven = Double.parseDouble(args[1]) ;
		double amountDueD = (int)amountDue ;
		double amountDueC = Math.round((amountDue - amountDueD) * 100.0) ;
		double amountGivenD = (int)amountGiven ;
		double amountGivenC = Math.round((amountGiven - amountGivenD) * 100.0) ;
		double x = (amountGiven - amountDue) * 100 ;
		double changeDue = Math.round(x) / 100.0 ;
		double changeDueD = (int)changeDue ;
		double changeDueC = Math.round((changeDue - changeDueD) * 100.0) ;
		int dollarsChange = (int)changeDueD ;
		int quartersChange = Math.round((int)changeDueC / 25) ;
		double y = changeDueC - (quartersChange * 25) ;
		int dimesChange = Math.round((int)y / 10) ;
		double z = y - (dimesChange * 10) ;
		int nickelsChange = Math.round((int)z / 5) ;
		double aa = z - (nickelsChange * 5) ;
		int penniesChange = Math.round((int)aa / 1) ;
		
		System.out.println("Amount Due From Customer: " + (int)amountDueD + " dollars and " + (int)amountDueC + " cents") ;
		System.out.println("Amount Given by Customer: " + (int)amountGivenD + " dollars and " + (int)amountGivenC + " cents") ;
		System.out.println("Amount of Change Due to Customer: " + (int)changeDueD + " dollars and " + (int)changeDueC + " cents") ;
		System.out.println("Dollars change = " + dollarsChange) ;
		System.out.println("Quarters change = " + quartersChange) ;
		System.out.println("Dimes change = " + dimesChange) ;
		System.out.println("Nickels change = " + nickelsChange) ;
		System.out.println("Pennies change = " + penniesChange) ;
	}
}