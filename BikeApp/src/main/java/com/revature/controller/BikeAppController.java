package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImplementation;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImplementation;

import io.javalin.http.Context;


public class BikeAppController {
	
	private static Scanner scan;
	private static PersonService personServ = new PersonServiceImplementation();

	private static BikeService bikeServ = new BikeServiceImplementation();
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Greetings, fellow biker! Welcome to BikeApp!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in\n3. Exit");
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
				System.out.println("1. View available bikes\n2. View my bikes");
				if (loggedInUser.getRole().getName().equals("User")) {
					System.out.println("other. Log out");
				} else if (loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage bikes\n4. View offers\nother. Log out");
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
					loggedInUser = (loggedInUser);
					break;
				case 4:
					loggedInUser = manageUsers(loggedInUser);
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
			}
		}
		scan.close();
	}
	
	private static Person manageUsers(Person loggedInUser) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void viewUserBikes(Person user) {
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your bikes: ");
			for (Bike bike : user.getBikes()) {
				System.out.println(bike);
			}
		} else {
			System.out.println("You currently don't own any bikes.");
		}
		
	}

	private static Person viewAvailableBikes(Person user) {
		Set<Bike> availablebikes = bikeServ.getAvailableBikes();
		
		for (Bike bike : availablebikes) {
			System.out.println(bike);
		}
		
		System.out.println("Would you like to adopt a cat? 1 for yes, other for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which bike? Type its ID.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.getStatus().getName().equals("Available")) {
					System.out.println(bike);
					System.out.println("Do you want to make an offer on  " + bike.getName() + "? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						bikeServ.addOffer(user, bike);
						System.out.println("You have made on offer on  " + bike.getName() + ".");
						// get the person with their updated bike set
						user = personServ.getPersonById(user.getId());
						break;
					} else {
						System.out.println("Do you wish to cancel your offer? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Sorry, " + bike.getName() + " is currently unavailable. Do you wish to make another selection?"
							+ " 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input != 1) {
						System.out.println("Returning to bike selection menu....");
						break;
					}
				}
			}
		} else {
			System.out.println("Returning to main menu...");
		}
		
		return user;
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
					System.out.println("User Confirmed. Welcome to Bike App!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
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

	public static void getBikeById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Bike bike = bikeServ.getBikeById(id);
		if(bike != null){
			ctx.status(200);
			ctx.json(bike);
		} else {
			ctx.status(404);
		}
	}
	

}
