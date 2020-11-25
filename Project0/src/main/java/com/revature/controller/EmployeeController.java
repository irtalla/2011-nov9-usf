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
	
	public static String[] getCommandDescriptions() {
		return commandDescriptions;
	}
	
	protected EmployeeController() {}
	public static EmployeeController getEmployeeController() {
		
		if (EmployeeController.employeeController == null) {
			EmployeeController.employeeController =  new EmployeeController();
			EmployeeController.productService = new ProductServiceImpl();  
			EmployeeController.offerService = new OfferServiceImpl(); 
		}
		
		return EmployeeController.employeeController; 
	}
	
	public static void dispatchUserActionBasedOnResponse(String userResponse) {
		
		final String[] userResponseArray = userResponse.split(" "); 
		final String command = userResponseArray[0].toUpperCase(); 
		final Integer productId = userResponseArray.length > 1 ? 
				Integer.parseInt( userResponseArray[1])
				: null; 
				
		final Integer offerId = productId; 

		switch (command) {
			case "HELP":
				for (String str : commandDescriptions) { System.out.println(str); }
				break; 
			case "GET_PRODUCTS":
				Application.displayDataSet( EmployeeController.productService.getAvailableProducts() );
				break;
			case "VIEW_REMAINING_PAYMENTS":
//				EmployeeController.productService.getRemainingPayments(productId);
				break; 
				
			// EMPLOYEE ACTIONS : ADD_PRODUCT, REMOVE_PRODUCT, VIEW_ALL_OFFERS, ACCEPT_OFFER, REJECT_OFFER, VIEW_ALL_PAYMENTS
			case "ADD_PRODUCT":
				createAndAddProduct();
				break; 
			case "REMOVE_PRODUCT":
				if (productId == null) {
					System.out.println("You forgot to enter an id for the product!");
					break;
				}
				EmployeeController.productService.removeProduct(productId);
				break;
			case "VIEW_ALL_OFFERS":
				Application.displayDataSet( EmployeeController.offerService.getAll() );
				break; 
			case "ACCEPT_OFFER":
				if (offerId == null) {
					System.out.println("You forgot to enter an id for the offer!");
					break;
				}
				EmployeeController.offerService.acceptOffer(offerId);
				break; 
			case "REJECT_OFFER":
				if (offerId == null) {
					System.out.println("You forgot to enter an id for the offer!");
					break;
				}
				EmployeeController.offerService.rejectOffer(offerId); 
				break; 
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userResponse);
		}	
	}
	
	

	
	protected static void createAndAddProduct() {
		
		while (true) {
			
			String[] categories = { "  Unspecified", "  Bike", "  Helmet", "  Pegs", "  Chain" };
			System.out.printf("Categories: \n\n");
			for (String category : categories) { System.out.println(category); }
			System.out.printf("\n");
			System.out.println("What is the category of the product you are adding? :");
			final String userInput = Application.getUserInput();
			if ( Application.getCategoryIdByName(userInput) == 0 ) {
				System.out.printf("Now, %s, you know we don't sell those.\n", Application.getCurrentUser().getUsername()); 
				System.out.printf("Would you like to try again? If so, enter 'yes'\n"); 
				if ( Application.getUserInput().equalsIgnoreCase("YES") ) {
					continue; 
				} else {
					break; 
				}
			}
			
			Product newProduct = new Product();
			newProduct.getCategory().setId( Application.getCategoryIdByName(userInput) );

			System.out.print("What is the name of product you are adding? :");		
			newProduct.setName( Application.getUserInput() );
			
			Double productPrice = 0.00;
			do {
				
				System.out.print("What is the price of the product you are adding? :");
				productPrice = Double.parseDouble( Application.getUserInput() );
				if( productPrice < 0.00 ) {
					System.out.printf("Bad input for price: %s\n", productPrice);
					System.out.printf("Would you like to try again? If so, enter 'yes'\n"); 
					if ( Application.getUserInput().equalsIgnoreCase("YES") ) {
						continue; 
					} else {
						return; 
					}
				}
				
			} while (productPrice <= 0.00); 
			
			newProduct.setPrice(productPrice);
			
			Product addedProduct = EmployeeController.productService.addProduct(newProduct); 
			
			if (addedProduct != null) {
				System.out.printf("\nSuccessfully added product: \n\n");
				System.out.println(addedProduct.toString());
			} else {
				System.out.printf("\n Internal system error encountered \n\n");
			}
			break; 

		}
	}
		
}
