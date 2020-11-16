package dev.elliman.data;

public class PersonDAOFactory {
	public PersonDAO getPersonDAO() {
		return new PersonCollection();
	}
}
