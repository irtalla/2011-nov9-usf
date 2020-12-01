package com.revature.controllers;

import java.util.List;

import com.revature.beans.Offer;
import com.revature.beans.Pitch;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

public class AuthorController {
	
	static ProductService productService = null; 
	static OfferService offerService = null; 
	
	private static AuthorController customerController = null; 
	
	private static enum customerActions{GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS};
	
	private static String[] commandDescriptions = {
			"	GET_PRODUCTS	            -  view the available products",
			"	MAKE_OFFER				    - make an offer for a bicycle",
			"	VIEW_MY_PRODUCTS	        - view the bicycles that I own",
			"	VIEW_MY_PAYMENTS 			- view all payments",
	};
		
	public static String[] getCommandDescriptions() {
		return commandDescriptions;
	}

	protected AuthorController() {
		
		AuthorController.productService = new ProductServiceImpl();  
		AuthorController.offerService = new OfferServiceImpl(); 
	}
	
	public static AuthorController getCustomerController() {
		
		if (AuthorController.customerController == null) {
			AuthorController.customerController =  new AuthorController();
		}
		return AuthorController.customerController; 
	}
	
	private static void createAndAddOffer() {
		
		System.out.println("Would you like to see a list of available products? yes or no");
		final String userChoice = Application.getUserInput(); 
		if ( userChoice.equalsIgnoreCase("YES") ) { 
			Application.displayDataSet( AuthorController.productService.getAvailableProducts() ); 
			}
		
		
		final Integer customerId = Application.getCurrentUser().getId();
		System.out.print("Enter the id for the product for which you are making an offer: ");
		final Integer productId = Integer.parseInt(Application.userInputScanner.nextLine()); 
		System.out.print("Enter the amount you want to offer for this product: "); 
		final Double offerAmount = Double.parseDouble(Application.userInputScanner.nextLine()); 
		
		Offer newOffer = new Offer(); 
		newOffer.setCustomerId(customerId);
		newOffer.setProductId(productId);
		newOffer.setAmount(offerAmount); 
		if ( AuthorController.offerService.add(newOffer) != null ) {
			System.out.println("Successfully added offer"); 
		} else {
			System.out.println("Internal System error: offer uncussessful"); 
		}
	}
	
	// Split and move to controllers
	public static void dispatchUserActionBasedOnResponse(String userInput) {
		
		final String[] userInputArray = userInput.split(" "); 
		final String command = userInputArray[0].toUpperCase(); 
		
		switch (command) {
			case "HELP":
				for (String str : commandDescriptions) { System.out.println(str); }
				break; 
			// CUSTOMER ACTIONS : GET_PRODUCTS, MAKE_OFFER, VIEW_MY_PRODUCTS, VIEW_REMAINING_PAYMENTS
			case "GET_PRODUCTS":
				handleGetProductsRequest();
				break;
			case "MAKE_OFFER":
				createAndAddOffer();
				break;
			case "VIEW_MY_PRODUCTS": 
				Application.displayDataSet(productService.getProductsByOwnerId( 
								Application.getCurrentUser().getId() 
								)
						);
				break;
			case "VIEW_MY_PAYMENTS":
				productService.getRemainingPayments(Application.getCurrentUser().getId() );
				break; 
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userInput);
		}
	}
	
	
	private static void handleGetProductsRequest() {
		
		System.out.println("What is the category of product you are interested in?");

		while (true) {
			
			String[] categories = { "  Bike", "  Helmet", "  Pegs", "  Chain" };
			System.out.printf("Categories: \n\n");
			for (String category : categories) { System.out.println(category); }
			System.out.println("What is the category of the products you are interested in? :");
			final String userChoice = Application.getUserInput();
			if ( Application.getCategoryIdByName(userChoice) == 0 ) {
				System.out.printf("Sorry, %s, you know we don't sell those.\n", Application.getCurrentUser().getUsername()); 
				System.out.printf("Would you like to try again? If so, enter 'yes'\n"); 
				if ( Application.getUserInput().equalsIgnoreCase("YES") ) {
					continue; 
				} else {
					break; 
				}
			} else {
				System.out.println("Fetching products...");
				Application.displayDataSet(productService.getProductByCategory(userChoice));
				break; 
			}
		}		
	}
	
	

}
