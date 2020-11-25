package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Status;

public class BikeCollections implements BikeDAO{
private static Set<Bike> bikes;
	
	public BikeCollections() {
		bikes = new HashSet<Bike>();
		Bike bk = new Bike();
		bk.setId(1);
		bk.setName("Fluffy");
		bk.setCondition("Mint");
		Brand b = new Brand();
		b.setId(1);
		b.setName("Persian");
		bk.setBrand(b);
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		bk.setStatus(s);
		bikes.add(bk);
		
		bk = new Bike();
		bk.setId(2);
		bk.setName("Lucky");
		bk.setCondition("Rusty");
		b = new Brand();
		b.setId(2);
		b.setName("Domestic Shorthair");
		bk.setBrand(b);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bk.setStatus(s);
		bikes.add(bk);
		
		bk = new Bike();
		bk.setId(3);
		bk.setName("Howard");
		bk.setCondition("Mint");
		b = new Brand();
		b.setId(3);
		b.setName("Calico");
		bk.setBrand(b);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bk.setStatus(s);
		bikes.add(bk);
	}

	@Override
    public Bike add(Bike t) {
		// TODO update id
		bikes.add(t);
		return t;
    }
    @Override
    public Bike getById(Integer id) {
        for(Bike bike : bikes) {
            if(bike.getId().equals(id)) {
                return bike;
            }
        }
        return null;
    }
    @Override
    public Set<Bike> getAll() {
        return bikes;
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
    public void update(Bike t) {
//    	if (c.getId() < cats.size()) {
//			for (Cat cat : cats) {
//				if (cat.getId().equals(c.getId())) {
//					cat.setName(c.getName());
//					cat.setAge(c.getAge());
//					cat.setBreed(c.getBreed());
//					cat.setStatus(c.getStatus());
//				}
//			}
//		} else {
//			add(c);
//		}
    	
    	// TODO check whether the cat exists first
    	Bike match = getById(t.getId());
    	if (match != null) {
    		match.setCondition(t.getCondition());
    		match.setBrand(t.getBrand());
    		match.setId(t.getId());
    		match.setName(t.getName());
    		match.setSpecialFeatures(t.getSpecialFeatures());
    		match.setStatus(t.getStatus());
    	}
    }
    @Override
    public void delete(Bike t) {
        if(bikes.contains(t)) {
            bikes.remove(t);
        }
    }

}
