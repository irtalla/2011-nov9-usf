package com.revature.controller;

import java.util.List;

import com.revature.beans.Product;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

public class CustomerController {
	
	static ProductService productService = null; 
	static OfferService offerService = null; 
	
	private static CustomerController customerController = null; 
	
	private static enum customerActions{GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS};
	
	private static String[] commandDescriptions = {
			"	GET_PRODUCTS	                -  view the available products",
			"	MAKE_OFFER [product id]	    - make an offer for a bicycle",
			"	VIEW_MY_PRODUCTS	            - view the bicycles that I own",
			"	VIEW_MY_PAYMENTS [product id]	- view all payments",
	};
	
	private CustomerController() {}
	
	public static CustomerController getCustomerController() {
		
		if (CustomerController.customerController == null) {
			CustomerController.customerController =  new CustomerController();
			EmployeeController.productService = new ProductServiceImpl();  
			EmployeeController.offerService = new OfferServiceImpl(); 
		}
		return CustomerController.customerController; 
		
	}
	
	private static void createAndAddOffer() {
		
		final Integer customerId = Application.getCurrentUser().getId();
		System.out.print("Enter the id for the product for which you are making an offer: ");
		final Integer productId = Integer.parseInt(Application.userInputScanner.nextLine()); 
		System.out.print("Enter the amount you want to offer for this product: "); 
		final Double offerPrice = Double.parseDouble(Application.userInputScanner.nextLine()); 
		CustomerController.productService.addOfferForProduct(customerId, productId, offerPrice);
	}
	
	// Split and move to controllers
	public static void dispatchUserActionBasedOnResponse(String userInput) {
		
		final String[] userInputArray = userInput.split(" "); 
		final String command = userInputArray[0].toUpperCase(); 
		
		switch (command) {
			// GENERIC ACTIONS : LOGIN, REGISTER, HELP, EXIT 
			case "LOGIN":
				Application.login();
				break; 
			case "REGISTER": 
				Application.register();
				break;
			case "HELP":
				for (String str : commandDescriptions) { System.out.println(str); }
				break; 
			case "EXIT": 
				Application.showExitScreen();
				break; 
			// CUSTOMER ACTIONS : GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS
			case "GET_PRODUCTS":
				CustomerController.productService.getAvailableProducts(); 
				break;
			case "MAKE_OFFER":
				CustomerController.createAndAddOffer();
				break;
			case "VIEW_MY_PRODUCTS": 
				CustomerController.productService.getProductsByOwnerId( Application.getCurrentUser().getId() ); 
				break;
			case "VIEW_REMAINING_PAYMENTS":
				CustomerController.productService.getRemainingPaymentsForProduct(Integer.parseInt( userInputArray[0]));
				break; 
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userInput);
		}
	}
	
	

}
