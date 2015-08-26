public class MakeChange
{
  public static void main(String [] args)
  {
    double AmountDue = Double.parseDouble(args[0]);
    double AmountGiven = Double.parseDouble(args[1]);
    int a = (int) AmountDue;
    int b = (int) AmountGiven;
    double adChange = Math.round((AmountDue - a )*(100));
    int change1 = (int) adChange;
    double agChange = Math.round((AmountGiven - b )*(100));
    int change2 = (int) agChange;
    double cd = (AmountGiven - AmountDue);
    int dollar = (int) cd;
    double cc = Math.round((cd - dollar)*(100));
    int cents = (int) cc;
    int Q = cents/25;
    int D = (cents - (Q*25))/10;
    int N = (cents - (Q*25) - (D*10))/5;
    int P = (cents - (Q*25) - (D*10) - (N*5));

    System.out.println("Amount Due From Customer = " + a + " dollars " + change1 + " cents" );
    System.out.println("Amount Given by Customer = " + b + " dollars " + change2 + " cents" );
    System.out.println("Amount of Change Due to Customer = " + dollar + " dollars " + cents + " cents" );
    System.out.println("Dollars Change = " + dollar );
    System.out.println("Quarters Change = " + Q );
    System.out.println("Dimes change = " + D );
    System.out.println("Nickels change = " + N );
    System.out.println("Pennies change = " + P );
  }
}