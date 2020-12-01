package com.revature.controllers;

import java.util.Locale;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

public class SeniorEditorrController extends AssistantEditorController {
	
	static PersonService personService = null; 
	static ProductService productService = null; 
	static OfferService offerService = null; 
	
	
	private static SeniorEditorrController managerController = null;
	
	
	protected SeniorEditorrController() {
		super(); 
		personService = new PersonServiceImpl();
	}
	
	public static SeniorEditorrController getManagerController() {
		
		if (managerController == null) {
			managerController =  new SeniorEditorrController();
			productService = new ProductServiceImpl();  
			offerService = new OfferServiceImpl(); 
			personService = new PersonServiceImpl();
		}
		
		return managerController; 
	}
	
	private static String[] commandDescriptions = {
			"GET_PRODUCTS	                 -  view the available products",
			"ADD_PRODUCT                     -  create and add a product to shore",
			"REMOVE_PRODUCT < product id >   -  remove a product from the store", 
			"VIEW_ALL_OFFERS  			     -  see all offers for all products",
			"ACCEPT_OFFER < offer id >       -  accept an offer for product. Reject all other offers",
			"REJECT_OFFER < offer id >       -  reject a specific offer for product",
			"VIEW_ALL_PAYMENS                -  see all payments made so far",
			"ADD_EMPLOYEE					 -  add corporate slave",
			"REMOVE_EMPLOYEE				 -  fire employee",
			"SEE_SALES_HISTORY				 -  see all accepted offers"
	};
	
	public static String[] getCommandDescriptions() {
		return commandDescriptions;
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
				Application.displayDataSet( productService.getAvailableProducts() );
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
				productService.removeProduct(productId);
				break;
			case "VIEW_ALL_OFFERS":
				Application.displayDataSet( offerService.getAll() );
				break; 
			case "ACCEPT_OFFER":
				if (offerId == null) {
					System.out.println("You forgot to enter an id for the offer!");
					break;
				}
				offerService.acceptOffer(offerId);
				break; 
			case "REJECT_OFFER":
				if (offerId == null) {
					System.out.println("You forgot to enter an id for the offer!");
					break;
				}
				offerService.rejectOffer(offerId); 
				break; 
			case "ADD_EMPLOYEE":
				createAndAddEmployee();
				break; 
			case "REMOVE_EMPLOYEE": 
				fireEmployee(); 
				break; 
			case "SEE_SALES_HISTORY": 
				viewSales(); 
				break; 
			default: 
				System.out.printf("Sorry, I didn't understand that! Check your spelling. Unknown command: %s\n", userResponse);
		}	
	}
	
	private static void viewSales() {
		
		Set<Offer> offers = offerService.getAllAcceptedOffers(); 
		Double salesTotal = 0.00;
		
		for (Offer offer : offers ) {
			Double sellingPrice =  offer.getAmount(); 
			salesTotal += sellingPrice; 
		}
		
		System.out.printf("Displaying sales history: \n\n"); 
		Application.displayDataSet(offers);
		System.out.format(Locale.ENGLISH, "There have been %d total sales. Total sales revenue is: $%.2f%n", 
				offers.size(), salesTotal);
	}

	protected static void fireEmployee() {
		
		System.out.println("Would you like to see a list of employees? yes or no");
		final String userChoice = Application.getUserInput(); 
		if ( userChoice.equalsIgnoreCase("YES") ) { 
			Set<Person> persons = personService.getAll();
			persons.removeIf( person -> ! person.getRole().getName().equalsIgnoreCase("EMPLOYEE") );
			Application.displayDataSet(persons); 
			}
		
		System.out.println("Enter the id of the person you wish to release: ");
		Integer id = Integer.parseInt(Application.getUserInput());
		Person employeeToBeFired = personService.getPersonById(id);
		System.out.println( employeeToBeFired.toString( ));
		personService.deletePerson(employeeToBeFired);
	}
	
	protected static void createAndAddEmployee() {
		
		while (true) {
			
			Person newEmployee = new Person();
			newEmployee.getRole().setId(2);
			
			String username, password; 
			do {
				System.out.print("What is the name of this new slave :");		
				username = Application.getUserInput();
				if( username.isBlank() ) {
					System.out.printf("Username cannot be blank\n");
					System.out.printf("Would you like to try again? If so, enter 'yes'\n"); 
					if ( Application.getUserInput().equalsIgnoreCase("YES") ) {
						continue; 
					} else {
						return; 
					}
				}
				
			} while ( username.isBlank() ); 
			newEmployee.setUsername(username);
			
			do {
				System.out.print("What is the password for this new slave :");		
				password = Application.getUserInput();
				if( password.isBlank() ) {
					System.out.printf("password cannot be blank\n");
					System.out.printf("Would you like to try again? If so, enter 'yes'\n"); 
					if ( Application.getUserInput().equalsIgnoreCase("YES") ) {
						continue; 
					} else {
						return; 
					}
				}
				
			} while ( password.isBlank() ); 
			newEmployee.setPassword(password);
			
			Person addedEmployee = personService.addPerson(newEmployee);
			
			if (addedEmployee != null) {
				System.out.printf("\nSuccessfully added product: \n\n");
				System.out.println(addedEmployee.toString());
			} else {
				System.out.printf("\n Internal system error encountered \n\n");
			}
			break; 

		}
	}
	
	
}

/*


- As a manager, I can make employee accounts.
- As a manager, I can fire employees.
- As a manager, I can view sales history of all offers.

*/