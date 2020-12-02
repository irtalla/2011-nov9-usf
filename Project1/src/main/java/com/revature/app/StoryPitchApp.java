package com.revature.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.data.BikePostgres;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class StoryPitchApp {
	private static PersonService personServ;
	private static BikeService bikeServ;
	private static OfferService offerServ;
	private static Role customerRole;
	private static Role employeeRole;
	private static Status active;
	private static Status accepted;
	private static Status rejected;
	
	static {
		personServ = new PersonServiceImpl();
		bikeServ = new BikeServiceImpl();
		offerServ = new OfferServiceImpl();
		
		//these rows are pre-existing in db:
		customerRole = new Role();
		customerRole.setId(1);
		customerRole.setName("Customer");
		employeeRole = new Role();
		employeeRole.setId(2);
		employeeRole.setName("Employee");
		
		active = new Status();
		active.setId(1);
		active.setName("Active");
		accepted = new Status();
		accepted.setId(1);
		accepted.setName("Accepted");
		rejected = new Status();
		rejected.setId(1);
		rejected.setName("Rejected");
	}
	
	public static void main(String[] args) throws NonUniqueUsernameException {
		boolean proceed = true;
		
		mainLoop:
		while(proceed) {
			System.out.println("Hello! Welcome to the Bike Shop!");
			Person loggedInUser = null;
			
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
							System.out.println("Exiting... Thank you! Have a good day!");
							break mainLoop;
					}
				}catch (Exception e){
					System.out.println("Invalid input! Try again!\n");
				}
			}
			
			while(loggedInUser != null) {
				if(loggedInUser.getRole().getName().equals("Customer")) {
					System.out.println("Press 1 to view available bikes. Press 2 to view bikes in your collection. Press 3 to log out. Press 4 to exit.");
					try {
						Integer choice = Integer.valueOf(scan.nextLine());
						switch (choice) {
							case 1:
								viewAvailableBikes(loggedInUser);
								break;
							case 2:
								viewUserBikes(loggedInUser);
								break;
							case 3:
								loggedInUser = null;
								System.out.println("You have been logged out.");
								break;
							case 4:
								loggedInUser = null;
								proceed = false;
								System.out.println("Exiting... Have a good day!");
								break;
							default:
								notifyOfInvalidInput();
								break;
						}
					}catch(NumberFormatException e) {
						notifyOfInvalidInput();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else if(loggedInUser.getRole().getName().equals("Employee")){
					System.out.println("Press 1 to manage bikes. Press 2 to log out.");
					try {
						Integer input = Integer.valueOf(scan.nextLine());
						switch(input) {
							case 1:
								manageBikes();
								break;
							case 2:
								loggedInUser = null;
								System.out.println("Logging out.");
								break;
							default:
								notifyOfInvalidInput();
								break;
						}
					}catch(NumberFormatException e) {
						notifyOfInvalidInput();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("This user is neither an employee nor a customer.");
				}
			}
		}
	}
	
	public static void displayMenuOptions() {
		System.out.println("Add bike to inventory.");
	}
	
	private static Person registerUser() {
		while (true) {
			Person newAccount = new Person();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			System.out.println("Is this account for an employee? 1 for yes, 2 for no.");
			boolean acceptingRoleSelection = true;
			while(acceptingRoleSelection) {
				try {
					switch (Integer.valueOf(scan.nextLine())) {
						case 1:
							newAccount.setRole(employeeRole);
							acceptingRoleSelection = false;
							break;
						case 2:
							newAccount.setRole(customerRole);
							acceptingRoleSelection = false;
							break;
						default:
							notifyOfInvalidInput();
							break;
					}
				}catch(Exception e) {
					notifyOfInvalidInput();
				}
			}

			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setBikes(new HashSet<Bike>());
					newAccount.setId(personServ.addPerson(newAccount).getId());
					System.out.println("Confirmed. Welcome!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken - let's try again.");
				} catch (Exception e) {
					System.out.println("Sorry, an error occured. Try again.");
				}
				break;
			case 2:
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
			}
			
		}
	}
	
	private static Person logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	
	private static Person viewAvailableBikes(Person user) {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		
		for (Bike bike : availableBikes) {
			System.out.println(bike.toString());
		}
		
		System.out.println("Would you like to make an offer for a bike? 1 for yes, other for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which bike? Type its Id.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.isAvailable()) {
					System.out.println(bike.toString());
					System.out.println("You want to make an offer to purchase a " + bike.getBrand() + " model " +  bike.getModel() + " in " + bike.getColor() + "? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						Offer newOffer = new Offer();
						newOffer.setPerson(user);
						newOffer.setBike(bike);
						System.out.println("Go ahead and input a weekly payment you think is fair.");
						
						boolean validInput = false;
						Double weeklyPayment;
						while(!validInput) {
							try {
								weeklyPayment = Double.valueOf(scan.nextLine());
								newOffer.setWeeklyPayment(weeklyPayment);
								validInput = true;
							}catch(NumberFormatException error) {
								System.out.println("Invalid entry. Try again.");
							}
						}
						
						System.out.println("Go ahead and input the number of weeks over which you would like to make these payments.");
						validInput = false;
						Integer weeks;
						while(!validInput) {
							try {
								weeks = Integer.valueOf(scan.nextLine());
								newOffer.setWeeks(weeks);
								validInput = true;
							}catch(NumberFormatException error) {
								System.out.println("Invalid entry. Try again.");
							}
						}
						
						offerServ.makeOffer(newOffer);
						System.out.println("Thank you! We'll consider your offer.");
						// get the person with their updated bikeset
						user = personServ.getPersonById(user.getId());
						break;
					} else {
						System.out.println("Okay, did you want to make an offer for a different bike? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Sorry, that's not an available bike. Do you want to try again?"
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
		
		return user;
	}
	
	private static void viewUserBikes(Person user) {
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your bikes: ");
			for (Bike bike : user.getBikes()) {
				System.out.println(bike);
			}
			boolean viewRemainingPayments = true;
			while (viewRemainingPayments) {
				System.out.println("View Remaining Payments?");
				System.out.println("1 for yes, other for no");
				int input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Which one? Type its id.");
					
					try {
						input = Integer.valueOf(scan.nextLine());
						Bike bike = new BikePostgres().getByIdWithoutRelations(input);
					
//						Offer acceptedOffer = new ArrayList<Offer>(offerServ.getActiveOffersForBike(bike)).get(0);
						Offer acceptedOffer = offerServ.getAcceptedOfferForBike(bike);
						if (bike != null && acceptedOffer != null) {
							System.out.println("Your remaining payments for the bike with id " + bike.getId() + " are $" + acceptedOffer.getWeeklyPayment() + " for " + acceptedOffer.getWeeks() + " more weeks." );
							viewRemainingPayments = false;
						} else {
							System.out.println("Sorry, that's not one of your bikes.");
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
		} else {
			System.out.println("You don't have any bikes... yet.");
		}
	}
	
	private static boolean manageBikes() {
		boolean keepManaging = true;
		System.out.println("Manage Bikes:\n1. Add a bike to the inventory\n2. Edit a bike in the inventory \n3. Manage active offers\n4. Remove a bike from the inventory. \n5. View Remaining Payments for a Bike \n6. Cancel");
		int input = Integer.valueOf(scan.nextLine());
		
//		inputHandling: switch(input) {
		try {
			if(input == 1) {
				Bike newBike = new Bike();
				System.out.println("Enter a brand name: ");
				newBike.setBrand(scan.nextLine());
				System.out.println("Enter a model name: ");
				newBike.setModel(scan.nextLine());
				System.out.println("Enter a color: ");
				newBike.setColor(scan.nextLine());
			
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newBike.setStatus(status);
				
				System.out.println(newBike.toString());
				System.out.println("Look good? 1 to confirm, other to start over");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newBike.setId(bikeServ.addBike(newBike).getId());
					System.out.println("You successfully added a " + newBike.getBrand() + " with id " + newBike.getId() + "!");
				}
			}else if(input == 2) {
				for (Bike bike: bikeServ.getAvailableBikes()) {
					System.out.println(bike.toString());
				}
				System.out.println("Which bike would you like to update? Enter its Id.");
				Bike bike= bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				
				if (bike!= null) {
					System.out.println("Editing bike with id" + bike.getId());
					System.out.println("Current changes:\nBrand: " + bike.getModel()
						+ " Model: " + bike.getModel() + " Color: " + bike.getColor());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Brand\n2. Model\n3. Color\n4. Save changes\nother. Cancel");
						try {
							input = Integer.valueOf(scan.nextLine());
							switch (input) {
								case 1:
									System.out.println("Enter new brand: ");
									bike.setBrand(scan.nextLine());
								break;
									case 2:
									System.out.println("Enter new model: ");
									bike.setModel(scan.nextLine());
									break;
								case 3:
									System.out.println("Enter new color: ");
									bike.setColor(scan.nextLine());
									break;
								case 4:
									bikeServ.updateBike(bike);
									System.out.println("You updated the bike with brand " + bike.getBrand() + " and id " + bike.getId() + " successfully.");
								default:
									editing = false;
									break;		
							}
						}catch(NumberFormatException e) {
							notifyOfInvalidInput();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			}else if(input == 3) {
				manageOffers();
			}else if (input == 4) {
				manageRemovalOfBikeFromInventory();
			}else if(input == 5) {
				viewRemainingPaymentsForBikeAsEmployee();
			}else if(input == 6) {
	//			return false;
				keepManaging = false;
	//				break;
	//				break inputHandling;
			}else {
				notifyOfInvalidInput();
			}
		}catch(NumberFormatException e) {
			notifyOfInvalidInput();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return keepManaging;
	}
	
	private static void viewRemainingPaymentsForBikeAsEmployee() {
		Set<Bike> purchasedBikes = bikeServ.getUnavailableBikes();
		
		for (Bike bike : purchasedBikes) {
			System.out.println(bike.toString());
		}
		
		boolean viewingRemainingPayments = true;
		while(viewingRemainingPayments) {
			System.out.println("Type in the id of the bike whose remaining payments you wish to view.");
		
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				Bike bike = new BikePostgres().getByIdWithoutRelations(input);
		
				Offer acceptedOffer = offerServ.getAcceptedOfferForBike(bike);
				if (bike != null && acceptedOffer != null) {
//					System.out.println("Selected bike: " + bike.toString());
					System.out.println("The remaining payments for the bike with id " + bike.getId() + " are $" + acceptedOffer.getWeeklyPayment() + " for " + acceptedOffer.getWeeks() + " more weeks." );
					viewingRemainingPayments = false;
				} else {
					System.out.println("Sorry, that's not a purchased bike.");
				}
			}catch(NumberFormatException e) {
				notifyOfInvalidInput();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void manageRemovalOfBikeFromInventory() {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		
		for (Bike bike : availableBikes) {
			System.out.println(bike.toString());
		}
		
		if(availableBikes.size() == 0) {
			System.out.println("No available bikes!");
			return;
		}
		
		while(true) {
			System.out.println("Type in the id of the bike you would like to remove. Press 0 to cancel.");
			try{
				int input = Integer.valueOf(scan.nextLine());
				if(input == 0) {
					return;
				}
				
				Bike selected = bikeServ.getBikeById(input);
				
				if(selected != null) {
					bikeServ.deleteBike(selected);
					System.out.println("Successfully removed bike with id " + selected.getId() + " from the shop.");
					return;
				}else {
					notifyOfInvalidInput();
				}
			}catch(NumberFormatException e) {
				notifyOfInvalidInput();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}

	private static Person manageUsers(Person user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				System.out.println("1. Remove by id\n2. Remove by username\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Person personToRemove = null;
				if (input == 1) {
					System.out.println("Enter the id of the user you want to remove.");
					personToRemove = personServ.getPersonById(Integer.valueOf(scan.nextLine()));
				} else if (input == 2) {
					System.out.println("Enter the username of the user you want to remove.");
					personToRemove = personServ.getPersonByUsername(scan.nextLine());
				}
				if (input == 1 || input == 2) {
					if (personToRemove != null) {
						System.out.println(personToRemove);
						System.out.println("Remove this user? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							personServ.deletePerson(personToRemove);
							System.out.println("You removed "
									+ personToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if (input == 2) {
				Person newAccount = new Person();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:\n1. Employee\nother. User");
				input = Integer.valueOf(scan.nextLine());
				Role role = new Role();
				if (input == 1) {
					role.setId(1);
					role.setName("Employee");
				} else {
					role.setId(2);
					role.setName("User");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					try {
						personServ.addPerson(newAccount);
						System.out.println("Added " + newAccount.getUsername() + " successfully.");
					} catch (NonUniqueUsernameException e) {
						System.out.println("Sorry, that username is already taken. Try again.");
					}
				}
			} else {
				break;
			}
		}
		
		return user;
	}
	
	public static void manageOffers() {
		Set<Offer> activeOffers = offerServ.getAllActiveOffers();
		if(activeOffers.size() == 0) {
			System.out.println("No active offers!");
			return;
		}
		
		for(Offer offer : activeOffers) {
			System.out.println(offer.toString());
		}
		System.out.println();
		boolean isManagingOffer = true;	
		
		while(isManagingOffer) {
			System.out.println("Input the id of the offer you would like to accept or reject. Input 0 to cancel.");
			
			try {
				Integer input = Integer.valueOf(scan.nextLine());
				if(input == 0) {
					return;
				}else {
					Offer selected = offerServ.getOfferById(input);
					System.out.println("Press 1 to accept this offer. Press 2 to reject this offer. Press 3 to select a different offer. Press 0 to cancel altogether.");
					input = Integer.valueOf(scan.nextLine());
					
					switch(input) {
						case 0:
							return;
						case 1:
							offerServ.acceptOffer(selected);
							System.out.println("Offer accepted!");
							isManagingOffer = false;
							break;
						case 2:
							offerServ.rejectOffer(selected);
							System.out.println("Offer rejected!");
							isManagingOffer = false;
							break;
						case 3:
							System.out.println("No problem.");
							break;
						default:
							notifyOfInvalidInput();
							break;
					}
				}
			}catch(NumberFormatException e) {
				notifyOfInvalidInput();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void notifyOfInvalidInput() {
		System.out.println("Invalid input! Please try again!");
	}
}
