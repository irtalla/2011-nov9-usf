package dev.warrington.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
        
        return new PersonPostgres();
    }
}
