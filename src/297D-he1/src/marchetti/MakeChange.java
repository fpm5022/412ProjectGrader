public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		double amountGiven = Double.parseDouble(args[1]);
		
		double dollarsDue = (int) amountDue;
		double centsDue = (amountDue%1)*100;
		double dollarsGiven = (int) amountGiven;
		double centsGiven = (amountGiven%1)*100;
		
		double dollarsChange1 = (int) (amountGiven-amountDue);
		double centsChange = ((amountGiven-amountDue)%1)*100;
		
		double dollarsChange2 = (int) dollarsChange1;
		double quartersChange = (int) (centsChange/25);
		double dimesChange = (int) (centsChange-(quartersChange*25))/10;
		double nickelsChange = (int) (centsChange-(quartersChange*25)-(dimesChange*10))/5;
		double penniesChange = (int) (centsChange-(quartersChange*25)-(dimesChange*10)-(nickelsChange*5));
		
		
		System.out.print("Amount Due From Customer = ");
		System.out.printf("%.0f", dollarsDue);
		System.out.print(" dollars and ");
		System.out.printf("%.0f", centsDue);
		System.out.print(" cents");
		System.out.println();
		System.out.print("Amount Given by Customer = ");
		System.out.printf("%.0f", dollarsGiven);
		System.out.print(" dollars and ");
		System.out.printf("%.0f", centsGiven);
		System.out.print(" cents");
		System.out.println();
		System.out.print("Amount of Change Due to Customer = ");
		System.out.printf("%.0f", dollarsChange1);
		System.out.print(" dollars and ");
		System.out.printf("%.0f", centsChange);
		System.out.print(" cents");
		System.out.println();
		System.out.print("Dollars change = ");
		System.out.printf("%.0f", dollarsChange2);
		System.out.println();
		System.out.print("Quarters change = ");
		System.out.printf("%.0f", quartersChange);
		System.out.println();
		System.out.print("Dimes change = ");
		System.out.printf("%.0f", dimesChange);
		System.out.println();
		System.out.print("Nickels change = ");
		System.out.printf("%.0f", nickelsChange);
		System.out.println();
		System.out.print("Pennies change = ");
		System.out.printf("%.0f", penniesChange);
		System.out.println();
	}
}
