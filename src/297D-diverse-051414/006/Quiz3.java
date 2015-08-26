public class Quiz3
{
  public static void main(String [] args)
  {   
    {
    //Question 4
      int k = 1;
      int sum = 0;
      do
      {
        sum += k*k;
        System.out.print(k + " "); 
        k++;       
      }while(k <= 5);
      System.out.println("\n" + "Sum = " + sum);
    }
    
    {
      //Question 5
      double sum = 0.0;
      int number = 0;
      int n = Integer.parseInt(args[0]);

      for(int k = 1; k <= n; k++)
      {
        number = Integer.parseInt(args[k]);
        sum += number;
      }

      System.out.println("Average = " + sum/n);
    }
  }
}

