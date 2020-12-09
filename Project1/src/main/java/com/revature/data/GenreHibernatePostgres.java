package com.revature.data;

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
		Integer newInt = 0;
		
		try (Session s = sessionFactory.getCurrentSession()){
			s.beginTransaction();
			newInt = (Integer) s.save(t);
			if (newInt != 0) s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return newInt;
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
		Set<Genre> genres = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM Genre";
			Query<Genre> q = s.createQuery(hql, Genre.class);
			List<Genre> resultList = q.getResultList();
			genres = new HashSet<>(resultList);
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
