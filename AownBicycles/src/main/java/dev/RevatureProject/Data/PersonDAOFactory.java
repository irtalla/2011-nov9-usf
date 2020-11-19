package dev.RevatureProject.Data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
        
        return (PersonDAO) new PersonCollections();
    }

}
