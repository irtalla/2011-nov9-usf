package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeAppController {
	
	public static Scanner scan;
	private static UserService userServ = new UserServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	
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
				System.out.println("1. Register\n2. Log In\n Q to Quit");
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
				System.out.println("1. Available Bikes\n2. View your Bikes\n");
				
				//check if user is a customer or an employee
				if (loggedInUser.getRole().getName().equals("Customer")) {
					System.out.println("other. Log out");
				} else if (loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage Bikes\n4. Manage Offers\n5. View all Payments\n Q to Log out");
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
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			Role r = new Role();
			r.setId(2);
			r.setName("Customer");
			newAccount.setRole(r);
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, Q to cancel");
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
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
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
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
		
		
	}
	
	//Customer View Available Bikes
	private static User viewAvailableBikes() {
		
	}
	
	//Customer View OWN bikes
	private static User viewUserBikes() {
		
	}
	
	
	//Employee Manage Bikes
	private static User manageBikes() {
		
	}
	//Employee Manage Offers
	private static User manageOffers() {
		
	}
	
	//Employee View Payments
	private static User viewPayments() {
		
	}
	
	
	
	
}
