package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO{

	@Override
	public Person add(Person t) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getById(Integer id) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getPitchesByPersonId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
