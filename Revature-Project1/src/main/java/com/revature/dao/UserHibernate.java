package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.User;
import com.revature.utils.SessionUtil;

public class UserHibernate implements UserDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public int registerAUser(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User verifyAUser(String username, String password) {
		Session s = su.getSession();
		
		String query = "FROM User where username = :username and password = :password";
		Query<User> q = s.createQuery(query, User.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<User> userList = q.getResultList();
		
		if (userList.size() == 1) {	//yes, a cheap and hacky way to ensure there are no duplicates
									//but it works.
			return userList.get(0);
		}
		
		
		return null;
	}
	
}
