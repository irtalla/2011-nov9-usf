package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Color;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BicycleService;
import com.revature.services.BicycleServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PaymentService;
import com.revature.services.PaymentServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class Controller {
	private static Scanner scan;
	private static BicycleService bicycleServ = new BicycleServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	private static PersonService personServ = new PersonServiceImpl();
	private static PaymentService paymentServ = new PaymentServiceImpl();
	private static int loginCount;
	private static int registerCount;
	private static String errorMessage = "Invalid input: reloading...\n";
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		System.out.println("What's poppin world?\n");
		
		startMenu();
		
		scan.close();
	}
	
	private static void startMenu() {
		try {
			loginCount = 1;
			registerCount = 1;
			
			System.out.println("1: Log in");
			System.out.println("2: Register");
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch(userInput) {
			case 1:
				logIn();
				break;
			case 2:
				register();
				break;
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			startMenu();
		}
	}
	
	private static void logIn() {
		try {
			System.out.println("Username:");
			String username = scan.nextLine();
			System.out.println("Password:");
			String password = scan.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if(personServ.getPersonByUsername(username) == null) {
				System.out.println("That's cap");
			}
			else if (user.getPassword().contentEquals(password)){
				mainMenu(user);
			}
			else {
				System.out.println("\nWrong password dog");
			}
			System.out.println("Wanna try again?");
			System.out.println("1: Yes please\n2: Nah");
			int userInput = Integer.valueOf((scan.nextLine()));
			switch(userInput){
			case 1:
				System.out.println("Okay take " + ++loginCount);
				logIn();
				break;
			case 2:
				System.out.println("Cool.\n");
				startMenu();
				break;
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			logIn();
		}
	}
	
	private static void register() {
		try {
			Person user = new Person();
			
			System.out.println("\nUsername: ");
			user.setUsername(scan.nextLine());
			
			System.out.println("Password: ");
			user.setPassword(scan.nextLine());
			
			System.out.println("\nAre you an employee or customer?");
			System.out.println("1: I'm a customer");
			System.out.println("2: I'm an employee");
			
			int userInput = Integer.valueOf(scan.nextLine());
			
			if(userInput == 2) {
				System.out.println("\nThat's cap\n");
			}
			
			Role r = new Role();
			r.setId(1);
			r.setName("Customer");
			
			user.setRole(r);
			
			System.out.println("Did I get this right?");
			System.out.println("Username: " + user.getUsername() + "\nPassword: " + user.getPassword() + "\n");
			System.out.println("Press 1: Yessir");
			System.out.println("Press 2: Nah man");
			userInput = Integer.valueOf(scan.nextLine());
			switch(userInput) {
			case 1:
				try {
					user.setId(personServ.addPerson(user));
					System.out.println("\nWe live baby\n");
					mainMenu(user);
				}
				catch(NonUniqueUsernameException e) {
					System.out.println("That username is taken");
					register();
				}
				break;
			case 2:
				System.out.println("Okay take " + ++registerCount);
				register();
				break;
				
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			register();
		}
	}
	
	private static void mainMenu(Person user) {
		try {
			System.out.println("\nWe're live baby.");
			System.out.println("1: View available bicycles\n2: View your bicycles");
			
			if(user.getRole().getName().equals("Employee")){
				System.out.println("3: Manage bicycles\n4: Manage Offers\n");
			}
			System.out.println("Other: log out");
			
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch(userInput) {
			
			case 1:
				viewAvailableBicycles(user);
			
			case 2:
				viewOwnedBicycles(user);
			
			case 3:
				if(employeeCheck(user))
					manageBicycles(user);
			
			case 4:
				if(employeeCheck(user))
					manageOffers(user);
				
			default:
				user = null;
			}
			if(user == null) {
				System.out.println("Logging out...\n");
				startMenu();
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			mainMenu(user);
		}
	}
	
	private static void viewAvailableBicycles(Person user) {
		try {
			Set<Bicycle> availableBicycles = bicycleServ.getAvailableBicycles();
			
			for(Bicycle b : availableBicycles) {
				System.out.println(b);
			}
			
			System.out.println("\n1: Make an offer\nOther: Back");
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch(userInput){
			case 1:
				System.out.println("Enter the bicycle ID: ");
				userInput = Integer.valueOf(scan.nextLine());
				Bicycle b = bicycleServ.getBicycleById(userInput);
				
				if(b.getStatus().getName().equals("Available")) {
					System.out.println("You've selected: " + b.getModel() + "\nWhat is your offer?");
					Double userOffer = Double.valueOf(scan.nextLine());
					
					Offer o = new Offer();
					o.setAmount(userOffer);
					o.setBicycle(b);
					o.setPerson(user);
					
					System.out.println("Receipt: " + o.getAmount() + " for " + b.getModel());
					user = personServ.getPersonById(user.getId());
					
					System.out.println("1: Continue\n2: Back");
					userInput = Integer.valueOf(scan.nextLine());
					if(userInput != 1)
						mainMenu(user);
				}
				else {
					System.out.println("Bicycle not available.");
					viewAvailableBicycles(user);
				}
			default:
				mainMenu(user);
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			viewAvailableBicycles(user);
		}
	}
	
	private static void viewOwnedBicycles(Person user) {
		try {	
			if(bicycleServ.getBicyclesByOwner(user).size() > 0) {
				System.out.println("Your bicycles: ");
				Set<Bicycle> ownedBicycles = bicycleServ.getBicyclesByOwner(user);
				for (Bicycle b : ownedBicycles) {
					System.out.println(b);
				}
				System.out.println("Enter bicycle ID: ");
				int userInput = Integer.valueOf(scan.nextLine());
				
				if(ownedBicycles.contains(bicycleServ.getBicycleById(userInput))) {
					Bicycle b = bicycleServ.getBicycleById(userInput);
					System.out.print(b);
					System.out.println("1: view payments\n2: calculate weekly payments\nOther: Back");
					
					userInput = Integer.valueOf(scan.nextLine());
					switch(userInput) {
					case 1:
						paymentServ.getPaymentsOnBicycle(b);
						break;
					case 2:
						calculatePayments(b, user);
						break;
					default:
						mainMenu(user);
					}
				}
			}
			else {
				System.out.println("No bicycles :(");
				mainMenu(user);
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			viewOwnedBicycles(user);
		}
	}
	
	private static void calculatePayments(Bicycle b, Person user) {
		try {
			System.out.println("How many weeks do you want to pay this off?");
			int userInput = Integer.valueOf(scan.nextLine());
			if(userInput > 0)
				System.out.println("That'll be $" + (b.getPrice()/userInput) + "per week.");
			else
				System.out.println("That'll be $" + b.getPrice() + "upfront.");
			
			System.out.println("1: Run another calculation\n2: Back to my bicycles\nOther: Back");
			userInput = Integer.valueOf(scan.nextLine());
			switch(userInput){
			case 1:
				calculatePayments(b, user);
				break;
			case 2:
				viewOwnedBicycles(user);
				break;
			default:
				mainMenu(user);
			}
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			calculatePayments(b, user);
		}
	}
	
	private static void manageBicycles(Person user) {
		try {
			System.out.println("1: Add bicycle\n2: Update bicycle\n3: Remove bicycle\nOther: Back");
			int userInput = Integer.valueOf(scan.nextLine());
			
			if(userInput == 1) {
				Bicycle b = new Bicycle();
				System.out.println("Model: ");
				b.setModel(scan.nextLine());
				System.out.println("1: Red\n2: Orange\n3: Yellow\n4: Green\n5: Blue\n6: Indigo\n7: Violet");
				Color c = new Color();
				userInput = Integer.valueOf(scan.nextLine());
				c.setId(userInput);
				switch(userInput) {
				case 1:
					c.setName("Red");
					break;
				case 2:
					c.setName("Orange");
					break;
				case 3: 
					c.setName("Yellow");
					break;
				case 4:
					c.setName("Green");
					break;
				case 5:
					c.setName("Blue");
					break;
				case 6:
					c.setName("Indigo");
					break;
				case 7:
					c.setName("Violet");
					break;
				default:
					mainMenu(user);
				}
				b.setColor(c);
				Status s = new Status();
				s.setId(1);
				s.setName("Available");
				b.setStatus(s);
				
				b.setId(bicycleServ.addBicycle(b));
				System.out.println(b + " has been added to the database");
				mainMenu(user);
			}
			
			else if(userInput == 2) {
				Set<Bicycle> bicycles = bicycleServ.getAvailableBicycles();
				for(Bicycle b : bicycles) {
					System.out.println(b);
				}
				System.out.println("Pick a bicycle to update (by ID)");
				
				userInput = Integer.valueOf(scan.nextLine());
				Bicycle b2 = bicycleServ.getBicycleById(userInput);
				System.out.println(b2.getModel() + "status: " + b2.getStatus().getName());
				System.out.println("1: Update status\nOther: Back");
				userInput = Integer.valueOf(scan.nextLine());
				switch(userInput) {
				case 1:
					Status s = new Status();
					s.setId(2);
					s.setName("Sold");
					b2.setStatus(s);
					System.out.println(b2);
					break;
				default:
					mainMenu(user);
				}
			}
			
			else if(userInput == 3) {
				for(Bicycle b : bicycleServ.getAvailableBicycles()) {
					System.out.println(b);
				}
				System.out.println("Pick a bicycle to delete (by ID)");
				userInput = Integer.valueOf(scan.nextLine());
				Bicycle b = bicycleServ.getBicycleById(userInput);
				bicycleServ.deleteBicycle(b);
			}
			
			else {
				mainMenu(user);
			}
			mainMenu(user);
		}
		catch(Exception e) {
			System.out.println(errorMessage);
			manageBicycles(user);
		}
	}
	
	private static Boolean employeeCheck(Person user) {
		if(user.getRole().getName().equals("Employee"))
			return true;
		else
			System.out.println("Nice try, customer.");
			return false;
	}
	
	 private static void manageOffers(Person user) {
	 	try {
	 		System.out.println("1: all offers\n2: offers by bicycle\n3: offers by user\nOther: Back");
			int userInput = Integer.valueOf(scan.nextLine());
			switch(userInput) {
			case 1:
				Set<Offer> allOffers = offerServ.getOffers();
				System.out.println("Offers: ");
				for(Offer o : allOffers) {
					System.out.println(o);
				}
				break;
			
			case 2:
				System.out.println("Bicycle ID: ");
				userInput = Integer.valueOf(scan.nextLine());
				Bicycle b = bicycleServ.getBicycleById(userInput);
				Set<Offer> bicycleOffers = offerServ.getOffersByBicycle(b);
				System.out.println("Offers: ");
				for(Offer o : bicycleOffers) {
					System.out.println(o);
				}
				break;
			
			case 3:
				System.out.println("Person ID: ");
				userInput = Integer.valueOf(scan.nextLine());
				Person p = personServ.getPersonById(userInput);
				Set<Offer> personOffers = offerServ.getOffersByPerson(p);
				System.out.println("Offers: ");
				for(Offer o : personOffers) {
					System.out.println(o);
				}
				break;
			
			default:
				mainMenu(user);
			}
			
			System.out.println("Offer ID: ");
			userInput = Integer.valueOf(scan.nextLine());
			
			try {
				Offer o = offerServ.getOfferById(userInput);
				System.out.println("1: Accept\n2: Reject\n3: Back");
				userInput = Integer.valueOf(scan.nextLine());
				switch(userInput) {
				case 1:
					offerServ.acceptOffer(o);
				case 2:
					offerServ.rejectOffer(o);
				default:
					manageOffers(user);
				}
			}
			catch(NullPointerException e) {
				System.out.println(errorMessage);
				manageOffers(user);
			}
	 	}
	 	catch(Exception e) {
	 		System.out.println(errorMessage);
	 		manageOffers(user);
	 	}
	}

}
