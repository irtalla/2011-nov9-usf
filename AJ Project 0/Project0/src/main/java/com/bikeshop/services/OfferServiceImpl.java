package com.bikeshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bikeshop.beans.Bike;
import com.bikeshop.beans.Offer;
import com.bikeshop.beans.Person;
import com.bikeshop.dao.OfferDAO;
import com.bikeshop.dao.OfferDAOFactory;

public class OfferServiceImpl implements OfferService {
	private OfferDAO od;
	Scanner scan = new Scanner(System.in);
	
	public OfferServiceImpl() {
		OfferDAOFactory odf = new OfferDAOFactory();
		od = odf.getOfferDAO();
		
	}

	@Override
	public List<Offer> viewOffers() {
		List<Offer> offers = new ArrayList<>();
		offers = od.getAllOffers();
		for (Offer o : offers ) {
			System.out.println(o.toString());
		}
		
		return offers;
	}

	@Override
	public List<Offer> userManageOffers(Person p) {
		List<Offer> offers = new ArrayList<>();
//		System.out.println("1. View my open offers\n2. Remove an offer\nOther. Log out");
//		changed this to VIEW instead for better loop flow
		int in = 1;
		
		switch (in) {
			case 1: 
				offers = od.getByPersonID(p.getId());
				
				if (offers.size() > 0) {
					for (Offer o : offers ) {
						System.out.println(o.toString());
					}
				} else System.out.println("It appears you don't have any open offers.");
				break;
			case 2:
				System.out.println("Enter the ID of the offer you wish to remove.");
				int s = Integer.valueOf(scan.nextLine());
				od.delete(s);
				
		}
		return offers;
	}

	@Override
	public boolean addOffer(Person p, Integer id) {
		boolean added = false;
		BikeService bs = new BikeServiceImpl();
		Bike b = bs.getByID(id);
		Offer o = new Offer();
		System.out.println("The price of the bike is " + b.getPrice() + ". Would you like to name a different price?\n1. Name your price\nOther. Keep the current price.");
		
		
		
			System.out.println("Okay. How much would you like to pay total?");
			Float f = Float.valueOf(scan.nextLine());
			if (f > 0) {
				o.setAmount(f);
			} else {
				System.out.println("Please input a real dollar amount");
				return false;
			}

				
		System.out.println("How many weekly payments would you like to make? \n1. 1 payment\n2. 4 payments\n3. 8 payments\nOther. Cancel");
		int input1 = scan.nextInt();
		if (input1 == 1) {

				o.setWeeks(1);
		}else if(input1 == 2) { 
				o.setWeeks(4);
		}else if (input1 ==3) {
				o.setWeeks(8);
		} else return false;
		
	
		if (o.getAmount() == b.getPrice() && o.getWeeks() == 1) {
			System.out.println("Please make a purchase instead");
			return false;
		} else {
			o.setPersonID(p.getId());
			o.setBikeID(b.getId());
			b.setOfferNum(b.getOfferNum() + 1);
			bs.updateBike(b);
			System.out.println(od.add(o).toString());
			added = true;
			
		}
		
		
		
		
		return added;
	}

	@Override
	public boolean declineOffer(int id) {
		boolean deleted = false;
		
		deleted = od.delete(id);
		
		return deleted;
	}

	@Override
	public boolean acceptOffer(Integer id) {
		boolean accepted = false;
		OfferService os = new OfferServiceImpl();
		BikeService bs = new BikeServiceImpl();
		Offer o = od.getByID(id);
		Bike b = bs.getByID(o.getBikeID());
		
		if (b.getInventory() > 1) {
			b.setInventory(b.getInventory() - 1);
			b.setOfferNum(b.getOfferNum() - 1);
			bs.updateBike(b);
			
			int newBike = bs.addBike(b);
			
			b.setId(newBike);
//			b.setOwnerID(o.getId());
//			b.setOfferNum(0);
			//TODO offerDAO reject all offers
//			b.setStatus("sold");
			
//			bs.updateBike(b);
			od.delete(id);
			
		} else {
//			b.setOwnerID(o.getId());
			b.setOfferNum(0);
			//TODO offerDAO reject all offers
//			b.setStatus("sold");
			os.deleteByBikeID(b.getId());
			
//			bs.updateBike(b);
		}

		b.setOwnerID(o.getPersonID());
		b.setWeeklyPayment(o.getAmount()/Float.valueOf(o.getWeeks()));
		b.setPaymentsLeft(o.getWeeks());
		b.setOfferNum(0);
		b.setOffers(null);
		b.setStatus("sold");
		b.setPrice(o.getAmount());
		bs.updateBike(b);
//		if (od.getByBike(b).size() == 0) {
			accepted = true;
//		}
		return accepted;
	}

	@Override
	public boolean updateOffer(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Offer> getByBike(Bike b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> getByBikeID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteByBikeID(Integer id) {
		boolean deleted = false;
		
		deleted = od.deleteByBikeID(id);
		
		return deleted;
	}

	@Override
	public List<Offer> getByPersonID(Integer id) {
		List<Offer> offers = new ArrayList<>();
		offers = od.getByPersonID(id);
		
		
		return offers;
	}

}
