public class SwapLetters
{
	public static void main(String[]args)
	{
		String str = (args[0]);
		int position1 = Integer.parseInt(args[1]);
		int position2 = Integer.parseInt(args[2]);
		
	    char spot1 = str.charAt(0);
		char spot2 = str.charAt(1);
		char spot3 = str.charAt(2);		
		char spot4 = str.charAt(3);
	    char spot5 = str.charAt(4);
		char spot6 = str.charAt(5);
		char spot7 = str.charAt(6);
		char spot8 = str.charAt(7);
		char spot9 = str.charAt(8);
		
		int spotVal1 = 0;
		int spotVal2 = 1;
		int spotVal3 = 2;
		int spotVal4 = 3;
		int spotVal5 = 4;
		int spotVal6 = 5;
		int spotVal7 = 6;
		int spotVal8 = 7;
		int spotVal9 = 8;

		position1 =  position1--;
		position2 =  position2--;
		
		if(position1 == spotVal1)
		{
			position1 = spot1;
		}
		else if(position1 == spotVal2)
		{
			position1 = spot2;
		}
		else if(position1 == spotVal3)
		{
			position1 = spot3;
		}
		else if(position1 == spotVal4)
		{
			position1 = spot4;
		}
		else if(position1 == spotVal5)
		{
			position1 = spot5;
		}
		else if(position1 == spotVal6)
		{
			position1 = spot6;
		}
		else if(position1 == spotVal7)
		{
			position1 = spot7;
		}
		else if(position1 == spotVal8)
		{
			position1 = spot8;
		}
		
		if(position2 == spotVal1)
		{
			position2 = spot1;
		}
		else if(position2 == spotVal2)
		{
			position2 = spot2;
		}
		else if(position2 == spotVal3)
		{
			position2 = spot3;
		}
		else if(position2 == spotVal4)
		{
			position2 = spot4;
		}
		else if(position2 == spotVal5)
		{
			position2 = spot5;
		}
		else if(position2 == spotVal6)
		{
			position2 = spot6;
		}
		else if(position2 == spotVal7)
		{
			position2 = spot7;
		}
		else if(position2 == spotVal8)
		{
			position2 = spot8;
		}
		
		
		String strPos1 = str.replace((char) position1, (char) position2);
		
		String strPos2 = str.replace((char) position2, (char) position1);
		
		
		System.out.println();
		System.out.println("Old String: " + str);	
		System.out.println("New String: " + strPos2);
	}
}
		