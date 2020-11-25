package dev.elliman.controller;

import java.util.Scanner;
import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Offer;
import dev.elliman.beans.Person;
import dev.elliman.beans.ScannerSingleton;
import dev.elliman.services.BikeService;
import dev.elliman.services.BikeServiceImpl;
import dev.elliman.services.OfferService;
import dev.elliman.services.OfferServiceImpl;
import dev.elliman.services.PersonService;
import dev.elliman.services.PersonServiceImpl;

/**
 * This will contain the methods that handle users interacting with the program
 * @author Will
 *
 */
public class UserHandler {

	private static Scanner input = ScannerSingleton.getScanner();
	private static PersonService ps = new PersonServiceImpl();
	private static BikeService bs = new BikeServiceImpl();
	private static OfferService os = new OfferServiceImpl();



	public static void addBike(Person user) {
		//an employee is adding a bike to the listing
		System.out.print("Model of bike: ");
		String model = input.nextLine();
		System.out.print("Color of bike: ");
		String color = input.nextLine();
		System.out.println("Adding bike to the database");

		bs.addBike(new Bike(model,color));
	}

	public static void viewAvalibleBikes(Person user) {
		System.out.println("Currently we have these bike avalible:");
		Set<Bike> bikes = bs.getAvalibleBikes();

		for(Bike b: bikes) {
			System.out.println("ID: " + b.getId() + " :: " + b.getModel() + " in " + b.getColor());
		}
	}

