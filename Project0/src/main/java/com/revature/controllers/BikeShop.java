package com.revature.controllers;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;

public class BikeShop {
	private static Scanner scan;
	private static PersonService personServ;
	private static CatService bikeServ;
	
	static {
		scan = new Scanner(System.in);
		personServ = new PersonServiceImpl();
		bikeService = new BikeServiceImpl();
		offerService = new OfferServiceImpl();
	}
	
	public static void main(String[] args) {
		boolean proceed = true;
		Scanner scan = new 
		while(proceed) {
			System.out.println("Hello! Welcome to the Bike Shop!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in\nother. Exit");
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
			Role r = new Role();
			r.setId(2);
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
					newAccount.setId(personServ.addPerson(newAccount));
					System.out.println("Confirmed. Welcome!");
					return newAccount;
				} bikech (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken - let's try again.");
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
		Set<Bike> availableBikes = bikeServ.getAvailableCats();
		
		for (Bike bike : availableBikes) {
			System.out.println(bike);
		}
		
		System.out.println("Would you like to make an offer for a bike? 1 for yes, other for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which bike? Type its ID.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.getStatus().getName().equals("Available")) {
					System.out.println(bike);
					System.out.println("You want to make an offer to purchase a " + bike.getBrand() + " model " +  bike.getModel() + " in " + bike.getColor() + "? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						Offer newOffer = new Offer();
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
						
						bikeServ.receiveOffer(bike, newOffer);
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
				System.out.print("View Remaining Payments?");
				System.out.println("one? 1 for yes, other for no");
				int input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Which one? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Bike bike = bikeServ.getCatById(input);
					if (bike != null && user.getBikes().contains(bike)) {
						System.out.println("Your remaining payments for the " + bike.getBrand() "bike with id " + bike.getId() + "are " + bikeServ.getRemainingPaymentBalanc() + "for " + bike.getRemainingWeeklyPayments() + " more weeks" );
						viewRemainingPayments = false;
					} else
						System.out.println("Sorry, that's not one of your bikes.");
				} else {
					break;
				}
			}
		} else {
			System.out.println("You don't have any bikes... yet.");
		}
	}
	
	private static Person manageCats(Person user) {
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Bikes:\n1. Add a bike to the inventory\n2. Edit a bike in the inventory\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
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
				System.out.println(newBike);
				System.out.println("Look good? 1 to confirm, other to start over");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newBike.setId(bikeServ.addCat(newBike));
					System.out.println("You successfully added a " + newBike.getBrand() + "wit id " + newBike.getId() + "!");
				}
			} else if (input == 2) {
				for (Bike bike: bikeServ.getAvailableCats()) {
					System.out.println(bike);
				}
				System.out.println("Which bikewould you like to update? Enter its ID.");
				Bike bike= bikeServ.getCatById(Integer.valueOf(scan.nextLine()));
				Bike newBike = bike;
				if (bike!= null) {
					System.out.println("Editing bike with id" + bike.getId());
					System.out.println("Current changes:\nBrand: " + newBike.getModel()
							+ " Model: " + newBike.getModel() + " Color: " + newBike.getColor());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Brand\n2. Model\n3. Color\n4. Save changes\nother. Cancel");
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
							bikeServ.updateBike(newBike);
							System.out.println("You updated the bike with brand " + newBike.getBrand() + " and id " + newBike.getId() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} else {
				break;
			}
		}
		
		return user;
	}
	
	private static Person manageUsers(Person user) {
		// if a user is not an employee, then they meant to log out, not go to this menu
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Person personToRemove = null;
				if (input == 1) {
					System.out.println("Enter the ID of the user you want to remove.");
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
}
