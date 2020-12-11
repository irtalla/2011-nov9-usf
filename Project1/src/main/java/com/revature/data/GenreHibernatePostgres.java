package com.revature.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.Genre;
import com.revature.util.HibernateUtil;

public class GenreHibernatePostgres implements GenreDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(Genre t) throws Exception {
		Integer newId = 0;
		
		try (Session s = sessionFactory.getCurrentSession()){
			s.beginTransaction();
			newId = (Integer) s.save(t);
			if (newId != 0) {
				s.getTransaction().commit();
			} else {
				s.getTransaction().rollback();
			}
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return newId;
	}

	@Override
	public Genre getById(Integer id) {
		Genre g = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			g = s.get(Genre.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return g;
	}

	@Override
	public Set<Genre> getAll() {
		Set<Genre> genres = new HashSet<>(getAllOrdered());
		return genres;
	}

	@Override
	public List<Genre> getAllOrdered() {
		List<Genre> genres = new ArrayList<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Genre ORDER BY id";
			Query<Genre> q = s.createQuery(hql, Genre.class);
			genres = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return genres;
	}
	
	@Override
	public void update(Genre t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Genre t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
