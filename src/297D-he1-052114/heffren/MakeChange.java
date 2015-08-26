public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args [0]);
		double amountGiven = Double.parseDouble(args [1]);

		int x = (int) amountDue;
		int y = (int) amountGiven;

		double change1 = Math.round((amountDue - x)*(100));
		int change2 = (int) change1;
		double change3 = Math.round((amountGiven - y)*(100));
		int change4 = (int) change3;
		double change5 = (amountGiven - amountDue);
		int dollarsChange = (int) change5;
		double change6 = Math.round((change5 - dollarsChange)*(100));

		int centsChange = (int) change6;
		int quartersChange = centsChange/25;
		int dimesChange = (centsChange - (quartersChange*25))/10;
		int nickelsChange = (centsChange - (quartersChange*25) - (dimesChange*10))/5;
		int penniesChange = (centsChange - (quartersChange*25) - (dimesChange*10) - (nickelsChange*5));

		System.out.println("Amount Due From Customer= " + x + " dollars " + change2 + " cents " );
		System.out.println("Amount Given By Customer= " + y + " dollars " + change4 + " cents " );
		System.out.println("Amount of Change Due to Customer= " + dollarsChange + " dollars " + centsChange + " cents " );
		System.out.println("Dollars Change= " + dollarsChange);
		System.out.println("Quarters Change= " + quartersChange);
		System.out.println("Dimes Change= " + dimesChange);
		System.out.println("Nickels Change= " + nickelsChange);
		System.out.println("Pennies Change= " + penniesChange);
	}
}
