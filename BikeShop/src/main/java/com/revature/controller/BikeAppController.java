package com.revature.controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Offer;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeAppController {
	private static Scanner scan;
	private static UserService userServ = new UserServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to BikeShop!");
			User loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in\nother. Quit");
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
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do?");
				System.out.println("1. Show bikes for sale\n2. Show my bikes");
				if (loggedInUser.getRole().getName().equals("User")) {
					System.out.println("other. Log out");
				} else if (loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage bike inventory\n4. Manage users\n5. Manage offers\n6. See user payments\nother. Log out");
				}
				int userInput = Integer.valueOf(scan.nextLine());
				switch (userInput) {
				case 1:
					loggedInUser = viewAvailableBikes(loggedInUser);
					break;
				case 2:
					viewUserBikes(loggedInUser);
					break;
				case 3:
					loggedInUser = manageBikes(loggedInUser);
					break;
				case 4:
					loggedInUser = manageUsers(loggedInUser);
					break;
				case 5:
					loggedInUser = manageOffers(loggedInUser);
					break;
				case 6:
					seePayments(loggedInUser);
					break;
				default:
					System.out.println("See you next time!");
					loggedInUser = null;
					break menuLoop;
				}
			}
		}
		scan.close();
	}

	private static void seePayments(User user) {
		if ((user.getRole().getName().equals("Employee")))
			System.out.println("No users have made payments yet.");
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
			Role r = new Role();
			r.setId(1);
			r.setName("User");
			newAccount.setRole(r);
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setId(userServ.addUser(newAccount));
					System.out.println("Confirmed. Welcome!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken - try again.");
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
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	
	/*
	 * returns updated person object in case the user adopts a cat
	 */
	private static User viewAvailableBikes(User user) {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		
		for (Bike bike : availableBikes) {
			System.out.println(bike);
		}
		
		System.out.println("Would you like to make an offer for a bike? 1 for yes, other for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which bike? Enter the ID.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.getStatus().getName().equals("Available")) {
					System.out.println(bike);
					System.out.println("You want to make an offer for this bike? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						System.out.println("How much are you offering for this bike (in dollars)?");
						input = Integer.valueOf(scan.nextLine());
						if (input > 0) {
						Offer o = new Offer();
						o.setBike(bike);
						o.setUser(user);
						o.setAmount(input);
						offerServ.addOffer(o);
						System.out.println("Offer submitted! The bike will be added to your account if your offer is accepted.");
						// get the user with their updated bikes
						user = userServ.getUserById(user.getId());
						break;
						}
						else {
							System.out.println("You have to offer a positive amount of dollars :/");
							System.out.println("Returning to menu...");
							break;
						}
					} else {
						System.out.println("Okay, did you want to offer on a different bike? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Sorry, that bike isn't available for sale. Do you want to try again?"
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
	
	private static void viewUserBikes(User user) {
		Set<Bike> bikes = null;
		try {
			bikes = userServ.getBikesByUserId(user.getId());
		
		if (bikes.size() > 0) {
			System.out.println("Viewing your bikes: ");
			for (Bike bike : bikes) {
				System.out.println(bike);
			}
			boolean seeDebt = false;
			while (true) {
				System.out.print("...would you like to see payments owed on a");
				if (seeDebt)
					System.out.print("nother");
				System.out.println(" bike? 1 for yes, other for no");
				int input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Which one? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Bike bike = bikeServ.getBikeById(input);
					if (bike != null && user.getBikes().contains(bike)) {
						System.out.println("You owe $" + bike.getPayment() + " for bike #" + bike.getId() + ".");
						seeDebt = true;
					} else
						System.out.println("Sorry, that's not one of your bikes.");
				} else {
					break;
				}
			}
		} else {
			System.out.println("You don't have any bikes... :(");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * employee-only menu:
	 * returns null if the user is not an employee
	 */
	private static User manageBikes(User user) {
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Bikes:\n1. Add a bike to inventory\n2. Edit a bike\n3. Remove a bike from inventory\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				Bike newBike = new Bike();
				System.out.println("Enter chassis color: ");
				newBike.setColor(scan.nextLine());
				System.out.println("Please enter brand ID:"
						+ "\n1. Schwinn\n2. Diamondback\n3. Fuji\nother. Giant");
				Brand brand = new Brand();
				brand.setId(Integer.valueOf(scan.nextLine()));
				switch(brand.getId()) {
				case 1:
					brand.setName("Schwinn");
					break;
				case 2:
					brand.setName("Diamondback");
					break;
				case 3:
					brand.setName("Fuji");
					break;
				default:
					brand.setName("Giant");
					break;
				}
				newBike.setBrand(brand);
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newBike.setStatus(status);
				System.out.println(newBike);
				System.out.println("Is this correct? 1 to confirm, other to start over");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newBike.setId(bikeServ.addBike(newBike));
					System.out.println("You successfully added this bike to the inventory!");
				}
			} else if (input == 2) {
				for (Bike bike : bikeServ.getAvailableBikes()) {
					System.out.println(bike);
				}
				System.out.println("Which bike would you like to update? Enter its ID.");
				Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				Bike newBike = bike;
				Brand newBrand = new Brand();
				if (bike != null) {
					System.out.println("Editing bike #" + bike.getId());
					System.out.println("Current changes:\nColor: " + newBike.getColor()
							+ " Brand: " + newBike.getBrand().getName());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Color\n2. Brand\n3. Save changes\nother. Cancel");
						input = Integer.valueOf(scan.nextLine());
						switch (input) {
						case 1:
							System.out.println("Enter new color: ");
							bike.setColor(scan.nextLine());
							break;
						case 2:
							System.out.println("Enter new brand id: ");
							newBrand.setId(Integer.valueOf(scan.nextLine()));
							switch(newBrand.getId()) {
							case 1:
								newBrand.setName("Schwinn");
								newBike.setBrand(newBrand);
								break;
							case 2:
								newBrand.setName("Diamondback");
								newBike.setBrand(newBrand);
								break;
							case 3:
								newBrand.setName("Fuji");
								newBike.setBrand(newBrand);
								break;
							default:
								newBrand.setName("Giant");
								newBike.setBrand(newBrand);
								break;
							}
						case 3:
							bikeServ.updateBike(newBike);
							System.out.println("You updated bike #" + newBike.getId() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} else if (input == 3) {
				System.out.println("Enter the ID of the bike you want to remove");
				Bike bikeToRemove = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				
				if (bikeToRemove != null) {
					System.out.println(bikeToRemove);
					System.out.println("Remove this bike? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						bikeServ.removeBike(bikeToRemove);
						System.out.println("You removed the "
								+ bikeToRemove.getColor() + " " + 
								bikeToRemove.getBrand().getName() + " successfully.");
					}
				} else {
					System.out.println("That bike doesn't exist.");
				}
				
			}
			else {
				break;
			}
		}
		
		return user;
	}
	
	/*
	 * employee-only menu:
	 * returns null if the user is not an employee
	 */
	private static User manageUsers(User user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Customers:\n1. Remove a user\n2. Add a user\nother. Cancel");
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
							userServ.removeUser(personToRemove);
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
				System.out.println("Choose a role:\n1. Employee\nother. User");
				input = Integer.valueOf(scan.nextLine());
				Role role = new Role();
				if (input == 1) {
					role.setId(3);
					role.setName("Employee");
				} else {
					role.setId(1);
					role.setName("User");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					try {
						userServ.addUser(newAccount);
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
	
	private static User manageOffers(User user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Offers:\n1. View Offers\n2. Accept/Reject Offer\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				if (offerServ.getOffers().size() > 0) {
				System.out.println("Showing offers on bikes:");
				for (Offer offer : offerServ.getOffers()) {
						System.out.println(offer);
					}
				} else {
					System.out.println("No one has submitted any offers yet.");
				}
			} else if (input == 2) {
				System.out.println("Type the ID of the offer you would like to accept or reject.");
				input = Integer.valueOf(scan.nextLine());
				Offer o = offerServ.getOfferById(input);
				System.out.println(o);
				System.out.println("1. Accept this offer\n2. Reject this offer\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				switch (input) {
				case 1:
					offerServ.acceptOffer(o);
					System.out.println("Offer #" + o.getId() + " accepted!");
					System.out.println("Buyer's weekly payment is $" + o.getAmount()/52);
					break;
				case 2:
					System.out.println("Offer #" + o.getId() + " rejected and removed from the system.");
					offerServ.removeOffer(o);
					break;
				default:
					break;
				}
			} else {
				break;
			}
		}
		
		return user;
	}
}
