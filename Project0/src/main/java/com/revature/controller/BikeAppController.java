package com.revature.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Model;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.beans.Offer;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.utils.ConnectionUtil;

public class BikeAppController {
	
	public static Scanner scan;
	private static UserService userServ = new UserServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		//Welcome Screen
		mainloop: while(userActive) {
			System.out.println("Welcome to Rydle Bikes");
			User loggedInUser = null;
			
			//when user is logged in
			while(loggedInUser == null) {
				System.out.println("Login: Enter Number to Select option");
				System.out.println("1. Register\n2. Log In\n3. Quit");
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
					break mainloop;
				
				}
				
				
				
				
			}
			
			//User Menu
			menuLoop: while (true) {
				System.out.println("Main Menu: Enter Number to Select Option");
				System.out.println("1. Available Bikes\n2. View your Bikes and Bills\n6. Log Out");
				
				//check if user is a customer or an employee
				if (loggedInUser.getRole().getName().equals("Customer")) {
					//System.out.println("6. Log out");
				} else if (loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage Bikes\n4. Manage Offers\n5. View all Payments");
				}
				int userInput = Integer.valueOf(scan.nextLine());
				
				switch (userInput) {
				case 1:
					loggedInUser = viewAvailableBikes(loggedInUser);
					break;
				
				case 2:
					loggedInUser = viewUserBikes(loggedInUser);
					break;
				case 3:
					loggedInUser = manageBikes(loggedInUser);
					break;
				case 4:
					loggedInUser = manageOffers(loggedInUser);
					break;
				case 5:
					loggedInUser = viewPayments(loggedInUser);
					
				default:
					System.out.println("Thanks for visiting Rydle Bikes!");
					loggedInUser = null;
					break menuLoop;
				
				
				} 
			}
			
			
			
		}
		scan.close();
	}
	
	
	//Register user
	private static User registerUser() {
		while (true) {
			User newAccount = new User();
			System.out.println("Enter a username:\n ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password:\n ");
			newAccount.setPassword(scan.nextLine());
			System.out.println("Enter your first name:\n ");
			newAccount.setFirstName(scan.nextLine());
			System.out.println("Enter your last name:\n ");
			newAccount.setLastName(scan.nextLine());
			Role r = new Role();
			r.setId(1);
			r.setName("Customer");
			newAccount.setRole(r);
			System.out.println("Does this look good?\n");
			System.out.println("Username: " + newAccount.getUsername()
					+ " \nPassword: " + newAccount.getPassword()
					+ "\nFirst Name: " + newAccount.getFirstName()
					+ "\nLast Name: " + newAccount.getLastName());
			System.out.println("1. confirm\n2. start over\n3. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setId(userServ.addUser(newAccount));
					System.out.println("Confirmed. Welcome!");
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
	
	//login user
	private static User logInUser() {
		while (true) {
			System.out.println("Enter username:\n ");
			String username = scan.nextLine();
			System.out.println("Enter password:\n ");
			String password = scan.nextLine();
			
			User user = userServ.getUserByUsername(username);
			if (user == null) {
				System.out.print("Username does not exsist. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("Incorrect Password. ");
			}
			System.out.println("Do you want to try again?\n1. Yes\n2. No.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
		
		
	}
	
	//Customer View Available Bikes
	private static User viewAvailableBikes(User user) {
		
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();

		for (Bike bike : availableBikes) {
			System.out.println(bike);
		}

		System.out.println("Would you like to Buy a Bike?\n 1. Yes\n2. No");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which Bike? Type its ID.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.getStatus().getName().equals("Available")) {
					System.out.println(bike);
					System.out.println("Do you want to make an offer to buy the " + bike.getName() + "?\n1. Yes\n2.No");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						//offerServ.(user, bike);
						Offer offer = new Offer();
						offer.setUserId(user.getId());
						offer.setBikeId(bike.getId());
						offer.setOfferStatus("Pending");
						offerServ.addOffer(offer);
						System.out.println("You did it! You offered for a bike " + bike.getName() + ".");
						// get the person with their updated cat set
						user = userServ.getUserById(user.getId());
						break;
					} else {
						System.out.println("Would you like to buy another Bike?\n1. Yes\2. No");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Sorry, that's not an available bike. Do you want to try again?\n"
							+ "1. Yes\n2. No");
					input = Integer.valueOf(scan.nextLine());
					if (input != 1) {
						System.out.println("Okay, that's fine.");
						break;
					}
				}
			}
		} else {
			System.out.println("Okay, that's fine, bye.");
		}
		return user;
	}
	
	//Customer View OWN bikes
	private static User viewUserBikes(User user) {
		
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your bikes:\n--------------------------------------\n ");
			for (Bike bike : user.getBikes()) {
				System.out.println(bike);
				System.out.println("Your Weekly Payment: " + bike.getPrice()/52 + " Dollars\nYour Remaining Balance: " + bike.getPrice() + "\n");
			}
			System.out.println("________________________________________\n");
		} else {
			System.out.println("You don't have do not own a bike!\n");
		}
		return user;
	}
	
	
	//Employee Manage Bikes
	private static User manageBikes(User user) {
		
		System.out.println("Manage Bikes: \n1. Create Bike\n2. Delete Bike\n3. Back to Main Menu");
		int input;
		input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			Bike newBike = new Bike();
			System.out.println("Enter a bike name: ");
			newBike.setName(scan.nextLine());
			System.out.println("Enter a price name: ");
			newBike.setPrice(Double.valueOf(scan.nextLine()));
			System.out.println("Choose a model by entering its ID:"
					+ "\n1. Mongoose\n2. BMX\n3. Huffy\n4. FisherPrice\n Other");
			Model model = new Model();
			model.setId(Integer.valueOf(scan.nextLine()));
			switch(model.getId()) {
			case 1:
				model.setName("Mongoose");
				break;
			case 2:
				model.setName("BMX");
				break;
			case 3:
				model.setName("Huffy");
				break;
			case 4:
				model.setName("FisherPrice");
				break;
			default:
				model.setName("GreatValue");
			}
			newBike.setModel(model);
			Status status = new Status();
			status.setId(1);
			status.setName("Available");
			newBike.setStatus(status);
			System.out.println(newBike);
			System.out.println("Look good? 1 to confirm, other to start over");
			input = Integer.valueOf(scan.nextLine());
			if (input == 1) {
				newBike.setId(bikeServ.addBike(newBike));
				System.out.println("You successfully added " + newBike.getName() + "!");
			}
		}else if (input == 2) {
			
			for (Bike bike : bikeServ.getAvailableBikes()) {
				System.out.println(bike);
				//print out all the bikes
			}
			System.out.println("Enter the ID of the Bike you want to Delete");
			Bike bikeToRemove = null;
			bikeToRemove = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
			System.out.println("Bikes To Delete:\n-------------\n");
			System.out.println(bikeToRemove);
			System.out.print("----------------\n");
			System.out.println("Remove this Bike?\n1. Yes\n2. No");
			input = Integer.valueOf(scan.nextLine());
			if(input == 1) {
				bikeServ.removeBike(bikeToRemove);
				System.out.println("You Deleted " + bikeToRemove.getName() + " Successfully!");
			}
		}else {
			System.out.println("Going Back to Menu");
		}
		
		return user; 
	}
	//Employee Manage Offers
	private static User manageOffers(User user) {
		System.out.println("Current Offers:\n------------------------------------------\n");
		
		Set<Offer> availableOffers = offerServ.getAvailableOffers();

		for (Offer offer : availableOffers) {
			System.out.println(offer);
		}
		
		System.out.println("_______________________________________");
		
		System.out.println("Manage Offers: \nPlease enter the  Offer_ID of the Offer you want to View:\n");
		
		
		Offer offerToJudge = offerServ.getById(Integer.valueOf(scan.nextLine()));
		System.out.println("Judge Offer:\n-----------------------------------------------\n");
		System.out.println(offerToJudge);
		System.out.println("___________________________________________________\n");
		System.out.println("Accept this offer?\n1. Yes\n2. No");
		
		int input = Integer.valueOf(scan.nextLine());
		//input = Integer.valueOf(scan.nextLine());
		
		if(input == 1) {
			//assign user_id and bike_id to user_bike table
			Offer offer = new Offer();
			offer.setId(offerToJudge.getId());
			offer.setUserId(offerToJudge.getUserId());
			offer.setBikeId(offerToJudge.getBikeId());
			
			//Change offer from pending to accepted
			offerServ.completeOffer(offer);
			
			//update availability of bike to Not Available
			Bike bike = new Bike();
			bike.setId(offerToJudge.getBikeId());
			bikeServ.updateBike(bike);
			//assign bike
			
			System.out.println("----Accepted Offer----\n------------------");
			
		} else if (input == 2 ) {
			//reject the offer by deleting the offer
			Offer offer = new Offer();
			offer.setId(offerToJudge.getId());
			offerServ.removeOffer(offer);
			//execute deletion
			
			System.out.println("else");
			
		}else {System.out.println("You gave me garbage, back to main");}
		
		return user;
	}
	
	//Employee View Payments
	private static User viewPayments(User user) {
		return user;
	}
	
	
	
	
}
