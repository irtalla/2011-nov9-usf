package dev.elliman.controller;

import java.util.Scanner;

import dev.elliman.beans.Person;
import dev.elliman.data.PersonDAO;
import dev.elliman.data.PersonDAOFactory;
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

	public static void main(String[] args) {
		
		//check for an admin user to be able to create managers and employees
		Person admin = personService.getPersonById(0);
		if(admin == null) {
			personService.addAdminUser();
		}
		
		
		 input = new Scanner(System.in);
		 int selection = 0;
		
		while(!stop) {
			System.out.println("Welcome to the bike shop. What would you like to do?\n To use this program enter the numbers of the options to select.");
			selection = getInput("Login", "Create a new account", "Stop");
			
			if(selection == 1) {
				//create a new account
				Person newCustomer = register();
				personService.createUser(newCustomer);
				//TODO: allow the person do do what they want to
			} else if(selection == 0) {
				login();
			} else {
				stop = true;
			}
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
		
		//TODO: get a username that is free
		System.out.print("Enter a username: ");
		String username = input.nextLine();
		
		System.out.println("Enter a password: ");
		String password = input.nextLine();
		
		return new Person(firstName, lastName, username, password, "Customer");
	}
	
	private static void login() {
		
	}
}
