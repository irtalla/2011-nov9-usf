package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.BikePart;

public interface BikePartDAO {
	
	public Integer add(BikePart p);
	
	public BikePart getByID(Integer id);
	
	public Set<BikePart> getAll();
	
	public void update(BikePart p);
	
	public void delete(BikePart p);
}
