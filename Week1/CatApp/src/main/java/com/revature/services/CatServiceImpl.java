package com.revature.services;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.data.CatDAO;
import com.revature.data.CatDAOFactory;
import com.revature.data.PersonDAO;

public class CatServiceImpl implements CatService {
	private CatDAO catDao;
	private PersonDAO personDao;
	
	public CatServiceImpl() {
		CatDAOFactory catDaoFactory = new CatDAOFactory();
<<<<<<< HEAD
		catDao = catDaoFactory.getCatDao();
=======
		catDao = catDaoFactory.getCatDAO();
>>>>>>> da704a2191c99435b3748685f8b2263ec6a423af
	}

	@Override
    public Integer addCat(Cat c) {
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
    public void updateCat(Cat c) {
        catDao.update(c);   
    }
    @Override
    public void adoptCat(Person p, Cat c) {
        // TODO also need to update the cat's status
        Set<Cat> set = p.getCats();
        set.add(c);
        p.setCats(set);
    }
    @Override
    public void removeCat(Cat c) {
        catDao.delete(c);
    }

}
