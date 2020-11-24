package com.revature.data;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.revature.beans.Accessory;

public class AccessoryCollections implements AccessoryDAO {
	private static HashSet<Accessory> data;

	public Accessory add(Accessory t) {
		if (data == null) 
		{
			data = new HashSet<Accessory>();
		}
		Integer maxID = 0;
		for (Accessory e : data)
		{
			if (e.getID() > maxID)
				maxID = e.getID();
		}
		t.setID(maxID+1);
		data.add(t);
		return t;
	}

	public Accessory getById(Integer id) {
		for (Accessory e : data)
		{
			if (e.getID().equals(id))
			{
				return e;
			}
		}
		return null;
	}

	public Set<Accessory> getAll() {
		return data;
	}

	public void update(Accessory t) {
		for (Accessory e : data)
		{
			if (e.equals(t))
			{
				t.setID(e.getID());
				data.add(t);
			}
		}
		
	}

	public void delete(Accessory t) {
		for(Accessory e : data)
		{
			if (e.equals(t))
			{
				data.remove(e);
			}
		}
		
	}
	
}
