package com.revature.data;

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
	
	/**
	 * 
	 * Adding a decision is probably the most complex and important
	 * operation in the system. We need to know who made the decision, 
	 * what type of decision was made, the stage of the pitch, the 
	 * form of the pitch, and possibly other decisions on the draft
	 * 
	 * 
	 */
	@Override
	public Decision add(Decision c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			
			/**
			 * First, let's get the pitch that is the object of the decision
			 * and the person who made the decision on the pitch
			 */
			Pitch pitch = pitchDAO.getById( c.getPitchId() ); 
			Stage stage = pitch.getStage();
			Form form = pitch.getForm(); 
			Person editor = personDAO.getById( c.getEditorId() ); 
			DecisionType decisionType = c.getDecisionType(); 
			Set<Decision> decisions = getByPitchId( pitch.getId() );
			Set<Person> genreCommitteMembers = personDAO.getAll().removeIf(
					p -> p.fd
					);
			/*
			 * In the simplest case, an editor of any seniority rejects
			 * a pitch that is not in final review. In that
			 * case, the pitch is immediately rejected.
			 */
			switch ( stage.getName().toUpperCase() ) {
			case "GENERAL REVIEW":
				if ( editor.getGenres().contains(pitch.getGenre()) ) {
					throw new InvalidGeneralEditorException(); 
				}
			case "GENRE REVIEW":
			case "SENIOR REVIEW":
				Status status = new Status(); 
			    status = UtilityDAO.getByName(status, "rejected"); 
				pitch.setStatus(status);
				pitchDAO.update(pitch); 
				break; 
				
			/**
			 * The final review is for pitches that have been approved
			 * by a senior editor, but whose draft has not bee approved. 
			 */
			case "FINAL REVIEW": 
				
				switch ( form.getName().toUpperCase() ) {
					case "ARTICLE": 
						Status status1 = new Status(); 
					    status1 = UtilityDAO.getByName(status1, "rejected"); 
						pitch.setStatus(status1);
						pitchDAO.update(pitch); 
						break; 
				
				}
				
				
				
				
			}
			
			
			
			
			
			s.save(c);
			
			
			
			
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				// Give indication of failure with null return value
				c = null; 
			}
		} finally {
			s.close();
		}
		return c;
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
