package spencer.revature.data;

import java.util.Set;


import spencer.revature.beans.Bicycle;

public interface BicycleDAO extends GenericDAO<Bicycle> {
	public Set<Bicycle> getAvailableBicycles();
}
