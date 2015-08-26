import java.lang.Math;

public class MakeChange
{
	public static void main(String[] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		double a = amountDue * 100.00;
		int A = (int) Math.round(a);
		
		int centsDue = A % 100;
		int dollarsDue = (int) (a - ( (int) (a % 100)) ) / 100;

		
		double amountGiven = Double.parseDouble(args[1]);
		double b = amountGiven * 100.00;
		int B = (int) Math.round(b);

		int centsGiven = B % 100;
		int dollarsGiven = (int) (b - ( (int) (b % 100)) ) / 100;
		{
			int C = B - A;
			int centsChange = C % 100;
			int dollarsChange = (C - (centsGiven)) / 100;
			{
				int quartersChange = centsChange / 25;
				int d = centsChange - (quartersChange * 25);
				{
					int dimesChange = d / 20;
					int e = d - (dimesChange * 10);
					{
						int nickelsChange = e / 5;
						int f = e - (nickelsChange * 5);
						{
							int penniesChange = f;





		System.out.println("Amount Due From Customer = " + dollarsDue + " dollars and " + centsDue + " cents");
		System.out.println("Amount Given by Customer = " + dollarsGiven + " dollars and " + centsGiven + " cents");
		System.out.println("Amount of Change Due to Customer = " + dollarsChange + " dollars and " + centsChange + " cents");
		System.out.println("Dollars change = " + dollarsChange);
		System.out.println("Quarters change = " + quartersChange);
		System.out.println("Dimes change = " + dimesChange);
		System.out.println("Nickels change = " + nickelsChange);
		System.out.println("Pennies change = " + penniesChange);
						}
					}
				}
			}
		}
	}
}

		