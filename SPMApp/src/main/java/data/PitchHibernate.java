package data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.HashSet;
import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Approval;
import beans.Draft;
import beans.Pitch;
import beans.Suggestion;
import beans.Usr;

import beans.Pitch;
import beans.Usr;
import utils.HibernateUtil;

public class PitchHibernate implements PitchDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	
	@Override
	public Pitch getById(Integer id) {
		Session s = hu.getSession();
		Pitch p = s.get(Pitch.class, id);
		s.close();
		return p;
	}

	@Override
	public Set<Pitch> getAll() {
		Session s = hu.getSession();
		String query = "FROM pitch";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		List<Pitch> pitchList = q.getResultList();
		Set<Pitch> pitchSet = new HashSet<>();
		pitchSet.addAll(pitchList);
		s.close();
		return pitchSet;
	}

	@Override
	public void update(Pitch t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}

	}

	@Override
	public Pitch add(Pitch p) {
		System.out.println("Pitch captured" + p);
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return p;
	}

	@Override
	public Set<Pitch> getPendingPitches() {
//		Session s = hu.getSession();
//		CriteriaBuilder cb = s.getCriteriaBuilder();
//		CriteriaQuery<Pitch> criteria = cb.createQuery(Pitch.class);
//		Root<Pitch> root = criteria.from(Pitch.class);
//		Predicate predicateForPrending = cb.(root.get("status_id"), 1)
//		criteria.select(root);
		
		
		System.out.println("Get pend reached");
		Session s = hu.getSession();
		String query = "FROM Pitch where status_id = :id1 or status_id=:id2";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id1", 1);
		q.setParameter("id2", 2);
		List<Pitch> pitchList = q.getResultList();
		Set<Pitch> pitchSet = new HashSet<>();
		System.out.println(pitchList);
		pitchSet.addAll(pitchList);
		s.close();
		return pitchSet;
	}

	@Override
	public <T> void approvePitch(Usr u, Pitch p) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			String sql = "insert into editor_pitch_approvals values (?, ?, ?, ?)";
			NativeQuery<T> query = s.createNativeQuery(sql);
			query.setParameter(1, u.getUsr_id());
			query.setParameter(2, p.getP_id());
			query.setParameter(3, p.getGenre());
			query.setParameter(4, u.getRole().getId());
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
			e.printStackTrace();
		} finally {
			s.close();
		}
		// editor_id, pitch_id, editor_genre, editor_role_id

	}
	public Approval getApprovalById(Integer Id) {
		Session s = hu.getSession();
		Approval a = s.get(Approval.class, Id);
		s.close();
		return a;
	}
	@Override
	public void update(Approval t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}
	@Override
	public Set<Pitch> getPitchesByAuthor(Integer id) {
		Session s = hu.getSession();
		String query = "FROM pitch where author_id = :id";
		Query<Pitch> q = s.createQuery(query, Pitch.class);
		q.setParameter("id", id);
		List<Pitch> approvalList = q.getResultList();
		Set<Pitch> approvalSet = new HashSet<>();
		approvalSet.addAll(approvalList);
		s.close();
		return approvalSet;
	}

	@Override
	public Approval add(Approval a) {
		System.out.println("Approval captured" + a);
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return a;
}


	@Override
	public Set<Approval> getApprovals() {
		Session s = hu.getSession();
		String query = "FROM Approval";
		Query<Approval> q = s.createQuery(query, Approval.class);
		List<Approval> approvalList = q.getResultList();
		Set<Approval> approvalSet = new HashSet<>();
		approvalSet.addAll(approvalList);
		s.close();
		return approvalSet;
	}

	@Override
	public Draft acceptDraft(Draft a) {
		System.out.println("Draft captured" + a);
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return a;
	}

	@Override
	public Set<Draft> getDrafts() {
		Session s = hu.getSession();
		String query = "FROM Draft";
		Query<Draft> q = s.createQuery(query, Draft.class);
		List<Draft> draftList = q.getResultList();
		Set<Draft> draftSet = new HashSet<>();
		draftSet.addAll(draftList);
		s.close();
		return draftSet;
	}

	@Override
	public Set<Suggestion> getSuggestions() {
		Session s = hu.getSession();
		String query = "FROM Suggestion";
		Query<Suggestion> q = s.createQuery(query, Suggestion.class);
		List<Suggestion> suggestionList = q.getResultList();
		Set<Suggestion> suggestionSet = new HashSet<>();
		suggestionSet.addAll(suggestionList);
		s.close();
		return suggestionSet;
	}

	@Override
	public Suggestion makeSuggestion(Suggestion a) {
		System.out.println("Suggest captured" + a);
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(a);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return a;
	}
}
