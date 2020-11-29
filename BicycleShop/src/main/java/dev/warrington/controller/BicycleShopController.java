package dev.warrington.controller;

import java.util.Formatter;
import java.util.Scanner;
import java.util.Set;

import dev.warrington.services.BicycleService;
import dev.warrington.services.BicycleServiceImpl;
import dev.warrington.services.OfferService;
import dev.warrington.services.OfferServiceImpl;
import dev.warrington.services.PaymentService;
import dev.warrington.services.PaymentServiceImpl;
import dev.warrington.services.PersonService;

import dev.warrington.services.PersonServiceImpl;

import dev.warrington.beans.Role;
import dev.warrington.beans.Bicycle;
import dev.warrington.beans.Offer;
import dev.warrington.beans.Payment;
import dev.warrington.beans.Person;

public class BicycleShopController {
	
	private static Scanner scan;
	private static PersonService personServ = new PersonServiceImpl();
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainloop: 
		while (userActive) {
			
			System.out.println("Welcome to the Bicycle Shop App!\n");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				
				System.out.println("Choose an option:\n");
				System.out.println("1. Login\n2. Register\n3. Exit\n");
				System.out.print("Selection: ");
				int userInput = Integer.valueOf(scan.nextLine());
				
				switch (userInput) {
				
				case 1:
					loggedInUser = loginUser();
					break;
					
				case 2:
					loggedInUser = registerUser();
					break;
					
				case 3:
					userActive = false;
					break mainloop;
					
				default:
					System.out.println("Choose a valid option...\n");
				
				}
				
			}
			
