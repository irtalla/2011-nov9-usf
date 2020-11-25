package com.james.controller;

import java.lang.ModuleLayer.Controller;
import java.util.Scanner;


import com.james.beans.*;
import com.james.data.BikePostgres;
import com.james.data.OfferPostgres;
import com.james.exceptions.NonUniqueUsernameException;


import com.james.services.PersonService;
import com.james.services.PersonServiceImpl;


public class BikeStoreAppController {
	private static Scanner scan;
	private static PersonService personServ = new PersonServiceImpl();	
	private static BikePostgres bikePostgres = new BikePostgres();
	private static OfferPostgres offerPostgres = new OfferPostgres();	
	
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
			
		
		Person loggedInUser = null;		
		System.out.println("Hello! Welcome to the Bike Shop!");
				
		while (loggedInUser == null) {
			System.out.println("What would you like to do?");
			System.out.println("1. Register\n2. Log in\nother. Exit");
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch (userInput) {
			case 1:
				loggedInUser = registerUser();				
			case 2:
				loggedInUser = logInUser();
				break;			
			}
		}		
		scan.close();
	}
	//	Bike Section
	private static Bike addBike () { 
		Bike newBike = new Bike();
		System.out.println("Enter bike name: ");
		newBike.setName(scan.nextLine());
		System.out.println("Enter bike's condition: ");
		newBike.setCondition(Integer.valueOf(scan.nextLine()));
		System.out.println("Does this look good?");		
		System.out.println("Name: " + newBike.getName() + "   Condition: "+ newBike.getCondition());
		System.out.println("1 to confirm, 2 to start over, other to cancel");
		int input = Integer.valueOf(scan.nextLine());
		
		switch (input) {
		case 1:
			try {
				newBike = bikePostgres.add(newBike);
				System.out.println("Confirmed. New bike added!");
				return newBike;
			} catch (NonUniqueUsernameException e) {
				
			}
			break;
		case 2:
			System.out.println("Okay, let's try again.");
			break;
		default:
			System.out.println("Okay, let's go back.");
			return null;
		}
		return null;		
	}
	
	private static void deleteBike () {
		Bike removeBike = new Bike();
		System.out.println("Enter the name of the bike you want to remove:  ");
		removeBike.setName(scan.nextLine());
		bikePostgres.delete(removeBike);
		System.out.println(removeBike.getName() +  " has been successfully removed from the database!");
	}
	
	private static void viewAvailableBikes () {
		// Set<Bike> availableBikes;
		bikePostgres.view();
		
	}
	
	private static Offer acceptReject() {
		int input;		
		Offer off = new Offer();		
		System.out.println("Do you want to accept offer?  1? Or reject offer?     2?");
		input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			off.setOfferState("owned");
			offerPostgres.update(off);
			System.out.println("You have successfull accept the offer.");
			return off;
		} else { 
			off.setOfferState("rejected");
			offerPostgres.update(off);
			System.out.println("You have successfull rejected the offer.");
			return off;
		}
		
	}
	
	private static void viewBikesOwned() {
		try {
			offerPostgres.view();
		} catch (NonUniqueUsernameException e) {
			
			e.printStackTrace();
		}
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
					System.out.println("Confirmed. Welcome new customer!");
					Person loggedInUser = null;	
					loggedInUser = logInUser();
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
				if (user.getRole().getId().equals(1)) { 
					employeePortal(username);
				} else {
					
					customerPortal(username);
				}
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
	
	private static void customerPortal(String username) {
		System.out.println("Welcome to the customer portal!");
		
		int input = 0;
		while (input !=4)	{	
		System.out.println("What would you like to do?");
		System.out.println("1. View available bikes.\n2. Make an offer\n3. View bike owned?\n4. Exit");
		input = Integer.valueOf(scan.nextLine());		
		
		
			switch (input) {
			case 1: viewAvailableBikes();
				break;
			case 2:	makeAnOffer(username);
				break;
			case 3: viewBikesOwned();;
				break;
			default:
				System.out.println("Okay, let's go back.");				
				Person loggedInUser = null;	
				loggedInUser = logInUser();
			}
		}
			
} 
			
		
	
	
	
	private static Offer makeAnOffer(String username) {
		Offer off = new Offer();
		off.setUserName(username);		
		
		
		System.out.println("To make an offer.\n");
		System.out.println("Enter bike name : ");
		off.setBikeName(scan.nextLine());
		System.out.println("Enter the offer: ");
		off.setOffer(Float.valueOf(scan.nextLine())); 
		System.out.println("Does this look good?");		
		System.out.println("Name: " + off.getBikeName() + "   offer =  "+ off.getOffer());
		System.out.println("1 to confirm, 2 to start over, other to cancel");
		int input	= Integer.valueOf(scan.nextLine());
		
		switch (input) {
		case 1:
			try {
				off = offerPostgres.add(off);
				System.out.println("Confirmed. New offer added!");
				return off;
			} catch (NonUniqueUsernameException e) {
				
			}
			break;
		case 2:
			System.out.println("Okay, let's try again.");
			break;
		default:
			System.out.println("Okay, let's go back.");
			return null;
		}
		return null;
		
	}
	
	
	private static void employeePortal (String username) {
		// Bike myBike =null;
		System.out.println("Welcome " + username + " to the employee portal!");		
		//	Offer off = null; 
		//	off = acceptReject(); do not work!
		int input = 0;
		while (input !=4)	{	
		System.out.println("What would you like to do?");
		System.out.println("1. Add a bike.\n2. Delete a bike\n4. Exit");
		input = Integer.valueOf(scan.nextLine());		
		
		
			switch (input) {
			case 1:  
				Bike myBike = addBike();
				break;
			case 2: deleteBike();;
				break;			
			default:
				System.out.println("Okay, let's go back.");				
				Person loggedInUser = null;	
				loggedInUser = logInUser();
			}
		}
		
		//myBike = addBike();		
		//deleteBike();
		// Add String length constraint 
		//Offer off = null;
		//off = acceptReject();
		
		
	}
	
}	
