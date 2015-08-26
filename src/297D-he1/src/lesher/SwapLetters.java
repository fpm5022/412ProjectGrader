public class SwapLetters
{
    public static void main(String[] args)
    {
        String str = args[0];
        int position1 = Integer.parseInt(args[1]);
        int pos1 = position1 - 1;
        int position2 = Integer.parseInt(args[2]);
        int pos2 = position2 - 1;
        char[] c = str.toCharArray();
        char temp = c[pos1];
        c[pos1] = c[pos2];
        c[pos2] = temp;
        String swapped = new String(c);
        System.out.println("Original String: " + str);
        System.out.println("New String: " + swapped);
        
    }
}