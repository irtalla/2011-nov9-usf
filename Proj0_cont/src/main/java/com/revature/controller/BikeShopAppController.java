package com.revature.controller;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Bike;
import com.revature.models.InventoryStatus;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PaymentService;
import com.revature.services.PaymentServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

public class BikeShopAppController {
	private static Scanner scan;
	private static UserService userServ = new UserServiceImpl();
	private static BikeService bikeServ = new BikeServiceImpl();
	private static OfferService offerServ = new OfferServiceImpl();
	private static PaymentService payServ = new PaymentServiceImpl();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Hello! Welcome to BikeShopApp!");
			System.out.println("Don't forget to wash your hands and practice social distancing!");
			User loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do?");
				System.out.println("1. Sign Up\n2. Log In\nOther: Exit");
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
					System.out.println("Good Bye!");
					break mainLoop;
				}
			}
			
			menuLoop: 
			while (true) {
				System.out.println("Please choose the user menus you would like to access: ");
				System.out.println("1. Default\n2. Customer\n3. Employee\n4. Manager\nOther: Log Out");
				int userInput = Integer.valueOf(scan.nextLine());
				String userRole = "";
				userRoleSwitch:
				switch (userInput) {
				case 1:
					for (Role r : loggedInUser.getRoles()) {
						if (r.getName().equals("User")) {
							userRole = "User";
						}
					}
					if (userRole.equals("User")) {
						System.out.println("What would you like to do?");
						System.out.println("1. Register for Customer Account\n2. View Available Bikes\nOther: Return to Menu Selection");
						int defaultUserInput = Integer.valueOf(scan.nextLine());
						userSwitch:
						switch (defaultUserInput) {
						case 1:
							loggedInUser = registerAsCustomer(loggedInUser);
							System.out.println("Thank You for Registering as a Customer!\nReturning to Menu Selection...");
							break userSwitch;
						case 2:
							loggedInUser = viewAvailableBikes(loggedInUser, userRole);
							break userSwitch;
						default:
							System.out.println("Returning to Menu Selection...");
							break userSwitch;
						}
					} else {
						System.out.println("Unauthorized access detected!\nTry Again.");
						break userRoleSwitch;
					}
				case 2:
					for (Role r : loggedInUser.getRoles()) {
						if (r.getName().equals("Customer")) {
							userRole = "Customer";
						}
					}
					if (userRole.equals("Customer")) {
						System.out.println("What would you like to do?");
						System.out.println("1. View Available Bikes");
						System.out.println("2. View Owned Inventory");
						System.out.println("3. Make an Offer for a Bike");
						System.out.println("4. View Remaining Payments");
						System.out.println("Other: Return to Menu Selection");
						int customerUserInput = Integer.valueOf(scan.nextLine());
						customerSwitch:
						switch (customerUserInput) {
						case 1:
							loggedInUser = viewAvailableBikes(loggedInUser, userRole);
							break customerSwitch;
						case 2:
						case 3:
						case 4:
						default:
							System.out.println("Returning to Menu Selection...");
							break customerSwitch;
						}
						
					} else {
						System.out.println("Unauthorized access detected!\nTry Again.");
						break userRoleSwitch;
					}
				case 3:
					for (Role r : loggedInUser.getRoles()) {
						if (r.getName().equals("Employee")) {
							userRole = "Employee";
						}
					}
					if (userRole.equals("Employee")) {
						System.out.println("What would you like to do?");
						System.out.println("1. Register for Customer Account");
						System.out.println("2. View Available Bikes");
						System.out.println("3. Add a Bike to Stock");
						System.out.println("4. Remove a Bike from Stock");
						System.out.println("5. View Pending Offer for a Bike");
						System.out.println("6. View All Payments");
						System.out.println("7. Edit a Bike");
						
						int employeeUserInput = Integer.valueOf(scan.nextLine());
						employeeSwitch:
						switch (employeeUserInput) {
						case 1:
							loggedInUser = registerAsCustomer(loggedInUser);
							System.out.println("Thank You for Registering as a Customer!\nReturning to Menu Selection...");
							break employeeSwitch;
						case 2:
							loggedInUser = viewAvailableBikes(loggedInUser, userRole);
							break employeeSwitch;
						case 3:
							addBike();
							System.out.println(loggedInUser);
							break employeeSwitch;
						case 4:
							removeBike();
							break employeeSwitch;
						case 5:
						case 6:
						default:
							System.out.println("Returning to Menu Selection...");
							break employeeSwitch;
						}
					}else {
						System.out.println("Unauthorized access detected!\nTry Again.");
						break userRoleSwitch;
					}
				case 4:
					for (Role r : loggedInUser.getRoles()) {
						if (r.getName().equals("Manager")) {
							userRole = "Manager";
						}
					}
					if (userRole.equals("Manager")) {
						System.out.println("What would you like to do?");
						System.out.println("1. Register for Customer Account");
						System.out.println("2. View Available Bikes");
						System.out.println("3. Add a Bike to Stock");
						System.out.println("4. Remove a Bike from Stock");
						System.out.println("5. View Pending Offer for a Bike");
						System.out.println("6. View All Payments");
						System.out.println("7. Edit a Bike");
						System.out.println("8. Create Employee Account");
						System.out.println("9. Remove Employee");
						System.out.println("10. View All Offers");
						
						int managerUserInput = Integer.valueOf(scan.nextLine());
						managerSwitch:
						switch (managerUserInput) {
						case 1:
							loggedInUser = registerAsCustomer(loggedInUser);
							System.out.println("Thank You for Registering as a Customer!\nReturning to Menu Selection...");
							break managerSwitch;
						case 2:
							loggedInUser = viewAvailableBikes(loggedInUser, userRole);
							break managerSwitch;
						case 3:
							addBike();
							break managerSwitch;
						case 4:
							removeBike();
							break managerSwitch;
						case 5:
						case 6:
						default:
							System.out.println("Returning to Menu Selection...");
							break managerSwitch;
						}
					}else {
						System.out.println("Unauthorized access detected!\nTry Again.");
						break userRoleSwitch;
					}
				default:
					System.out.println("Until Next Time!... ");
					loggedInUser = null;
					break menuLoop;
				}
			}
		}
		scan.close();		
	}
	
	private static User registerUser() {
		while (true) {
			User newAccount = new User();
			System.out.println("Enter a username: ");
			newAccount.setUsername(scan.nextLine());
			System.out.println("Enter a password: ");
			newAccount.setPassword(scan.nextLine());
			Role r = new Role();
			r.setId(1);
			r.setName("User");
			Set<Role> roles = new HashSet<>();
			roles.add(r);
			newAccount.setRoles(roles);
			System.out.println("You've entered:");
			System.out.println("Username: " + newAccount.getUsername() 
					+ "\tPassword: " + newAccount.getPassword());
			System.out.println("1. Confirm\n2. Start Over\nOther: Cancel");
			int input = Integer.valueOf(scan.nextLine());
			switch (input) {
			case 1:
				try {
					newAccount.setId(userServ.addUser(newAccount));
					System.out.println("Welcome to the bike shop, " + newAccount.getUsername() + "!");
					return newAccount;
				} catch (NonUniqueUsernameException e) {
					System.out.println("Username taken. Try again.");
				}
			case 2:
				System.out.println("Starting over.");
				break;
			default:
				System.out.println("Returning to main menu.");
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
				System.out.print("Username not found. ");
			} else if (user.getPassword().equals(password)) {
				System.out.println("Welcome back, " + username + "!");
				return user;
			} else {
				System.out.print("That password is incorrect. ");
			}
			System.out.println("Do you want to try again? \n1. Yes \nOther: No.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	
	private static User registerAsCustomer(User currentUser) {
		Set<Role> roles = currentUser.getRoles();
		
		for (Role r : roles) {
			if (r.getName().equals("Customer")) {
				System.out.println("Seems like you are already our customer! Thank you and enjoy shopping with us!");
				return currentUser;
			}
		}
		Role r = new Role();
		r.setId(3);
		r.setName("Customer");

		roles.add(r);
		currentUser.setRoles(roles);
		userServ.updateUser(currentUser);
		return currentUser;
	}
	
	private static User viewAvailableBikes(User currentUser, String role) {
		Set<Bike> availableBikes = bikeServ.getAvailableBikes();
		System.out.println("Here are the available bikes: ");
		if (availableBikes.size() == 0) {
			System.out.println("No available bikes at shop");
			return currentUser;
		}
		for (Bike b : availableBikes) {
			System.out.println(b);
		}
		
		if (role.equals("User")) {
			while (true) {
				System.out.println("Register as a customer to purchase these amazing bikes!");
				System.out.println("Would you like to return to the User Menu?\n1: Yes\n2: No\nOther: Cancel");
				int userInput = Integer.valueOf(scan.nextLine());
				switch(userInput) {
				case 1:
					System.out.println("Returning to User Menu...");
					return currentUser;
				case 2:
					System.out.println("Feel free to return to User Menu when you're done browsing!");
					break;
				default:
					return currentUser;
				}
			}
		}
		else if (role.equals("Customer")) {
			while (true) {
				System.out.println("Would you like to purchase a bike?");
				
			}
		} else if (role.equals("Employee") || role.equals("Manager")) {
			while (true) {
				System.out.println("What would you like to do?");
				System.out.println("1. Add a Bike");
				System.out.println("2. Remove a Bike");
				System.out.println("Other: Cancel");
//				System.out.println("3. Edit a Bike");
				int employeeInput = Integer.valueOf(scan.nextLine());
				switch(employeeInput) {
				case 1:
					addBike();
					break;
				case 2:
					removeBike();
					break;
				default:
					System.out.println("Returning to Employee Menu");
					return currentUser;
				}
			}
		} else {
			System.out.println("Something is wrong with your role.");
			System.out.println("Returning to Menu...");
		}
		
		return currentUser;
		
	}

	private static User viewOwnedInventory(User currentUser) {
		
		return currentUser;
	}
	
	private static void addBike() {
		Integer bikeId = 0;
		Bike b = new Bike();
		while (true) {
			System.out.println("What will be the value of this bike?");
			Float value = Float.valueOf(scan.nextLine());
			b.setValue(value);
			
			System.out.println("What is the availability of this bike? (defaults to available)");
			System.out.println("1. Available\n2. Unavailable");
			int availability = Integer.valueOf(scan.nextLine());
			InventoryStatus is = new InventoryStatus();
			switch(availability) {
			case 2:
				is.setId(2);
				is.setName("Unavailable");
				break;
			case 1:
			default:
				is.setId(1);
				is.setName("Available");
				break;
			}
			b.setStatus(is);
			
			System.out.println("Are you satisfied with the bike info?");
			System.out.println(b);
			System.out.println("1. Yes\n2. No");
			int satisfied = Integer.valueOf(scan.nextLine());
			switch(satisfied) {
			case 1:
				System.out.println("Alright! New bike is being added!");
				bikeId = bikeServ.addBike(b);
				return;
			case 2:
				System.out.println("Let's look further into it.");
				break;
			default:
				System.out.println("Returning to Menu");
				return;
			}
		}
	}
	
	private static void removeBike() {
		Bike b = new Bike();
		
		Set<Bike> bikes = bikeServ.getBikes();
		System.out.println("Here are all the bikes: ");
		if (bikes.size() == 0) {
			System.out.println("No bikes at shop to be removed");
			return;
		}
		for (Bike bike : bikes) {
			System.out.println(bike);
		}
		while (true) {
			System.out.println("What is the id of the bike you would like to remove?");
			int bikeId = Integer.valueOf(scan.nextLine());
			b = bikeServ.getBikeById(bikeId);
			System.out.println("Are you sure that you want to remove this bike?");
			System.out.println(b);
			System.out.println("1. Yes\n2. No\nOther: Cancel");
			int decision = Integer.valueOf(scan.nextLine());
			switch(decision) {
			case 1:
				System.out.println("Deleting the bike...");
				bikeServ.removeBike(b);
				System.out.println("Bike sucessfully removed! Returning to Menu...");
				return;
			case 2:
				System.out.println("Let's look further into it.");
				break;
			default:
				System.out.println("Returning to Menu");
				return;
			}
		}
	}
	
	private static void editBike() {
		Bike b = new Bike();
		Set<Bike> bikes = bikeServ.getBikes();
		System.out.println("Here are all the bikes: ");
		if (bikes.size() == 0) {
			System.out.println("No bikes at shop to be edited");
			return;
		}
		
		for (Bike bike : bikes) {
			System.out.println(bike);
		}
		while (true) {
			System.out.println("What is the id of the bike you would like to edit?");
			int bikeId = Integer.valueOf(scan.nextLine());
			b = bikeServ.getBikeById(bikeId);
			
			System.out.println("What will be the value of this bike?");
			Float value = Float.valueOf(scan.nextLine());
			b.setValue(value);
			
			System.out.println("What is the availability of this bike? (defaults to available)");
			System.out.println("1. Available\n2. Unavailable");
			int availability = Integer.valueOf(scan.nextLine());
			InventoryStatus is = new InventoryStatus();
			switch(availability) {
			case 2:
				is.setId(2);
				is.setName("Unavailable");
				break;
			case 1:
			default:
				is.setId(1);
				is.setName("Available");
				break;
			}
			b.setStatus(is);
			
			System.out.println("Are you satisfied with the bike info?");
			System.out.println(b);
			System.out.println("1. Yes\n2. No");
			int satisfied = Integer.valueOf(scan.nextLine());
			switch(satisfied) {
			case 1:
				System.out.println("Alright! New bike is being edited!");
				bikeServ.updateBike(b);
				return;
			case 2:
				System.out.println("Let's look further into it.");
				break;
			default:
				System.out.println("Returning to Menu");
				return;
			}
		}
	}
}
