package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.AdditionalFile;
import com.revature.util.HibernateUtil;

public class AdditionalFileHibernatePostgres implements AdditionalFileDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(AdditionalFile t) throws Exception {
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
			e.getCause().printStackTrace();
		}
		return newId;
	}

	@Override
	public AdditionalFile getById(Integer id) {
		AdditionalFile af = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			af = s.get(AdditionalFile.class, id);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return af;
	}
	
	@Override
	public AdditionalFile getByPath(String path) {
		AdditionalFile af = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM AdditionalFile where path = :path";
			Query<AdditionalFile> q = s.createQuery(hql, AdditionalFile.class);
			q.setParameter("path", path);
			af = q.getSingleResult();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return af;
	}

	@Override
	public Set<AdditionalFile> getAll() {
		Set<AdditionalFile> files = new HashSet<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM AdditionalFile";
			Query<AdditionalFile> q = s.createQuery(hql, AdditionalFile.class);
			List<AdditionalFile> resultList = q.getResultList();
			files = new HashSet<>(resultList);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return files;
	}

	@Override
	public void update(AdditionalFile t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

	@Override
	public void delete(AdditionalFile t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

}
