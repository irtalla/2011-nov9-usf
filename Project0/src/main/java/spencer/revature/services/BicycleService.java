package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;

public interface BicycleService {
	// "create" method: returns the unique identifier of the added Bicycle
		public Integer addBicycle(Bicycle b);
		// "read" methods
		public Bicycle getBicycleById(Integer id);
		public Set<Bicycle> getBicycles();
		public Set<Bicycle> getAvailableBicycles();
		// "update" methods
		public void updateBicycle(Bicycle b);
		public void assignBicycle(Customer c, Bicycle b);
		// "delete" methods
		public void removeBicycle(Bicycle b);
} 
