package controller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import beans.Usr;
import beans.Bikes;
import beans.Offers;
import beans.Role;
import services.UserService;
import services.UserServiceImpl;
import utilities.Print;
import services.BikeService;
import services.BikeServiceImpl;
import exceptions.NonUniqueUsernameException;


public class BikeController {
	private static Scanner scan;
	private static UserService userServ = new UserServiceImpl(); // TODO
	private static BikeService bikeServ = new BikeServiceImpl(); // TODO

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to the BikeApp!");
			System.out.println("o__         __o        ,__o        __o           __o");
			System.out.println(",>/_       -\\<,      _-\\_<,       _`\\<,_       _ \\<_");
			System.out.println("(*)`(*).....O/ O.....(*)/'(*).....(*)/ (*).....(_)/(_)");
			System.out.println("\n\n\n");
			Usr loggedInUsr = null; 
			
			while (loggedInUsr == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Register\n2. Log in \nother. Exit");
				int userInput = Integer.valueOf(scan.nextLine());
				
				switch(userInput) {
				case 1:
					loggedInUsr = registerUsr();
					break;
				case 2:
					loggedInUsr = logInUsr();
					break;
				default:
					userActive = false;
					break mainLoop;
				}
			}
		menuLoop: while (true) {
			System.out.println("What would you like to do?");
			System.out.println("1. View available bikes \n2. View my bikes. \n3. Add money to account. Negative adds go to jail.");
			if (loggedInUsr.getRole().getName().equals("Usr")) {
				System.out.println("other. Log out");
			} else if (loggedInUsr.getRole().getName().equals("Employee")) {
				System.out.println("4. Manage bikes \n 5. Manage users\n other. Log Out.");
			}
		int userInput = Integer.valueOf(scan.nextLine());
		switch (userInput) {
		case 1:
			loggedInUsr = viewAvailableBikes(loggedInUsr);
			break;
		case 2:
			viewUserBikes(loggedInUsr);
			break;
		case 3:
			AddUserMoney(loggedInUsr);
		case 4:
			loggedInUsr = manageBikes(loggedInUsr);
			break;
		case 5:
			loggedInUsr = manageUsers(loggedInUsr);
			break;
		default:
			System.out.println("See you next time!");
			loggedInUsr = null;
			break menuLoop;
			}
		}
	}
	scan.close();
	}
private static Usr AddUserMoney(Usr user) {
	while (true) {
		double baseAmount = userServ.getMoney(user);
		System.out.println("You currently have" + baseAmount + "moneys.");
		System.out.println("What would you like to set your money to?");
		double input = Double.valueOf(scan.nextLine());
		try {
		userServ.setMoney(input, user);
		}catch (Exception e) {
		e.printStackTrace();
	}
		System.out.println("Congrats! Money added. Police are on the way.");
	}
}

