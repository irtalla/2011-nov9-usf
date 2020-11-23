package dev.rev.data;


import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Person;

public interface PersonDAO extends GenericDAo<Person> {
	public Person getByUsername(String username,String password);
	public Person add(Person p) throws NonUniqueUsernameException;
	public String checkusername(String usernmae);
}
