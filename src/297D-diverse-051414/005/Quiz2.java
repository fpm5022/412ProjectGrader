public class Quiz2
{
    public static void main(String [] args)
    {
        {
            int a = 0; int b = 1; int c = 2;
            if(b > a)
                System.out.println("Inside outer if-statement");
                if(c < b)
                    System.out.println("Inside inner if statement");
            else
                System.out.println("Inside inner else statement");
            }
        
        {
            boolean a = false; 
            if(a=true) 
            {
                System.out.println("Boo Hoo");
            }
            else 
            {
                System.out.println("La La La");
            }
        }
        
        {
        double a = 2.0;  double b = 3.0;
        System.out.println("(a > b) = " + (a > b) + "; ((1/3) + (1/3) == (a/b)) = " + (((1/3) + (1/3)) == (a/b)));
        
        boolean x = true;  boolean y = false;  boolean z = true;
        System.out.println("(x | y) = " + (x | y) + "; ((x && y) | z) = " + ((x && y) | z));
        }

    }
}