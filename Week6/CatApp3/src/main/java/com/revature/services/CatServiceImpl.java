package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BreedDAO;
import com.revature.data.CatDAO;
import com.revature.data.PersonDAO;
import com.revature.data.StatusDAO;

@Service
public class CatServiceImpl implements CatService {
	private CatDAO catDao;
	private PersonDAO personDao;
	private StatusDAO statusDao;
	private BreedDAO breedDao;
	
	@Autowired
	public CatServiceImpl(CatDAO c, PersonDAO p) {
		catDao = c;
		personDao = p;
	}

	@Override
    public Integer addCat(Cat c) {
		c.setStatus(statusDao.getByName("Available"));
        return catDao.add(c).getId();
    }
    @Override
    public Cat getCatById(Integer id) {
        return catDao.getById(id);
    }
    @Override
    public Set<Cat> getCats() {
        return catDao.getAll();
    }
    @Override
    public Set<Cat> getAvailableCats() {
        return catDao.getAvailableCats();
    }
    @Override
    public Set<Breed> getBreeds() { 
    	return breedDao.getAll();
    }
    @Override
    public void updateCat(Cat c) {
        catDao.update(c);   
    }
    @Override
    public void adoptCat(Person p, Cat c) {
    	Status status = new Status();
    	status.setId(2);
    	status.setName("Adopted");
    	c.setStatus(status);
    	updateCat(c);
        Set<Cat> set = p.getCats();
        set.add(c);
        p.setCats(set);
        personDao.update(p);
        
    }
    @Override
    public void removeCat(Cat c) {
        catDao.delete(c);
    }

}
