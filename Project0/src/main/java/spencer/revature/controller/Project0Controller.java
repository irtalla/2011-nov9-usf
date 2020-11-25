package spencer.revature.controller;

import java.util.Scanner;
import java.util.Set;


import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;
import spencer.revature.beans.Payment;
import spencer.revature.beans.User;
import spencer.revature.services.BicycleService;
import spencer.revature.services.BicycleServiceImpl;
import spencer.revature.services.CustomerService;
import spencer.revature.services.CustomerServiceImpl;
import spencer.revature.services.EmployeeService;
import spencer.revature.services.EmployeeServiceImpl;
import spencer.revature.services.OfferService;
import spencer.revature.services.OfferServiceImpl;

public class Project0Controller {

	private static Scanner scan;
	private static BicycleService bicycleServ = new BicycleServiceImpl();
	private static CustomerService custServ = new CustomerServiceImpl();
	private static OfferService offServ = new OfferServiceImpl();
	private static EmployeeService empServ = new EmployeeServiceImpl();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Welcome to the bike shop!");
			User loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Customer Registration\n2. Log in\nother. Exit");
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
					break mainLoop;
				}
			}
		
		
		menuLoop: while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1. View available bikes");
			if (loggedInUser instanceof Customer) {
				System.out.println("2. View my bikes\n3. View balance on bikes\nother. Log out");
			} else if (loggedInUser instanceof Employee) {
				System.out.println("2. Add bike\n3. View pending offers\n4. View payments\nother. Log out");
			}
			int userInput = Integer.valueOf(scan.nextLine());
			switch (userInput) {
			case 1:
				loggedInUser = viewAvailableBikes(loggedInUser);
				break;
			case 2:
				if(loggedInUser instanceof Customer) {
					viewMyBikes(loggedInUser);
				}else {
				    addBike(loggedInUser);
				}
				break;
			case 3:
				if(loggedInUser instanceof Customer) {
				     viewBikeBlance(loggedInUser);
				}else {
					 viewPendingOffers(loggedInUser);
				}
				break;
			case 4:
				if(loggedInUser instanceof Customer) {
					break;
				}else {
					viewPayments(loggedInUser);
				}
				break;
			default:
				System.out.println("See you later!");
				loggedInUser = null;
				break menuLoop;
			}
			if (loggedInUser == null) {
				System.out.println("See you later!");
				break menuLoop;
			}
		}
		
		}
	}

	private static void viewPayments(User loggedInUser) {
		Set<Payment> pays = offServ.getPayments(); 
		
		for(Payment pay : pays) {
				System.out.println("payment ID:"+pay.getId()+" Payment amount:"+pay.getPayment()+
						" Offer ID:"+pay.getOffer().getId());
		}
	}

	private static void viewPendingOffers(User loggedInUser) {
		while (true) {
			Set<Offer> offers = offServ.getOffers(); 
			for(Offer off : offers) {
				if(off.getOfferStatus().getId()==2)
					System.out.println("ID:"+off.getId()+" Bike ID:"+off.getBicycle().getId()+
							" Bike Model:"+off.getBicycle().getModel()+" Offer:"+off.getAmount());
			}
			System.out.println("Press 1 to select an offer, press other to return to main menu");
			int input = Integer.valueOf(scan.nextLine());
			if (input == 1) {
				System.out.print("Enter offer ID of offer to accept or reject");
			    input = Integer.valueOf(scan.nextLine());
			    int offerid=input;
			    System.out.print("Enter 1 to accept or 2 to reject");
			    input = Integer.valueOf(scan.nextLine());
			    OfferStatus os =new OfferStatus();
			    if(input==1) {
			    os.setId(1);
			    Offer o =offServ.getOfferById(offerid);
			    o.setOfferStatus(os);
			    offServ.updateOffer(o);
			    bicycleServ.assignBicycle(o.getCustomer(),  bicycleServ.getBicycleById(o.getBicycle().getId()));
			    }else {
			    os.setId(3);
			    Offer o =offServ.getOfferById(offerid);
			    o.setOfferStatus(os);
			    offServ.updateOffer(o);
			    }
			    
			    
			}else {
				break;
			}
		}
	}

	private static void addBike(User loggedInUser) {
		Bicycle bike = new Bicycle();
		System.out.print("Enter a Model: ");
		bike.setModel(scan.nextLine());
		bicycleServ.addBicycle(bike);
	    System.out.println("----You added " + bike.getModel() + "-----\n");
	}

	private static void viewBikeBlance(User loggedInUser) {
		double payment=0;
		Set<Bicycle> bikes = custServ.getCustomerBikes((Customer)loggedInUser);
		if (bikes.size()>0) {
			System.out.println("-------Your bikes with payments due----: ");
			for (Bicycle bike : bikes) {
				payment=offServ.getPayDue(bike.getId());
				if(payment>0)
					System.out.println(bike.getModel()+" "+String.format("%.2f", payment));
		    }
			System.out.println("\nYour weekly payment: "+String.format("%.2f", offServ.weeklyRemainder(loggedInUser.getId()))+"\n");
		}
		
	}

	private static void viewMyBikes(User loggedInUser) {
		
		Set<Bicycle> bikes = custServ.getCustomerBikes((Customer)loggedInUser);
		if (bikes.size()>0) {
			System.out.println("-------Your bikes----: ");
			for (Bicycle bike : bikes) {
				System.out.println(bike);
		    }
		}else {
			System.out.println("\nYou have no bikes!\n");
			
		}
		System.out.println("\n");
	}

	//bicycleServ.assignBicycle((Customer)loggedInUser, bike);
	
	private static User viewAvailableBikes(User loggedInUser) {
		Set<Bicycle> availableBikes = bicycleServ.getAvailableBicycles();
		
		for (Bicycle bike : availableBikes) {
			System.out.println(bike);
		}
		if(loggedInUser instanceof Customer) {
			System.out.println("Would you like to make an offer on a bike? 1 for yes, other for no");
		}else {
			System.out.println("Would you like to delete a bike? 1 for yes, other for no");
		}
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.print("Which bike? Input its ID:");
				input = Integer.valueOf(scan.nextLine());
				Bicycle bike = bicycleServ.getBicycleById(input);
				if (bike != null && bike.getAvailable().getStatus().equals("Available")) {
					if(loggedInUser instanceof Customer) {
						System.out.println("You want to make an offer on the " + bike.getModel() + "? 1 for yes, other for no");
					}else {
						System.out.println("You want to remove the " + bike.getModel() + "? 1 for yes, other for no");
					}
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						if(loggedInUser instanceof Customer) {
							System.out.print("Input offer in Dollars xxx.xx:");
							Double dinput = Double.valueOf(scan.nextLine());
							Offer o = new Offer();
							o.setAmount(dinput);
							o.setBicycle(bike);
							o.setCustomer((Customer)loggedInUser);
							offServ.addOffer(o);
							System.out.println("Offer --- sent!");
						}else {;
							bicycleServ.removeBicycle(bike);
							System.out.println("Bike --- removed!");
						}
						break;
					} else {
						System.out.println("Okay would you like to view the other bikes? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1)
							break;
					}
				} else {
					System.out.println("Invalid bike ID. Would you like to try again?"
							+ " 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input != 1) {
						System.out.println("Returning to main menu");
						break;
					}
				}
			}
		} else {
			System.out.println("Returning to main menu");
		}
		
		return loggedInUser;
	}

	private static User logInUser() {
		while (true) {
			System.out.print("Enter username: ");
			String username = scan.nextLine();
			System.out.print("Enter password: ");
			String password = scan.nextLine();
			
			User user = empServ.getEmployeeByUsername(username);
			if(user==null)
				user = custServ.getCustomerByUsername(username);
			
			if (user == null) {
				System.out.print("\nNobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!\n");
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

	private static User registerUser() {
		while (true) {
			User newAccount = new Customer();
			System.out.print("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.print("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			
			
			if(empServ.getEmployeeByUsername(newAccount.getUsername())==null && custServ.getCustomerByUsername(newAccount.getUsername())==null) 
			{
				System.out.println("\nDoes this look good?");
				System.out.println("Username: " + newAccount.getUsername()
					+ " \nPassword: " + newAccount.getPassword());
				System.out.println("---\n1 to confirm \n2 to start over \nother to cancel");
				int input = Integer.valueOf(scan.nextLine());
				switch (input) {
				case 1:
					newAccount.setId(custServ.addCustomer((Customer)newAccount));
					System.out.println("Welcome!");
					return newAccount;
				case 2:
					System.out.println("Okay try again.");
					break;
				default:
					System.out.println("Okay --- let's go back.");
					return null;
				}
			
			}
			System.out.println("Username Taken --- please try again");
		}
	}
}
