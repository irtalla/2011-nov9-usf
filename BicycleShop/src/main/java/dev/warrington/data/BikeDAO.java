package dev.warrington.data;

import java.util.Set;

import dev.warrington.beans.Bicycle;

public interface BikeDAO extends GenericDAO<Bicycle> {
	
	public Bicycle add(Bicycle b);
	
	public Set<Bicycle> getAvailable();
	
	public Set<Bicycle> getMine(Integer id);
	
	public void updateBicycleStatus(Integer id);
	
	public void updateOwners(Integer bid, Integer cid);
	
}