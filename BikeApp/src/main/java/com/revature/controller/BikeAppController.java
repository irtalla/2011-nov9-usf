package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.Type;
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
					loggedInUser = manageBikes(loggedInUser);
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
	
	private static Person manageBikes(Person loggedInUser) {
		if (!(loggedInUser.getRole().getName().equals("Employee")))
			return null;
		while(true){
			System.out.println("Manage Bikes\n1. Add a bike\n2Edit a bike\nOther. Back");
			int input = Integer.valueOf(scan.nextLine());
			if (input == 1){
				Bike newbike = new Bike();
				System.out.println("Select bike brand by entering its ID:\n"
						+"1. Diamondback\n2.Trek\n3. Fuji\n4. Santana\n");
				Brand newbrand = new Brand();
				newbrand.setId(Integer.valueOf(scan.nextLine()));
				switch (newbrand.getId()) {
				case 1:
					newbrand.setName("Diamondback");
					break;
				case 2:
					newbrand.setName("Trek");
					break;
				case 3:
					newbrand.setName("Fuji");
					break;
				case 4:
					newbrand.setName("Santana");
					break;
				}
				newbike.setBrand(newbrand);
				
				System.out.println("Choose bike type by entering its ID:\n"
						+ "1. Mountain\n2.Racing\n3.Electric\n4.Tandem\n");
				Type newbiketype = new Type();
				newbiketype.setId(Integer.valueOf(scan.nextLine()));
				switch (newbiketype.getId()) {
				case 1:
					newbiketype.setName("Mountain");
					break;
				case 2:
					newbiketype.setName("Racing");
					break;
				case 3:
					newbiketype.setName("Electric");
					break;
				case 4:
					newbiketype.setName("Tandem");
					break;
				}
				newbike.setType(newbiketype);
				System.out.println("Choose a color:\n1. Red\n2. Yellow\n3.Green\n4. Blue\n5. Purple"
						+ "\n6. Black\n7. White");
				System.out.println("Enter bike's year.\n");
				newbike.setYear(Integer.valueOf(scan.nextLine()));
				System.out.println("Enter price of bike.\n");
				newbike.setPrice(Double.valueOf(scan.nextLine()));
				Status newstatus = new Status();
				newstatus.setId(1);
				newstatus.setName("Available");
				System.out.printf("Are you sure you want to add the %s %s %s %s bike for$%f? \n1. Yes\n2. No",
				newbike.getYear(), newbike.getColor(), newbike.getBrand(),newbike.getType(), newbike.getPrice());
				input = Integer.valueOf(scan.nextLine());
				if(input ==1){
					newbike.setStatus(newstatus);
					System.out.printf("The %s %s %s %s bike has been successfully added to the store for $%f.",
					newbike.getYear(), newbike.getColor(), newbike.getBrand(),newbike.getType(), newbike.getPrice());
				} 			
			}else if(input ==2){
				for (Bike bike : bikeServ.getAvailableBikes()){
					System.out.println(bike);
				}
				System.out.println("Which bike would you like to edit? Enter its ID");
				Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				Bike newBike = bike;
				if(bike != null){
					System.out.println("Editing " + bike.getName());
					System.out.println("Current changes:\nColor: " + newBike.getColor()
					+ "\nPrice: "+newBike.getPrice());
					boolean editing = true;
					while(editing){
						System.out.println("Edit:\n1. Color\n2. Price\n3. Save\nOther Cancel");
						input = Integer.valueOf(scan.nextLine());
						switch(input){
						case 1:
							System.out.println("Enter new color: ");
							bike.setColor(scan.nextLine());
							break;
						case 2:
							System.out.println("Enter new price: ");
							bike.setPrice(Double.valueOf(scan.nextLine()));
							break;
						case 3:
							bikeServ.updateBike(newBike);
							System.out.println("You updated " + newBike.getName() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} else{
				break;
			}
		}
		return loggedInUser;			
	}

	private static Person manageUsers(Person loggedInUser) {
		if (!(loggedInUser.getRole().getName().equals("Employee")))
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
		
		return loggedInUser;
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
		
		System.out.println("Would you like to buy a bike? 1 for yes, other for no");
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
						System.out.println("How much are you willing to offer?");
						double offer = Double.valueOf(scan.nextLine());
						System.out.printf("Are you sure you want to offer $%f?\n1. Yes\nOther. No", offer);
						input = Integer.valueOf(scan.nextLine());
						if (input == 1){
							bikeServ.addOffer(user, bike);
							System.out.printf("You have made on offer on  the %s %s %s %s bike for $%f.\n",
							bike.getYear(), bike.getColor(), bike.getBrand(),bike.getType(), offer);
							user = personServ.getPersonById(user.getId());
							break;
						} else break;
						
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
				System.out.println("Welcome back, "+ username+ "!");
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