	public static void purchaseBike(Person user) {
		viewAvalibleBikes(user);

		System.out.println("Which bike would you like to make an offer on? (-1 to cancel)");
		Integer bikeID;
		Set<Bike> bikes = bs.getAvalibleBikes();
		Bike bike = null;
		while(true) {
			try {
				bikeID = Integer.valueOf(input.nextLine());
				if(bikeID < 1) {
					System.out.println("Canceling...");
					return;
				} else {
					//check that the id is valid
					for(Bike b : bikes) {
						if(b.getId().equals(bikeID)) {
							bike = b;
							break;
						}
					}
					if(bike == null) {
						System.out.println("Please enter a valid number. (-1 to cancel)");
					} else {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}
		}


		Integer price = null;
		System.out.println("How much will you offer for this bike? We only accept full sacks of flour as payment. (-1 to cancel)");
		while(true) {
			try {
				price = Integer.valueOf(input.nextLine());
				if(price < 0){
					System.out.println("Canceling...");
					return;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}
		}
		
		Integer payments = null;
		System.out.println("How much will pay per payment? (-1 to cancel)");
		while(true) {
			try {
				payments = Integer.valueOf(input.nextLine());
				if(payments <= 0){
					System.out.println("Canceling...");
					return;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}
		}

		Offer offer  = new Offer(user,bike, price, payments, 0);
		//System.out.println(offer);
		os.makeOffer(offer);
		System.out.println("Your offer has been submitted.");
	}

	public static void viewActiveOffers(Person user) {
		Set<Offer> allOffers = os.getActiveOffer();
		if(allOffers.size() == 0) {
			System.out.println("There are no active offers.");
		} else {
			System.out.println("Printing all offers...");
			for(Offer o : allOffers) {
				System.out.println("Offer [id=" + o.getId() + ", person=" + o.getPerson().getFirstName() + ", bike=" + o.getBike().getModel() + ", price=" + o.getPrice() + "]");
			}
		}
	}

	public static void acceptOffer(Person user) {
		viewActiveOffers(user);
		System.out.println("Which offer would you like to accept? (-1 to cancel)");

		Set<Offer> allOffers = os.getActiveOffer();
		Offer offer = null;
		Integer id = null;
		while(true) {
			try {
				id = Integer.valueOf(input.nextLine());
				if(id < 0){
					System.out.println("Canceling...");
					return;
				} else {
					//check that the id is valid
					for(Offer o : allOffers) {
						if(o.getId().equals(id)) {
							offer = o;
							break;
						}
					}
					if(offer == null) {
						System.out.println("Please enter a valid number. (-1 to cancel)");
					} else {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}

		}
		
		os.acceptOffer(offer.getId(), user);
	}
	
	public static void rejectOffer(Person user) {
		viewActiveOffers(user);
		System.out.println("Which offer would you like to reject? (-1 to cancel)");

		Set<Offer> allOffers = os.getActiveOffer();
		Offer offer = null;
		Integer id = null;
		while(true) {
			try {
				id = Integer.valueOf(input.nextLine());
				if(id < 0){
					System.out.println("Canceling...");
					return;
				} else {
					//check that the id is valid
					for(Offer o : allOffers) {
						if(o.getId().equals(id)) {
							offer = o;
							break;
						}
					}
					if(offer == null) {
						System.out.println("Please enter a valid number. (-1 to cancel)");
					} else {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}

		}
		
		os.rejectOffer(offer.getId());
	}

	public static void removeBike(Person user) {
		viewAvalibleBikes(user);

		System.out.println("Which bike would you like to remove? (-1 to cancel)");
		Integer bikeID;
		Set<Bike> bikes = bs.getAvalibleBikes();
		Bike bike = null;
		while(true) {
			try {
				bikeID = Integer.valueOf(input.nextLine());
				if(bikeID < 1) {
					System.out.println("Canceling...");
					return;
				} else {
					//check that the id is valid
					for(Bike b : bikes) {
						if(b.getId().equals(bikeID)) {
							bike = b;
							break;
						}
					}
					if(bike == null) {
						System.out.println("Please enter a valid number. (-1 to cancel)");
					} else {
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid number. (-1 to cancel)");
			}
		}
		
		System.out.println("Removing...");
		bs.delete(bike);
	}

	public static void viewOwnedBikes(Person user) {
		Set<Bike> ownedBikes = bs.getOwnedBikes(user);
		if(ownedBikes.size() == 0) {
			System.out.println("You do not own any bikes.");
		} else {
			System.out.println("The bikes you own are :");
			for(Bike b : ownedBikes) {
				System.out.println(b);
			}
		}
		
	}
	
	public static void viewRemaingPayments(Person user) {
		Set<Offer> offers = os.getAcceptedOffer(user);
		if(offers.size() == 0) {
			System.out.println("You have no remaining payments.");
		} else {
			for(Offer o: offers) {
				System.out.println("Offer [id=" + o.getId() + ", bike=" + o.getBike().getModel() + ", price=" + o.getPrice() + ", remaining=" + o.getPaymentRemaining() + "]");
				//calculate payments remaining
				Integer remain = o.getPaymentRemaining()/o.getPaymentSize();
				if(o.getPaymentRemaining()%o.getPaymentSize() == 0) {
					System.out.println("Only " + remain + " payments remaining");
				} else {
					System.out.println("Only " + (remain+1) + " payments remaining");
				}
			}
		}
	}
	
	public static void viewAllRemainingPayments(Person user) {
		Set<Offer> offers = os.getAllAcceptedOffers();
		if(offers.size() == 0) {
			System.out.println("Thre are no remaining payments");
		} else {
			for(Offer o: offers) {
				System.out.println("Offer [id=" + o.getId() + ", person=" + o.getPerson().getFirstName() + ", bike=" + o.getBike().getModel() + ", price=" + o.getPrice() + ", remaining=" + o.getPaymentRemaining() + "]");
				//calculate payments remaining
				Integer remain = o.getPaymentRemaining()/o.getPaymentSize();
				if(o.getPaymentRemaining()%o.getPaymentSize() == 0) {
					System.out.println("Only " + remain + " payments remaining");
				} else {
					System.out.println("Only " + (remain+1) + " payments remaining");
				}
			}
		}
	}
	
	public static void viewOfferStatus(Person user) {
		Set<Offer> offers = os.getOffers(user);
		
		if(offers.size() == 0) {
			System.out.println("You have made no offers.");
		} else {
			System.out.println("You have made these offers:");
			for(Offer o : offers) {
				//System.out.println(o);
				System.out.println("Offer [id=" + o.getId() + ", bike=" + o.getBike().getModel() + ", price=" + o.getPrice() + ", status=" + o.getStatus() + ", payment size=" + o.getPaymentSize() + "]");
			}
		}
	}
}
