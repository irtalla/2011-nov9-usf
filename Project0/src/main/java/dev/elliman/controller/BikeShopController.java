package dev.elliman.controller;

import java.util.Scanner;

import dev.elliman.beans.Person;
import dev.elliman.data.PersonDAO;

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
	 * Data Access Objects
	 */
	private static PersonDAO personDAO;

	public static void main(String[] args) {
		
		//TODO: check for an admin user to be able to create managers and employees
		
		 input = new Scanner(System.in);
		 int selection = 0;
		
		while(!stop) {
			System.out.println("Welcome to the bike shop. What would you like to do?\n To use this program enter the numbers of the options to select.");
			selection = getInput("Login", "Create a new account", "Stop");
			
			if(selection == 0) {
				Person newCustomer = createAccount();
				//TODO: add the customer to the database
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
	
	private static Person createAccount() {
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
}
