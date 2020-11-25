package com.revature.data;

import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.Offer;
import com.revature.beans.Status;
import com.revature.beans.Type;

public class BikeCollections implements BikeDAO{
	private static Set<Bike> bikes;
	private static Set<Offer> offers;
	
	public BikeCollections() {
		bikes = new HashSet<Bike>();
		Bike b = new Bike();
		b.setId(1);
		b.setYear(2012);
		b.setPrice(199.99);
		b.setColor("Blue");
		Type t = new Type();
		t.setId(1);
		t.setName("Mountain");
		b.setType(t);
		Brand br = new Brand();
		br.setId(1);
		br.setName("Diamondback");
		b.setBrand(br);
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);
		
		b = new Bike();
		b.setId(2);
		b.setYear(2018);
		b.setPrice(299.99);
		b.setColor("Red");
		t = new Type();
		t.setId(2);
		t.setName("Racing");
		b.setType(t);
		br = new Brand();
		br.setId(2);
		br.setName("Trek");
		b.setBrand(br);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);
		
		b = new Bike();
		b.setId(3);
		b.setYear(2020);
		b.setPrice(499.99);
		b.setColor("Black");
		t = new Type();
		t.setId(3);
		t.setName("Electric");
		b.setType(t);
		br = new Brand();
		br.setId(3);
		br.setName("Fuji");
		b.setBrand(br);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);
		
		b = new Bike();
		b.setId(4);
		b.setYear(2014);
		b.setPrice(149.99);
		b.setColor("Red");
		t = new Type();
		t.setId(1);
		t.setName("Mountain");
		b.setType(t);
		br = new Brand();
		br.setId(3);
		br.setName("Fuji");
		b.setBrand(br);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);
		
		b = new Bike();
		b.setId(5);
		b.setYear(2009);
		b.setPrice(254.99);
		b.setColor("Yellow");
		t = new Type();
		t.setId(4);
		t.setName("Tandem");
		b.setType(t);
		br = new Brand();
		br.setId(4);
		br.setName("Santana");
		b.setBrand(br);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);

		b = new Bike();
		b.setId(6);
		b.setYear(2017);
		b.setPrice(249.99);
		b.setColor("White");
		t = new Type();
		t.setId(1);
		t.setName("Racing");
		b.setType(t);
		br = new Brand();
		br.setId(1);
		br.setName("Diamondback");
		b.setBrand(br);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		bikes.add(b);
	}

	public Bike getById(Integer id) {
		for(Bike bike : bikes){
			if(bike.getId().equals(id)) return bike;
		}
		return null;
	}

	public Set<Bike> getAll() {
		return bikes;
	}

	public void update(Bike t) {
		Bike match = getById(t.getId());
		if(match != null){
			match.setYear(t.getYear());
			match.setColor(t.getColor());
			match.setPrice(t.getPrice());
			match.setType(t.getType());
			match.setBrand(t.getBrand());
			match.setStatus(t.getStatus());
		}
	}

	public void delete(Bike t) {
		if(bikes.contains(t)) bikes.remove(t);	
	}

	public Bike add(Bike t) {
		bikes.add(t);
		return t;
	}

	public Set<Bike> getAvailableBikes() {
		Set<Bike> abikes = new HashSet<>();
		for(Bike bike : bikes){
			if(bike.getStatus().getName().equals("Available")) abikes.add(bike);
		}
		return abikes;
	}

	public static Set<Bike> getBikes() {
		return bikes;
	}

	public static void setBikes(Set<Bike> bikes) {
		BikeCollections.bikes = bikes;
	}

	@Override
	public Set<Offer> getOffer() {
		return offers;
	}

}
