public class Dollar 
{

    public static void main(String[] args) 
    {

        double amountDue = Double.parseDouble(args[0]);
        double a = Math.round(amountDue*100.0)/100.0;
        double amountGiven = Double.parseDouble(args[1]);
        double b = (Math.round(amountGiven*100.0)/100.0);
        double amountChange = (Math.round((b-a)*100.0)/100.0);

        int due = (int) (amountDue * 1000) / 10;
        int given = (int) (amountGiven * 1000) / 10;
        int change = (int) (amountChange * 1000) / 10;

        int dueCents = due % 100;
        int givenCents = given % 100;
        int changeCents = change % 100;

        int dueDollars = (int) Math.floor(amountDue);
        int givenDollars = (int) Math.floor(amountGiven);
        int changeDollars = (int) Math.floor(amountChange);

        int dollars = changeDollars;
        int quarters = changeCents / 25;
        changeCents = changeCents-25*quarters;
        
        int dimes = changeCents / 10; 
        changeCents = changeCents-10*dimes;
        
        int nickels = changeCents / 5;
        changeCents = changeCents-5*nickels;
       
        int pennies = changeCents;
        
        System.out.println("Amount Due From Customer = " + dueDollars 
            + " dollars and " 
            + dueCents + " cents");
        System.out.println("Amount Given by Customer = " + givenDollars 
            + " dollars and " 
            + givenCents + " cents");
        System.out.println("Amount of Change Due to Customer = " 
            + changeDollars + " dollars and " 
            + changeCents + " cents");
        System.out.println("Dollars change = " + dollars);
        System.out.println("Quarters change = " + quarters);
        System.out.println("Dimes change = " + dimes);
        System.out.println("Nickels change = " + nickels);
        System.out.println("Pennies change = " + pennies);

    }

}