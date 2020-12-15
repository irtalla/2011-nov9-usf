package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.exceptions.NonUniqueCommitteeException;
import com.revature.models.Committee;
import com.revature.models.Genre;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class CommitteeHibernatePostgres implements CommitteeDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Override
	public Integer add(Committee t) throws NonUniqueCommitteeException {
		Integer newId = 0;
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			newId = (Integer) s.save(t);
			if (newId != 0) {
				s.getTransaction().commit();
			} else {
				s.getTransaction().rollback();
			}
		} catch (Exception e) {
			if (e.getCause().getMessage().contains("violates unique constraint")) {
				System.out.println("non-unique");
				throw new NonUniqueCommitteeException();
			}
			e.printStackTrace();
		}
		return newId;
	}

	@Override
	public Committee getById(Integer id) {
		Committee c = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			c = s.get(Committee.class, id);
			c.setName(assembleName(c));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Committee getByGenre(Genre genre) {
		Committee c = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Committee where genre_id = :genre_id";
			Query<Committee> q = s.createQuery(hql, Committee.class);
			q.setParameter("genre_id", genre.getId());
			List<Committee> resultList = q.getResultList();
			if (resultList.size() > 0) {
				c = resultList.get(0);
				c.setName(assembleName(c));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Set<User> getCommitteeEditorsByRole(Committee t, Role role) {
		Set<User> editors = new HashSet<>();
		if (t != null && role != null) {
			for (User u : getById(t.getId()).getEditors()) {
				if (u.getRole().equals(role)) {
					editors.add(u);
				}
			}
		}
		return editors;
	}

	@Override
	public Set<User> getCommitteeEditors(Committee t) {
		Set<User> editors = new HashSet<>();
		if (t != null) {
			editors = getById(t.getId()).getEditors();
		}
		return editors;
	}
	
	@Override
	public Set<Committee> getAll() {
		Set<Committee> committees = new HashSet<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Committee";
			List<Committee> resultList = s.createQuery(hql, Committee.class).list();
			for (Committee c : resultList) {
				c.setName(assembleName(c));
			}
			committees = new HashSet<Committee>(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return committees;
	}

	@Override
	public void update(Committee t) throws NonUniqueCommitteeException {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			if (e.getCause().getMessage().contains("violates unique constraint")) {
				throw new NonUniqueCommitteeException();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Committee t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String assembleName(Committee c) {
		return (c.getGenre().getName() + " Committee");
	}

}
