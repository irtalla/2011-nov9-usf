package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.revature.beans.Draft;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.DraftFromUnapprovedPitchException;
import com.revature.utils.HibernateUtil;

public class DraftHibernate extends GenericHibernate<Draft> implements DraftDAO{

//	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public DraftHibernate() {
		super(Draft.class, "draft");
	}
	
//	public DraftHibernate(Class<Draft> type) {
//		super(type);
//	}

	@Override
	public Set<Draft> getPendingDraftsWithGenre(Genre g) {
		// Criteria API: a way of making queries in a programmatic syntax
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Draft> cq = cb.createQuery(Draft.class);
		Root<Draft> root = cq.from(Draft.class);
		
		Predicate predicateForGenre = cb.equal(root.get("genre"), g.toString());
		
		cq.select(root).where(predicateForGenre);
		cq.orderBy(cb.asc(root.get("priority"))); //sort in order of priority (alphabetically, since "High" < "Normal")
		
		List<Draft> d = s.createQuery(cq).getResultList();
		return new HashSet<Draft>(d);
	}

	public Draft addDraft(Draft d) throws DraftFromUnapprovedPitchException {
		Pitch p = d.getPitch();
		if(!p.getStatus().equals(Status.APPROVED)) {
			throw new DraftFromUnapprovedPitchException();
		}else {
			this.add(d);
		}
		return d;
	}

	public Set<Draft> getAllEagerly(String ownerIdName, Integer ownerId) {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where " + ownerIdName + " = " + ownerId;
        NativeQuery<Draft> query = s.createNativeQuery(sql, this.type);
        List<Draft> matches = query.getResultList();
        for(Draft d : matches) {
        	Pitch p = new PitchHibernate().getByIdLazily(d.getPitchId());
        	Person author = new PersonHibernate().getByIdLazily(p.getAuthorId());
        	p.setAuthor(author);
        	d.setPitch(p);
        }
        return new HashSet<>(matches);
	}
	
	@Override
	public Set<Draft> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where " + ownerIdName + " = " + ownerId;
        NativeQuery<Draft> query = s.createNativeQuery(sql, this.type);
        List<Draft> matches = query.getResultList();
        for(Draft d : matches) {
        	Pitch p = new PitchHibernate().getByIdLazily(d.getPitchId());
        	Person author = new PersonHibernate().getByIdLazily(p.getAuthorId());
        	p.setAuthor(author);
        	d.setPitch(p);
        }
        return new HashSet<>(matches);
	}

	@Override
	public Draft getByIdEagerly(Integer id) {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where id = " + id;
        NativeQuery<Draft> query = s.createNativeQuery(sql, this.type);
        Draft draft = query.getSingleResult();
        Pitch pitch = new PitchHibernate().getByIdLazily(draft.getPitchId());
        Person author = new PersonHibernate().getByIdLazily(pitch.getAuthorId());
        pitch.setAuthor(author);
        draft.setPitch(pitch);
        return draft;
	}
}
