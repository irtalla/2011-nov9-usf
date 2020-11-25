package com.revature.services;
import java.util.Set;

import com.revature.beans.*;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.SingletonScanner;
public class ControllerService {
	private static SingletonScanner scan = SingletonScanner.getScannerSingleton();
	private static PersonService pServ = new PersonServiceImpl();
	private static BicycleService bServ = new BikeServiceImpl();
	private static BikeTypeService btServ = new BikeTypeServiceImpl();
	private static OfferService oServ = new OfferServiceImpl();
	private static PaymentService payServ = new PaymentServiceImpl();
	
	public static void enterSystem() {
		SingletonScanner scan = SingletonScanner.getScannerSingleton();
		boolean userActive = true;
		
		
		//enter the system
		introLoop: while(userActive) {
				System.out.println("Welcome to the Bike Shop!");
				Person loggedInUser = null;
				
				while(loggedInUser == null) {
					System.out.println("What would you like to do?");
					System.out.println("1. Register \n"
										+ "2. Log in \n"
										+ "3. exit");
					int userInput = Integer.valueOf(scan.getScanner().nextLine());
					
					switch(userInput) {
					case 1:
						loggedInUser = registerUser();
						break;
					case 2:
						loggedInUser = logInUser();
						break;
					case 3:
						System.out.println("Goodbye!");
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							//this code should never run
							e.printStackTrace();
						}
						System.exit(0);
					default:
						userActive = false;
						break introLoop;
					}//end switch
				}//end While loggedInUser ==null
				
				
			//loop through menu options for user	
			menu: while(true) {
				System.out.println("Hi " + loggedInUser.getUsername() + "!\n"
						+ "What can I do for you today?");
				System.out.println("1. View available Bikes\n"
						+ "2. View owned Bikes\n");
				if(loggedInUser.getRole().getName().equals("Customer")) {
					System.out.println("5. Log out");
				}else if(loggedInUser.getRole().getName().equals("Employee")) {
					System.out.println("3. Manage current Bikes in the shop\n"
							+ "4. Manage Customers\n"
							+ "5. Log out");
				}
				int userInput = Integer.valueOf(scan.getScanner().nextLine());
				switch(userInput) {
				case 1:
					loggedInUser = (Person) viewAvailableBikes(loggedInUser);
					break;
				case 2:
					viewOwnedBikes(loggedInUser);
					break;
				case 3:
					if(!loggedInUser.equals("Customer")) {
						loggedInUser = manageBikes(loggedInUser);
						break;
					}
				case 4:
					if(!loggedInUser.equals("Customer")){
						loggedInUser = manageUsers(loggedInUser);
						break;
					}
					
				default:
					System.out.println("Logging out!");
					loggedInUser = null;
					break menu;
				}//end menu switch
				
			}//end menu loop
				
		}//end intro loop
		
