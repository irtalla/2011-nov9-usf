package com.revature.controller;

import com.revature.beans.Product;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

public class EmployeeController {
	
	static ProductService productService = null; 
	static PersonService personService = null; 
	static OfferService offerService = null; 
	
	private static EmployeeController employeeController = null;
	
	
	private static enum employeeActions{ADD_PRODUCT, REMOVE_PRODUCT, ACCEPT_OFFER, REJECT_OFFER, VIEW_ALL_PAYMENTS}; 
	private static String[] commandDescriptions = {
			"GET_PRODUCTS	                 -  view the available products",
			"ADD_PRODUCT                     -  create and add a product to shore",
			"REMOVE_PRODUCT < product id >   -  remove a product from the store", 
			"VIEW_ALL_OFFERS  			     -  see all offers for all products",
			"ACCEPT_OFFER < offer id >       -  accept an offer for product. Reject all other offers",
			"REJECT_OFFER < offer id >       -  reject a specific offer for product",
			"VIEW_ALL_PAYMENS                -  see all payments made so far"
	};
	
	private EmployeeController() {}
	public static EmployeeController getEmployeeController() {
		
		if (EmployeeController.employeeController == null) {
			EmployeeController.employeeController =  new EmployeeController();
			EmployeeController.productService = new ProductServiceImpl(); 
			EmployeeController.personService = new PersonServiceImpl(); 
			EmployeeController.offerService = new OfferServiceImpl(); 
		}
		
		return EmployeeController.employeeController; 
	}
	
	public static void dispatchUserActionBasedOnResponse(String userResponse) {
		
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
				for (String str : commandDescriptions) { System.out.println(str); }
				break; 
			case "EXIT": 
				Application.showExitScreen();
				break; 
			
			// CUSTOMER ACTIONS : GET_PRODUCTS, VIEW_REMAINING_PAYMENTS
			case "GET_PRODUCTS":
				EmployeeController.productService.getAvailableProducts(); 
				break;
			case "VIEW_REMAINING_PAYMENTS":
				EmployeeController.productService.getRemainingPaymentsForProduct(productId);
				break; 
				
			// EMPLOYEE ACTIONS : ADD_PRODUCT, REMOVE_PRODUCT, VIEW_ALL_OFFERS, ACCEPT_OFFER, REJECT_OFFER, VIEW_ALL_PAYMENTS
			case "ADD_PRODUCT":
				createAndAddProduct();
				break; 
			case "REMOVE_PRODUCT":
				EmployeeController.productService.removeProduct(productId);
				break;
			case "VIEW_ALL_OFFERS":
				if (productId == null ) {
					EmployeeController.offerService.getOffers(); 
				} else {
					EmployeeController.offerService.getOffersByProductId(productId);
				}
				break; 
			case "ACCEPT_OFFER":
				EmployeeController.offerService.acceptOffer(offerId);
				break; 
			case "REJECT_OFFER":
				EmployeeController.offerService.rejectOffer(offerId); 
				break; 
				
				
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userResponse);
		}	
	}
	
	// Moved to employee controller
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
		
		
		EmployeeController.productService.addProduct(newProduct);
	}
		
}
