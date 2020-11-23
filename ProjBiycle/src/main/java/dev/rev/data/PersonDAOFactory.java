package dev.rev.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
        
        return new PersonPostgre();
    }
}
