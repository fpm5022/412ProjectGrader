public class TestProgram
{
  public static void main(String [] args)
  {
    {
    SwapLetters program = new SwapLetters();

    System.out.println("\n" + "Start SwapLetters Test");
    String [] arg =  new String [3];
    arg[0]="987654321";
    arg[1]="1";
    arg[2]="6";
    program.main(arg);
    System.out.println("End of SwapLetters Test" + "\n");
    }

    {
    MakeChange program = new MakeChange();

    System.out.println("\n" + "Start MakeChange Test");
    String [] arg =  new String [2];
    arg[0]="53.27";
    arg[1]="80";
    program.main(arg);
    System.out.println("End of MakeChange Test" + "\n");
    } 
 
  }
}