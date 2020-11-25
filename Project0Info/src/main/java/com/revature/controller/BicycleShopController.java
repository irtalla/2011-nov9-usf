package com.revature.controller;

import java.util.Scanner;
import java.util.Set;
import com.revature.beans.Bicycle;
import com.revature.beans.Category;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.data.PaymentPostgres;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BicycleService;
import com.revature.services.OfferService;
import com.revature.services.PersonService;

public class BicycleShopController {
	private static Scanner scan;
	private static BicycleService bicycleService = new BicycleService();
	private static PersonService personService = new PersonService();
	private static OfferService offerService = new OfferService();

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
        loginLoop: while(true) {
        	Person loggedInUser = null;
        	
        	System.out.println("Welcome to the Bicycle Shop.");
        	
            while (loggedInUser == null) {
            	System.out.println("\nPlease login. Enter a number associated with the following options to continue.");
                System.out.println("\n1. Log In");
                System.out.println("\n2. Register for an Account");
                System.out.println("\n\nAll Other Number - Exit Application");
                
                try {
                	int input = Integer.valueOf(scan.nextLine());
                    
                    switch (input) {
    				case 1:
    						loggedInUser = logInUser();
    						break;
    				case 2:
    						loggedInUser = registerUser();
    						break;
    				default:
    						break loginLoop;
    				}
					
				} catch (NumberFormatException e) {
					System.out.println("Please enter a number only.");
				}
                
            }
        	
            
        	//Finished logging in user
            
            if (loggedInUser.getRole().getName().contains("customer")) {
            	mainMenu: while (true) {
            		System.out.println("Welcome " + loggedInUser.getUsername() + ". \nMain menu:\n");
            		System.out.println("1. View all avaliable bicycles.");
            		System.out.println("2. View owned bicycle(s)");
            		System.out.println("Other Numbers To Log Out.");
            		int input = Integer.valueOf(scan.nextLine());
    				switch (input) {
	    				case 1: viewAllAvalaibleBicycles(loggedInUser);
	    						break;
	    				case 2: viewOwnedBicycles(loggedInUser);
	    						break;
	    				default:
	    						break mainMenu;
    				}
            		
            		
            	}
            }else {
            	mainMenu: while (true) {
            		System.out.println("Welcome " + loggedInUser.getUsername() + ". \n\nMain menu:");
            		System.out.println("1. Add a bicycle to the shop.");
            		System.out.println("2. Remove a bicyle from the shop.");
            		System.out.println("3. View Offers");
            		System.out.println("4. View all payments.");
            		System.out.println("Other Numbers To Log Out.");
            		int input = Integer.valueOf(scan.nextLine());
    				switch (input) {
	    				case 1: addBicycle();
	    						break;
	    				case 2: removeBicycle();
	    						break;
	    				case 3:	viewOffers();
	    						break;
	    				case 4:	viewAllPayments();
								break;
	    				default:
	    						break mainMenu;
    				}
            	}
            }// End of customer/employee if statement
        	
        }// End of loginLoop
        
	}// End of static main


	private static Person logInUser() {
		Boolean goBack = false;
		Person loggedInUser = null;
	
		while (!goBack) {
			System.out.println("Please Log In Below - ");
			System.out.println("Please enter your username:");
			String username = scan.nextLine();
			System.out.println("Please enter your password:");
			String password = scan.nextLine();
			loggedInUser = personService.login(username, password);
			if(loggedInUser == null) {
				System.out.println("Error: invalid logins.");
				System.out.println("Would you like to try again or go back?");
				System.out.println("1. Try logging in again.");
				System.out.println("\nAnything else to go back");
				int input = Integer.valueOf(scan.nextLine());
				switch (input) {
				case 1: break;

				default:
						goBack = true;
						break;
				}
			}else {
				goBack = true;
			}
		}
		return loggedInUser;
	}
	
	private static Person registerUser() {
		System.out.println("Register a new user.");
		
		while (true) {
			System.out.println("Please enter your username:");
			String username = scan.nextLine();
			System.out.println("Please enter your password:");
			String password = scan.nextLine();
			System.out.println("Login information correct?");
			System.out.println("Username: " + username);
			System.out.println("Password: "+ password);
			System.out.println("\n1. Yes");
			System.out.println("\n2. No");
			System.out.println("\nAny Other Number To Go Back");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1: 
					Person newPerson = new Person();
					Role role = new Role();
					newPerson.setRole(role);
					newPerson.setUsername(username);
					try {
						personService.addPerson(newPerson);
					} catch (NonUniqueUsernameException e) {
						System.out.println("This username is unavaliable. Please try again.");
						break;
					}
					personService.updatePassword(newPerson, password);
					return newPerson;
			case 2: 
					break;
			default:
					return null;
			}
		}
		
	}
	
	// Customer Functions
	
	private static void viewAllAvalaibleBicycles(Person loggedInPerson) {
		Set<Bicycle> avaliableBicycles = bicycleService.getAvaliableBicycles();
		System.out.println("All avaliable bicycles:");
		for (Bicycle bicycle : avaliableBicycles) {
			System.out.println("ID: " + bicycle.getId( ) + " - (" + bicycle.getCategory().getName().toUpperCase() + ") " +bicycle.getModelName());
		}
		main: while (true) {
			System.out.println("Options:");
			System.out.println("1. Go back to main menu.");
			System.out.println("2. Make an offer.");
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					break;
				}
				if (input == 2) {
					System.out.println("Which bicycle would you like to make an offer for? Please enter bicycle ID.");
					try {
						Integer selectedBicycleId = Integer.valueOf(scan.nextLine());
						for (Bicycle bicycle : avaliableBicycles) {
							if (bicycle.getId() == selectedBicycleId) {
								System.out.println("Existing Offer(s) :");
								if (offerService.getOfferByBicycle(bicycle).size() != 0) {
									for (Offer offer : offerService.getOfferByBicycle(bicycle)) {
										System.out.println("\t " + offer.getPrice());
									}
								}else {
									System.out.println("No existing offer on this bicycle. You're the first one!");
								}
								
								System.out.println("Please enter your offer in USD.");
								try {
									double offerInput = Double.valueOf(scan.nextLine());
									Offer newOffer = new Offer(null, bicycle, loggedInPerson, offerInput);
									
									System.out.println("Would you like to submit the offer?");
									System.out.println("Offer:  $" + offerInput + " for Bicycle ID: " + bicycle.getId());
									System.out.println("\n1. Yes");
									System.out.println("2. No");
									
									try {
										int selection = Integer.valueOf(scan.nextLine());
										if (selection == 1) {
											offerService.addOffer(newOffer);
											System.out.println("Offer submitted! If your offer is selected, you will see it underneath your avaliable bicycles.");
										}else {
											System.out.println("Selected 'No'. Going back.");
											break main;
										}
									} catch (Exception e) {
										System.out.println("Invalid input");
									}
								} catch (Exception e) {
									System.out.println("Please enter a valid price.");
								}
								break main;
							}
						}
						System.out.println("Invalid bicycle. Going back to see all avaliable bicycles.");
					} catch (NumberFormatException e) {
						System.out.println("Please enter a valid ID# only.");
					}
					break main;
				}else {
					System.out.println("Not an option. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number only.");
			}
		}
	}
	
	private static void viewOwnedBicycles(Person loggedInUser) {
		Set<Bicycle> ownedBicycles = bicycleService.getOwnedBicycles(loggedInUser);
		
		if (ownedBicycles.size() != 0) {
			System.out.println("You own the following bicycles:");
			for (Bicycle bicycle : ownedBicycles) {
				System.out.println("-----------------------------------");
				System.out.println("ID: " + bicycle.getId( ) + " - (" + bicycle.getCategory().getName().toUpperCase() + ") " +bicycle.getModelName());
				System.out.println("\t Owned Amount: " + bicycleService.ownedAmount(bicycle));
			}
		}else {
			System.out.println("According to our system, you do not own any bicycles.\nPlease check out our avaliable bicycles in the main menu.");
		}
		while (true) {
			System.out.println("Press 1 to Go Back.");
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number only.");
			}
			
		}
	}
	
	
	// Employee Functions
	
	private static void addBicycle() {
		while(true) {
			Bicycle bicycle = new Bicycle();
			System.out.println("Adding a new bicycle or type 'Quit' to go back");
			System.out.println("Please enter in the model name:");
			String modelString = scan.nextLine();
			if (modelString.contains("Quit") || modelString.contains("quit")) {
				break;
			}
			System.out.println("What category is the bicycle under?");
			System.out.println("1. bmx");
			System.out.println("2. road");
			System.out.println("3. mountain");
			try {
				Integer categoryInteger = Integer.valueOf(scan.nextLine());
				
				bicycle.setModelName(modelString);
				Category category = new Category();
				category.setId(categoryInteger);
				bicycle.setCategory(category);
				Status status = new Status();
				status.setId(1);
				bicycle.setStatus(status);
				
				int retID = bicycleService.addBicycle(bicycle);
				System.out.println("Bicycle " +  retID + " added.");
				break;
			} catch (Exception e) {
				System.out.println("Invalid input. Starting Over.");
			}
		}
	}
	
	private static void removeBicycle() {
		Set<Bicycle> avaliableBicycles = bicycleService.getAvaliableBicycles();
		System.out.println("Removing a bicycle.");
		if (!avaliableBicycles.isEmpty()) {
			for (Bicycle bicycle : avaliableBicycles) {
				System.out.println("ID: " + bicycle.getId( ) + " - (" + bicycle.getCategory().getName().toUpperCase() + ") " +bicycle.getModelName());
			}
			main: while(true) {
				System.out.println("Which bicycle to delete. Please enter bicycle ID. Type 0 to quit.");
				try {
					Integer input = Integer.valueOf(scan.nextLine());
					if (input == 0) {
						break main;
					}
					for (Bicycle bicycle : avaliableBicycles) {
						if (input == bicycle.getId()) {
							bicycleService.delete(bicycle);
							System.out.println("Bicycle with ID: " + bicycle.getId() + " is deleted.");
							break main;
						}
					}
					System.out.println("Not a valid id. Please try again.");
				} catch (Exception e) {
					System.out.println("Input error");
				}
			}
		}else {
			System.out.println("No bicycles in inventory. Returning to main menu.");
		}
		
	}
	
	private static void viewOffers() {
		Set<Offer> offers = offerService.getAll();
		
		// printing out sorted offers
		System.out.println("Offers:");
		for(Offer offer : offers) {
			System.out.println(offer);
		}
		
		
		main: while (true) {
			System.out.println("Options: ");
			System.out.println("1. Accept an offer.");
			System.out.println("2. Go back to main menu.");
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Please enter in the offer ID.");
					try {
						Integer idSelection = Integer.valueOf(scan.nextLine());
						Offer selectedOffer = offerService.getByInt(idSelection);
						if (selectedOffer != null) {
							offerService.seletedOffer(selectedOffer);
							System.out.println("Selected Offer:");
							System.out.println(selectedOffer);
							System.out.println("All other other on the same bicycle will be rejected and deleted.");
							System.out.println("Bicycle " + selectedOffer.getBicycle().getId() + " now belongs to " + selectedOffer.getPerson().getUsername() + " at a price of " + selectedOffer.getPrice() + " .");
							break main;
						}else {
							System.out.println("Invalid Offer.");
						}
					} catch (Exception e) {
						System.out.println("Error, please try again.");
					}
				} else if (input == 2) {
					break main;
				} else {
					System.out.println("Not an option. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number only.");
			}
			
		}
		
		
		
		
	}
	
	private static void viewAllPayments() {
		PaymentPostgres paymentPostgres = new PaymentPostgres();
		Set<Payment> payments = paymentPostgres.getAll();
		
		if (payments.size() != 0) {
			System.out.println("Listing all payments in system:");
			for (Payment payment : payments) {
				System.out.println("-----------------------------------");
				System.out.println(payment.toString());
			}
		}else {
			System.out.println("No payment in system.");
		}
		while (true) {
			System.out.println("Press 1 to Go Back.");
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number only.");
			}
			
		}
		
	}


	


	


	
}
