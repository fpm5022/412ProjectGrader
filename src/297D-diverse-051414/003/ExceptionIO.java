import java.io.*;
import java.util.Scanner;

public class ExceptionIO {

    public static void main(String[] args) {

        errorCaller();
        helloReader();
        helloWriter();
        helloReader();
    }
    
    public static void helloReader(){
        String fileName = "HelloText.txt";
        String helloString;
        try{
            File inputFile = new File(fileName);
            Scanner in = new Scanner(inputFile);
            helloString = in.nextLine();
            System.out.println(helloString);
            in.close();
            
        }catch(IOException e){
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
        }
    }
    
    
    public static void helloWriter(){
        String helloString = "Hello World!";
        String fileName = "HelloText.txt";
        try{
            PrintWriter out = new PrintWriter(fileName);
            out.println(helloString);
            out.close();
        }catch(Exception e){
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
        }
    }
      
    public static void errorCaller(){
        try{
            errorThrower();
        }catch(Exception e){
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
        }finally{
            
        }
    }
     
    public static void errorThrower() throws Exception{
        boolean somethingBad = false;
        // Various things happen here
        somethingBad = true;
        if(somethingBad){
            throw new Exception("Something bad happened!");
        }
    }
    
    
}