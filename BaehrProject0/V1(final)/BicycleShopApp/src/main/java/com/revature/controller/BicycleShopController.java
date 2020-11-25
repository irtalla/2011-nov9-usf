package com.revature.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.*;
import com.revature.services.*;

public class BicycleShopController {
	private static Scanner scan;
	private static UserServices userServ = new UserServicesImpl();
	private static OfferServices offerServ = new OfferServicesImpl();
	private static PaymentServices paymentServ = new PaymentServicesImpl();
	private static PurchaseServices purchaseServ = new PurchaseServicesImpl();
	private static BicycleServices bicycleServ = new BicycleServicesImpl();
	private static BicycleTypeServices bicycleTypeServ = new BicycleTypeServicesImpl();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
	
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to the One Stop Bike Shop, your number one source for bicycles!");
			User loggedInUser = null;
		
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in\nother. Exit");
				try {
					int userInput = Integer.valueOf(scan.nextLine());

					switch (userInput) {
					case 1:
						loggedInUser = registerUser();
						break;
					case 2:
						loggedInUser = logInUser();
						break;
					default:
						userActive = false;
						break mainLoop;
					}
				} catch (NumberFormatException e) {
					break;
					//e.printStackTrace();
				}
			}
			if(loggedInUser != null) {
				menuLoop: while (true) {
					System.out.println("What would you like to do?");
					System.out.println("1. View available bicycles\n2. View my bicycles\n3 View my payment history \n4 Make a payment");
					if (loggedInUser.getRoleID().equals(2)) {
						System.out.println("other. Log out");
					} else if (loggedInUser.getRoleID().equals(0)  || loggedInUser.getRoleID().equals(1)) { //1 for manager, 2 for employee
						System.out.println("5. Manage bicycles\n6. Manage users\n7. Manage offers\n8. View purchase history\nother. Log out");
					}
					try {
						int userInput = Integer.valueOf(scan.nextLine());
						switch (userInput) {
						case 1:
							viewAvailableBicycles(loggedInUser);
							break;
						case 2:
							viewUserBicycles(loggedInUser);
							break;
						case 3:
							loggedInUser = viewUserPayments(loggedInUser);
							break;
						case 4:
							loggedInUser = makePayment(loggedInUser);
						case 5:
							if (loggedInUser.getRoleID().equals(2)) {
								System.out.println("See you next time!");
								break;
								}
							loggedInUser = manageBicycles(loggedInUser);
							break;
						case 6:
							if (loggedInUser.getRoleID().equals(2)) {
								System.out.println("See you next time!");
								break;
								}
							loggedInUser = manageUsers(loggedInUser);
							break;
						case 7:
							if (loggedInUser.getRoleID().equals(2)) {
								System.out.println("See you next time!");
								break;
								}
							loggedInUser = manageOffers(loggedInUser);
							break;
						case 8:
							if (loggedInUser.getRoleID().equals(2)) {
								System.out.println("See you next time!");
								break;
								}
							loggedInUser = viewPurchases(loggedInUser);
							break;
						default:
							System.out.println("See you next time!");
							loggedInUser = null;
							break menuLoop;
						}
						if (loggedInUser == null) {
							System.out.println("See you next time!");
							break menuLoop;
						}
					} catch (NumberFormatException e) {
						break;
						//e.printStackTrace();
					}
				}
			} else {
				break;
			}
		}
		scan.close();
	}
	
	private static User makePayment(User loggedInUser) {
		if(loggedInUser.getPaymentsRemaining() == 0) {
			System.out.println("You have no payments remaining to make");
			return loggedInUser;
		}
		double numPayments = loggedInUser.getPaymentsRemaining().doubleValue();
		double paymentAmount = loggedInUser.getAccountBalance()/numPayments;
		Payment p = new Payment();
		p.setAmount(paymentAmount);
		p.setUserId(loggedInUser.getUserID());
		System.out.println("You have " + numPayments + " payments of $" + paymentAmount + " remaining to make.");
		System.out.println("Enter 1 to confirm payment, any other key to cancel");
		
		try {
			int userInput = Integer.valueOf(scan.nextLine());
			if(userInput == 1) {
				paymentServ.addPayment(p);
				System.out.println("Payment successful!");
				return userServ.getUserById(loggedInUser.getUserID());
			} else {
				System.out.println("Payment cancelled");
			return loggedInUser;
			}
		} catch (NumberFormatException e) {
			System.out.println("Payment cancelled");
			return loggedInUser;
			//e.printStackTrace();
		}
	}

	private static User viewUserPayments(User loggedInUser) {
		Set<Payment> payments = paymentServ.getByUserId(loggedInUser.getUserID());
		if(payments.size() > 0) {
			for(Payment p : payments) {
				System.out.println(p);
			}
		} else {
			System.out.println("You have no payments in your history");
		}
		System.out.println(" ");
		return loggedInUser;
		
	}

	private static User viewPurchases(User loggedInUser) {
		Set<Purchase> purchases = purchaseServ.getAll();
		for(Purchase p : purchases) {
			System.out.println(p);
		}
		System.out.println(" ");
		return loggedInUser;
	}

	private static User manageOffers(User loggedInUser) {
		while (true) {
			System.out.println("1. View offer history:\n2. View oustanding offers\n3. Accept an offer\nother. Cancel");
			try {
				int input = Integer.valueOf(scan.nextLine());
				switch(input) {
				case 1:
					Set<Offer> allOffers = new HashSet<Offer>();
					allOffers = offerServ.getAll();
					if(allOffers.size() > 0) {
						for(Offer o : allOffers) {
							System.out.println(o);
						}
						break;
					} else {
						System.out.println("There are no offers to view");
						return loggedInUser;
					}
				case 2:
					Set<Offer> outstandingOffers = new HashSet<Offer>();
					outstandingOffers = offerServ.getOutstanding();
					if(outstandingOffers.size() > 0) {
						for(Offer o : outstandingOffers) {
							System.out.println(o);
						}
						break;
					} else {
						System.out.println("There are no offers to view");
						return loggedInUser;
					}
				case 3:
					Set<Offer> outstandingOfferset = new HashSet<Offer>();
					outstandingOfferset = offerServ.getOutstanding();
					if(outstandingOfferset.size() > 0) {
						//System.out.println(input);
						System.out.println("Enter the id of the offer which you wish to accept, choosing from the following list:");
						for(Offer o : outstandingOfferset) {
							System.out.println(o);
						}
						try {
							int userinput = Integer.valueOf(scan.nextLine());
							Offer o = offerServ.getOfferByOfferId(userinput);
							if(o == null) {
								System.out.println("No such offer for that id");
							} else {
								offerServ.acceptOffer(o);
								System.out.println(bicycleServ.getBicycleById(20));
								System.out.println("Offer accepted!");
								break;
							}
						} catch (NumberFormatException e) {
							System.out.println("No such offer for that id");
							//e.printStackTrace();
						}
						break;
					} else {
						System.out.println("There are no oustanding offers to accept");
						return loggedInUser;
					}
				default:
					return loggedInUser;
				}
				
			} catch (NumberFormatException e) {
				return loggedInUser;
				//e.printStackTrace();
			}
		}
	}

	/*
	 * returns person object if user successfully registered
	 * returns null if user cancelled
	 */
	private static User registerUser() {
		while (true) {
			User newAccount = new User();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			newAccount.setRoleID(2); // all customers have this role ID
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			try {
				int input = Integer.valueOf(scan.nextLine());
				switch (input) {
				case 1:
					try {
						newAccount.setUserID((userServ.addUser(newAccount))); 
						if(newAccount.getUserID() == 0) {
							System.out.println("Sorry, that username-password combination is invalid - please try again.");
							break;
						} else {
							System.out.println("Confirmed. Welcome!");
						}
						return newAccount;
					} catch (Exception e) {
						System.out.println("Sorry, that username-password combination is invalid - please try again.");
					}
					break;
				case 2:
					System.out.println("Okay, let's try again.");
					break;
				default:
					System.out.println("Okay, let's go back.");
					return null;
				}
			} catch (NumberFormatException e) {
				return null;
				//e.printStackTrace();
			}
			
		}
	}
	
	/*
	 * returns person object if successfully logged in
	 * returns null otherwise
	 */
	private static User logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			User user = userServ.getUserByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			try {
				int input = Integer.valueOf(scan.nextLine());
				if (input != 1) {
					break;
				}
			} catch (NumberFormatException e) {
				break;
				//e.printStackTrace();
			}
		}
		return null;
	}
	
	// selects all available Bicycles
	private static void viewAvailableBicycles(User user) {
		Set<Bicycle> availableBicycles = bicycleServ.getAvailable();
		
		for (Bicycle b : availableBicycles) {
			System.out.println(b);
		}
		
		System.out.println("Would you like to submit an offer for a bicycle? 1 for yes, other for no");
		try {
			int input = Integer.valueOf(scan.nextLine());
			if (input == 1) {
				while (true) {
					System.out.println("Which bicycle? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Bicycle b = bicycleServ.getBicycleById(input);
					if (b != null && b.getStatus().equals("Available")) {
						System.out.println(b);
						System.out.println("You want to submit an offer for " + b + "?\n1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							Offer o = new Offer();
							o.setBicycle(b);
							System.out.println(b.getBicycleType());
							o.setStatus("Pending");
							o.setUser_id(user.getUserID());
							System.out.println("Enter the amount, in USD, wich you would like to offer");

							Integer inputAsInteger = Integer.valueOf(scan.nextLine());
							double doubleInput = inputAsInteger.doubleValue();
							System.out.println("You want to submit an offer for of $" + doubleInput +" for " + b +"?\n1 for yes, other for no");
							input = Integer.valueOf(scan.nextLine());
							if (input == 1) {
								o.setOffer(doubleInput);
								System.out.println(o);
								offerServ.addOffer(o);
								System.out.println(o);
								Integer newId = o.getOffer_id();
								//System.out.println(newId);
								//System.out.println(o);
								Offer updatedOffer = offerServ.getOfferByOfferId(newId);
								System.out.println("You have submitted the following offer: \n" + updatedOffer);
								break;
							}
						} else {
							System.out.println("Okay, did you want to submit an offer for a different bicycle? 1 for yes, other for no");
							input = Integer.valueOf(scan.nextLine());
							if (input != 1)
								break;
						}
					} else {
						System.out.println("Sorry, that bicycle is not available. Do you want to try again?"
								+ " 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1) {
							System.out.println("Okay, that's fine.");
							break;
						}
					}
				}
			} else {
				System.out.println("Okay, that's fine.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Okay, that's fine.");
			//e.printStackTrace();
		}
		return;
	}
	
	// method for printing out the set of bicycles owned by the logged in user
	private static void viewUserBicycles(User user) {
		Set<Bicycle> userBikes = new HashSet<Bicycle>();
		userBikes = bicycleServ.getByUserId(user.getUserID());
		if (userBikes.size() > 0) {
			System.out.println("Viewing your bicycles: ");
			for (Bicycle b : userBikes) {
				System.out.println(b);
			}
		} else {
			System.out.println("You don't have any bicycles at this time.");
		}
	}
	
	/*
	 * employee-only menu:
	 * returns null if the user is not an employee (managers are also employees)
	 */
	private static User manageBicycles(User user) {
		if (user.getRoleID().equals(2)){
			System.out.println("Customers do not have the authority to do this");
			return null;
		}
		while (true) {
			System.out.println("Manage Bicycles:\n1. Add a bicycle\n2. Edit a bicycle\n3. Delete a bicycle\nother. Cancel");
			try {
				int input = Integer.valueOf(scan.nextLine());
				
				if (input == 1) {
					Bicycle newBicycle = new Bicycle();
					BicycleType bt = new BicycleType();
					System.out.println("Enter the manufacturer name: ");
					bt.setManufacturer(scan.nextLine());
					System.out.println("Enter the model name: ");
					bt.setModel(scan.nextLine());
					bt.setId(bicycleTypeServ.addBicycleType(bt));
					newBicycle.setBicycleType(bt);
					System.out.println("Enter  year: ");
					newBicycle.setYear(Integer.valueOf(scan.nextLine()));
					newBicycle.setOwner_id(1); //all new bicycles are owned by user 1: the store
					newBicycle.setStatus("Available"); //by default all new bicycles are available for sale
					System.out.println("Enter the price, in USD, for this new bicycle");
					Integer inputAsInteger = Integer.valueOf(scan.nextLine());
					double doubleInput = inputAsInteger.doubleValue();
					newBicycle.setPrice(doubleInput);
					
					System.out.println(newBicycle);
					System.out.println("Is this correct? 1 to confirm, other to start over");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						newBicycle.setId(bicycleServ.addBicycle(newBicycle));
						System.out.println("You successfully added the following bicycle: \n" + newBicycle);
					}
				} else if (input == 2) {
					for (Bicycle b : bicycleServ.getAvailable()) {
						System.out.println(b);
					}
					System.out.println("Which bicycle would you like to update? Enter its ID.");
					Bicycle b = bicycleServ.getBicycleById(Integer.valueOf(scan.nextLine()));
					Bicycle newBicycle = new Bicycle();
					newBicycle.setId(b.getId());
					if (b != null) {
						System.out.println("Editing: " + b);
						boolean editing = true;
						while (editing) {
							System.out.println("Edit:\n1. Type\n2. Year\n3. Price\n4. Save changes\nother. Cancel");
							try {
								input = Integer.valueOf(scan.nextLine());
								switch (input) {
								case 1:
									System.out.println("Enter bicycle type id, selecting from the list below: ");
									Set<BicycleType> btset = new HashSet<BicycleType>();
									btset = bicycleTypeServ.getAll();
									for (BicycleType bt : btset) {
										System.out.println(bt);
									}
									Integer typeId = Integer.valueOf(scan.nextLine());
									if(typeId.equals(1) || typeId.equals(2) || typeId.equals(3)) {
										b.setBicycleType(bicycleTypeServ.getById(typeId));
									}
									else {
										System.out.println("Invalid bicycle type id");
										break;
									}
								case 2:
									System.out.println("Enter new year: ");
									b.setYear(Integer.valueOf(scan.nextLine()));
									break;
								case 3:
									System.out.println("Enter new price: ");
									Integer inputAsInteger = Integer.valueOf(scan.nextLine());
									double doubleInput = inputAsInteger.doubleValue();
									b.setPrice(doubleInput);
									break;
								case 4:
									bicycleServ.updateBicycle(b);
									System.out.println("You updated " + b + " successfully.");
								default:
									editing = false;
									break;
								}
							} catch (NumberFormatException e) {
								break;
								//e.printStackTrace();
							}
						}
					}
				} else if (input == 3) {
					for (Bicycle b : bicycleServ.getAvailable()) {
						System.out.println(b);
					}
					System.out.println("Which bicycle would you like to delete? Enter its ID.");
					Bicycle b = bicycleServ.getBicycleById(Integer.valueOf(scan.nextLine()));
					if (b != null) {
						System.out.println("Is this the bicycle you wish to delete?");
						System.out.println(b);
						System.out.println("Enter 1 to confrim, other to cancel");
						try {
							Integer confirmInput = Integer.valueOf(scan.nextLine());
							if(confirmInput.equals(1)) {
								bicycleServ.deleteBicycle(b);
							}
							else {
								break;
							}
						} catch (NumberFormatException e) {
							break;
							//e.printStackTrace();
						}
					}
				}else {
					break;
				}
			} catch (NumberFormatException e) {
				break;
				//e.printStackTrace();
			}
		}
		
		return user;
	}
	
	/*
	 * manager-only menu:
	 * returns null if the user is not an employee
	 */
	private static User manageUsers(User user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRoleID().equals(0))) {
			System.out.println("Only managers have access to this feature");
			return null;
		}
		while (true) {
			try {
				System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
				int input = Integer.valueOf(scan.nextLine());
				
				if (input == 1) {
					System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
					input = Integer.valueOf(scan.nextLine());
					User personToRemove = null;
					if (input == 1) {
						System.out.println("Enter the ID of the user you want to remove.");
						personToRemove = userServ.getUserById(Integer.valueOf(scan.nextLine()));
					} else if (input == 2) {
						System.out.println("Enter the username of the user you want to remove.");
						personToRemove = userServ.getUserByUsername(scan.nextLine());
					}
					if (input == 1 || input == 2) {
						if (personToRemove != null) {
							System.out.println(personToRemove);
							System.out.println("Remove this user? 1 for yes, other for no");
							input = Integer.valueOf(scan.nextLine());
							if (input == 1) {
								userServ.deleteUser(personToRemove);
								System.out.println("You removed "
										+ personToRemove.getUsername() + " successfully.");
							}
						} else {
							System.out.println("That user doesn't exist.");
						}
					}
				} else if (input == 2) {
					User newAccount = new User();
					System.out.println("Enter a username: ");
					newAccount.setUsername(scan.nextLine());
					System.out.println("Enter a password: ");
					newAccount.setPassword(scan.nextLine());
					System.out.println("Choose a role:\n1. Manager\n2. Employee\nother. Customer");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						newAccount.setRoleID(0);
					} if(input == 2){
						newAccount.setRoleID(1);
					} else {
						newAccount.setRoleID(2);
					}
					System.out.println(newAccount);
					System.out.println("Look good? 1 to confirm, other to cancel");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
							userServ.addUser(newAccount);
							System.out.println("Added " + newAccount.getUsername() + " successfully.");
						}
					}
					else {
						break;
					}
			} catch (NumberFormatException e) {
				break;
				//e.printStackTrace();
			}	
		}
		
		return user;
	}
	
	
	
	
}