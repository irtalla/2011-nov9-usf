package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.revature.beans.Draft;
import com.revature.beans.Genre;
import com.revature.beans.Pitch;
import com.revature.beans.Status;
import com.revature.exceptions.DraftFromUnapprovedPitchException;
import com.revature.utils.HibernateUtil;

public class DraftHibernate extends GenericHibernate<Draft> implements DraftDAO{

//	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public DraftHibernate() {
		super(Draft.class);
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

}
