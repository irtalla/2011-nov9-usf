package com.revature.data;

import java.util.Set;
import com.revature.beans.Bicycle;

public interface BicycleDAO extends GenericDAO<Bicycle>{

	public Bicycle add(Bicycle b);
	public Set<Bicycle> getAll();
	public Set<Bicycle> getAvailable();
	public Set<Bicycle> getByUserId(Integer id);

}
