package ProjectZero;

import java.io.*;

public class MainScreen {

	public static void main(String[] args) {
		BufferedReader  readKeyBoard = new BufferedReader(new InputStreamReader(System.in)); 
        int             selection = 9;
        boolean         exit = false;

        while(!exit) {
        	System.out.println("Welcome to the Bicycle Shop.");
            System.out.println("\nPlease login. Enter a number associated with the following options to continue.");
            System.out.println("\n1. Customer");
            System.out.println("\n2. Employee");
            System.out.println("\n\n\n0. Exit Application");
            
            try {
    			selection = Integer.parseInt(readKeyBoard.readLine());
    		} catch (NumberFormatException e) {
    			System.out.print("\n ***** Please enter 1, 2, or 0 only. ****** \n");
    			//e.printStackTrace();
    		} catch (IOException e) {
    			//e.printStackTrace();
    		}
            
            switch (selection) {
            	case 1:
            			break;
            	case 2:
            			break;
            	case 0: exit = true;
            			break;
            	default:System.out.println("Invalid input, ignoring.");
            			break;
            }
        }
        
	}

}