private static Usr registerUsr() {
	while (true) {
		Usr newAccount = new Usr();
		System.out.println("Put in the username you'd like: ");
		newAccount.setUsername(scan.nextLine());
		System.out.println("Please enter a password: ");
		newAccount.setPassword(scan.nextLine());
		Role r = new Role();
		r.setId(1);
		r.setName("Usr");
		newAccount.setRole(r);
		System.out.println("Press 1 to finish, 2 to restart, or something else to cancel.");
		int input = Integer.valueOf(scan.nextLine());
		switch (input) {
		case 1:
			try {
				newAccount.setID(userServ.addUser(newAccount));
				System.out.println("Confirmed. Welcome!");
				return newAccount;
			} catch (NonUniqueUsernameException e) {
				System.out.println("Sorry, someone else has that username.");
			}
			break;
		case 2:
			System.out.println("Okay, let's try again.");
			break;
		default:
			System.out.println("Okay, let's do something else.");
			return null;
		}
	}
}
private static Usr logInUsr() {
	while (true) {
		System.out.println("Put your username in the prompt if you know what's good for you: \n");
		String username = scan.nextLine();
		System.out.println("Powerword: \n");
		String password = scan.nextLine();
		Usr user = userServ.getUserByUsername(username);
		if (user == null) {
			System.out.println("Username not found. ");
		} else if (user.getPassword().equals(password)) {
			System.out.println("Welcome!");
			return user;
		} else {
			System.out.println("That password is incorrect. ");
		}
		System.out.println("Do you want to try again? 1 for yes, other for no.");
		int input = Integer.valueOf(scan.nextLine());
		if (input != 1) {
			break;
		}
	}
	return null;
}
	private static Usr viewAvailableBikes(Usr user) {
		Set<Bikes> availableBikes = bikeServ.getAvailableBikes();
		
		for (Bikes bike : availableBikes) {
			System.out.println(bike);
		}
			System.out.println("Would you like to make an offer on a bike? Enter 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input == 1) {
				while (true) {
					System.out.println("Which bike? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Bikes bike = bikeServ.getBikeById(input);
					if (bike != null && bike.getStatus().equals("Available")) {
						
						System.out.println(bike);
						System.out.println("Enter 1 to confirm offer on" + bike.getName());
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							System.out.println("Enter the amount you'd like to offer.");
							double amount = Double.valueOf(scan.nextLine());
							Offers offer = new Offers();
							offer.setAmount(amount);
							offer.setOfferer(user.getId());
							offer.setTargetBike(bike);
							try {
								userServ.placeOffer(offer);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							System.out.println("Offer complete!");
							break;
						} else {
							System.out.println("Do you want to place an offer for another bike?" + " Enter 1 for yes, other for no.");
							input = Integer.valueOf(scan.nextLine());
							if (input != 1) 
								break; 	
						   }
						 } else {
						System.out.println("Sorry, that bike's unavailable. To try again, enter 1, other to return.");
						input = Integer.valueOf(scan.nextLine()); 
						 if (input != 1 ) {
							System.out.println("Okay, that's fine");
							break;
						 }
					}
				}	} else {
				System.out.println("Okay, that's fine.") ;
						}
			
			return user;
	}
			private static void viewUserBikes(Usr user) {
				try {
					if (userServ.getBikesByUserId(user.getId()).size() > 0) {
						System.out.println("Viewing your bikes: ");
						for (Bikes bike : userServ.getBikesByUserId(user.getId())) {
							System.out.println(bike);
						} } else {
							System.out.println("You haven't purchased a bike yet.");
						}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
	private static Usr manageBikes(Usr user) {
		if (!user.getRole().getName().equals("Employee"))
			return null;
		
		while (true) {
			System.out.println("Manage Bikes:\n1. Add a bike\n2. Edit a bike\n3 View offers \nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				Bikes newBike = new Bikes();
				System.out.println("Enter the model name: ");
				newBike.setName(scan.nextLine());
				newBike.setStatus("Available");
				System.out.println(newBike);
				System.out.println("Press 1 to confirm the entry, other to cancel.");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newBike.setId(bikeServ.addBike(newBike));
					System.out.println("Successful add!");
				}
			} else if (input == 2) {
				Set<Bikes> availableBikes = bikeServ.getAvailableBikes();
				for (Bikes bike : bikeServ.getAvailableBikes()) {
					availableBikes.add(bike);
					System.out.println(availableBikes);
					break;
				}
				System.out.println("Enter the ID of the bike you'd like to update.");
				Bikes bike;
				try {
					bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
					
				} catch (NumberFormatException e) {
					break;
				}
				Bikes newbike = bike;
				if (bike != null) {
					System.out.println("Editing " + bike.getName());
					System.out.println("Current changes:\nName: " + newbike.getName());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Name\n2. Save changes \n3. Remove bike \nother. Cancel");
						input = Integer.valueOf(scan.nextLine());
						switch (input) {
						case 1:
							System.out.println("Enter new name: ");
							bike.setName(scan.nextLine());
							break;
						case 2:
							bikeServ.updateBike(newbike);
							System.out.println("You updated " + newbike.getName() + " successfully.");
							break;
						case 3:
							bikeServ.removeBike(newbike);
							System.out.println("Bike removed.");
						default:
							editing = false;
							break;
						}
					}
				}
				}
				else if (input == 3) {
					try {
						Set<Offers> OffersList = userServ.getOffers();
						for (Offers Offers : OffersList) {
							OffersList.add(Offers);
							System.out.println(Offers);
						}
						System.out.println("Would you like to accept an offer? Enter 1 for yes, other to cancel.");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							System.out.println("Enter the Offer ID you would like to accept.");
							Integer inputId= Integer.valueOf(scan.nextLine());
							userServ.acceptOffer(inputId);
							//userServ.setMoney(userServ.getMoney(userServ.getUserById(userServ.getOfferById(inputId).getOfferer())) - (userServ.getOfferById(inputId)).getAmount(), (userServ.getUserById(userServ.getOfferById(inputId).getOfferer())));
							break;
						}
						else { break;
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				break;
			}
		}
		return user;
	}
	
	
	
	private static Usr manageUsers(Usr user) {
		if (!(user.getRole().getName().equals("Employee")))
			return null;
		
		while (true) {
			System.out.println("Manage users:\n1. Remove a user\n2. Add a user\nother. Cancel");
int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Usr userToRemove = null;
				if (input == 1) {
					System.out.println("Enter the ID of the user you want to remove.");
					userToRemove = userServ.getUserById(Integer.valueOf(scan.nextLine()));
				} else if (input == 2) {
					System.out.println("Enter the username of the user you want to remove.");
					userToRemove = userServ.getUserByUsername(scan.nextLine());
				}
				if (input == 1 || input == 2) {
					if (userToRemove != null) {
						System.out.println(userToRemove);
						System.out.println("Remove this user? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							userServ.deleteUser(userToRemove);
							System.out.println("You removed "
									+ userToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if (input == 2) {
				Usr newAccount = new Usr();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:\n1. Employee\nother. User");
				input = Integer.valueOf(scan.nextLine());
				Role role = new Role();
				if (input == 1) {
					role.setId(2);
					role.setName("Employee");
				} else {
					role.setId(1);
					role.setName("Usr");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					try {
						userServ.addUser(newAccount);
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
}

