package com.bikeshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Person;
import com.bikeshop.dao.BikeDAO;
import com.bikeshop.dao.BikeDAOFactory;

public class BikeServiceImpl implements BikeService {
	private BikeDAO bd;
	Scanner scan = new Scanner(System.in);
	
	
	public BikeServiceImpl() {
		BikeDAOFactory bdf = new BikeDAOFactory();
		bd = bdf.getBikeDAO();
	}

	@Override
	public boolean updateBike(Bike b) {
		return bd.updateBike(b);
		
		
	}
	@Override
	public int addBike(Bike b) {
		int id = bd.addBike(b);
		return id;
		
		
	}
	public Bike getByID(int id) {
		Bike b = bd.getByID(id);
		return b;
		
	}

	@Override
	public boolean delBike(int id) {
		Bike b = bd.getByID(id);
		
		if (b != null) {
			boolean t = bd.delBike(b);
			return t;
			
		} else return false;
		
		
	}

	@Override
	public void viewOffers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean declineOffer() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean acceptOffer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int calcPay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Bike> getPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bike> getByOwner(Integer id) {
		
		return bd.getByOwner(id);
	}
	public boolean makePayment(Person p, Bike b) {
		float amount = b.getPrice();
		boolean paid = false;
		if (b.getPaymentsLeft() > 1) amount = b.getWeeklyPayment();
		System.out.println("The amount you will pay is " + amount + ". Is this ok? \n1. Yes\n2. No");
		int userInput = Integer.valueOf(scan.nextLine());
		switch (userInput) {
			case 1:
				System.out.println("Please enter your credit or debit card number");
				String card = scan.nextLine();
				if (card.length() == 16) {
					paid = true;
					
					if(b.getPaymentsLeft() > 0 ) {
						b.setPaymentsLeft(b.getPaymentsLeft() - 1);
						bd.updateBike(b);
					}
					
					
					System.out.println("Payment of $" + amount + " Successful!");
				}else {
					System.out.println("Please try again");
					break;
				}
				
			default:
				break;
				
		}
		
		return paid;
		
	};
	

	public List<Bike> viewUserBikes (Person user) {
		BikeService bs = new BikeServiceImpl();
		List<Bike> due = new ArrayList<>();
		List<Bike> paid = new ArrayList<>();
//		if (user.getBikes().size() == 0) {
			List<Bike> bikes = new ArrayList<>();
			
			bikes = bs.getByOwner(user.getId());
			user.setBikes(bikes);
			
//		}
		if (user.getBikes().size() == 0) {
			System.out.println("Oh no! It appears you don't own any bikes. We can help with that!");
			return paid;
		}
		else if (user.getBikes().size() > 0) {			
			for (Bike b2 : user.getBikes()) {				
				if (b2.getPaymentsLeft() > 0) {					
					due.add(b2);
				} else if (b2.getPaymentsLeft() == 0) {					
					paid.add(b2);
				}
			}			
		}
		if (paid.size() > 0) {
			System.out.println("These bikes are paid in full! You da best :)");
			for (Bike f:paid) {
				System.out.println(f.toString());
		}
			
		}
		if (due.size() > 0) {
			System.out.println("The following bikes have payments due");
			for (Bike f:due) {
				System.out.println(f.toString());
				
			}
		}
		return user.getBikes();
	}
	
	public void purchaseBike(Person p, int id) {
		BikeService bs = new BikeServiceImpl();
		Bike b = bs.getByID(id);
//		float price = b.getPrice();
		boolean purchased = bs.makePayment(p, b);
		if (purchased == true) {
			if (b.getInventory() > 1) {
				b.setInventory(b.getInventory() - 1);
				bs.updateBike(b);
				
				int newBike = bs.addBike(b);
				
				b.setId(newBike);
				b.setOwnerID(p.getId());
				b.setOfferNum(0);
				//TODO offerDAO reject all offers
				b.setStatus("sold");
				
				bs.updateBike(b);
				
				
			} else {
				b.setOwnerID(p.getId());
				b.setOfferNum(0);
				//TODO offerDAO reject all offers
				b.setStatus("sold");
				
				bs.updateBike(b);
			}
			
			
		}
		
		
	}
	public Bike newBike() {
		Bike b = new Bike ();
		System.out.println("Please enter the following information");
		System.out.println("Manufacturer");
		b.setManufacturer(scan.nextLine());
		System.out.println("Model");
		b.setModel(scan.nextLine());
		System.out.println("Inventory");
		b.setInventory(Integer.valueOf(scan.nextLine()));
		System.out.println("Price");
		b.setPrice(Float.valueOf(scan.nextLine()));
		System.out.println("Tire Size");
		b.setTireSize(Integer.valueOf(scan.nextLine()));
		System.out.println("Length");
		b.setLength(Integer.valueOf(scan.nextLine()));
		System.out.println("Description");
		b.setDescription(scan.nextLine());
		b.setStatus("Available");
		
		System.out.println("Does this look good?");
		System.out.println("Manufacturer: " + b.getManufacturer() + "\nModel: " + b.getModel() +
				"\nInventory: " + b.getInventory() + "\nPrice: " + b.getPrice() + "\nTire Size: " + b.getTireSize()
				+ "\nLength: " + b.getLength() + "\nDescription: " + b.getDescription());
		System.out.println("1. Confirm\nOther. Exit");
		
		int input = Integer.valueOf(scan.nextLine());
		
		switch (input) {
			case 1: 
				BikeService bs = new BikeServiceImpl();
				int id = bd.addBike(b);
				b.setId(id);
				return b;

			default:
				System.out.println("Okay, let's go back.");
				return null;
				
		}
		
	
	}
	
	public boolean editBike(int id) {
//		BikeService bs = new BikeServiceImpl();
		
		Bike b = bd.getByID(id);
		System.out.println(b.toString());
		System.out.println("Please enter the following information");
		System.out.println("Manufacturer");
		b.setManufacturer(scan.nextLine());
		System.out.println("Model");
		b.setModel(scan.nextLine());
		System.out.println("Inventory");
		b.setInventory(Integer.valueOf(scan.nextLine()));
		System.out.println("Price");
		b.setPrice(Float.valueOf(scan.nextLine()));
		System.out.println("Tire Size");
		b.setTireSize(Integer.valueOf(scan.nextLine()));
		System.out.println("Length");
		b.setLength(Integer.valueOf(scan.nextLine()));
		System.out.println("Description");
		b.setDescription(scan.nextLine());
		b.setStatus("Available");
		
		System.out.println("Does this look good?");
		System.out.println("Manufacturer: " + b.getManufacturer() + "\nModel: " + b.getModel() +
				"\nInventory: " + b.getInventory() + "\nPrice: " + b.getPrice() + "\nTire Size: " + b.getTireSize()
				+ "\nLength: " + b.getLength() + "\nDescription: " + b.getDescription());
		System.out.println("1. Confirm\nOther. Exit");
		
		int input = Integer.valueOf(scan.nextLine());
		
		switch (input) {
			case 1: 
				
				Boolean b2 = bd.updateBike(b);
//				b.setId(id1);
				return b2;

			default:
				System.out.println("Okay, let's go back.");
				return false;
				
		}
		
	
	}
}
