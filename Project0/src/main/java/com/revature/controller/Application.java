package com.revature.controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.beans.Role;
import com.revature.beans.Person;
import com.revature.services.*;


/*
 * 
 * We can decompose application functionality into four interfaces:
 * 
 * personInterface
 * customerInterface
 * employeeInterface
 * managerInterface
 * 
 * We could then have four controllers, each of which implements an interface: 
 * personController
 * customerController
 * employeeController
 * managerController 
 * 
 * 
 *  DataFlow Graph
 * 
 * Application -> Controller -> Service -> DAO -> DB
 * 			^			|						   |
 * 			|  			|						   |
 * 			|			-----					   |
 * 			|				|					   |
 * 			|				V					   v
 * 		  STDIN <- User <- STDOUT <- Service  <-  DAO
 */

interface UserInterface {
	public void login(); 
	public void register();
}



public class Application {
	
	private static Application application =  null; 
	private static Person currentUser = null; 
	static Scanner userInputScanner = null;
	private static boolean isRunning = false; 
	
	
	
	private static CustomerController customerController = null; 
	private static EmployeeController employeeController = null; 

	
	private static PersonService personServ = new PersonServiceImpl(); 
	
	
	public static CustomerController getCustomerController() {
		return customerController;
	}

	public static void setCustomerController(CustomerController customerController) {
		Application.customerController = customerController;
	}

	public static EmployeeController getEmployeeController() {
		return employeeController;
	}

	public static void setEmployeeController(EmployeeController employeeController) {
		Application.employeeController = employeeController;
	}

	private Application() {
		Application.userInputScanner = new Scanner(System.in);
		Application.setRunning(true);
	}
	
	public static Application getApplication() {
		
		if ( Application.application == null ) {
			Application.application = new Application();   			
		}
		
		return Application.application; 
	}
		
	
	/* ---------------------------------------------------------------------------------*/
	
	public static void init() {
		
		Application.showWelcomeScreen();
		Application.run();
	}
	
	
	/**
	 * 
	 * @param userType : They type of user being signed in (i.e., customer, employee)
	 * This methods initializes specific controllers based on the credentials of the
	 * user. This is a security measure. 
	 */
	public static void initControllers(String userType) {
		
		switch (userType.toUpperCase()) {
			case "CUSTOMER": 
				Application.customerController = CustomerController.getCustomerController();  
				System.out.printf("Welcome, %s! A truly-valued customer!\n\n", 
						Application.getCurrentUser().getUsername() );
				break; 
			case "EMPLOYEE": 
				Application.employeeController = EmployeeController.getEmployeeController(); 
				System.out.printf("Welcome, %s! Our shop doesn't work if you don't!\n\n", 
						Application.getCurrentUser().getUsername() );
				break; 
			default: 	
				System.out.println("Uh oh : something when wrong!"); 
		} 
	}
	
	
	public static boolean isRunning() {
		return isRunning;
	}
	
	private static void run() {
		while (Application.isRunning) {
			Application.getUserResponse();
		}
	}
	
	private static void setRunning(boolean isRunning) {
		Application.isRunning = isRunning;
	}
	
	
	
	static void getUserResponse() {
		
		System.out.print("Enter input here: "); 
		String userResponse = Application.userInputScanner.nextLine();
		
		String userRoleName = Application.getCurrentUser().getRole().getName(); 
		switch( userRoleName.toUpperCase() ) {
		case "CUSTOMER": 
			CustomerController.dispatchUserActionBasedOnResponse(userResponse);
			break; 
		case "EMPLOYEE":
			EmployeeController.dispatchUserActionBasedOnResponse(userResponse);
			break; 
		}
	}
	
	static String getUserInput() {
		return Application.userInputScanner.nextLine();
	}
	/**
	 * Clears the console
	 */
	static void clearConsole() {
		
		try {
			Runtime.getRuntime().exec("clear");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Shows the welcome screen 
	 */
	public static void showWelcomeScreen() {
		Application.clearConsole();
		System.out.println("Welcome to Generic Bicycle App: the most generic CRUD application");
		System.out.println("Enter 'login' to login, 'register' to register, or 'exit' to clsoe application.");
		
		
		String input = userInputScanner.nextLine();
		
		switch ( input.toUpperCase() ) {
			case "LOGIN": 
				Application.login();
				break; 
			case "REGISTER":
				Application.register();
				break;
			case "EXIT":
				Application.exit();
			default: 
				System.out.printf("Sorry, I don't understand what '%s' means. Let's try again...\n", input);
				Application.showWelcomeScreen();
				break;
		}
		
	}
	
	
	public static void exit() {
		Application.userInputScanner.close();
		Application.showWelcomeScreen();
	}
	
	public static void showExitScreen() {
		System.out.println("Thank you for visiting Generic Product Application. We hope you had fun!");
	}
	
	
	public static <T> void printToScreen(T object) {
		System.out.println( object.toString() ); 
	}
	
	public static <T> void displayData( Set<T> objects) {
		objects.forEach( object -> printToScreen(object) );
	}
	
	private static boolean setCurrentUser(Person user) {
		
		if ( user == null ) {
			return false;
		} else {
			Application.currentUser = user;
			return true; 
		}
	}
	
	public static Person getCurrentUser() {
		return Application.currentUser; 
	}
	
	
	
	public static void login() {
		
		while (true) {
			System.out.print("Enter username: ");
			String username = userInputScanner.nextLine();
			System.out.print("Enter password: ");
			String password = userInputScanner.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				Application.setCurrentUser(user);
				Application.initControllers( Application.currentUser.getRole().getName() );
				break;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 'yes' for yes, other for 'no' for no.");
			String input = userInputScanner.nextLine();
			if (input.equalsIgnoreCase("no")) {
				break;
			}
		}
	};
	
	
	public static void register() {
		
		while (true) {
			Person newAccount = new Person();
			System.out.print("Enter a username: ");
			newAccount.setUsername( userInputScanner.nextLine() );
			System.out.print("Enter a password: ");
			newAccount.setPassword( userInputScanner.nextLine() );

			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("Enter 'yes' to confirm, 'no' to start over, other to cancel");
			String input = userInputScanner.nextLine();
			switch ( input.toUpperCase() ) {
			case "YES":
				try {
					Application.setCurrentUser( personServ.addPerson(newAccount) ); 
					Application.initControllers( Application.currentUser.getRole().getName() );
					return; 
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "NO":
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return; 
			}
			
		}

		
		
	}; 
	
	
	



}
