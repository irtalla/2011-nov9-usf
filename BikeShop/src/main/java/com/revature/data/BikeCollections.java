package com.revature.data;


import java.util.HashSet;
import java.util.Set;


import com.revature.beans.Bicycle;

public class BikeCollections implements BikeDAO {
	private static HashSet<Bicycle> data;

	public Bicycle add(Bicycle t) {
		if (data == null) 
		{
			data = new HashSet<Bicycle>();
		}
		Integer maxID = 0;
		for (Bicycle e : data)
		{
			if (e.getID() > maxID)
				maxID = e.getID();
		}
		t.setID(maxID+1);
		data.add(t);
		return t;
	}

	public Bicycle getById(Integer id) {
		for (Bicycle e : data)
		{
			if (e.getID().equals(id))
			{
				return e;
			}
		}
		return null;
	}

	public Set<Bicycle> getAll() {
		return data;
	}

	public void update(Bicycle t) {
		for (Bicycle e : data)
		{
			if (e.equals(t))
			{
				t.setID(e.getID());
				data.add(t);
			}
		}
		
	}

	public void delete(Bicycle t) {
		for(Bicycle e : data)
		{
			if (e.equals(t))
			{
				data.remove(e);
			}
		}
		
	}
}
