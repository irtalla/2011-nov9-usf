package com.revature.controller;

import java.io.IOException;
import java.util.Scanner;

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
		
		Application.clearConsole();
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
				System.out.println("Welcome, customer!");
				break; 
			case "EMPLOYEE": 
				Application.employeeController = EmployeeController.getEmployeeController(); 
				System.out.println("Welcome, employee!");
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
		
		System.out.println("Welcome to Generic Bicycle App: the most generic CRUD application");
		System.out.println("Enter 'login' to login, 'register' to register, or 'exit' to clsoe application.");
		Application.login(); 
	}
	
	
	public static void showExitScreen() {
		System.out.println("Thank you for visiting Generic Product Application. We hope you had fun!");
		Application.userInputScanner.close();
	}
	
	
	
	
	public boolean setUserPermissions(Person user) {
		System.out.println(user.getRole().toString());
		return false; 
	}
	
	private boolean setCurrentUser(Person user) {
		
		if ( Application.currentUser == null ) {
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
		
		
		
		// Get user type 
		System.out.println("Login sequence initiated. To begin, by specify whether you are a customer or an employee. \n"
				+ "You may also enter c for customer or e for employee. \n");
		System.out.print("I am a: "); 
		String userType = userInputScanner.nextLine();
		System.out.println(userType);
		
		String userRoles[] = {
				"customer",
				"c",
				"employee",
				"e",
				"manager",
				"m"
		}; 
		
		
		
		
		// Get username 
		System.out.print("Please enter your username: "); 
		String username = userInputScanner.nextLine();
		
		
		// Get password 
		System.out.println("Hi, " + username + ". To finish authentication, please enter your password below");
		System.out.print("Password: "); 
		String password = userInputScanner.nextLine();
		
		System.out.println("Thank you for logging you.");
		
		// The person is a customer by default 
		Application.currentUser = new Person();
		Application.currentUser.getRole().setName("employee");
		Application.initControllers( Application.currentUser.getRole().getName() );  
		
		
	};
	
	
	public static void register() {}; 
	
	
	



}