		scan.close();
	}
	
	
	private static Person logInUser() {
		while (true) {
			System.out.println("Enter username: ");
			String username = scan.getScanner().nextLine();
			System.out.println("Enter password: ");
			String password = scan.getScanner().nextLine();
			
			Person user = pServ.getPersonByUsername(username);
			if (user == null) {
				System.out.print("Nobody exists with that username. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? 1 for yes, 2. for no.");
			int input = Integer.valueOf(scan.getScanner().nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	
	
	private static Person registerUser() {
		int input = -1;
		boolean tryagain = true;
		while (tryagain) {
			Person newAccount = new Person();
			System.out.println("Enter a username: ");
			String un = (scan.getScanner().nextLine());
			System.out.println("Enter a password: ");
			String pw = scan.getScanner().nextLine();
			System.out.println("Please re-enter password: ");
			if(scan.getScanner().nextLine().equals(pw)) {
				newAccount.setUsername(un);
				newAccount.setPassword(pw);
			}else {
				System.out.println("Sorry that password didn't match");
				System.out.println("try again? y/n");
				if(scan.getScanner().nextLine().equals("n")) {
					tryagain = false;
					break;
				}else if(scan.getScanner().nextLine().equals("y")) {
					registerUser();
				}
			}
			Role r = new Role();
			r.setId(1);
			r.setName("Customer");
			newAccount.setRole(r);
			System.out.println("Does this look good?");
			System.out.println("Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword());
			System.out.println("1 to confirm, 2 to start over, 3 to cancel");
			try {
			input = Integer.valueOf(scan.getScanner().nextLine());
			}catch(NumberFormatException e) {
				System.out.println("please input 1, 2, 3");
			}
			switch (input) {
			case 1:
				try {
					newAccount.setId(pServ.addPerson(newAccount));
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
			}//end switch
		}//end while
		return null; 
	}//end registerUser
	
	static Person viewAvailableBikes(Person user){
		Set<Bicycle> availaBikes = bServ.getAvailableBicycles();
		
		for(Bicycle b : availaBikes) {
			System.out.println("Id: "+b.getId() + " Bike: "+ b.getName() + " is a " + b.getBt().getTypeName() + " at " + b.getPrice());
		}
		
		System.out.println("If you saw a bike that you like, and would like "
				+ "to make an offer on enter 1.  \nenter 2 to exit");
		int input = Integer.valueOf(scan.getScanner().nextLine());
		if(input == 1) {
			while(true) {
				System.out.println("What was the ID of the Bicycle that you would like to purchase?");
				input = Integer.valueOf(scan.getScanner().nextLine());
				Bicycle buying = bServ.getBicycleById(input);
				if(buying != null && buying.getStatus().getName().equals("available")) {
					System.out.println("You would like to make an offer on  " + buying.getName() + "?"
							+ "\n press 1 to confirm offer amount, press 2 backup.");
					input = Integer.valueOf(scan.getScanner().nextLine());
					if(input == 1) {
						Offer offer = new Offer();
						Person p = pServ.getPersonByUsername(user.getUsername());
						offer.setPerson(p);
						offer.setBicycle(buying);
						offer.setAmount(buying.getPrice());
						//System.out.println(offer.toString());
						try {
							oServ.addOffer(offer);
						} catch (NullPointerException e) {
							System.out.println("something didn't exist!");
							e.printStackTrace();
						} catch (NonUniqueUsernameException e) {
							System.out.println("this should never happen1");
							e.printStackTrace();
						}
						System.out.println("Thanks you for your offer on " + buying.getName()+ ".");
						System.out.println("An employee will be getting to your offer shortly");
						user = pServ.getPersonById(user.getId());
						break;
					}else {
						System.out.println("Okay, if you want to buy a different bike enter 1, else enter 2.");
						input = Integer.valueOf(scan.getScanner().nextLine());
						if(input != 1) {
							break;
						}
					}
				}else {
					System.out.println("That bicycle is not available. Purchase a different bicycle?\n"
							+ "Enter 1 for yes, 2 to back out.");
					input = Integer.valueOf(scan.getScanner().nextLine());
					if(input != 1) {
						System.out.println("Backing out.");
						break;
					}//end not available if
				}//end else
			}//end while
		}else {
			System.out.println("Come back again later when new bikes come in.");
		}//end if after viewed available bikes
		return user;
	}//end viewAvailableBikes and bike purchase method
	
	static void viewOwnedBikes(Person user){
		//payServ.getPaymentById(user.getId());
		System.out.println("");
/*
		if( payServ.getPaymentById(user.getId()) != null) {
*/
			Set<Payment> payments = payServ.getPaymentsByUserId(user.getId());
			for (Payment pay : payments) {
				System.out.format("Own " +pay.getB().getName() + "\n"
						+ "current balance on this bike is: $" + pay.getAmount() + " at $"+ String.format("%.2f", pay.getAmount()/52) +" a week\n");

			}
/*			
		}else {
			System.out.println("You do not own any bikes");
		}
*/	

	}//end viewOwnedBikes
	
	private static Person manageBikes(Person user) throws NullPointerException{
		if(!(user.getRole().getName().equals("Employee")) ) {
			return null;
		}
		manage_loop: while(true) {
			System.out.println("Hello " +user.getUsername()+" \n"
					+ "Magaing the Bicycles in the shop:\n"
					+ "Enter 1 to add a Bicycle to the shop.\n"
					+ "Enter 2 to edit a Bicycle in the shop. \n"
					+ "Enter 3 to delete a Bicycle in the shop.\n"
					+ "Enter 4 to exit");
			int input = Integer.valueOf(scan.getScanner().nextLine());
			
			if(input ==1) {//add new bike
				Bicycle newBike = new Bicycle();
				System.out.println("Enter the name of the Bicycle: ");
				newBike.setName(scan.getScanner().nextLine());
				System.out.println("Enter 1 if Bicycle type is a StreetBike\n"
						+ "Enter 2 if Bicycle type is a MountainBike\n"
						+ "Enter 3 if Bicycle type is a TrickBike\n"
						+ "Enter 4 if adding a new Type of Bicycle");
				
				int typeInput = Integer.valueOf(scan.getScanner().nextLine());
				BikeType bt = new BikeType();
				switch(typeInput) {
				case 1:
					bt.setId(1);
					bt.setTypeName("Streetbike");
					break;
				case 2:
					bt.setId(2);
					bt.setTypeName("Mountainbike");
					break;
				case 3:
					bt.setId(3);
					bt.setTypeName("Trickbike");
					break;
				case 4:
					System.out.println("Enter the name of this type of bike");
					String typeName = (scan.getScanner().nextLine());
					if(btServ.getBikeTypeByName(typeName) !=null) {
						System.out.println("That type already exists!  Exiting system");
						break manage_loop;
					}else {
						bt.setTypeName(typeName);
						bt.setId(btServ.addBikeType(bt));
						break;
					}
				default:
					System.out.println("Invalid entry, exiting!");
					break manage_loop;
				}//end swtich
				newBike.setBt(bt);
				Status s = new Status();
				s.setId(1);
				s.setName("available");
				newBike.setStatus(s);
				System.out.println("Enter price of bike example 450.00");
				Double pr = Double.valueOf(scan.getScanner().nextLine());
				newBike.setPrice(pr);
				System.out.println("Name: "+newBike.getName() +" Type: " +newBike.getBt() + "Status: " + newBike.getStatus() + "Price: "+newBike.getPrice());
				System.out.println("Enter 1 if this Bicycle is correct\n"
						+ "Enter anything else if you want to start over");
				input = Integer.valueOf(scan.getScanner().nextLine());
				if(input==1) {
					try {
						newBike.setId(bServ.addBicycle(newBike));
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (NonUniqueUsernameException e) {
						e.printStackTrace();
					}
				}
					System.out.println(newBike.getName() + " has been added to the shop!");
			}else if(input ==2) {//edit a bike
					for(Bicycle b : bServ.getAvailableBicycles()) {
						System.out.println("Id: "+b.getId() + " Bike: "+ b.getName() + " is a " + b.getBt().getTypeName() + " at " + b.getPrice());
					}
					System.out.println("Enter the id of the bicycle needs to be updated?");
					
				
					Bicycle bike = bServ.getBicycleById(Integer.valueOf(scan.getScanner().nextLine()));
					//empty any input
					System.out.println("press enter");
					scan.getScanner().nextLine();
					Bicycle newBike = bike;
					if(bike != null) {
						System.out.println("Editing " + bike.getName());
						System.out.println("Current values: \n"
								+ "Bicycle name: " + newBike.getName()
								+ "\nBicycle type: " + newBike.getBt().getTypeName()
								+ "\nBicycle status: " + newBike.getStatus().getName());
						boolean editing = true;
						while(editing) {
							System.out.println("Enter the name of the field to edit, or save to save changes:\n"
									+ "Name\n"
									+ "Type\n"
									+ "Status\n"
									+ "Save"
									+ "\nor press enter to escape");
							String in = scan.getScanner().nextLine().toLowerCase();
							switch(in) {
							case "name":
								System.out.println("Enter new name: ");
								bike.setName(scan.getScanner().nextLine());
								break;
							case "type":
								System.out.println("Enter new name of Type: ");
								String typeName = scan.getScanner().nextLine();
								BikeType bt = new BikeType();
								if(btServ.getBikeTypeByName(typeName)!=null) {
									bt = btServ.getBikeTypeByName(typeName);
									bike.setBt(bt);
								}else {
									bt.setTypeName(typeName);
									bt.setId(btServ.addBikeType(bt));
								}
								break;
							case "status":
								Status s = new Status();
								System.out.println("Changing bike status");
								if(bike.getStatus().getId() == 1) {
									s.setId(2);
									s.setName("unavailable");
								}else {
									s.setId(1);
									s.setName("available");
								}
								bike.setStatus(s);
								break;
							case "save":
								bServ.updateBicycle(newBike);
								System.out.println(newBike.getName() + " has been succesfully updated");
								break;
							default:
								editing = false;
								System.out.println("exiting menu");
								break manage_loop;
									
							}//end switch
						}//end while editing
					}//end if bike = null 		
				}else if(input == 3){//delete a bike
					Bicycle bikeToRemove = new Bicycle();
					Set<Bicycle> availaBikes = bServ.getAvailableBicycles();
					
					for(Bicycle b : availaBikes) {
						System.out.println("Id: "+b.getId() + " Bike: "+ b.getName() + " is a " + b.getBt().getTypeName() + " at $" + b.getPrice());
					}
					System.out.println("What is the id of the Bicycle that you want to remove?");
					input = Integer.valueOf(scan.getScanner().nextLine());
					bikeToRemove = bServ.getBicycleById(input);
					System.out.println("Id: "+bikeToRemove.getId() + " Name:"+ bikeToRemove.getName() +" is the bicycle you want to remove?\n"
							+ "enter 1 to confirm\n"
							+ "enter 2 to backout");
					input = Integer.valueOf(scan.getScanner().nextLine());
					if(input ==1) {
						bServ.deleteBicycle(bikeToRemove);
						System.out.println("Remove Success");
					}else {
						System.out.println("Backing out!");
					}
				}else {
					break manage_loop;
			}
			break;
		}//end while
		return user;
	}//end manageBikes
	
	static Person manageUsers(Person user) {
		if(!(user.getRole().getName().equals("Employee"))){
			return null;
		}
		manage_user: while(true) {
			
			System.out.println("Managing Users:"
					+ "\n1. Remove a user?"
					+ "\n2. Add a user?"
					+ "\n3. check Offers and Payments"
					+ "\n4. exit");

			int input = Integer.valueOf(scan.getScanner().nextLine());
			
			if(input ==1) {
				Set<Person> userlist = pServ.getAll();
				for(Person peeps : userlist) {
					System.out.println(peeps);
				}
				System.out.println("1. Remove a user by id \n"
						+ "2. Remove a user by usernname\n"
						+ "3. exit");
				input = Integer.valueOf(scan.getScanner().nextLine());
				Person personToRemove = null;
				if(input ==1) {
					System.out.println("Enter Id of user to remove");
					int removing = Integer.valueOf(scan.getScanner().nextLine());
					if(removing ==1) {
						System.out.println("Cannot remove the owner!\n"
								+ "The owner will be notified of this!");
						break manage_user;
					}//end not the owner
					personToRemove = pServ.getPersonById(removing);
				}//end remove by ID
				else if(input == 2) {
					System.out.println("Enter username of user to remove");
					String uName = scan.getScanner().nextLine();
					if(uName.equals("owner")) {
						System.out.println("Cannot remove the owner!\n"
								+ "The owner will be notified of this!");
						break manage_user;
					}//end not the owner
					personToRemove = pServ.getPersonByUsername(uName);
				}//end remove by username
				if(input ==1 ||input ==2) {
					if(personToRemove != null) {
						System.out.println("Remove " +personToRemove +"?"
								+ "\n Enter 1 to confirm"
								+ "\n Enter 2 to backout");
						input = Integer.valueOf(scan.getScanner().nextLine());
						if(input == 1) {
							pServ.deletePerson(personToRemove);
							System.out.println("Remove Successful");
						}//end confirm	
					}else {//if person to remove was null
						System.out.println("That user does not exist");
					}
				}//end if or
				//end remove a user
				
				
			}else if (input == 2) {// add a user
				Person newAccount = new Person();
				System.out.println("Create username: ");
				newAccount.setUsername(scan.getScanner().nextLine());
				System.out.println("Enter password: ");
				newAccount.setPassword(scan.getScanner().nextLine());
				System.out.println("Enter 1 if Customer or 2 if Employee?");
				input = Integer.valueOf(scan.getScanner().nextLine());
				Role r = new Role();
				if(input == 2) {
					r.setId(2);
					r.setName("Employee");
				}else {
					r.setId(1);
					r.setName("Customer");
				}
				newAccount.setRole(r);
				System.out.println(newAccount);
				System.out.println("Is this correct?\n"
						+ "1 to confirm\n"
						+ "2. cancel");
				input = Integer.valueOf(scan.getScanner().nextLine());
				if(input ==1) {
					try {
						pServ.addPerson(newAccount);
					}catch(NonUniqueUsernameException e) {
						System.out.println("Username taken, try again.");
					}
				}//added user
				
				
			}else if (input == 3){
				Set<Offer> offerList = oServ.getAllOffers();
				for(Offer offer : offerList) {
					System.out.println("Id:"+offer.getId()+ " User:" + offer.getPerson().getUsername() + " Bike:" + offer.getBicycle().getName() + " at $" + offer.getAmount());
				}
				System.out.println("Enter 1 to Accept an Offer"
						+ "\nEnter 2 to Reject an Offer"
						+ "\nEnter 3 to see payments"
						+ "\nEnter 4 to exit");
				input = Integer.valueOf(scan.getScanner().nextLine());
				if(input == 1) {
					System.out.println("Enter the Id of the Offer you are Accepting");
					Offer offerToAccept;
					input = Integer.valueOf(scan.getScanner().nextLine());
					if(oServ.getOfferById(input)!= null) {
						offerToAccept = oServ.getOfferById(input);
						System.out.println(offerToAccept.getId() + " is this correct?\n"
								+ "enter 1 to confirm\n"
								+ "enter 2 to backout");
						input = Integer.valueOf(scan.getScanner().nextLine());
						if(input ==1) {
							Person p = offerToAccept.getPerson();
							Bicycle b = offerToAccept.getBicycle();
							Status s = new Status();
							s.setId(2);
							s.setName("unavailable");
							b.setStatus(s);
							p.getBikes().add(b);
							pServ.updatePerson(p);
							bServ.updateBicycle(b);
							oServ.acceptOffer(offerToAccept.getId());
							Payment pay = new Payment();
							pay.setB(b);
							pay.setP(p);
							pay.setAmount(b.getPrice());
							try {
								payServ.addPayment(pay);
							} catch (NullPointerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NonUniqueUsernameException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							System.out.println("Offer Accepted and all others removed.\nSuccess!");
						}else {
							System.out.println("Backing out!");
						}
					//id was null
					}else {
						System.out.println("That Id was invalid");
					}
				}//end Accept Offer 
				else if(input == 2) {
					System.out.println("Enter the Id of the Offer you are Accepting");
					Offer offerToReject;
					input = Integer.valueOf(scan.getScanner().nextLine());
					if(oServ.getOfferById(input)!= null) {
						offerToReject = oServ.getOfferById(input);
						oServ.deleteOffer(offerToReject);
						System.out.println("Rejected offer");
					}else {
						System.out.println("That id was invalid");
					}
				}//end Reject Offer
				else if(input == 3){//end manage Offers
					System.out.println("Printing all Payments");
					Set<Payment> payments = payServ.getAllPayments();
					for(Payment payment : payments) {
						System.out.println(payment.getId() + " Person:" + payment.getP().getUsername() + " owes:" + payment.getAmount() + " on Bike: " + payment.getB().getName());
					}
			
			}else if(input >= 4){
				break manage_user;
				}
			}//ennd of get all users -- get all offers
			break;
		}
		return user;
	}
}
