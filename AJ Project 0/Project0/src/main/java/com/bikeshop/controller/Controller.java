package com.bikeshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.bikeshop.beans.*;
import com.bikeshop.services.*;

public class Controller {
	private static Scanner scan;
	private static PersonService ps = new PersonServiceImpl();
	private static BikeService bs = new BikeServiceImpl();
	private static AllService as = new AllServiceImpl();
	private static OfferService os = new OfferServiceImpl();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		
		
//		Bike test = new Bike();
//		test.setManufacturer("Huffy");
//		test.setModel("Alpine");
//		test.setStatus("Available");
//		test.setInventory(1);
//		test.setPrice(119.99f);
//		test.setTireSize(28);
//		test.setLength(26);
//		test.setDescription("This bike goes super fast!!");
//		
//		bs.addBike(test);
//		
//		bs.delBike(6);
		
//		Offer test = new Offer();
//		test.setAmount(109.99f);
//		test.setBikeID(5);
//		test.setPersonID(1);
//		test.setWeeks(4);
//		
		
		
		
		
		boolean userActive = true;
		
		mainLoop: while (userActive) {
			System.out.println("Welcome to the Bike Shop!");
			Person loggedInUser = null;
			
			while (loggedInUser == null) {
				System.out.println("What would you like to do");
				System.out.println("1. Register\n2. Log in\n3. View available bikes\nother. Exit");
				int in = Integer.valueOf(scan.nextLine());
				
				switch (in) {
					case 1: 
						
						loggedInUser = ps.registerUser();

					case 2: 
						loggedInUser = ps.logInUser();
						break;
					case 3: 
						List<Bike> bikes = as.getInventory();
						if (bikes !=null) {
							for (Bike b : bikes) {
								System.out.println(b.toString());
								System.out.println("");
							} 
							break; 
						}else break;
					default:
						userActive = false;
						break mainLoop;
				}
			}
			
			menuLoop: while (true) {
				System.out.println("What would you like to do");
				if (loggedInUser.getRole().equals("customer")) {
					System.out.println("1. View available bikes\n2. View my bikes\n3. Purchase a bike\n"
							+ "4. Make an offer\n5. View my open offers\n6. Cancel an offer\n7. Make a payment\nOther. Log out");
					int userInput = Integer.valueOf(scan.nextLine());
					switch (userInput) {
						case 1:
							List<Bike> bikes = as.getInventory();
							for (Bike b : bikes) {
								System.out.println(b.toString());
							}
							break;
						
						case 2:
							bs.viewUserBikes (loggedInUser);
							break;
						case 3: 
							System.out.println("Please type the ID of the bike you wish to purchase.");
							int purchasing = Integer.valueOf(scan.nextLine());
							bs.purchaseBike (loggedInUser, purchasing);
							
							break;
						case 4: 
							System.out.println("Please type the ID of the bike you wish to place an offer on.");
							int offering = Integer.valueOf(scan.nextLine());
							os.addOffer(loggedInUser, offering);
							break;
						case 5: 
							os.userManageOffers(loggedInUser);
							break;
						case 6: 
							System.out.println("Please type the ID of the offer you wish to delete.");
							int decline = Integer.valueOf(scan.nextLine());
							os.declineOffer(decline);
							break;
						case 7: 
							System.out.println("Please type the ID of the bike you wish to make a payment on.");
							int pay = Integer.valueOf(scan.nextLine());
							
							bs.makePayment(loggedInUser, bs.getByID(pay));
							break;
						default:
							System.out.println("Thanks for shopping!");
							loggedInUser = null;
							break menuLoop;
					}
				} else if (loggedInUser.getRole().equals("employee")) {
					System.out.println("1. View available bikes\n2. Add a bike\n3. Remove a bike\n"
							+ "4. View open offers\n5. Accept an offer\n6. Decline an offe\n7. Edit a bike\nOther. Log out");
					int userInput = Integer.valueOf(scan.nextLine());
					switch (userInput) {
						case 1: 
							List<Bike> bikes = as.getInventory();
							for (Bike b : bikes) {
								System.out.println(b.toString());
							}
							break;
						case 2:
							bs.newBike();
							break;
						case 3:
							System.out.println("Enter the ID of the bike you wish to delete.");
							int s = Integer.valueOf(scan.nextLine());
							bs.delBike(s);
							break;
						case 4:
							os.viewOffers();
							break;
						case 5:
							System.out.println("Enter the ID of the offer you wish to accept.");
							int z = Integer.valueOf(scan.nextLine());
							os.acceptOffer(z);
							break;
						case 6:
							System.out.println("Enter the ID of the offer you wish to decline.");
							int t= Integer.valueOf(scan.nextLine());
							os.declineOffer(t);
							break;
						case 7:
							System.out.println("Enter the ID of the bike you wish to edit");
							int id = Integer.valueOf(scan.nextLine());
							bs.editBike(id);
							break;
						default:
							System.out.println("Thanks for shopping!");
							loggedInUser = null;
							break menuLoop;				
					}
				}else if (loggedInUser.getRole().equals("manager")) {
					System.out.println("1. View available bikes\n2. Add a bike\n3. Remove a bike\n"
							+ "4. View open offers\n5. Accept an offer\n6. Decline an offer\n7. Edit a bike"
							+ "\n8. Create new employee/manager account\n9. Terminate an employee account\nOther. Log out");
					int userInput = Integer.valueOf(scan.nextLine());
					switch (userInput) {
						case 1: 
							List<Bike> bikes = as.getInventory();
							for (Bike b : bikes) {
								System.out.println(b.toString());
							}
							break;
						case 2:
							bs.newBike();
							break;
						case 3:
							System.out.println("Enter the ID of the bike you wish to delete.");
							int s = Integer.valueOf(scan.nextLine());
							bs.delBike(s);
							break;
						case 4:
							os.viewOffers();
							break;
						case 5:
							System.out.println("Enter the ID of the offer you wish to accept.");
							int z = Integer.valueOf(scan.nextLine());
							os.acceptOffer(z);
							break;
						case 6:
							System.out.println("Enter the ID of the offer you wish to decline.");
							int t= Integer.valueOf(scan.nextLine());
							os.declineOffer(t);
							break;
						case 7:
							System.out.println("Enter the ID of the bike you wish to edit");
							int id = Integer.valueOf(scan.nextLine());
							bs.editBike(id);
							break;
						case 8:
							ps.registerEmployee();
							break;
						case 9:
							ps.terminate();
							
							break;
						case 10:
							break;
						default:
							System.out.println("Thanks for shopping!");
							loggedInUser = null;
							break menuLoop;				
					}
				}if (loggedInUser.getRole().equals("terminated")) {
					System.out.println("Looks like your account has been terminated. Tough luck bub...");
					break menuLoop;
				}
			}
		}
		scan.close();
	}
	


	
	


	
	
}


