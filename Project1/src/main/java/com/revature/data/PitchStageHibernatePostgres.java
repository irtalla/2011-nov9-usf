package com.revature.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.models.PitchStage;
import com.revature.util.HibernateUtil;

public class PitchStageHibernatePostgres implements PitchStageDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Integer add(PitchStage t) throws Exception {
		Integer newInt = 0;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			newInt = (Integer) s.save(t);
			if (newInt != 0) {
				s.getTransaction().commit();
			} else {
				s.getTransaction().rollback();
			}
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return newInt;
	}

	@Override
	public PitchStage getById(Integer id) {
		PitchStage ps = new PitchStage();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			ps = s.get(PitchStage.class, id);
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return ps;
	}

	@Override
	public Set<PitchStage> getAll() {
		Set<PitchStage> stages = new HashSet<>(getAllOrdered());
		return stages;
	}
	
	@Override
	public List<PitchStage> getAllOrdered() {
		List<PitchStage> stages = new ArrayList<>();
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "FROM PitchStage ORDER BY id";
			Query<PitchStage> q = s.createQuery(hql, PitchStage.class);
			stages = q.getResultList();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
		
		return stages;
	}

	@Override
	public void update(PitchStage t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

	@Override
	public void delete(PitchStage t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

}
