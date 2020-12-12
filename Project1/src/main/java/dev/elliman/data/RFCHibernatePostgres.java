package dev.elliman.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dev.elliman.beans.RFC;
import dev.elliman.utils.HibernateUtil;

public class RFCHibernatePostgres implements RFCDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public RFC makeRequest(RFC rfc) {
		Session s = hu.getSession();
		
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(rfc);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return rfc;
	}

	@Override
	public List<RFC> getRFC(Integer claimID) {
		Session  s = hu.getSession();
		
		String query = "from RFC where claim.id = :id";
		Query<RFC> q = s.createQuery(query, RFC.class);
		q.setParameter("id", claimID);
		
		List<RFC> rfcList = q.getResultList();
		s.close();
		return rfcList;
	}

	@Override
	public Boolean answer(Integer id, String answer) {
		Session s = hu.getSession();
		
		RFC rfc = s.get(RFC.class, id);
		rfc.setAnswer(answer);
		
		Boolean success = false;
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(rfc);
			tx.commit();
			success = true;
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			success = false;
		} finally {
			s.close();
		}
		
		return success;
	}

}
