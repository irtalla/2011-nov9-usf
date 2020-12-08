//package com.revature.services;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.jupiter.api.Test;
//
//import com.revature.beans.Person;
//import com.revature.data.DAO;
//import com.revature.data.PersonPostgres;
//import com.revature.exceptions.NonUniqueUsernameException;
//
//public class PersonServicesTest extends DaoTest<Person>{
//	@Override
//	protected DAO<Person> createDao() {
//		return new PersonPostgres();
//	}
//
//	@Override
//	protected Person createObject() {
//		Person owner = new Person();
//		owner.setUsername("Owner");
//		return owner;
//	}
//
//	@Override
//	protected Set<Person> createTestSubjectSet() {
//		Set<Person> people = new HashSet<Person>();
//		Person person1 = new Person();
//		person1.setUsername("Person 1");
//		people.add(person1);
//		
//		Person person2 = new Person();
//		person1.setUsername("Person 2");
//		people.add(person2);
//		return people;
//	}	
//	
//	@Override @Test
//	public void testAdd() throws Exception{
//		super.testAdd();
//		
//		Person person3 = new Person();
//		person3.setUsername("Owner"); //same as our testSubject created in createObject
//		assertThrows(NonUniqueUsernameException.class, () -> dao.add(person3));
//	}
//}
