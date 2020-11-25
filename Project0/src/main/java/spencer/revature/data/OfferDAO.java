package spencer.revature.data;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Offer;

public interface OfferDAO extends GenericDAO<Offer>{

	public void rejectAll(Bicycle b);

	public double remainder(Integer id);

	public double getPayDue(Integer id);


}
