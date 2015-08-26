public class MakeChange
{
	public static void main(String [] args)
	{
		double amountDue = Double.parseDouble(args[0]);
		double amountGiven = Double.parseDouble(args[1]);
		double amountGiven = Math.round(amountGiven);
		double changeDue = (amountDue - amountGiven);
		int dollarsChange = (int) changeDue;
		double quartersChange = Math.round((changeDue/.25)-(4*dollarsChange));
		double dimesChange = Math.round((changeDue/.1)-(10*dollarsChange)-(2.5*quartersChange));
		double nickelsChange = Math.round((changeDue/.05)-(20*dollarsChange)-(5*quartersChange)-(2*dimesChange));
		double penniesChange = Math.round((changeDue/.01)-(100*dollarsChange)-(25*quartersChange)-(10*dimesChange)-(5*nickelsChange));
	}
}