package com.cross.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cross.beans.Decision;
import com.cross.beans.DecisionType;
import com.cross.beans.Form;
import com.cross.beans.Person;
import com.cross.beans.Pitch;
import com.cross.beans.Stage;
import com.cross.beans.Status;
import com.cross.beans.Decision;
import com.cross.utils.HibernateUtil;

import exceptions.InvalidGeneralEditorException;

public class DecisionHibernate implements DecisionDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	private PitchDAO pitchDAO = new PitchHibernate(); 
	private PersonDAO personDAO = new PersonHibernate(); 
	
	@Override
	public Decision getById(Integer id) {
		Session s = hu.getSession();
		Decision c = s.get(Decision.class, id);
		s.close();
		return c;
	}

	@Override
	public Set<Decision> getAll() {
		Session s = hu.getSession();
		String query = "FROM Decision";
		Query<Decision> q = s.createQuery(query, Decision.class);
		List<Decision> decisionsList = q.getResultList();
		Set<Decision> decisionsSet = new HashSet<>();
		decisionsSet.addAll(decisionsList);
		s.close();
		return decisionsSet;
	}
	
	@Override
	public boolean update(Decision t) {
		Boolean didUpdate = false; 
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
			didUpdate = true; 
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				didUpdate = false; 
			}
		} finally {
			s.close();
		}
		return didUpdate; 
	}
	
	@Override
	public Decision add(Decision c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean delete(Decision t) {
		Boolean didDelete = false; 
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
			didDelete = true; 
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				didDelete = false; 
			}
		} finally {
			s.close();
		}
		return didDelete; 
	}
	

	@Override
	public Set<Decision> getByEditorId(Integer editorId) {

		Session s = hu.getSession();
		String query = "FROM Decision where editor_id = :id";
		Query<Decision> q = s.createQuery(query, Decision.class);
		q.setParameter("id", editorId);
		List<Decision> decisionsList = q.getResultList();
		Set<Decision> decisionsSet = new HashSet<>();
		decisionsSet.addAll(decisionsList);
		s.close();
		return decisionsSet;
	}
	
	@Override
	public Set<Decision> getByPitchId(Integer pitchId) {

		Session s = hu.getSession();
		String query = "FROM Decision where pitch_id = :id";
		Query<Decision> q = s.createQuery(query, Decision.class);
		q.setParameter("id", pitchId);
		List<Decision> decisionsList = q.getResultList();
		Set<Decision> decisionsSet = new HashSet<>();
		decisionsSet.addAll(decisionsList);
		s.close();
		return decisionsSet;
	}	
	
}

