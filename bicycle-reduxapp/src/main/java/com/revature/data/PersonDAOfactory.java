package com.revature.data;

public class PersonDAOfactory {
    public PersonCollections getPersonDAO() {
        
        return new PersonCollections();
    }
}
