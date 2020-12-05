package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
		User u = null;
		
		try (Session s = session.getCurrentSession()) {
			s.beginTransaction();
			u = s.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User getByUsername(String username) {
		User u = null;
		
		try (Session s = session.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM User WHERE username = '" + username + "'";
			List<User> resultList = s.createQuery(hql, User.class).list();
			System.out.println(resultList.size());
			if (resultList.size() > 0) u = resultList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public User getByEmail(String email) {
		User u = null;
		
		try (Session s = session.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM User WHERE email = '" + email + "'";
			List<User> resultList = s.createQuery(hql, User.class).list();
			if (resultList.size() > 0) u = resultList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	@Override
	public Set<User> getByRole(Role role) {
		Set<User> users = null;
		
		try (Session s = session.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM User WHERE role_id = " + role.getId();
			List<User> resultList = s.createQuery(hql, User.class).list();
			users = new HashSet<User>(resultList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public Set<User> getAll() {
		Set<User> users = null;
		
		try (Session s = session.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM User";
			List<User> resultList = s.createQuery(hql, User.class).list();
			users = new HashSet<User>(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public void update(User t) throws NonUniqueUsernameException, NonUniqueEmailException, InvalidEmailException {
		try (Session s = session.getCurrentSession()){
			s.beginTransaction();
			s.update(t);
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

	}
	
	@Override
	public void delete(User t) {
		try (Session s = session.getCurrentSession()){
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
