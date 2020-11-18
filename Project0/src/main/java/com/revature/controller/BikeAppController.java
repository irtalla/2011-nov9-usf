package com.revature.controller;

import java.util.Scanner;

import com.revature.beans.Bike;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.User;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeAppController {
	
	public static Scanner scan;
	
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
					System.out.println("3. Manage Bikes\n4. Manage Offers\5. View all Payments\n Q to Log out");
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
		
	}
	
	//login user
	private static User logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			User user = UserServ.getUserByUsername(username);
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
