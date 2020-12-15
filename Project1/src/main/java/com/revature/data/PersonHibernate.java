package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.revature.beans.Draft;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.DraftFromUnapprovedPitchException;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.HibernateUtil;

public class PersonHibernate extends GenericHibernate<Person> implements PersonDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public PersonHibernate() {
		super(Person.class, "person");
	}
	
//	@Override
//	public void add(Person p) {
//		Session s = hu.getSession();
//		Transaction tx = null;
//		try {
//			tx = s.beginTransaction();
//			s.save(p);
//			tx.commit();
//		} catch (Exception e) {
//			if (tx != null)
//				tx.rollback();
//		} finally {
//			s.close();
//		}
//		return;
//	}

	@Override
	public void addPerson(Person p) throws NonUniqueUsernameException {
		try {
			this.add(p);
		}catch(Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
	}
	
	@Override
	public void updatePerson(Person p) throws NonUniqueUsernameException {
		try {
			this.add(p);
		}catch(Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
	}
	
	public Person getByUsername(String username) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		
		criteria.select(root).where(predicateForUsername);
		
		Person p = s.createQuery(criteria).getSingleResult();
		return p;
	}

	public Set<Person> getAllPeopleWithRole(Role role) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForRole = cb.equal(root.get("role"), role);
		
		criteria.select(root).where(predicateForRole);
		
		List<Person> people = s.createQuery(criteria).getResultList();
		return new HashSet<>(people);
	}

	public Set<Person> getAllEditorsWithRoleAndGenre(Role role, Genre genre) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		
		Predicate predicateForRole = cb.equal(root.get("role"), role);
		Predicate predicateForGenre = cb.equal(root.get("genre"), genre);
		
		criteria.select(root).where(predicateForRole, predicateForGenre);
		
		List<Person> people = s.createQuery(criteria).getResultList();
		return new HashSet<>(people);
	}
	
	public Set<Person> getAllEagerly(String ownerIdName, Integer ownerId) {
		//already have an author
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where " + ownerIdName + " = " + ownerId;
        NativeQuery<Person> query = s.createNativeQuery(sql, this.type);
        List<Person> matches = query.getResultList();
        for(Person p : matches) {
        	Set<Pitch> pitches = new PitchHibernate().getAllLazilyWhereOwnerIdIs("author_id", ownerId);
        	for(Pitch pitch : pitches) {
        		pitch.setDraft(new DraftHibernate().getByIdLazily(pitch.getDraftId()));
        		pitch.setFeedback(new PitchFeedbackHibernate().getAllLazilyWhereOwnerIdIs("pitch_id", pitch.getId()));
        		pitch.setInfoRequests(new PitchInfoRequestHibernate().getAllLazilyWhereOwnerIdIs("pitch_id", pitch.getId()));
        	}
        	try {
				p.setPitches(pitches);
			} catch (NonAuthorHasPitchesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	
        	sql = "select * from genre_committee join (SELECT * from person join genre_committee_membership on person.id = genre_committee_membership.editor_id where person.id = " + ownerId + ") as editor_membership on genre_committee.id = genre_committee_id";
        	NativeQuery<GenreCommittee> gcQuery = s.createNativeQuery(sql, GenreCommittee.class);
            List<GenreCommittee> gcS = gcQuery.getResultList();
            p.setGenreCommittees(new HashSet<>(gcS));
        }
        return new HashSet<>(matches);
	}
	
	@Override
	public Set<Person> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
//		Session s = hu.getSession();
//		String sql = "SELECT * from " + this.tableName + " where " + ownerIdName + " = " + ownerId;
//        NativeQuery<Person> query = s.createNativeQuery(sql, this.type);
//        List<Person> matches = query.getResultList();
//        for(Person p : matches) {
//        	Pitch p = new PitchHibernate().getByIdLazily(d.getPitchId());
//        	Person author = new PersonHibernate().getByIdLazily(p.getAuthorId());
//        	p.setAuthor(author);
//        	d.setPitch(p);
//        }
//        return new HashSet<>(matches);
		//is not owned
		return null;
	}

	@Override
	public Person getByIdEagerly(Integer id) {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where id = " + id;
        NativeQuery<Person> query = s.createNativeQuery(sql, this.type);
        Person Person = query.getSingleResult();
        Pitch pitch = new PitchHibernate().getByIdLazily(Person.getPitchId());
        Person author = new PersonHibernate().getByIdLazily(pitch.getAuthorId());
        pitch.setAuthor(author);
        draft.setPitch(pitch);
        return draft;
	}
}
