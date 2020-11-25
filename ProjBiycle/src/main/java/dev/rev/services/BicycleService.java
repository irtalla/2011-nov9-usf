package dev.rev.services;

import java.util.List;
import java.util.Set;

import dev.rev.model.Bicycle;

public interface BicycleService {
	
	public int addBicycle(Bicycle b) throws Exception;
	public Bicycle getBicyclebyBrand(String brand);
	public Set<Bicycle> getallBicyles();
	public void updateBicycle(Bicycle b);
	public void deleteBicycle(int b);
	public Bicycle getbyID(int id);
	public void updateBikeStatus(int id,int person_id,int price);
	public List<Bicycle> bikes(int p_id);
	public void updatepayment(int bike_id,int amount);
	
	

}
