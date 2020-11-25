package com.revature.services;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class PersonService {
    PersonDAO personDao;

    public PersonService() {
        this.personDao = new PersonDAOFactory().getPersonDAO();
    }

    public Person loginUser(String userName) {
        return this.personDao.loginUser(userName);
    }

    public Person createUser(String userName, String type) {
        return this.personDao.createUser(userName, type);
    }
}
