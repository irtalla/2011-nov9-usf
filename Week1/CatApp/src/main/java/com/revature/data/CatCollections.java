package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Status;

public class CatCollections implements CatDAO {
	private static Set<Cat> cats;
	
	public CatCollections() {
		cats = new HashSet<Cat>();
		Cat c = new Cat();
		c.setId(1);
		c.setName("Fluffy");
		c.setAge(3);
		Breed b = new Breed();
		b.setId(1);
		b.setName("Persian");
		c.setBreed(b);
		Status s = new Status();
		s.setId(1);
		s.setName("Available");
		c.setStatus(s);
		cats.add(c);
		
		c.setId(2);
		c.setName("Lucky");
		c.setAge(7);
		b = new Breed();
		b.setId(2);
		b.setName("Domestic Shorthair");
		c.setBreed(b);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		c.setStatus(s);
		cats.add(c);
		
		c.setId(3);
		c.setName("Howard");
		c.setAge(1);
		b = new Breed();
		b.setId(3);
		b.setName("Calico");
		c.setBreed(b);
		s = new Status();
		s.setId(1);
		s.setName("Available");
		c.setStatus(s);
		cats.add(c);
	}

	@Override
	public Cat add(Cat t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cat getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cat> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Cat> getAvailableCats() {
		return null;
	}

	@Override
	public void update(Cat t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Cat t) {
		// TODO Auto-generated method stub

	}

}
