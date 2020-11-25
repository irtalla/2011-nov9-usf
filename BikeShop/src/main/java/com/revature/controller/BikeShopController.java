package com.revature.controller;

import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Accessory;
import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Offer;
import com.revature.beans.Payment;
import com.revature.beans.Role;
import com.revature.services.AccessoryService;
import com.revature.services.AccessoryServiceImpl;
import com.revature.services.BicycleService;
import com.revature.services.BicycleServiceImpl;
import com.revature.services.CustomerService;
import com.revature.services.CustomerServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PaymentService;
import com.revature.services.PaymentServiceImpl;

public class BikeShopController 
{
	//services
	private static OfferService offServ = new OfferServiceImpl();
	private static CustomerService custServ = new CustomerServiceImpl();
	private static BicycleService bikeServ = new BicycleServiceImpl();
	private static AccessoryService accServ = new AccessoryServiceImpl();
	private static PaymentService payServ = new PaymentServiceImpl();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		while(run());
		
	}
	
	public static boolean run() 
	{
		Customer user = null;
		System.out.println("Welcome to the Bike Shop!\nPlease Select an Option:\n1. Login\n2. Sign Up\n3. Exit");
		
		login: while(true)
		{
			String line = scan.nextLine();
			if(line.equals("1"))
			{
				while(true)
				{
					System.out.println("Enter Credentials\nUsername: ");
					String username = scan.nextLine();
					System.out.println("Password: ");
					String password = scan.nextLine();
					user = verifyLogin(username,password);
					if (user != null)
					{
						break login;
					}
					else
					{
						System.out.println("Incorrect Login!");
					}
				}
				
			}
			else if (line.equals("2"))
			{
				String newUsername;
				String newPassword;
				String newName;
				while(true)
				{
					System.out.println("Enter new username (30 characters or less)");
					newUsername = scan.nextLine();
					if (!verifyUniqueUsername(newUsername))
					{
						System.out.println("Username alreay in use, please try again");
					}
					else
					{
						break;
					}
				}
				System.out.println("Enter a password (30 characters or less)");
				newPassword = scan.nextLine();
				System.out.println("Enter your name (30 characters or less");
				newName = scan.nextLine();
				user = new Customer();
				user.setUsername(newUsername);
				user.setPassword(newPassword);
				user.setName(newName);
				if (custServ.addCustomer(user) != null);
					break;
				
			}
			else if (line.equals("3"))
			{
				return false;
			}
				
		}
		
		System.out.println("Login Successful! Welcome " + user.getName() + "!");
		switch(user.getRole().getId())
		{
		case 1:
			System.out.println("Logged in as Employee");
			break;
		case 2:
			System.out.println("Logged in as Manager");
			break;
		}
		while(true)
		{
			Integer choice = null;
			while(true)
			{
				boolean valid = true;
				System.out.println("What would you like to do? (Please enter the number of your choice)");
				
				System.out.println("1. Logout\n2. View Balance Details\n3. View Available Bicycles\n4. View Bicycle Accessories");
				System.out.println("5. View Owned Bicycles\n6. View Owned Accessories");
				switch(user.getRole().getId())
				{
				case 1:
					System.out.println("7. View User payments");
					break;
				case 2:
					System.out.println("7. View User payments\n8. View Employee Accounts\n9. View Offer History");
					break;
				}
				
				try
				{
					choice = Integer.parseInt(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
				}
				
				if(choice < 1 || choice > 9 || (user.getRole().getId() < 1 && choice > 6) || (user.getRole().getId() < 2 && choice > 7))
					valid = false;
				
				if (valid)
					break;
				else
				{
					System.out.println("Invalid Choice! please try again!");
				}
				
			}
			
			switch(choice)
			{
			case 1:
				System.out.println("GoodBye");
				return true;
			case 2:
				viewBalance(user);
				break;
			case 3:
				viewAvailableBicycles(user);
				break;
			case 4:
				viewAccessories(user);
				break;
			case 5:
				viewMyBicycles(user);
				break;
			case 6:
				viewMyAccessories(user);
				break;
			case 7:
				viewUserPayments(user);
				break;
			case 8:
				viewEmployeeAccounts(user);
				break;
			case 9:
				viewOfferHistory(user);
				break;
			}
		}
		
		
		//repeats until user quits
		//return true;
		
		
		
	}
	
	
	private static void viewBalance(Customer user)
	{
		while(true)
		{
			System.out.println("Current Balance: " + user.getBalance());
			System.out.println("Current Weekly Payment: " + user.getWeeklyPayment());
			Integer choice = getIntegerUserInput(1,2,"What would you like to do?\n1. Return\n2. Make a Payment");
			switch(choice)
			{
			case 1:
				return;
			case 2:
				Float amount = getFloatUserInput(0f, user.getBalance(), "How much would you like to pay? (maximum of " + user.getBalance() + ")");
				payServ.addPayment(new Payment(user.getID(),amount));
				System.out.println("Payment of " + amount + " made. Thank you for your patronage!");
				user.setBalance(user.getBalance() - amount);
				custServ.updateCustomer(user);
			}
		}
		
		
		
	}
	private static void viewAvailableBicycles(Customer user)
	{
		while(true)
		{
			Set<Bicycle> bikes = bikeServ.getBicycles();
			for (Bicycle b : bikes)
			{
				boolean available = true;
				
				for(Offer o : b.getOffers())
				{
					if (o.getStatus().getID() == 1)
						available = false;
				}
				
				if (available)
				{
					System.out.println("ID: "+ b.getID() + " Model: " + b.getModel() + " Brand: " + b.getBrand() + " Asking Price: " + b.getPrice());
					System.out.print("\tOffers: ");
					for (Offer o : b.getOffers())
					{
						System.out.print( "ID: " + o.getID() + " Price: " + o.getPrice() + " Status: " + o.getStatus().getName() + "\t");
					}
					System.out.println();
				}
				
				
			}
			Integer choice = null;
			if (user.getRole().getId() > 0)
				choice = getIntegerUserInput(1, 6, "What would you like to do?\n1. Return\n2. Make an offer\n3. Edit a Bicycle\n4. Add a Bicycle\n5. Remove a Bicycle\n6. Accept/Reject an offer");
			else
				choice = getIntegerUserInput(1, 2, "What would you like to do?\n1. Return\n2. Make an offer");
			
			if(choice == 1)
				return;
			else if (choice == 2)
			{
				Bicycle selected = null;
				while(true)
				{
					boolean valid = false;
					Integer select = getIntegerUserInput(0,null,"Please Select a Bicycle\nID: ");
					for (Bicycle b : bikes)
					{
						if (select.equals(b.getID()))
						{
							valid = true;
							selected = b;
						}
					}
					if(valid)
						break;
					else
						System.out.println("No Matching ID found!");
				}
				Float price = getFloatUserInput(selected.getPrice(), null, "Please enter a price (must be greater than asking price, payment will be divided over 8 weeks)");
				Offer newOffer = new Offer();
				newOffer.setItem(selected.getID());
				newOffer.setOwner(user.getID());
				newOffer.setPrice(price);
				newOffer.setID(offServ.addOffer(newOffer));
				user.getOffers().add(newOffer);
				System.out.println("Offer Placed!");
			}
			else if(choice == 3)
			{
				Bicycle selected = null;
				while(true)
				{
					boolean valid = false;
					Integer select = getIntegerUserInput(0,null,"Please Select a Bicycle\nID: ");
					for (Bicycle b : bikes)
					{
						if (select.equals(b.getID()))
						{
							valid = true;
							selected = b;
						}
					}
					if(valid)
						break;
					else
						System.out.println("No Matching ID found!");
				}
				System.out.println("Curent Fields are\nID: "+ selected.getID() + " Model: " + selected.getModel() + " Brand: " + selected.getBrand() + " Asking Price: " + selected.getPrice());
				System.out.println("Please enter new fields\n Model: ");
				selected.setModel(scan.nextLine());
				System.out.println("Brand: ");
				selected.setBrand(scan.nextLine());
				selected.setPrice(getFloatUserInput(0f,null, "Asking Price: "));
				bikeServ.updateBicycle(selected);
				System.out.println("Changes Applied!");
			}
			else if (choice == 4)
			{
				Bicycle selected = new Bicycle();
				System.out.println("Please enter new fields\n Model: ");
				selected.setModel(scan.nextLine());
				System.out.println("Brand: ");
				selected.setBrand(scan.nextLine());
				selected.setPrice(getFloatUserInput(0f,null, "Asking Price: "));
				bikeServ.addBicycle(selected);
				System.out.println("Bike Added!");
			}
			else if (choice == 5)
			{
				Bicycle selected = null;
				while(true)
				{
					boolean valid = false;
					Integer select = getIntegerUserInput(0,null,"Please Select a Bicycle\nID: ");
					for (Bicycle b : bikes)
					{
						if (select.equals(b.getID()))
						{
							valid = true;
							selected = b;
						}
					}
					if(valid)
						break;
					else
						System.out.println("No Matching ID found!");
				}
				bikeServ.removeBicycle(selected);
				System.out.println("Bicycle Removed!");
			}
			else if (choice == 6)
			{
				Bicycle selected = null;
				while(true)
				{
					boolean valid = false;
					Integer select = getIntegerUserInput(0,null,"Please Select a Bicycle\nID: ");
					for (Bicycle b : bikes)
					{
						if (select.equals(b.getID()))
						{
							valid = true;
							selected = b;
						}
					}
					if(valid)
						break;
					else
						System.out.println("No Matching ID found!");
				}
				System.out.println("Current offers are:");
				for (Offer o : selected.getOffers())
				{
					System.out.println( "ID: " + o.getID() + " Price: " + o.getPrice() + " Status: " + o.getStatus().getName());
				}
				Offer offerToAccept = null;
				while (true)
				{
					boolean valid = false;
					Integer select = getIntegerUserInput(0,null,"Please Select an Offer\nID: ");
					for (Offer o : selected.getOffers())
					{
						if (select.equals(o.getID()))
						{
							valid = true;
							offerToAccept = o;
						}
					}
					if(valid)
						break;
					else
						System.out.println("No Matching ID found!");
				}
				Integer accept = getIntegerUserInput(1,2, "Please choose an option\n1. Accept\n2. Reject");
				if (accept == 1)
				{
					if (user.getOffers().contains(offerToAccept))
					{
						user.getOffers().remove(offerToAccept);
						offServ.acceptOffer(offerToAccept);
						user.getOffers().add(offerToAccept);
					}
					else
						offServ.acceptOffer(offerToAccept);
				}
				else if (accept == 2) {
					if (user.getOffers().contains(offerToAccept))
					{
						user.getOffers().remove(offerToAccept);
						offServ.rejectOffer(offerToAccept);
						user.getOffers().add(offerToAccept);
					}
					else
						offServ.rejectOffer(offerToAccept);
				}
				
				
			}
				
			
		}
		
	}
	private static void viewAccessories(Customer user)
	{
		while(true) 
		{
			Set<Accessory> all = accServ.getAccessories();
			System.out.println("Available Accessories:");
			for (Accessory a : all)
			{
				System.out.println("ID: " + a.getID() + " Category: " + a.getCategory() + " Name: " + a.getName() + " Brand: " + a.getBrand() + " Price " + a.getPrice());
			}
			
			Integer choice = null;
			if (user.getRole().getId() > 0)
			{
				choice = getIntegerUserInput(1, 5, "What would you like to do?\n1. Return\n2. Purchase Accessory\n3. Add a new Accessory\n4. Edit an Accessory");
			}
			else
			{
				choice = getIntegerUserInput(1, 5, "What would you like to do?\n1. Return\n2. Purchase Accessory");
			}
			
			if (choice == 1)
			{
				return;
			}
			else if (choice == 2)
			{
				Accessory selected = null;
				
				while(true)
				{
					boolean valid = false;
					Integer input = getIntegerUserInput(0,null,"Please select an Accessory\nID: ");
					for (Accessory a: all)
					{
						if (input == a.getID())
						{
							valid = true;
							selected = a;
						}
						

					}
					if (valid)
						break;
					System.out.println("Error! Accessory not Foind!");
				}
				
				System.out.println("Category: " + selected.getCategory() + " Name: " + selected.getName() + " Brand: " + selected.getBrand() + " Price " + selected.getPrice());
				Integer num = getIntegerUserInput(1,null,"How many would you like to purchase?");
				accServ.purchase(selected, user.getID(), num);
				user.setBalance(user.getBalance() + num * selected.getPrice());
				if(user.getCart().containsKey(selected))
				{
					user.getCart().put(selected, user.getCart().get(selected)+num);
				}
				else
					user.getCart().put(selected, num);
				System.out.println("Purchase Successful!");
			}
			else if (choice == 3)
			{
				Accessory toAdd = new Accessory();
				System.out.println("Please enter a Name");
				toAdd.setName(scan.nextLine());
				System.out.println("Please enter a Catagory");
				toAdd.setCategory(scan.nextLine());
				System.out.println("Please enter a Brand");
				toAdd.setBrand(scan.nextLine());
				toAdd.setPrice(getFloatUserInput(0f,null,"Please enter a Price"));
				accServ.addAccessory(toAdd);
				System.out.println("Accessory Added Successfully!");
			}
			else if (choice == 4)
			{
				Accessory selected = null;
				
				while(true)
				{
					boolean valid = false;
					Integer input = getIntegerUserInput(0,null,"Please select an Accessory\nID: ");
					for (Accessory a: all)
					{
						if (input == a.getID())
						{
							valid = true;
							selected = a;
						}
						

					}
					if (valid)
						break;
					System.out.println("Error! Accessory not Foind!");
				}
				System.out.println("Current Attributes:");
				System.out.println("Category: " + selected.getCategory() + " Name: " + selected.getName() + " Brand: " + selected.getBrand() + " Price " + selected.getPrice());
				System.out.println("Please enter a Name");
				selected.setName(scan.nextLine());
				System.out.println("Please enter a Catagory");
				selected.setCategory(scan.nextLine());
				System.out.println("Please enter a Brand");
				selected.setBrand(scan.nextLine());
				selected.setPrice(getFloatUserInput(0f,null,"Please enter a Price"));
				accServ.updateAccessory(selected);
				System.out.println("Accessory Updated Successfully!");
			}
			
		}
		
	}
	private static void viewMyBicycles(Customer user)
	{
		while(true)
		{
			System.out.println("My Bicycles:");
			for(Bicycle b : user.getGarage())
			{
				System.out.println("Model: " + b.getModel() + " Brand: " + b.getBrand());
			}
			getIntegerUserInput(1,1,"Press 1 to Return");
			return;
		}
		
	}
	private static void viewMyAccessories(Customer user)
	{
		while(true)
		{
			for(Accessory a : user.getCart().keySet())
			{
				System.out.println("ID: " + a.getID() + " Category: " + a.getCategory() + " Name: " + a.getName() + " Brand: " + a.getBrand() + " Price: " + a.getPrice() + " Amount: " + user.getCart().get(a));
			}
			
			getIntegerUserInput(1,1,"Press 1 to Return");
			return;
		}
	}
	private static void viewUserPayments(Customer user)
	{
		Set<Payment> all = payServ.getPayments();
		for (Payment p : all)
		{
			System.out.println("Customer ID: " + p.getCustomerID() + " Amount: " + p.getAmount() + " Date: " + p.getDate());
		}
		getIntegerUserInput(1,1,"Press 1 to Return");
	}
	private static void viewEmployeeAccounts(Customer user)
	{
		while(true)
		{
			Set<Customer> all = custServ.getCustomers();
			
			for(Customer c : all)
			{
				if (c.getRole().getId() == 1)
					System.out.println("ID: " + c.getID() + " Name: " + c.getName());
			}
			
			Integer choice = getIntegerUserInput(1,3,"What would you like to do?\n1. Return\n2. Hire New Employee\n3. Fire an Employee");
			if(choice == 1)
			{
				return;
			}
			else if (choice == 2)
			{
				Customer newEmployee = new Customer();
				Role role = new Role();
				role.setId(1);
				role.setName("employee");
				newEmployee.setRole(role);
				System.out.println("Please enter a name:");
				newEmployee.setName(scan.nextLine());
				System.out.println("Please enter a username:");
				newEmployee.setUsername(scan.nextLine());
				System.out.println("Please enter a password:");
				newEmployee.setPassword(scan.nextLine());
				custServ.addCustomer(newEmployee);
				System.out.println("New Employee " + newEmployee.getName() + " Hired!");
			}
			else if (choice == 3)
			{
				Customer selected = null;
				while (true)
				{
					boolean valid = false;
					Integer id = getIntegerUserInput(0,null,"Please choose an unfortunate soul\nID:");
					
					for(Customer c : all)
					{
						if (c.getID() == id && c.getRole().getId() == 1)
						{
							valid = true;
							selected = c;
						}
					}
					
					if(valid)
						break;
					System.out.println("Error! No ID found!");
				}
				Role role = new Role();
				role.setId(0);
				role.setName("customer");
				selected.setRole(role);
				custServ.updateCustomer(selected);
				System.out.println("Employee Fired!");
				
			}
		}
		
		
		
	}
	private static void viewOfferHistory(Customer user)
	{
		while(true)
		{
			Set<Offer> all = offServ.getOffers();
			
			for(Offer o : all)
			{
				System.out.println("ID: " + o.getID() + " Customer ID: " + o.getOwner() + " Bicycle ID: " + o.getItem() + " Price: " + o.getPrice() + " Status: " + o.getStatus().getName());
			}
			getIntegerUserInput(1,1,"Press 1 to return");
			return;
		}
		
		
	}
	
	
	private static Float getFloatUserInput(Float lowBound,Float upperBound, String prompt)
	{
		Float choice = null;
		while(true)
		{
			System.out.println(prompt);
			boolean valid = true;
			if (lowBound == null && upperBound == null)
			{
				try
				{
					
					choice = Float.parseFloat(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
				}
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound == null && upperBound != null)
			{
				try
				{
					choice = Float.parseFloat(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice > upperBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound != null && upperBound == null)
			{
				try
				{
					choice = Float.parseFloat(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice < lowBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound != null && upperBound != null)
			{
				try
				{
					choice = Float.parseFloat(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice > upperBound || choice < lowBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			System.out.println("Error! Invalid Input!");
		}
	}
	
	private static Integer getIntegerUserInput(Integer lowBound,Integer upperBound, String prompt)
	{
		Integer choice = null;
		while(true)
		{
			System.out.println(prompt);
			boolean valid = true;
			if (lowBound == null && upperBound == null)
			{
				try
				{
					choice = Integer.parseInt(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound == null && upperBound != null)
			{
				try
				{
					choice = Integer.parseInt(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice > upperBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound != null && upperBound == null)
			{
				try
				{
					choice = Integer.parseInt(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice < lowBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			else if(lowBound != null && upperBound != null)
			{
				try
				{
					choice = Integer.parseInt(scan.nextLine());
				}
				catch(NumberFormatException e)
				{
					valid = false;
					continue;
				}
				if (choice > upperBound || choice < lowBound)
					valid = false;
				if (valid)
				{
					return choice;
				}
			}
			System.out.println("Error! Invalid Input!");
		}
		
	}
	//check if provided username is unique, returns true if unique, false otherwise
	private static boolean verifyUniqueUsername(String username)
	{
		return custServ.verifyUniqueUsername(username);
	}
	
	//gets a user account according to the given username and password
	private static Customer verifyLogin(String username, String password)
	{		
		return custServ.getCustomerByLogin(username, password);
	}

}
