package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Offer;
import spencer.revature.beans.Payment;

public interface OfferService {
	// "create" method: returns the unique identifier of the added Bicycle
		public Integer addOffer(Offer o);
		public Offer createOffer(Customer c, Bicycle b);
		// "read" methods
		public Offer getOfferById(Integer id);
		public Set<Offer> getOffers();
		public double weeklyRemainder(Integer id);
		public double getPayDue(Integer id);
		// "update" methods
		public void updateOffer(Offer o);
		// "delete" methods
		public void removeOffer(Offer o);
		public Set<Payment> getPayments();
		
}
