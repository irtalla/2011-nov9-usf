package data;

import java.util.Set;

import beans.Bikes;
import beans.Offers;
import data.GenericDAO;

public interface BikeDAO extends GenericDAO<Bikes> {
	public Bikes add(Bikes b);
	public Set<Bikes> getAvailableBikes();
}
