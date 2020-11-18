package dev.elliman.controller;

import java.util.Scanner;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.data.PersonDAO;
import dev.elliman.data.PersonDAOFactory;
import dev.elliman.exceptions.NonUniqueUsernameException;
import dev.elliman.services.PersonService;
import dev.elliman.services.PersonServiceImpl;

public class BikeShopController {

	/*
	 * loop the program so people can logout and login without restarting the program
	 */
	private static boolean stop = false;

	/*
	 * This is the only class that will have input so I am not doing anything fancy with the scanner
	 */
	private static Scanner input;

	/*
	 * 
	 */
	private static PersonService personService = new PersonServiceImpl();

	/*
	 * The current logged in user
	 */
	private static Person currentUser;

	public static void main(String[] args) {

		//check for an admin user to be able to create managers and employees
		Person admin = personService.getPersonById(0);
		if(admin == null) {
			personService.addAdminUser();
		}


		input = new Scanner(System.in);
		int selection = 0;

		while(!stop) {
			System.out.println("Welcome to the bike shop. What would you like to do?\nTo use this program enter the numbers of the options to select.");
			selection = getInput("Login", "Create a new account", "Stop");

			if(selection == 1) {
				//create a new account
				Person newCustomer = register();
				try {
					personService.createUser(newCustomer);
					currentUser = newCustomer;
					System.out.println("Account created successfully. Welcome to the bike shop.");
					useApplication();
				} catch (NonUniqueUsernameException e) {
					//e.printStackTrace();
					System.out.println("There was an problem creating your account, returning you to the login menu.");
					continue;
				}
			} else if(selection == 0) {
				currentUser = login();
				useApplication();
			} else {
				stop = true;
				continue;
			}

			//at this point the person is logged in

		}
	}

	private static int getInput(String... options) {
		while(true) {
			for(int i = 0; i < options.length; i++) {
				System.out.println(i + " - " + options[i]);
			}

			try {
				return Integer.valueOf(input.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, please try again");
			}
		}
	}

	private static Person register() {
		System.out.println("Welcome to the bike shop account creation.");
		System.out.print("Enter your first name: ");
		String firstName = input.nextLine();
		System.out.print("Enter your last name: ");
		String lastName = input.nextLine();

		String username;
		String password;
		while(true) {
			System.out.print("Enter a username: ");
			username = input.nextLine();

			if(personService.getPersonByUsername(username) == null) {
				break;
			} else {
				System.out.println("Username already taken");
			}
		}

		System.out.print("Enter a password: ");
		password = input.nextLine();

		return new Person(firstName, lastName, username, password, new Role());
	}

	private static Person login() {

		while(true) {
			System.out.print("Username: ");
			String username = input.nextLine();
			System.out.print("Password: ");
			String password = input.nextLine();

			Person account = personService.getPersonByUsername(username);
			if(account == null) {
				System.out.println("Username not found");
			} else if(account.getPassword().equals(password)) {
				System.out.println("Welcome back");
				return account;
			} else {
				System.out.println("incorrect password");
			}

			System.out.println("Would you like to try again?");
			int answer = getInput("Yes", "No");
			if(answer != 0) {
				return null;
			}
		}
	}
	
	private static void useApplication() {
		System.out.println("Use Application");
		
		logout();
	}
	
	private static void logout() {
		currentUser = null;
	}
}
