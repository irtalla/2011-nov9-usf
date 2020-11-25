package com.revature.controller;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.beans.Status;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class BikeAppController {
	private static Scanner scan;
	private static PersonService personServ = new PersonServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	
	public static void main(String[] args) {
    
	scan = new Scanner(System.in);
	boolean userActive = true;
	
	mainLoop: while (userActive) {
		System.out.println("Welcome to The Bicycle Shop!\n");
		Person loggedInUser = null;
		
		while (loggedInUser == null) {
			System.out.println("What would you like to do?");
			System.out.println("R)egister");
			System.out.println("L)og in");
			System.out.println("E)xit");
			String userInput = scan.nextLine().toUpperCase();
			
			switch (userInput) {
			case "R":
				loggedInUser = registerUser();
				System.out.println(loggedInUser.getRole());
				break;
			case "L":
				loggedInUser = logInUser();
				break;
			case "E":
				userActive = false;
				break mainLoop;
			default:
				System.out.println("Please select again");
				userInput = scan.nextLine().toUpperCase();
			}
		}
		
		menuLp: while (true) {
			System.out.println("\nWhat activity would you like?\n");
			System.out.println("A)vailable bikes");
			System.out.println("M)y bikes");
			System.out.println("P)ayments");
			if (loggedInUser.getRole().getName().equals("user")) {
				System.out.println("L)og out");
			} else if (loggedInUser.getRole().getName().equals("employee")) {
				System.out.println("B) Manage Bikes");
				System.out.println("O) Manage Offers");
				System.out.println("V)iew Payments");
				System.out.println("L)og out");
			} else if (loggedInUser.getRole().getName().equals("manager")) {
				System.out.println("U) Manage Users");
				System.out.println("B) Manage Bikes");
				System.out.println("O) Manage Offers");
				System.out.println("V)iew Payments");
				System.out.println("L)og out");
			}
			String userInput = scan.nextLine().toUpperCase();
			switch (userInput) {
			case "A":
				loggedInUser = viewAvailableBikes(loggedInUser);
				break;
			case "M":
				viewUserBikes(loggedInUser);
				break;
			case "P":
				viewMyPayments(loggedInUser);
				break;
			case "B":
				loggedInUser = manageBikes(loggedInUser);
				break;
			case "U":
				loggedInUser = manageUsers(loggedInUser);
				break;
			case "O":
				loggedInUser = manageOffers(loggedInUser);
				break;
			case "V":
				loggedInUser = viewPayments(loggedInUser);
				break;
			case "L":
				System.out.println("Thanks for shopping with us!\n");
				loggedInUser = null;
				break menuLp;
			default:
				System.out.println("Please select again");
				userInput = scan.nextLine().toUpperCase();
			}
		}
	}
	scan.close();
    
    
    
	}
	private static Person registerUser() {
		while (true) {
			Person newAccount = new Person();
			System.out.println("Please enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Please enter a password: ");
			newAccount.setPassword(scan.nextLine());
			System.out.println("Please enter a role: E)mployee, U)ser or M)anager");
			String rl = scan.nextLine().toUpperCase();
			Role r = new Role();
			if (rl.equals("M")) 
			{
				System.out.println("Please enter manager password");
				String pw = scan.nextLine();
				if (pw.equals("AVerySecretPassword")) 
				
					{
						r.setId(3);
						r.setName("manager");
					}
					else 
					{
						r.setId(2);
						r.setName("user");
					}
				
			}
			else if (rl.equals("E")) 
			{
				System.out.println("Please enter employee password");
				String pw = scan.nextLine();
				if (pw.equals("ASecretPassword"))
				{
					r.setId(1);
					r.setName("employee");
				}
				else 
				{
					r.setId(2);
					r.setName("user");
				}
			}
			else if (rl.equals("U"))
			{
				r.setId(2);
				r.setName("user");
			}
			newAccount.setRole(r);
			System.out.println("Is this correct?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("Role: " + newAccount.getRole());
			System.out.println("C)onfirm, S)tart over, E)xit");
			String input = scan.nextLine().toUpperCase();
			switch (input) {
			case "C":
				try {
					newAccount.setId(personServ.addPerson(newAccount));
					System.out.println("Welcome, "+newAccount.getUsername()+"!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken; please try again.");
				}
				break;
			case "S":
				System.out.println("Please try again.");
				break;
			default:
				System.out.println("Please go back.");
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
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? Y)es, other for no.");
			String input = scan.nextLine().toUpperCase();
			if (input != "Y") {
				break;
			}
		}
		return null;
	}
	
	
	private static Person viewAvailableBikes(Person user) {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		Offer newOffer = new Offer();
		for (Bike bike : availableBikes) {
			System.out.println(bike);
		}
		System.out.println(availableBikes.size());
		if (availableBikes.size()==0) System.out.println("There are no available bikes");
		else {
		System.out.println("Would you like to bid on a bike? Y)es, or N)o");
		String input = scan.nextLine().toUpperCase();
		if (input.equals("Y")) {
			while (true) {
				System.out.println("Enter the bike ID.");
				int id = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(id); 
				if (bike != null && bike.getStatus().getId().equals(1)) {
					System.out.println(bike);
					System.out.println("Do you want to bid on " + bike.getModel() + "? Y)es, other for no");
					input = scan.nextLine().toUpperCase();
					if (input.equals("Y")) {
						System.out.println("You bid " + bike.getModel() + ".");
					    System.out.println("How much to bid?");
					    String amount = scan.nextLine();
					    float amtFlt= Float.valueOf(amount);
					    newOffer= bikeServ.bidBike(user, bike, amtFlt);
					    offerServ.addOffer(newOffer);
						break;
					} else {
						System.out.println("Did you want to purchase a different bike? Y)es, or N)o");
						input = scan.nextLine().toUpperCase();
						if (input != "Y")
							break;
					}
				} else {
					System.out.println("Sorry, that bike is not for sale. Do you want to try again?"
							+ " Y)es, or N)o");
					input = scan.nextLine().toUpperCase();
					if (!input.equals("Y")) {
						System.out.println("Okay.");
						break;
					}
				}
			}
		} else {
			System.out.println("Okay.");
		}
	}
		return user;
	}
	private static Person viewPayments(Person user) {

		DecimalFormat df = new DecimalFormat("0.00");
		 {
			System.out.println("Viewing all payments: ");
			for (Person person : personServ.getPersons()) {
				person.getBikes();

			for (Bike bike : person.getBikes()) {
				System.out.println("$"+df.format(bike.getPrice()) +" for " + person.getUsername());
			}
			}
		}
		
		return user;
	}
	private static void viewUserBikes(Person user) {
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your bikes: ");

			for (Bike bike : user.getBikes()) {
				if (bike.getStatus().getId()==2) bike.getStatus().setName("Purchased");
				
				System.out.println(bike);
			}
			}
		 else {
			System.out.println("You don't own any bike.");
    	}
	}
	
	private static void viewMyPayments(Person user) {
		DecimalFormat df = new DecimalFormat("0.00");
  
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your payments: \n");
			for (Bike bike : user.getBikes()) {
				System.out.println("$"+df.format(bike.getPrice()));
				System.out.println("For the three month payment plan same as cash");
				System.out.println("That is $"+df.format(bike.getPrice()/12) + " per week\n");
			}
			}
		 else {
			System.out.println("You don't own any bike.");
    	}
	}
	
	private static Person manageBikes(Person user) {
		
		if ((user.getRole().getName().equals("user")))
			return null;
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();

		 {
		while (true) {
			System.out.println("Manage Bikes:");
			System.out.println("A)dd a bike");
			
			if (availableBikes.size()==0) System.out.println("eX)it to exit");
			else {
				System.out.println("V)iew bikes");
				System.out.println("E)dit a bike");
				System.out.println("D)elete a bike");
				System.out.println("eX)it to exit");
			}
			String input = scan.nextLine().toUpperCase();
			if (input.equals("V")) {
				for (Bike bike : bikeServ.getAvailableBikes()) {
					System.out.println(bike);
			}
			}	else if (input.equals("A")) {
				Bike newBike = new Bike();
				System.out.println("Enter a model: ");
				newBike.setModel(scan.nextLine());
				System.out.println("Enter a color: ");
				newBike.setColor(scan.nextLine());
				System.out.println("Enter a price: ");
				newBike.setPrice(Float.valueOf(scan.nextLine()));
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newBike.setStatus(status);
				System.out.println(newBike);
				System.out.println("Correct? C)onfirm, S)tart over");
				input =scan.nextLine().toUpperCase();
				if (input.equals("C")) {
					newBike.setId(bikeServ.addBike(newBike));
					System.out.println("You successfully added " + newBike.getModel() + "!");
					availableBikes = bikeServ.getAvailableBikes();
				}
			} else if (input.equals("E")) {
				for (Bike bike : bikeServ.getAvailableBikes()) {
					System.out.println(bike);
				}
				System.out.println("Which bike would you like to update? Enter its ID.");
				Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				Bike newBike = bike;
				if (bike != null) {
					System.out.println("Editing " + bike.getModel());
					System.out.println("Current changes:");
					System.out.println("Model: " + newBike.getModel()
							+ " Color: " + newBike.getColor());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:");
						System.out.println("M)odel");
						System.out.println("C)olor");
						System.out.println("S)ave changes");
						System.out.println("eX)it to cancel");
						input = scan.nextLine().toUpperCase();
						switch (input) {
						case "M":
							System.out.println("Enter new model: ");
							bike.setModel(scan.nextLine());
							break;
						case "C":
							System.out.println("Enter new color: ");
							bike.setColor(scan.nextLine());
							break;
						case "S":
							bikeServ.editBike(newBike);
							System.out.println("You updated " + newBike.getModel() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} 
			
			else if (input.equals("D")) {
				for (Bike bike : bikeServ.getAvailableBikes()) {
					System.out.println(bike);
				}
				System.out.println("Which bike would you like to delete? Enter its ID.");
				Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				Bike delBike = bike;
				if (bike != null) {
					System.out.println("Deleting " + bike.getModel());
					bikeServ.removeBike(delBike);
				}
			} 
			
			else {
				break;
			}
		}
		}
		return user;
	}
	

	private static Person manageUsers(Person user) {
		Set<Bike> bikesToRemove = new HashSet<>();
		if (!(user.getRole().getName().equalsIgnoreCase("manager")))
			return null;
		while (true) {
			System.out.println("Manage Users:");
			System.out.println("V)iew users");
			System.out.println("R)emove a user");
			System.out.println("A)dd a user");
			System.out.println("E)xit");
			String input = scan.nextLine().toUpperCase();
			
			if (input.equals("V"))
			{
				Set<Person> Persons = personServ.getPersons();
				for (Person person : Persons) {
					System.out.println(person);
				}
			}
			
			else if (input.equals("R")) {

			Person personToRemove = null;

					System.out.println("Enter the ID of the user you want to remove.");
					personToRemove = personServ.getPersonById(Integer.valueOf(scan.nextLine()));
				{
					if (personToRemove != null) {
						System.out.println(personToRemove);
						System.out.println("Remove this user? Y)es, or N)o");
						input = scan.nextLine().toUpperCase();
						if (input.equals("Y")) {
							bikesToRemove = bikeServ.getBikeByOwnerId(personToRemove.getId());
							for (Bike bike : bikesToRemove) {
								bikeServ.removeBike(bike);
							}
					
							personServ.deletePerson(personToRemove);
							System.out.println("You removed "
									+ personToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if (input.equals("A")) {
				Person newAccount = new Person();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:");
				System.out.println("E)mployee");
				System.out.println("U)ser");
				System.out.println("M)anager");
				input = scan.nextLine().toUpperCase();
				Role role = new Role();
				if (input.equals("E")) {
					role.setId(1);
					role.setName("employee");
				} else if(input.equals("U")) {
					role.setId(2);
					role.setName("user");
				} else {
					role.setId(3);
					role.setName("manager");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Correct? C)onfirm, E)xit to cancel");
				input = scan.nextLine().toUpperCase();
				if (input.equals("C")) {
					try {
						personServ.addPerson(newAccount);
						System.out.println("Added " + newAccount.getUsername() + " successfully.");
						
					} catch (NonUniqueUsernameException e) {
						System.out.println("Sorry, that username is already taken. Try again.");
					}
				}
			} else {
				break;
			}
		}
		
		return user;
	}
	private static Person manageOffers(Person user) {

		if ((user.getRole().getName().equals("user")))
			return null;
		Set<Offer> availableOffers = offerServ.getAvailableOffers();
		Set<Offer> acceptedOffers = offerServ.getAcceptedOffers();
		Set<Bike> purchasedBikes = new HashSet<Bike>(); 
	
		while (true) {
			System.out.println("Manage Offers:");
			if (user.getRole().getName().equals("manager"))
			{
			System.out.println("S)ales history of accepted offers");
			System.out.println("V)iew unaccepted offers");
			System.out.println("A)ccept offer");
			System.out.println("R)eject offer");
			System.out.println("E)xit");
			} else if (user.getRole().getName().equals("employee"))
			{
				System.out.println("V)iew unaccepted offers");
				System.out.println("A)ccept offer");
				System.out.println("R)eject offer");
				System.out.println("E)xit");
			}
			String input = scan.nextLine().toUpperCase();
		
			if (input.equals("V"))
			{
				for (Offer offer : availableOffers) {
					System.out.println(offer);
				}
			}
			else if (input.equals("S")) 
			{
				for (Offer offer : acceptedOffers) {
					System.out.println(offer);
				}
			}
			else if (input.equals("A")) 
	    	{
				System.out.println("Which offer would you like to accept? Enter its ID.");
				Offer offer = offerServ.getOfferById(Integer.valueOf(scan.nextLine()));
				Bike acceptedBike = bikeServ.getBikeById(offer.getBikeId());
				Offer acceptOffer = offer;
				
				if (acceptOffer != null) 
				{
				    Person p = new Person();
					Status s = new Status();
				    s.setId(2);
				    s.setName("Purchased");
				    acceptedBike.setStatus(s);
				    acceptedBike.setPrice(acceptOffer.getPrice());
				    acceptedBike.setOwnerId(acceptOffer.getPersonId());
				    bikeServ.updateBike(acceptedBike);   // update bike
				    acceptOffer.setAccept("accepted");
				    offerServ.updateOffer(acceptOffer);
				    System.out.println("Accepting " + acceptOffer);
				    System.out.println("For "+acceptedBike);
				    System.out.println("Other competing offers are being deleted!");
				    offerServ.removeAllOffersforBikeID(acceptedBike.getId());        // delete competing offers
				    p=personServ.getPersonById(acceptOffer.getPersonId());  
				    purchasedBikes.add(acceptedBike);
					bikeServ.purchaseBike(p, acceptedBike);
				    p.setBikes(purchasedBikes);// add bike to person   
				    break;
				}   
			} 
			else if (input.equals("R")) 
				{
					System.out.println("Which offer would you like to reject? Enter its ID.");
					Offer rejOffer = offerServ.getOfferById(Integer.valueOf(scan.nextLine()));
					
					if (rejOffer != null) 
					{
						System.out.println("Rejecting " + rejOffer.getId());
						rejOffer.setAccept("rejected");
						 offerServ.updateOffer(rejOffer);
						break;
				    }
				}
			else if (input.equals("E")) {
				System.out.println("Exiting");
				break;
			}
			}
		return user;
	}
	}
			
		
