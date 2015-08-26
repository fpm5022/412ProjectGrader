/**
   Program to compute the change due in whole dollars, quarters, dimes,
   nickles, and pennies based on the amount due and amount given from
   customer.
*/

public class MakeChange {
  public static void main(String[] args) {

    double amountDue =  Double.parseDouble(args[0]);
    double amountGiven =  Double.parseDouble(args[1]);

    final int PENNIES_PER_DOLLAR = 100;
    final int PENNIES_PER_QUARTER = 25;
    final int PENNIES_PER_DIME = 10;
    final int PENNIES_PER_NICKEL = 5;

    // Amount due from customer
    int amountDueDollars = (int) amountDue; 
    int amountDueCents = (int) Math.round((amountDue * PENNIES_PER_DOLLAR))
      % PENNIES_PER_DOLLAR;
    
    // Amount given by customer
    int amountGivenDollars = (int) amountGiven;
    int amountGivenCents = (int) Math.round((amountGiven * PENNIES_PER_DOLLAR))
      % PENNIES_PER_DOLLAR;
    
    // Change due
    double changeDue = (amountGiven - amountDue);
    int amountChangeDollars = (int) changeDue;
    int amountChangeCents = (int) Math.round((changeDue * PENNIES_PER_DOLLAR))
      % PENNIES_PER_DOLLAR;
    
    // Quarters due
    int amountDueQuarters = (int) Math.round(amountChangeCents / PENNIES_PER_QUARTER);
    int amountAfterQuarters = amountChangeCents - amountDueQuarters
      * PENNIES_PER_QUARTER;

    // Dimes due
    int amountDueDimes = (int) Math.round(amountAfterQuarters / PENNIES_PER_DIME);
    int amountAfterDimes = amountAfterQuarters - amountDueDimes
      * PENNIES_PER_DIME;

    // Nickels Due
    int amountDueNickels = (int) Math.round(amountAfterDimes / PENNIES_PER_NICKEL);
    int amountAfterNickels = amountAfterDimes - amountDueNickels
      * PENNIES_PER_NICKEL;

    // Pennies Due
    int amountDuePennies = (int) amountAfterNickels;

    // lets print out our information 
    System.out.println("Amount Due From Customer = " + amountDueDollars
        + " dollars and " + amountDueCents + " cents");
    System.out.println("Amount Given by Customer = " + amountGivenDollars
        + " dollars and " + amountGivenCents + " cents");
    System.out.println("Amount of Change Due to Customer = " + amountChangeDollars
        + " dollars and " + amountChangeCents + " cents");
    System.out.println("Dollars change = " + amountChangeDollars);
    System.out.println("Quarters change = " + amountDueQuarters);
    System.out.println("Dimes change = " + amountDueDimes);
    System.out.println("Nickels change = " + amountDueNickels);
    System.out.println("Pennies change = " + amountDuePennies);

  }

}
