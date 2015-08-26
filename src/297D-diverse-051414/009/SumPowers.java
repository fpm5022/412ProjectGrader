public class SumPowers
{
  public static void main(String [] args)
  {
    int n = Integer.parseInt(args[0]);
    
    int sumk = 0;
    int sumk2 = 0;
    int sumk3 = 0;
    int sumk4 = 0;
    
    for(int k = 1; k <= n; k++)
    {
      sumk += k;
      sumk2 += k*k;
      sumk3 += k*k*k;
      sumk4 += k*k*k*k;
    }
    
    System.out.println("n = " + n + "; sumk = " + sumk + "; sumk2 = " + sumk2 + ";  sumk3 = " + sumk3 + "; sumk4 = " + sumk4);
  }
}