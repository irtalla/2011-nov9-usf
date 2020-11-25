package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;

import com.revature.beans.Person;
import com.revature.beans.Status;



public class BikeCollections implements BikeDAO {
private Set<Bike> bikes;
	
	public BikeCollections() {
		bikes = new HashSet<>();
		
		Bike b = new Bike();
		b.setId(1);
		b.setModel("Schwinn");
		b.setColor("blue");
		b.setPrice(150.00f);
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
	    b.setStatus(s);
		bikes.add(b);
		
		b = new Bike();
		b.setId(2);
		b.setModel("Peugot");
		b.setColor("Red");
		b.setPrice(450.00f);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		b.setStatus(s);
		bikes.add(b);
		
		
		
		
		
		
		
	}
	
	
	@Override
    public Bike add(Bike t) {
		
		bikes.add(t);
		return t;
    }

	@Override
	public Bike getById(Integer id) {
		for (Bike b: bikes) {
			if (b.getId().equals(id))
				return b;
		}
		return null;
	}
	


	@Override
	public Set<Bike> getAll() {
		return bikes;
	}

	@Override
	public void update(Bike t) {
		for (Bike b: bikes) {
			if (b.getId() == t.getId()) {
				this.delete(b);
				bikes.add(t);
				return;
			}
		}
		//return;
	}

	@Override
	public void delete(Bike t) {
		bikes.remove(t);
	}
	
    @Override
    public Set<Bike> getAvailableBikes() {
        Set<Bike> abikes = new HashSet<>();
        for(Bike bike : bikes) {
        	// TODO needed to get the name from status
            if(bike.getStatus().getName().equals("Available")) {
                abikes.add(bike);
            }
        }
        return abikes;

    }


	@Override
	public Set<Bike> getBikesByPersonId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void edit(Bike t) {
		for (Bike b: bikes) {
			if (b.getId() == t.getId()) {
				this.delete(b);
				bikes.add(t);
				return;
			}
		}
		
	}


	@Override
	public Set<Bike> getByOwnerId(Integer id) {
        Set<Bike> bikes = new HashSet<>();
        for(Bike bike : bikes) {
      
            if(bike.getOwnerId() == id) {
                bikes.add(bike);
            }
        }
        return bikes;
	}
}
