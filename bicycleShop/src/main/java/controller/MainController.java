package controller;

import java.util.Scanner;

import model.Person;
import model.Role;

public class MainController {

	private static Scanner scan;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("=====================  Welcome Bicycle Shop!=======================");

		System.out.println("===================== Main Menu!=======================");
		scan = new Scanner(System.in);
boolean userActive = true;
		
		mainLoop: while (userActive) {
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("Choose the Option?");
				System.out.println("1. Register\n2. Log in\n 3.To exit");
				int userInput = Integer.valueOf(scan.nextLine());
				
				switch (userInput) {
				case 1:
					loggedInUser = register();
					break;
				case 2:
					loggedInUser = login();
					break;
				default:
					userActive = false;
					break mainLoop;
				}
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do?");
				System.out.println("1. View available cats\n2. View my cats");
				if (loggedInUser.getName().equals("User")) {
					System.out.println("other. Log out");
				} else if (loggedInUser.getName().equals("Employee")) {
					System.out.println("3. Manage cats\n4. Manage users\nother. Log out");
				}
				int userInput = Integer.valueOf(scan.nextLine());
				switch (userInput) {
				case 1:
					//loggedInUser = viewAvailableCats(loggedInUser);
					break;
				case 2:
					//viewUserCats(loggedInUser);
					break;
				case 3:
					//loggedInUser = manageCats(loggedInUser);
					break;
				case 4:
					//loggedInUser = manageUsers(loggedInUser);
					break;
				default:
					System.out.println("See you next time!");
					loggedInUser = null;
					break menuLoop;
				}
			}
		}
		scan.close();

		
	}
	
	public static Person register() {
		
		
		Person newUser = new Person(0, null, null, null,null);
		String role;
		System.out.println("1.Register as a Customer \n 2.Regiser as an Employee");
		String nu=scan.nextLine();
		Role role1=new Role(0,null);

		role1.setId(1);
		do {
		if(nu=="1") {
			
		role="Customer";
		role1.setRole(role);
		//role1.setId(1);
		}else if(nu=="2") {
			role="Employee";
			role1.setRole(role);
			//role1.setId(1);
		}else if(nu == "3")  
			register();
		
		
		System.out.println("Enter the right option");	
		}
		while(nu.equals("3"));
		newUser.setUsername(scan.nextLine());
		
		System.out.println("Enter a username: ");
		newUser.setUsername(scan.nextLine());
		System.out.println("Enter a name: ");
		newUser.setName(scan.nextLine());
		System.out.println("Enter a password: ");
		newUser.setPassword(scan.nextLine());
		newUser.setRole(role1);
		
		
		System.out.println("Confirm the information below");
		System.out.println("Username: " + newUser.getUsername()
				+ " Password: " + newUser.getPassword()+" Name:  "+newUser.getName()+ "Role: "+
				newUser.getRole());
		System.out.println("1 to confirm, 2 to start over, other to cancel");
		int input = Integer.valueOf(scan.nextLine());
		switch (input) {
		case 1:
			try {
			//	newUser.setId(personServ.addPerson(newUser));
				System.out.println("Confirmed. Welcome!");
				return newUser;
			} catch (Exception e) {
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
		
	
		
		return null;
		
	}
	
	public static Person login() {
		
			return null;
		
		
	}

}
