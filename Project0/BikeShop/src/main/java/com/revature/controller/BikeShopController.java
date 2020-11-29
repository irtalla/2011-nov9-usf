package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

public class BikeShopController {
	private static Scanner scan; 
	private static PersonService personServ = new PersonServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		mainLoop: while (true) {
			System.out.println("Hello! Welcome to BikeShop!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("\t1. Register");
				System.out.println("\t2. Log in");
				System.out.println("\t0. Exit");
				System.out.print("> ");
				String userInput = scan.nextLine().trim();
				
				switch(userInput) {
				case "1": 
					loggedInUser = registerUser();
					break;
				case "2":
					loggedInUser = logInUser();
					break;
				default:
					scan.close();
					return; 
				}
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do?");
				System.out.println("\t1. View available bikes");
				System.out.println("\t2. View my bikes");
				if (loggedInUser.getRole().getName().equals("customer")) {
					System.out.println("\t0. Log out");
				} else if (loggedInUser.getRole().getName().equals("employee")) {
					System.out.println("\t3. Manage bikes");
					System.out.println("\t0. Log out");
				} else if (loggedInUser.getRole().getName().equals("manager")) {
					System.out.println("\t3. Manage users");
					System.out.println("\t0. Log out");
				}
				System.out.print("> ");
				String userInput = scan.nextLine();
				switch (userInput) {
					case "0":
						System.out.println("See you next time!");
						loggedInUser = null;
						break menuLoop;
					case "1":
						loggedInUser = viewAvailableBikes(loggedInUser);
						break;
					case "2":
						viewUserBikes(loggedInUser);
						break;
					case "3":
						if (loggedInUser.getRole().getName().equals("employee")) {
							loggedInUser = manageBikes(loggedInUser);
						} else {
							System.out.println("\nNot Allowed\n");
						}
						break;
					case "4":
						if (loggedInUser.getRole().getName().equals("employee")) {
							loggedInUser = manageUsers(loggedInUser);
						} else {
							System.out.println("\nNot Allowed\n");
						}
						break;
					default:
						System.out.println("\nSorry didn't get that please try again\n");
				}
			}

		}
	}

	
	private static Person registerUser() {
		while (true) {
			Person newAccount = new Person();
			System.out.print("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.print("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			
			int choice = -1;
			do {
				System.out.print("Choose a role (1. customer, 2. for employee, or 3. manager): ");
				try {
					choice = Integer.parseInt(scan.nextLine());
					
					if(choice == 1) {
						Role r = new Role();
						r.setId(1);
						r.setName("customer");
						newAccount.setRole(r);
					} else if (choice == 2) {
						Role r = new Role();
						r.setId(2);
						r.setName("employee");
						newAccount.setRole(r);
					} else if (choice == 3) {
						Role r = new Role();
						r.setId(2);
						r.setName("manager");
						newAccount.setRole(r);
					} else {
						System.out.println("Invalid option please select 1, 2, or 3");
						choice = -1;
					}
				} catch(NumberFormatException e) {
					System.out.println("Could not parse option please select 1, 2, or 3");
					choice = -1;
				}
			} while(choice == -1);
			
			System.out.println("Does this look good?");
			System.out.println(
					"Username: " + newAccount.getUsername()
					+ " Password: " + newAccount.getPassword() 
					+ " role: " + newAccount.getRole().getId() + "-" + newAccount.getRole().getName());
			System.out.print("enter 1 to confirm, 2 to start over, any other char to cancel: ");
			String input = scan.nextLine().trim();
			if(input.startsWith("1")) {
				try {
					Person newPerson = personServ.addPerson(newAccount);
					if(newPerson != null) {
						System.out.println("Confirmed. Welcome!");
					} else {
						System.err.println("Error could not create.");
					}
					return newPerson;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Sorry, that username is taken - let's try again.");
				}
			} else if (input.startsWith("2")) {
				System.out.println("Okay, let's try again.");
			}
			else {
				System.out.println("Okay, let's go back.");
				return null;
			}	
		}
		
	}
	
	
	private static Person logInUser() {
		while (true) {
			System.out.print("Enter username: ");
			String username = scan.nextLine();
			System.out.print("Enter password: ");
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
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			String input = scan.nextLine().trim();
			if (!input.startsWith("1")) {
				break;
			}
		}
		return null;
	}

	
	private static Person viewAvailableBikes(Person user) {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		
		for (Bike bike : availableBikes) {
			System.out.println(bike);
		}
		
		System.out.println("Would you like to purchase a bike? 1 for yes, 9 for no");
		int input = Integer.valueOf(scan.nextLine());
		if (input == 1) {
			while (true) {
				System.out.println("Which bike? Type its ID.");
				input = Integer.valueOf(scan.nextLine());
				Bike bike = bikeServ.getBikeById(input);
				if (bike != null && bike.getStatus().getName().equals("available")) {
					System.out.println(bike);
					System.out.println("Do you want to purchase bike " + bike.getId() + ", brand " + bike.getBrand().getName() + "? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (input == 1) {
						bikeServ.purchaseBike(user, bike);
						System.out.println("You did it! You purchased " + bike.getId() + ", brand " + bike.getBrand().getName() + ".");
						// get the person with their updated cat set
						user = personServ.getPersonById(user.getId());
						break;
					} else {
						System.out.println("Okay, did you want to purchase a different one? 1 for yes, other for no");
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

	private static void viewUserBikes(Person user) {
		if (user.getBikes().size() > 0) {
			System.out.println("Viewing your bikes: ");
			for (Bike bike : user.getBikes()) {
				System.out.println(bike);
			}
			boolean testBike = false;
			while (true) {
				System.out.print("...would you like to test ");
				if (testBike)
					System.out.print("another ");
				System.out.println("one? 1 for yes, other for no");
				int input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					System.out.println("Which one? Type its ID.");
					input = Integer.valueOf(scan.nextLine());
					Bike bike = bikeServ.getBikeById(input);
					if (bike != null && user.getBikes().contains(bike)) {
						System.out.println("You test " + bike.getBrand() + ". Yay");
						testBike = true;
					} else
						System.out.println("Sorry, that's not one of your bikes.");
				} else {
					break;
				}
			}
		} else {
			System.out.println("You don't have any bikes... yet.");
		}
	}

	private static Person manageBikes(Person user) {
		if (!(user.getRole().getName().equals("employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Bikes:\n1. Add a bike\n2. Edit a bike\n3. Remove a bike\n9. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				Bike newBike = new Bike();
				System.out.println("Enter a name: ");
				newBike.setName(scan.nextLine());
				System.out.println("Enter an condition: ");
				newBike.setCondition(scan.nextLine());
				System.out.println("Choose a brand by entering its ID:"
						+ "\n1. Cannondale\n2. Trek\n3. Scott\nother. Other");
				Brand brand = new Brand();
				brand.setId(Integer.valueOf(scan.nextLine()));
				switch(brand.getId()) {
				case 1:
					brand.setName("Cannondale");
					break;
				case 2:
					brand.setName("Trek");
					break;
				case 3:
					brand.setName("Scott");
					break;
				default:
					brand.setName("Other");
					break;
				}
				newBike.setBrand(brand);
				Status status = new Status();
				status.setId(1);
				status.setName("Available");
				newBike.setStatus(status);
				System.out.println(newBike);
				System.out.println("Look good? 1 to confirm, other to start over");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
					newBike.setId(bikeServ.addBike(newBike));
					System.out.println("You successfully added " + newBike.getName() + "!");
				}
			} else if (input == 2) {
				for (Bike bike : bikeServ.getAvailableBikes()) {
					System.out.println(bike);
				}
				System.out.println("Which bike would you like to update? Enter its ID.");
				Bike bike = bikeServ.getBikeById(Integer.valueOf(scan.nextLine()));
				Bike newBike = bike;
				if (bike != null) {
					System.out.println("Editing " + bike.getBrand());
					System.out.println("Current changes:\nBrand: " + newBike.getBrand()
							+ " Condition: " + newBike.getCondition());
					boolean editing = true;
					while (editing) {
						System.out.println("Edit:\n1. Name\n2. Condition\n3. Save changes\nother. Cancel");
						input = Integer.valueOf(scan.nextLine());
						switch (input) {
						case 1:
							System.out.println("Enter new brand: ");
							bike.setName(scan.nextLine());
							break;
						case 2:
							System.out.println("Enter new condition: ");
							bike.setCondition(scan.nextLine());
							break;
						case 3:
							bikeServ.updateBike(newBike);
							System.out.println("You updated " + newBike.getBrand() + " successfully.");
						default:
							editing = false;
							break;
						}
					}
				}
			} 
			// insert else if for removal of a bike
			else if (input == 3) {
				
				System.out.println("1. Remove by ID\n9. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Bike bikeToRemove = null;
				if (input == 1 && bikeToRemove == null) {
					System.out.println("Enter the ID of the bike you want to remove.");
					
					int bikeID = Integer.valueOf(scan.nextLine());
					
					System.out.println( "bikeID: " + bikeID );
					
					bikeToRemove = bikeServ.getBikeById( bikeID );

					if ( bikeToRemove == null )
						System.out.println( "bikeToRemove is null" );
						
					
					System.out.println( "bikeToRemove: " + bikeToRemove );
					System.out.println("Remove this bike? 1 for yes, other for no");
					input = Integer.valueOf(scan.nextLine());
					if (bikeToRemove != null) {
						bikeServ.removeBike(bikeToRemove);
						System.out.println("You removed "
								+ bikeToRemove.getName() + " successfully.");
					}
					else {
							System.out.println("That bike doesn't exist.");
						}
				} 
				else if (input == 1 && bikeToRemove != null) {
						System.out.println(bikeToRemove);
						System.out.println("Remove this bike? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							bikeServ.removeBike(bikeToRemove);
							System.out.println("You removed "
									+ bikeToRemove.getName() + " successfully.");
						}
						else {
								System.out.println("That bike doesn't exist.");
							}
				}
				
			} 
			
			
			// end of else if for removal of a bike
			else {
				break;
			}
		}
		
		return user;
	}
	
	private static Person manageUsers(Person user) {
		
		if (!(user.getRole().getName().equals("employee")))
			return null;
		
		while (true) {
			System.out.println("Manage Users:\n1. Remove a user\n2. Add a user\nother. Cancel");
			int input = Integer.valueOf(scan.nextLine());
			
			if (input == 1) {
				System.out.println("1. Remove by ID\n2. Remove by username\nother. Cancel");
				input = Integer.valueOf(scan.nextLine());
				Person personToRemove = null;
				if (input == 1) {
					System.out.println("Enter the ID of the user you want to remove.");
					personToRemove = personServ.getPersonById(Integer.valueOf(scan.nextLine()));
				} else if (input == 2) {
					System.out.println("Enter the username of the user you want to remove.");
					personToRemove = personServ.getPersonByUsername(scan.nextLine());
				}
				if (input == 1 || input == 2) {
					if (personToRemove != null) {
						System.out.println(personToRemove);
						System.out.println("Remove this user? 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input == 1) {
							personServ.deletePerson(personToRemove);
							System.out.println("You removed "
									+ personToRemove.getUsername() + " successfully.");
						}
					} else {
						System.out.println("That user doesn't exist.");
					}
				}
			} else if (input == 2) {
				Person newAccount = new Person();
				System.out.println("Enter a username: ");
				newAccount.setUsername(scan.nextLine());
				System.out.println("Enter a password: ");
				newAccount.setPassword(scan.nextLine());
				System.out.println("Choose a role:\n1. Employee\n0. Customer");
				input = Integer.valueOf(scan.nextLine());
				Role role = new Role();
				if (input == 1) {
					role.setId(1);
					role.setName("employee");
				} else {
					role.setId(2);
					role.setName("customer");
				}
				newAccount.setRole(role);
				System.out.println(newAccount);
				System.out.println("Look good? 1 to confirm, other to cancel");
				input = Integer.valueOf(scan.nextLine());
				if (input == 1) {
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



}
