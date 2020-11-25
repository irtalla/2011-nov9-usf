package controller;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import exceptions.NonUniqueUsernameException;
import models.*;
import services.*;

public class BikeAppController {
	public static Scanner scan;
	private static UserService userServ = new UserServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	private static PaymentService payServ = new PaymentServiceImpl();
	//private static ModelService modelServ = new ModelServiceImpl();
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);

		User testU = new User(3,"temp4","temp","temp1","temp",true);
		//Payments(testU);
		//CheckmyweeklyPayment(testU);
		//modifyOffer(testU);
			
		boolean userActive = true;
		
		mainLoop: while (userActive) {
		System.out.println("Welcome to the Bike Shop");
		User LoggedInUser = null;
		Bike CurrentBike = null;//might not need
		while (LoggedInUser == null) {
			System.out.println("What would you like to do?");
			System.out.println("1. Register\n2. Log in\nother. Exit");
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch (userInput) {
			case 1:
				LoggedInUser = registerUser();
				break;
			case 2:
				LoggedInUser = logInUser();
				break;
			default:
				userActive = false;
				break mainLoop;
			}
		}// end of loggedinuser loop
		menuLoop: while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1. View available bikes\n2. View my bikes \n3. View payment \n4 View all My Payments ");
			if (LoggedInUser.isIsEmployee() == (false)) {
				System.out.println("other. Log out");
			} else if (LoggedInUser.isIsEmployee() == (true)) {
				System.out.println("\n5. Manage Bikes \n6. Manage offers \n7. View All Payments \nother Log out");
			}
			int userInput = Integer.valueOf(scan.nextLine());
			switch (userInput) {
			case 1:
				LoggedInUser = viewAvailableBikes(LoggedInUser);
				break;
			case 2:
				LoggedInUser = getMyBikes(LoggedInUser);//logs out after run
				break;
			case 3:
				LoggedInUser = Payments(LoggedInUser);
				break;
			case 4:
				LoggedInUser = getMyPayments(LoggedInUser);//logs out after run
				break;
			case 5:
				LoggedInUser = manageBikes(LoggedInUser);
				break;
			case 6:
				LoggedInUser = modifyOffer(LoggedInUser);
				break;
			case 7:
				LoggedInUser = getAllPayments(LoggedInUser);
				break;
			default:
				System.out.println("See you next time!");
				LoggedInUser = null;
				break menuLoop;
			}
			if (LoggedInUser == null) {
				System.out.println("See you next time!");
				break menuLoop;
			}
		}
		
		}
		}
	private static User getMyBikes(User user)	
	{
	
		Set<Payment> availablePay = payServ.getAllByUserId(user.getUserId());		
		for (Payment pay : availablePay) {
			System.out.println(pay.getBike());
		}
		return user;
	}
	private static User Payments(User user) {
		System.out.println("Enter the id of the Payment");
		int input = Integer.valueOf(scan.nextLine());
		Payment pay = payServ.getById(input);
		if(pay.getCustomer().equals(user)) {
		Float a = (float)(pay.getWeektotal());
		int b = pay.getWeektotal()-pay.getWeeksdone();
		Float x = pay.getAmount()/a;
		System.out.println("You have "+x+" due for this week and "+b +"weeks of payment left on this bike");
		System.out.println("Would you like to make a payment? 1 for yes");
		input = Integer.valueOf(scan.nextLine());

		if(input == 1) {
			Payment pa = pay;
			pa.setWeeksdone(pay.getWeeksdone()+1);
			if(pa.getWeeksdone()==pa.getWeektotal()) {
				pa.setComplete(true);
			}
			payServ.updatePayment(pa);
		}else {
			System.out.println("This isnt your payment");
		}
		}
		return user;
	}
	/*private static User CheckmyweeklyPayment(User user) {
		Set<Payment> availablePay = payServ.getAll();
		Float total = 0f;
		int a = 0;
		Iterator<Payment> it = availablePay.iterator();
		Object p[]= availablePay.toArray();	
		for (Payment pay : availablePay) {
		total = total + ((p.getAmount()/pay.getWeektotal());
		}
		System.out.println("This weeks payment is $" + total);
		return user;
	}*/
	private static User getAllPayments(User user) {
		if (!(user.isIsEmployee() == true))
			return null;
		Set<Payment> availablePay = payServ.getAll();		
		for (Payment pay : availablePay) {
			System.out.println(pay);
		}
		return user;
	}
	private static User getMyPayments(User user) {
		if (!(user.isIsEmployee() == true))
			return null;
		Set<Payment> availablePay = payServ.getAllByUserId(user.getUserId());		
		for (Payment pay : availablePay) {
			System.out.println(pay);
		}
		return user;
	}
	private static User modifyOffer(User user) {
		if (!(user.isIsEmployee() == true))
			return null;
		
		while (true) {
			for (Offer offer : offerServ.getAllOffers()) {
				System.out.println(offer);
			}
			System.out.println("Choose the id of the offer you would like to modify");
			int input = Integer.valueOf(scan.nextLine());
			Offer newOffer = offerServ.getOfferById(input);
			System.out.println("What would you like to do \n1 for accepting \n2 for counter offering ");
			input = Integer.valueOf(scan.nextLine());
			if(input == 1) {
				newOffer.setStatus("Accepted");
				Bike newBike = bikeServ.getBikeById(newOffer.getBikeId().getId());
				Payment pay = new Payment();
				Status newStatus = new Status(1, "Sold");
				pay.setCustomer(newOffer.getCustomerId());
				pay.setBike(newBike);
				pay.setAmount(newOffer.getAmount());
				pay.setWeektotal(8);
				newBike.setStatus(newStatus);
				bikeServ.updateBike(newBike);
				offerServ.updateOffer(newOffer);
				payServ.addPayment(pay);
				offerServ.MassUpdate(newBike.getId(), newOffer.getOfferId());
			}else if(input == 2){
				System.out.println("Please enter your counter offer");
				Float f = Float.valueOf(scan.nextLine());
				newOffer.setAmount(f);
				offerServ.updateOffer(newOffer);
			}else {
				return user;
			}
		}
		}
	
	private static void makeOffer(User user) {
		for (Bike bike : bikeServ.getAvailableBikes()) {
			System.out.println(bike);
		}
		Boolean makeOffer = true;
		while(makeOffer == true) {
		System.out.println("Which Bike would you like to place an offer on? Enter its ID.");
		//Integer input = Integer.valueOf(scan.nextLine());
		int input = Integer.valueOf(scan.nextLine());
		Bike newBike = bikeServ.getBikeById(input);
		Offer newOffer = new Offer();
		newOffer.setBikeId(newBike);
		System.out.println("What would you like to offer");
		newOffer.setAmount(Integer.valueOf(scan.nextLine()));
		newOffer.setCustomerId(user);
		newOffer.setStatus("Pending");
		offerServ.addOffer(newOffer);
		makeOffer=false;
		}
	}
	private static User registerUser() {
		while (true) {
			User newAccount = new User();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			System.out.println("Enter Your First name: ");
			newAccount.setFname(scan.nextLine());
			System.out.println("Enter your Last name: ");
			newAccount.setLname(scan.nextLine());
			newAccount.setIsEmployee(false);
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword()+ " First name: "+newAccount.getFname()+" Last name: "+newAccount.getLname());
			System.out.println("1 to confirm, 2 to start over, other to cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setUserId(userServ.addUser(newAccount));
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
	
	private static User logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			User user = userServ.getUserByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	

private static User viewAvailableBikes(User user) {
	Set<Bike> availableBikes = bikeServ.getAvailableBikes();
	
	for (Bike bike : availableBikes) {
		System.out.println(bike);
	}
	
	System.out.println("Would you like to put in an offer on a bike? 1 for yes, other for no");
	int input = Integer.valueOf(scan.nextLine());
	if (input == 1) {
		while (true) {
			System.out.println("Which Bike? Type its ID.");
			input = Integer.valueOf(scan.nextLine());
			Bike bike = bikeServ.getBikeById(input);
			if (bike != null && bike.getStatus().getAvalibilty().equals("For Sale")) {//not working
				System.out.println(bike);
		
				System.out.println("You want to place this offer on this bike? 1 for yes, other for no");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Please enter the amount for the offer");
					input = Integer.valueOf(scan.nextLine());
					Offer o = new Offer(input,user,bike);
					offerServ.addOffer(o);
					System.out.println("Your offer has been placed.");
					// get the person with their updated cat set
					user = userServ.getUserById(user.getUserId());
					break;
				} else {
					System.out.println("Okay, did you want to place another offer for a different bike? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input != 1)
						break;
				}
			} else {
				System.out.println("Sorry, that's not an available bike. Do you want to try again?"
						+ " 1 for yes, other for no");
				input = Integer.valueOf(scan.nextLine());
				if (input != 1) {
					System.out.println("Okay, that's fine.");
					break;
				}
			}
		}
	} else {
		System.out.println("Okay, that's fine.");
	}
	
	return user;
}
private static User manageBikes(User user) {
	if (!(user.isIsEmployee() == true))
		return null;
	
	while (true) {
		System.out.println("Manage Bikes:\n1. Add a bike\n2. Edit a bike\n3. Remove a bike \nother. Cancel");
		int input = Integer.valueOf(scan.nextLine());
		
		if (input == 1) {//adding new bike
			Bike newBike = new Bike();
			System.out.println("Enter a Color: ");
			newBike.setColor(scan.nextLine());
			System.out.println("Enter an size: ");
			newBike.setSize(scan.nextLine());
			System.out.println("Choose a Model by entering its ID:"
					+ "\n1. Sentinel\n2. amoots\n3. Cannondale\n4. Lysnkey \nother. Other");
			Model model = new Model();
			model.setId(Integer.valueOf(scan.nextLine()));
			switch(model.getId()) {
			case 1:
				model.setName("Sentintal");
				model.setType("Mountain Bike");
				model.setBrand("Transition");
				model.setYear(2020);
				break;
			case 2:
				model.setName("Vamoots Disc Road");
				model.setType("Raod Bike");
				model.setBrand("Moots");
				model.setYear(2016);
				break;
			case 3:
				model.setName("Cannondale");
				model.setType("Mountain Bike");
				model.setBrand("Trigger 2");
				model.setYear(2018);
				break;
			case 4:
				model.setName("Lynskey");
				model.setType("Road Bike");
				model.setBrand("CooperCx");
				model.setYear(2017);
				break;
			default:
				//breed.setName("Other");
				break;
			}
			newBike.setModeltype(model);
			Status status = new Status();
			status.setId(1);
			status.setAvalibilty("For Sale");
			newBike.setStatus(status);
			System.out.println(newBike);
			System.out.println("Look good? 1 to confirm, other to start over");
			input = Integer.valueOf(scan.nextLine());
			if (input == 1) {
				newBike.setId(bikeServ.addBike(newBike));
				System.out.println("You successfully added a new bike with the id " + newBike.getId() + "!");
			}
		} else if (input == 2) {//editing bikes
			for (Bike bike : bikeServ.getAvailableBikes()) {
				System.out.println(bike);
			}
			System.out.println("Which Bike would you like to update? Enter its ID.");
			Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
			Bike newBike = bike;
			if (bike != null) {
				System.out.println("Editing " + bike.getColor());
				System.out.println("Current changes:\nColor: " + newBike.getId()
						+ " Color: " + newBike.getColor() + " Size: " + newBike.getSize());
				boolean editing = true;
				while (editing) {
					System.out.println("Edit:\n1. Size\n2. Color\n3. Save changes\nother. Cancel");
					input = Integer.valueOf(scan.nextLine());
					switch (input) {
					case 1:
						System.out.println("Enter new Color: ");
						bike.setColor(scan.nextLine());
						break;
					case 2:
						System.out.println("Enter new Size: ");
						bike.setSize(scan.nextLine());
						break;
					case 3:
						bikeServ.updateBike(newBike);
						System.out.println("You updated " + newBike.getId() + " successfully.");
					default:
						editing = false;
						break;
					}
				}
			}
		} else if(input == 3){//deleting
			for (Bike bike : bikeServ.getBikes()) {
				System.out.println(bike);
			}
			System.out.println("Which Bike would you like to remove? Enter its ID.");
			Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
			Bike newBike = bike;
			bikeServ.removeBike(newBike);
			System.out.println("This Bike has been removed");
		}
		else {
			break;
		}
	}
	
	return user;
}
}
