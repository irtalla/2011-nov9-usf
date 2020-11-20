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
 * 
 */
interface UserInterface {
	public void login(); 
	public void register();
}



public class Application {
	
	private static Application application =  null; 
	private static Person currentUser = null; 
	private static Scanner userInputScanner = null;
	private static boolean isRunning = false; 
	
	private static ProductService productService = null; 
	private static PersonService userService = null; 
	private static OfferService offerService = null; 
	
	private static enum customerActions{GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS};
	private static enum employeeActions{ADD_PRODUCT, REMOVE_PRODUCT, ACCEPT_OFFER, REJECT_OFFER, VIEW_ALL_PAYMENTS}; 
	
	private static String[] customerCommandDescriptions = {
			"GET_PRODUCTS	-  view the available products",
			"MAKE_OFFER [product id]	- make an offer for a bicycle",
			"VIEW_MY_PRODUCTS	- view the bicycles that I own",
			"VIEW_MY_PAYMENTS [product id]	- view all payments",
			"/n/n"
	};
	
	
	
	
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
	}
	
	
	/**
	 * 
	 * @param userType : They type of user being signed in (i.e., customer, employee)
	 * This methods initializes specific controllers based on the credentials of the
	 * user. This is a security measure. 
	 */
	private static void initControllers(String userType) {
		
		switch (userType.toUpperCase()) {
			case "CUSTOMER": 
				Application.productService = new ProductServiceImpl(); 
				System.out.println("Welcome, customer!");
			case "MANAGER":
			case "EMPLOYEE": 
				Application.offerService = new OfferServiceImpl(); 
				Application.userService = new PersonServiceImpl(); 
				System.out.println("Welcome, employee!");
				break; 
			default: 			
		}

		Application.run(); 
	}
	
	
	public static boolean isRunning() {
		return isRunning;
	}
	
	private static void run() {
		while (Application.isRunning) {
			Application.getUserResponse();
		}
	}
	
	private static void printHelpScreen() {
		
		
		for ( String commandDescription : Application.customerCommandDescriptions ) {
			System.out.println(commandDescription); 
		}
		
	}
	

	private static void setRunning(boolean isRunning) {
		Application.isRunning = isRunning;
	}
	
	
	private static void createAndAddProduct() {
		System.out.print("What is the type of product you are adding? :");
		String productType = Application.getUserInput(); 
		if ( !productType.equalsIgnoreCase("BIKE") ) {
			System.out.println("System can only support creation of bike objects");
		}
		
		System.out.print("What is the name of product you are adding? :");
		String productName = Application.getUserInput(); 
		
		System.out.print("What is the price of the product you are adding? :");
		Double productPrice = Double.parseDouble( Application.getUserInput() );
		if ( productPrice < 0.00 ) {
			System.out.printf("Bad input for price: %s\n", productPrice);
		}
		
		System.out.print("What is the category of product you are adding? :");
		String productCategory = Application.getUserInput();
		
		Product newProduct = new Product(); 
		newProduct.setName(productName);
		newProduct.setPrice(productPrice);
		newProduct.getCategory().setName(productCategory);
		
		
		Application.productService.addProduct(newProduct);
		
		
		
	}

	private static void dispatchUserActionBasedOnResponse(String userResponse) {
		
		
		final String[] userResponseArray = userResponse.split(" "); 
		final String command = userResponseArray[0].toUpperCase(); 
		final Integer productId = userResponseArray.length > 1 ? 
				Integer.parseInt( userResponseArray[1 ])
				: null; 
				
		final Integer offerId = productId; 

		switch (command) {
			// GENERIC ACTIONS : LOGIN, REGISTER, HELP, EXIT 
			case "LOGIN":
				Application.login();
				break; 
			case "REGISTER": 
				Application.register();
				break;
			case "HELP":
				Application.printHelpScreen();
				break; 
			case "EXIT": 
				Application.showExitScreen();
				break; 
			
			// CUSTOMER ACTIONS : GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS
			case "GET_PRODUCTS":
				Application.productService.getAvailableProducts(); 
				break;
			case "MAKE_OFFER":
				System.out.print("Enter your offer price: "); 
				final Double offerPrice = Double.parseDouble(Application.userInputScanner.nextLine());
				final Integer customerId = Application.currentUser.getId();
				Application.productService.addOfferForProduct(customerId, productId, offerPrice);
				break;
			case "VIEW_MY_PRODUCTS": 
				Application.productService.getProductsByOwnerId(Application.currentUser.getId()); 
				break;
			case "VIEW_REMAINING_PAYMENTS":
				Application.productService.getRemainingPaymentsForProduct(productId);
				break; 
				
			// EMPLOYEE ACTIONS : ADD_PRODUCT, REMOVE_PRODUCT, VIEW_ALL_OFFERS, ACCEPT_OFFER, REJECT_OFFER, VIEW_ALL_PAYMENTS
			case "ADD_PRODUCT":
				Application.createAndAddProduct();
				break; 
			case "REMOVE_PRODUCT":
				Application.productService.removeProduct(productId);
				break;
			case "VIEW_ALL_OFFERS":
				if (productId == null ) {
					Application.offerService.getOffers(); 
				} else {
					Application.offerService.getOffersByProductId(productId);
				}
				break; 
			case "ACCEPT_OFFER":
				Application.offerService.acceptOffer(offerId);
				break; 
			case "REJECT_OFFER":
				Application.offerService.rejectOffer(offerId); 
				break; 
				
				
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userResponse);
		}
		
		
		
		
	}
	
	public static void getUserResponse() {
		
		System.out.println("Enter input here: "); 
		String userResponse = Application.userInputScanner.nextLine();
		Application.dispatchUserActionBasedOnResponse(userResponse);
	}
	
	public static String getUserInput() {
		return Application.userInputScanner.nextLine();
	}
	/**
	 * Clears the console
	 */
	public static void clearConsole() {
		
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
		
		
		Application.getUserResponse(); 
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
	
	
	
	public static void login() {
		
		
		
		// Get user type 
		System.out.println("Login sequence initiated. To begin, by specify whether you are a customer or an employee. \n"
				+ "You may also enter c for customer or e for employee. \n");
		System.out.print("I am a: "); 
		String userType = userInputScanner.nextLine();
		System.out.println(userType);
		
		
		// Get username 
		System.out.print("Please enter your username: "); 
		String username = userInputScanner.nextLine();
		
		
		// Get password 
		System.out.println("Hi, " + username + ". To finish authentication, please enter your password below");
		System.out.print(		"Password: "); 
		String password = userInputScanner.nextLine();
		
		System.out.println("Thank you for logging you.");
		
		
		Application.currentUser = new Person();
		Application.initControllers( Application.currentUser.getRole().getName() );  
		
		
	};
	
	
	public static void register() {}; 
	
	
	



}
