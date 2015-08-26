



public class MakeChange
{
    public static void main(String[] args) 
{
        double amountDue = Double.parseDouble (args [0]);
        double amountGiven = Double.parseDouble (args [1]);

          final int dollar = 100;
          final int quarter = 25;
          final int dime = 10;
          final int nickel = 5;
          final int penny = 1;

         double amountDuePennies =  amountDue * 100;
         int amountInPennies = (int) amountDuePennies * 1;
    
         double amountGivenPennies =  amountGiven * 100;
         int paidInPennies = (int) amountGivenPennies * 1;
        int change = ( paidInPennies - amountInPennies );
        int dollarChange =  change / dollar;
          change = change % dollar;
        int quarterChange = change / quarter;
          change = change % quarter;
        int dimeChange = change / dime;
          change = change % dime;
        int nickelChange = change / nickel;
          change = change % nickel;
        int pennyChange = change / penny;
        System.out.println("Dollars change: " + dollarChange);
        System.out.println("Quaters change: " + quarterChange);
        System.out.println("Dime change: " + dimeChange);
        System.out.println("Nickel change: " + nickelChange);
        System.out.println("Penny change:  " + pennyChange);
    }
}
