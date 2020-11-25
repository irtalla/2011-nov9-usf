package JDBC;
import java.util.Set;

import Entity.Bike;

public interface BikeJDBC extends GenericJDBC<Bike> {
	public Bike add(Bike b);
	public Set<Bike> getAvailableBikes();

}
