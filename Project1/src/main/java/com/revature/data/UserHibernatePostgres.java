package com.revature.data;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.exceptions.InvalidEmailException;
import com.revature.exceptions.NonUniqueEmailException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserHibernatePostgres implements UserDAO {
	private SessionFactory session = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		Integer newId = 0;
		try (Session s = session.getCurrentSession()){
			s.beginTransaction();
			newId = (Integer) s.save(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				if (e.getMessage().contains("username")) {
					throw new NonUniqueUsernameException();
				} else if (e.getMessage().contains("email")) {
					throw new NonUniqueEmailException();
				} else {
					e.printStackTrace();
				}
			} else if (e.getMessage().contains("violates check constraint")) {
				if (e.getMessage().contains("email")) {
					throw new InvalidEmailException();
				} else {
					e.printStackTrace();
				}
			}
			e.printStackTrace();
		}

		return newId;
	}
	
	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<User> getByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}


}
