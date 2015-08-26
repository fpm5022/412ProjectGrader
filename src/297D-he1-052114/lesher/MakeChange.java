public class MakeChange
{
    public static void main(String[] args)
    {
        double amountDue = Double.parseDouble(args[0]);
        int dueDollars = (int) Math.floor(amountDue);
        int dueCents = (int) Math.round(((amountDue - dueDollars) * 100));
        double amountGiven = Double.parseDouble(args[1]);
        int givenDollars = (int) Math.floor(amountGiven);
        int givenCents = (int) Math.round(((amountGiven - givenDollars) * 100));
        double changeDue = amountGiven - amountDue;
        int changeDollars = (int) Math.floor(changeDue);
        int changeCents = (int) Math.round(((changeDue - changeDollars) * 100));
        int changeQuarters = changeCents / 25;
        int changeDimes = (changeCents % 25) / 10;
        int changeNickels = ((changeCents % 25) % 10) / 5;
        int changePennies = ((changeCents % 25) % 25) % 5;
        
        int centsDue = dueCents - (int) (amountDue);
        double centsDue1 = (centsDue * 1000) / 10;
        System.out.println("Amount Due From Customer = " + dueDollars + " dollars and " +  dueCents + " cents");
        System.out.println("Amount Given by Customer = " + givenDollars + " dollars and " +  givenCents + " cents");
        System.out.println("Amount of Change Due to Customer = " + changeDollars + " dollars and " +  changeCents + " cents");
        System.out.println("Dollars change = " + changeDollars);
        System.out.println("Quarters change = " + changeQuarters);
        System.out.println("Dimes change = " + changeDimes);
        System.out.println("Nickels change = " + changeNickels);
        System.out.println("Pennies change = " + changePennies);
        
    }
}