public class MakeChange
{
	public static void main(String [] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		int centsDue = (int) Math.round(amountDue*100);
		double amountGiven = Double.parseDouble(args[1]);
		int centsGiven = (int) Math.round(amountGiven*100);
		System.out.printf("Amount Due From Customer = %d dollars and %d cents \n", (centsDue / 100), (centsDue % 100));
		System.out.printf("Amount Given By Customer = %d dollars and %d cents \n", (centsGiven / 100), (centsGiven % 100));
		int changeDue = centsGiven - centsDue;
		System.out.printf("Amount of Change Due to Customer = %d dollars and %d cents \n", (changeDue / 100), (changeDue % 100));
		int dollarsDue = changeDue / 100;
		changeDue = changeDue - (dollarsDue*100);
		int quartersDue = changeDue / 25;
		changeDue = changeDue - (quartersDue*25);
		int dimesDue = changeDue / 10;
		changeDue = changeDue - (dimesDue*10);
		int nickelsDue = changeDue / 5;
		changeDue = changeDue - (nickelsDue*5);
		int penniesDue = changeDue;
		System.out.printf("Dollars change = %d \nQuarters change = %d \nDimes change = %d \nNickels change = %d \nPennies change = %d \n", dollarsDue, quartersDue, dimesDue, nickelsDue, penniesDue);
		
	}
}