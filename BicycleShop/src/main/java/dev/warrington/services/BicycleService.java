package dev.warrington.services;

import java.util.Set;

import dev.warrington.beans.Bicycle;

public interface BicycleService {

	public Integer addBicycle(Bicycle b);
	
	public Bicycle getBicycleById(Integer id);
	
	public Set<Bicycle> getAvailable();
	
	public Set<Bicycle> getAll();
	
	public Set<Bicycle> getMine(Integer id);
	
	public void updateBicycle(Integer id);
	
	public void updateOwnership(Integer bid, Integer cid);
	
	public void deleteBicycle(Bicycle b);
	
}