			menuloop:
			while (true) {
				System.out.println("Choose an option:\n");
				
				if (loggedInUser.getRole().getId() == 1) {
					
					System.out.println("1. View all bicycles\n2. Make an offer\n3. View my bicycles\n4. View my payments\n5. Log out\n");
					System.out.print("Selection: ");
					int userInput = Integer.valueOf(scan.nextLine());
					
					switch (userInput) {
					
						case 1:
							viewAvailableBikes();
							break;
						
						case 2:
							makeOffer(loggedInUser);
							break;
						
						case 3:
							viewMyBicycles(loggedInUser.getId());
							break;
							
						case 4:
							viewMyPayments(loggedInUser.getId());
							break;
						
						case 5:
							loggedInUser = null;
							break menuloop;
							
						default:
							System.out.println("Choose a valid option...\n");
					
					}
					
				} else {
					
					System.out.println("1. Add a bicycle\n2. Remove a bicycle\n3. Manage offers\n4. View all payments\n5. Log out\n");
					System.out.print("Selection: ");
					int userInput = Integer.valueOf(scan.nextLine());
					
					switch (userInput ) {
					
						case 1:
							addBicycle();
							break;
						
						case 2:
							removeBicycle();
							break;
						
						//TODO: Manage offers
						case 3:
							manageOffers();
							break;
						
						case 4:
							viewAllPayments();
							break;
					
						case 5:
							loggedInUser = null;
							break menuloop;
							
						default:
							System.out.println("Choose a valid option...\n");
					
					}
					
				}
				
			}
		
		}
		
		scan.close();
		
	}
	
	private static Person loginUser() {
		
		while(true) {
			
			System.out.print("Enter username: ");
			String username = scan.nextLine();
			System.out.print("Enter password: ");
			String password = scan.nextLine();
			
			Person user = personServ.getPersonByUsername(username);
			if (user == null) {
				System.out.print("\nUsername does not exist...\n");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome!\n");
				return user;
			} else {
				System.out.print("\nPassword is incorrect...\n");
			}
			System.out.println("\n1. Reattempt login\nOr press any other number to go back...\n");
			System.out.print("Selection: ");
			int input = Integer.valueOf(scan.nextLine());

			if (input != 1) {
				break;
			}
			
		}
		
		return null;
		
	}
	
	//TODO fix duplicate usernames
	private static Person registerUser() {
		
		while(true) {
			
			Person newAccount = new Person();
			System.out.print("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.print("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			Role r = new Role();
			r.setId(1);
			r.setName("customer");
			newAccount.setRole(r);
			
			System.out.println("Confirm your information:\n");
			System.out.println("Username: " + newAccount.getUsername()
					+ "\nPassword: " + newAccount.getPassword());
			System.out.println("\n1. Confirm\n2. Input information again\n3. Go back\n");
			System.out.print("Selection: ");
			
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setId(personServ.addPerson(newAccount));
					System.out.println("Account created!\n");
					return newAccount;
				} catch (Exception e) {
					
					System.out.println("That username is already taken...\n");
		 
				}
				break;
			case 2:
				System.out.println("Re-enter information:\n");
				break;
			case 3:
				System.out.println("Registration cancelled\n");
			default:
				System.out.println("Choose a valid option...\n");
				return null;
			}
			
		}
		
	}
	
	private static void makeOffer(Person p) {
		
		BicycleService bs = new BicycleServiceImpl();
		OfferService os = new OfferServiceImpl();
		
		Set<Bicycle> bikes = bs.getAvailable();
		
		Offer o = null;
		
		Integer bicycleId = null;
		
		viewAvailableBikes();
		
		System.out.println("\nWhich bicycle would you like to make an offer on?");
		System.out.print("Enter ID or 0 to go back: ");
		
		Integer input = Integer.valueOf(scan.nextLine());
		
		switch(input) {
		
			case 0:
				break;
				
			default:
				
				for (Bicycle bike : bikes) {
					
					if (bike.getId() == input) {
						
						bicycleId = input;
						break;
						
					}
					
				}
				
				if (bicycleId == null) {
					
					System.out.println("\nID not found...\n");
					
				} else {
					
					Formatter f1 = new Formatter();
					o = new Offer(bicycleId, p.getId());
					
					System.out.print("\nEnter an amount to offer: $");
					o.setAmount(Double.valueOf(scan.nextLine()));
					
					o.setId(os.addOffer(o));
					System.out.println("\nOffer submitted:\n");
					System.out.println("ID: " + o.getId() + "   Amount: $" + f1.format("%1.2f", o.getAmount()) + "\n");
					
					f1.close();
					
				}
		
		}
		
	}
	
	private static void manageOffers() {
		
		OfferService os = new OfferServiceImpl();
		
		Set<Offer> offers = os.getAllOffers();
		
		System.out.println("\nOpen Offers:");
		
		for (Offer offer : offers) {
			
			Formatter f = new Formatter();
			
			System.out.println("\nID: " + offer.getId() + "   Bicycle ID: " + offer.getBikeId() + "  Customer ID: " + offer.getCustomerId()
					+ "   Amount: $" + f.format("%1.2f", offer.getAmount()));
			
			f.close();
			
		}
		
		System.out.print("\nChoose an ID to manage or 0 to go back: ");
		
		Integer input = Integer.valueOf(scan.nextLine());
		
		switch(input) {
		
			case 0:
				break;
				
			default:
				Offer o = null;
				
				for (Offer offer : offers) {
					
					if (offer.getId() == input) {
						
						o = offer;
						break;
						
					}
					
				}
				
				if (o == null) {
					
					System.out.println("\nID not found...\n");
					
				} else {
					
					Formatter f = new Formatter();
					
					System.out.println("\nChosen Offer:\nID: " + o.getId() + "   Bicycle ID: " + o.getBikeId() + "  Customer ID: " + o.getCustomerId()
					+ "   Amount: $" + f.format("%1.2f", o.getAmount()));
					
					f.close();
					
					System.out.print("\nPress 1 to accept offer, 2 to reject offer, and any other key to cancel: ");
					
					input = Integer.valueOf(scan.nextLine());
					
					switch(input) {
					
						case 1:
							PaymentService ps = new PaymentServiceImpl();
							BicycleService bs = new BicycleServiceImpl();
							
							Payment p = new Payment(o.getCustomerId(), o.getBikeId(), o.getAmount(), (o.getAmount()/52.0), 52);
							p.setId(ps.addPayment(p));
							os.deleteGroup(o.getBikeId());
							bs.updateBicycle(o.getBikeId());
							bs.updateOwnership(o.getBikeId(), o.getCustomerId());
							System.out.println("\nOffer Accepted!\n");
							break;
						
						case 2:
							os.deleteOffer(o);
							System.out.println("\nOffer Rejected!\n");
							break;
							
						default:
							break;
					
					}
					
				}
				
		}
		
	}
	
	private static Set<Bicycle> viewAllBikes() {
		
		BicycleService bs = new BicycleServiceImpl();
		
		Set<Bicycle> bikes = bs.getAll();
		
		System.out.println("\nAll Bicycles:\n");
		
		for (Bicycle bike : bikes) {
			
			String stat = bike.getStatus() == 1 ? "For Sale" : "Sold";
			
			System.out.println("ID: " + bike.getId() + "   Manufacturer: " + bike.getManufacturer() + "   Model: " + bike.getModel() + "   Color: " + bike.getColor() + "   Status: " + stat + "\n");
			
		}
		
		return bikes;
		
	}
	
	private static void viewAvailableBikes() {
		
		BicycleService bs = new BicycleServiceImpl();
		
		Set<Bicycle> bikes = bs.getAvailable();
		
		System.out.println("\nAvailable bicycles:\n");
		
		for (Bicycle bike : bikes) {
			
			System.out.println("ID: " + bike.getId() + "   Manufacturer: " + bike.getManufacturer() + "   Model: " + bike.getModel() + "   Color: " + bike.getColor() + "\n");
			
		}
		
	}
	
	private static void addBicycle() {
		
		BicycleService bs = new BicycleServiceImpl();
		
		System.out.println("\nInput the new bicycle's information:\n");
		System.out.print("Manufacturer name: ");
		String manufacturer = scan.nextLine();
		System.out.print("Model name: ");
		String model = scan.nextLine();
		System.out.print("Asking price: ");
		Double askingPrice = Double.valueOf(scan.nextLine());
		System.out.print("Color: ");
		String color = scan.nextLine();
		
		Bicycle b = new Bicycle(manufacturer, model, askingPrice, color);
		
		b.setId(bs.addBicycle(b));
		
		System.out.println("\nSuccess!  Bicycle with ID of " + b.getId() + " added.\n");
	}
	
	private static void removeBicycle() {
		
		BicycleService bs = new BicycleServiceImpl();
		Set<Bicycle> bikes;
		
		removeLoop:
		while (true) {
			
			System.out.println("\nSelect a bicycle ID to remove:\n");
			bikes = viewAllBikes();
		
			System.out.print("Enter id or 0 to go back: ");
			Integer input = Integer.valueOf(scan.nextLine());
			
			switch(input) {
			
				case 0:
					break removeLoop;
					
				default:
					Bicycle b = null;
					
					for (Bicycle bike : bikes) {
						
						if (bike.getId() == input) {
							b = bike;
							break;
						}
						
					}
					
					if (b == null) {
						
						System.out.println("\nID not found...\n");
						
					} else {
						
						bs.deleteBicycle(b);
						
						System.out.println("\nFollowing bicycle successfully removed:\n");
						System.out.println("ID: " + b.getId() + "   Manufacturer: " + b.getManufacturer() + "   Model: " + b.getModel() + "   Color: " + b.getColor() + "\n");
						
					}
			}
		}
		
		
	}
	
	private static void viewMyBicycles(Integer id) {
		
		BicycleService bs = new BicycleServiceImpl();
		
		Set<Bicycle> bikes = bs.getMine(id);
		
		System.out.println("\nMy bicycles:\n");
		
		if (bikes.size() == 0) {
			
			System.out.println("You own no bikes!\n");
			
		} else {
		
			for (Bicycle bike: bikes) {
			
				System.out.println("ID: " + bike.getId() + "   Manufacturer: " + bike.getManufacturer() + "   Model: " + bike.getModel() + "   Color: " + bike.getColor() + "\n");
			
			}
			
		}
		
	}
	
	private static void viewMyPayments(Integer id) {
		
		PaymentService ps = new PaymentServiceImpl();
		
		Set<Payment> payments = ps.getMyPayments(id);
		viewMyBicycles(id);
		
		if (payments.size() > 0 ) {

			System.out.print("\nEnter the ID of the bicycle payments you wish to view or press 0 to go back: ");
		
			Integer input = Integer.valueOf(scan.nextLine());
		
			switch(input) {
		
				case 0:
					break;
				
				default:
					Payment p = null;
					
					for (Payment payment : payments) {
						
						if (payment.getBicycleId() == input) {
							
							p = payment;
							break;
							
						}
						
					}
					
					if (p == null) {
						
						System.out.println("\nID not found...");
						
					} else {
						
						Formatter f1 = new Formatter();
						Formatter f2 = new Formatter();
						
						System.out.println("\nYou have " + p.getPaymentsRemaining() + " payments of $" + f1.format("%1.2f", p.getWeeklyPayment()) + " each remaining "
								+ "with an outstanding balance of $" + f2.format("%1.2f", p.getTotalOwed()) + "\n");
						
						f1.close();
						f2.close();
						
						System.out.print("Press 1 to make a payment and any other number to go back:");
						input = Integer.valueOf(scan.nextLine());
						
						if (input == 1) {
							
							p.setPaymentsRemaining(p.getPaymentsRemaining() - 1);
							p.setTotalOwed(p.getTotalOwed() - p.getWeeklyPayment());
							
							ps.makePayment(p);
							
							System.out.println("\nPayment successfully made!\n");
							
						}
					}
		
			}
		
		}
	
	}
	
	private static void viewAllPayments() {
		
		PaymentService ps = new PaymentServiceImpl();
		
		Set<Payment> payments = ps.getAllPayments();
		
		System.out.println("\nAll Payments:\n");
		
		if (payments.size() == 0) {
			
			System.out.println("No current payments!\n");
			
		} else {
		
			for (Payment payment : payments) {
			
				Formatter f1 = new Formatter();
				Formatter f2 = new Formatter();
			
				System.out.println("ID: " + payment.getId() + "   Customer ID: " + payment.getCustomerId() + "   Bicycle ID: " + payment.getBicycleId()
						+ "   Total Owed: $" + f1.format("%1.2f", payment.getTotalOwed()) + "   Weekly Payment: $" + f2.format("%1.2f", payment.getWeeklyPayment())
						+ "   Payments Remaining: " + payment.getPaymentsRemaining() + "\n");
			
				f1.close();
				f2.close();
			
			}
		
		}
		
	}

	
}