package Providers;
import java.util.Set;

import Entity.Bike;
import Entity.Human;

public interface BikeP {
	
	public Integer addBike(Bike b);
	public Bike getBikeById(Integer id);
	public Set<Bike> getBikes();
	public Set<Bike> getAvailableBikes();
	public void updateBike(Bike b);
	public void buyBike(Human h,Bike b);
	public void removeBike(Bike b);

}
