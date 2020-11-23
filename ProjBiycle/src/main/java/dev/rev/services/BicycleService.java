package dev.rev.services;

import java.util.Set;

import dev.rev.model.Bicycle;

public interface BicycleService {
	
	public int addBicycle(Bicycle b) throws Exception;
	public Bicycle getBicyclebyBrand(String brand);
	public Set<Bicycle> getallBicyles();
	public void updateBicycle(Bicycle b);
	public void deleteBicycle(Bicycle b);
	public Bicycle getbyID(int id);
	

}
