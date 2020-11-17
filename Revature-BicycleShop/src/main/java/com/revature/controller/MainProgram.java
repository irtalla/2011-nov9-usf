package com.revature.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.*;
import com.revature.services.*;

public class MainProgram {
	//note that as time goes on, this will be phased out for the database
	
	private static UserService userActions = new UserFunctions();
	private static EmployeeService employeeActions = new EmployeeFunctions();
	private static CustomerService customerActions = new CustomerFunctions();
	
	private static Customer currentCustomer;
	private static Employee currentEmployee;
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		loginRegistrationLoop(scanner);
		
		scanner.close();
	}
	
	private static void loginRegistrationLoop(Scanner scanner) {
		String option = "";
		
		while (true) {
			System.out.println("Welcome to Punk-Sure, the best Earthbound bike shop around!");
			System.out.println("Press 1 to register or 2 to log in. Press anything else to quit.");
			option = scanner.nextLine();
			
			if (option.equals("1")) {
				register(scanner);
			}
			else if (option.equals("2")) {
				System.out.print("Username: ");
				String username = scanner.nextLine();
				System.out.print("Password: ");
				String password = scanner.nextLine();
				User couldLogIn = userActions.validatePotentialUser(username, password);
				if (couldLogIn != null) {
					System.out.println("You have logged in.");
					if (couldLogIn instanceof Customer) {
						currentCustomer = (Customer) couldLogIn;
						customerActions.addCustomerization(currentCustomer);
						System.out.println(currentCustomer.toString());
						customerMenuLoop(scanner);
					}
					else if (couldLogIn instanceof Employee) {
						System.out.println("You're an employee.");
						break;
					}

					break;
				}
				else {
					System.out.println("Incorrect username/password combo.");
				}
			}
			else {
				System.out.println("Thank you for using Punk-Sure.");
				break;
			}
			
		}
	}
	
	private static void register(Scanner scanner) {
		while (true) {
			System.out.print("Please enter your username: ");
			String newUsername = scanner.nextLine();
			System.out.print("Please enter your password: ");
			String newPassword = scanner.nextLine();
			
			System.out.println("Is this what you wanted? (Username: " + newUsername + "  Password: " + newPassword + ")");
			System.out.println("Press 1 to register. Press 2 to redo. Anything else to cancel.");
			String option = scanner.nextLine();
			
			if (option.equals("1")) {
				Customer newCustomer = new Customer(newUsername, newPassword);
				userActions.customerRegistration(newUsername, newPassword);
				System.out.println("You have been registered. Returning to the main menu...");
				break;
			}
			else if (option.equals("2")) {
				System.out.println("Sure. Make sure to think about choosing a strong password.");
			}
			else {
				System.out.println("Returning to the main menu.");
				break;
			}
		}
	}
	
	private static void customerMenuLoop(Scanner scanner) {
		while(true) {
			System.out.println("Welcome, " + currentCustomer.getUsername());
			System.out.println("Please choose an option:");
			System.out.println("1: Make an offer for a bicycle.");
			System.out.println("2: Look at all available bicycles.");
			System.out.println("3: View bicycles I own.");
			System.out.println("4: View pending payments for bicycles I would like to buy.");
			System.out.println("q: Log out (quit).");
			String option = scanner.nextLine();
			
			Set<Bicycle> availableBicycles;
			
			switch (option) {
				case "1":
					System.out.println("Which bicycle would you like to make an offer on?");
					int bicycleIndex = 1;
					availableBicycles = customerActions.viewAllAvailableBicycles();
					for (Bicycle bicycle: availableBicycles) {
						System.out.print((bicycle.getStatus().equals("available")) ? bicycleIndex + ": " + bicycle.getBikeModel() + bicycle.getBikeType() + "\n" : "");
					}
					String bikeOption = scanner.nextLine();
					int bikeOptionAsNum = Integer.parseInt(bikeOption); //handle the NumberFormatException
					Bicycle bikeChosen = (Bicycle)availableBicycles.toArray()[bikeOptionAsNum];
					
					System.out.println("How much do you want to offer for the bike?");
					String potentialOffer = scanner.nextLine();
					double offer;
					offer = Double.parseDouble(potentialOffer);		//handle the NullPointerException and the NumberFormatException found here.
					
					Offer offerObject = new Offer(currentCustomer, bikeChosen, offer);
					customerActions.makeAnOffer(offerObject);
					System.out.println("You have offered $" + offer + " for the " + bikeChosen.getBikeModel() + bikeChosen.getBikeType() + " that was placed by " + bikeChosen.getSeller().getUsername());
					break;
				case "2":
					System.out.println("Here are all of the bicycles that you can make offers for: ");
					availableBicycles = customerActions.viewAllAvailableBicycles();
					for (Bicycle bicycle: availableBicycles) {
						System.out.println((bicycle.getStatus().equals("available")) ? bicycle : "");
					}
					break;
			}
		}
	}
	
	private static void employeeMenuLoop(Scanner scanner) {
		
	}
	
}
