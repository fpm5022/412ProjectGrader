public class MakeChange
{
 public static void main(String [] args)
 {
  double amountDue = Double.parseDouble(args[0]);
  double amountGiven = Double.parseDouble(args[1]);
  int a = (int) amountDue;
  int b = (int) amountGiven;
  double adChange = Math.round((amountDue - a)*(100));
  int change1 = (int) adChange;
  double agChange = Math.round((amountGiven - b)*(100));
  int change2 = (int) agChange;
  double cd = (amountGiven - amountDue);
  int changedollar = (int) cd;
  double cc = Math.round((cd - changedollar)*(100));
  int changecents = (int) cc;
  int quarters = changecents/25;
  int dimes = (changecents - (quarters*25))/10;
  int nickels = (changecents - (quarters*25) - (dimes*10))/5;
  int pennies = (changecents - (quarters*25) - (dimes*10) - (nickels*5));


  System.out.println("Amount Due From Customer= " + a + " dollars " + change1 + " cents" );
  System.out.println("Amount Given by Customer= " + b + " dollars " + change2 + " cents" );
  System.out.println("Amount of Change Due to Customer= " + changedollar + " dollars " + changecents + " cents" );
  System.out.println("Dollars change= " + changedollar);
  System.out.println("Quarters change= " + quarters);
  System.out.println("Dimes change= " + dimes);
  System.out.println("Nickels change= " + nickels);
  System.out.println("Pennies change= " + pennies);
 }
}