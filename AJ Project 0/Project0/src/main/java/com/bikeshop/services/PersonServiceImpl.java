package com.bikeshop.services;

import java.util.Scanner;

import com.bikeshop.beans.Person;
import com.bikeshop.beans.Role;
import com.bikeshop.dao.PersonDAO;
import com.bikeshop.dao.PersonDAOFactory;

public class PersonServiceImpl implements PersonService{
	private PersonDAO pd;
	Scanner scan = new Scanner(System.in);
	
	public PersonServiceImpl() {
		PersonDAOFactory personDaoFactory = new PersonDAOFactory();
		pd = personDaoFactory.getPersonDAO();
	}
		
	@Override
	public Person addPerson(Person p) {
		return pd.add(p);
		
	}

	@Override
	public Person getPersonById(Integer id) {
		
		return pd.getByID(id);
	}

	@Override
	public Person getPersonByUsername(String user) {

		return pd.getByUsername(user);
	}

	@Override
	public Person updatePerson(Person p) {
		
		return pd.updatePerson(p);
		
		
	}

	@Override
	public boolean delete(Person p) {
		Integer id = p.getId();
		return pd.delete(id);
		
		
	}
	public Person registerUser() {
		PersonService ps = new PersonServiceImpl();
		Person newb = new Person();
		
		System.out.println("Enter a username: ");
		newb.setUsername(scan.nextLine());
		System.out.println("Enter a password: ");
		newb.setPassword(scan.nextLine());
		System.out.println("Enter your first name");
		newb.setFirst(scan.nextLine());
		System.out.println("Enter your last name");
		newb.setLast(scan.nextLine());
		System.out.println("Enter your email");
		newb.setEmail(scan.nextLine());
		newb.setRole("customer");
		
		System.out.println("Does this look good?");
		System.out.println("Username: " + newb.getUsername() + "\nPassword: " + newb.getPassword() + "\nFirst: " + newb.getFirst() + "\n"
				+ "Last: " + newb.getLast() + "\nEmail: " + newb.getEmail());
		System.out.println("1. Confirm\n2. Start over\nother. Exit");
		
		int input = Integer.valueOf(scan.nextLine());
		Person q = new Person();
		switch (input) {
			case 1:
				Person p = ps.getPersonByUsername(newb.getUsername());
				if (p != null) {
					System.out.println("That username is taken... Please try again.");
					break;
				} else if (newb.getUsername() == "" || newb.getPassword() == "" || newb.getFirst() == "" || newb.getLast() == "" || newb.getEmail() == "") {
					System.out.println("Please input all information to continue.");
					break;
				} else {
					q = ps.addPerson(newb);
					return q;
				}
			case 2:
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
				
		}
//		System.out.println(q.toString());
		return q;
	}

	public Person logInUser() {
		PersonService ps = new PersonServiceImpl();
		while (true) {
			System.out.println("Enter username: ");
			String user = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			
			Person p = ps.getPersonByUsername(user);
			if (p == null) {
				System.out.println("Username doesn't exist");
			} else if (p.getPassword().equals(password)) {
				System.out.println("Logged in successfully");
				return p;
			} else  System.out.println("Password was incorrect");
			
			System.out.println("Do you want to try again? 1 for yes, other for no.");
			int input = Integer.valueOf(scan.nextLine());
			if (input != 1) {
				break;
			}
		}
		return null;
	}
	public Person registerEmployee() {
		PersonService ps = new PersonServiceImpl();
		Person newb = new Person();
		
		System.out.println("Enter a username: ");
		newb.setUsername(scan.nextLine());
		System.out.println("Enter a password: ");
		newb.setPassword(scan.nextLine());
		System.out.println("Enter their first name");
		newb.setFirst(scan.nextLine());
		System.out.println("Enter their last name");
		newb.setLast(scan.nextLine());
		System.out.println("Enter their email");
		newb.setEmail(scan.nextLine());
		System.out.println("Will this employee be a manager?\n1. Yes\nOther No");
		int userInput = Integer.valueOf(scan.nextLine());
		if (userInput == 1) {
			newb.setRole("manager");
		} else newb.setRole("employee");
		
		System.out.println("Does this look good?");
		System.out.println("Username: " + newb.getUsername() + "\nPassword: " + newb.getPassword() + "\nFirst: " + newb.getFirst() + "\n"
				+ "Last: " + newb.getLast() + "\nEmail: " + newb.getEmail() + "\nRole: " + newb.getRole());
		System.out.println("1. Confirm\n2. Start over\nother. Exit");
		
		int input = Integer.valueOf(scan.nextLine());
		Person q = new Person();
		switch (input) {
			case 1:
				Person p = ps.getPersonByUsername(newb.getUsername());
				if (p != null) {
					System.out.println("That username is taken... Please try again.");
					break;
				} else if (newb.getUsername() == "" || newb.getPassword() == "" || newb.getFirst() == "" || newb.getLast() == "" || newb.getEmail() == "") {
					System.out.println("Please input all information to continue.");
					break;
				} else {
					q = ps.addPerson(newb);
					return q;
				}
			case 2:
				System.out.println("Okay, let's try again.");
				break;
			default:
				System.out.println("Okay, let's go back.");
				return null;
				
		}
//		System.out.println(q.toString());
		return q;
	}

	@Override
	public void terminate() {
		PersonService ps = new PersonServiceImpl();
		System.out.println("Enter the username of the account you wish to terminate");
		String user = scan.nextLine();
		Person p = pd.getByUsername(user);
		p.setRole("terminated");
		pd.updatePerson(p);
		System.out.println(pd.getByID(p.getId()).toString());
	}
	

}
