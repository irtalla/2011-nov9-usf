package data;
import java.util.Set;
import models.Bike;
public interface BikeDao extends GenericDAO<Bike>{
	public Bike add(Bike c);
	public Set<Bike> getAvailableBikes();
}
