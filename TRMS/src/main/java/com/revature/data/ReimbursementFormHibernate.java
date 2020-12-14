package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.GradePresentationFile;
import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementChangeNotification;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.utils.HibernateUtil;

public class ReimbursementFormHibernate implements ReimbursementFormDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public ReimbursementForm add(ReimbursementForm t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return t;
	}

	@Override
	public ReimbursementForm getById(Integer id) {
		Session s = hu.getSession();
		ReimbursementForm form = s.get(ReimbursementForm.class, id);
		s.close();
		return form;
	}

	@Override
	public Set<ReimbursementForm> getAll() {
		Session s = hu.getSession();
		String query = "FROM ReimbursementForm";
		Query<ReimbursementForm> q = s.createQuery(query, ReimbursementForm.class);
		List<ReimbursementForm> typeList = q.getResultList();
		Set<ReimbursementForm> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public void update(ReimbursementForm t) {
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
	public void delete(ReimbursementForm t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public Stage getStageById(Integer id) {
		Session s = hu.getSession();
		Stage stage = s.get(Stage.class, id);
		s.close();
		return stage;
	}

	@Override
	public Status getStatusById(Integer id) {
		Session s = hu.getSession();
		Status status = s.get(Status.class, id);
		s.close();
		return status;
	}

	@Override
	public GradingFormat getGradingFormatById(Integer id) {
		Session s = hu.getSession();
		GradingFormat format = s.get(GradingFormat.class, id);
		s.close();
		return format;
	}

	@Override
	public Set<GradingFormat> getAllGradingFormats() {
		Session s = hu.getSession();
		String query = "FROM GradingFormat";
		Query<GradingFormat> q = s.createQuery(query, GradingFormat.class);
		List<GradingFormat> typeList = q.getResultList();
		Set<GradingFormat> typeSet = new HashSet<>();
		typeSet.addAll(typeList);
		s.close();
		return typeSet;
	}

	@Override
	public Integer addPresentationFile(GradePresentationFile file) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(file);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return file.getId();
	}

	@Override
	public GradePresentationFile getGradePresentationFileById(Integer id) {
		Session s = hu.getSession();
		GradePresentationFile format = s.get(GradePresentationFile.class, id);
		s.close();
		return format;
	}

	@Override
	public Set<GradePresentationFile> getGradePresentationFileByFormId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM GradePresentationFile WHERE formId = :formId";
		Query<GradePresentationFile> q = s.createQuery(query, GradePresentationFile.class);
		q.setParameter("formId", id);
		List<GradePresentationFile> l = q.getResultList();
		Set<GradePresentationFile> set = new HashSet<>();
		set.addAll(l);
		return set;
	}

	@Override
	public void deleteGradePresentationFile(GradePresentationFile file) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(file);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public Set<ReimbursementChangeNotification> getReimbursementChangeNotificationByFormId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM ReimbursementChangeNotification WHERE formId = :formId";
		Query<ReimbursementChangeNotification> q = s.createQuery(query, ReimbursementChangeNotification.class);
		q.setParameter("formId", id);
		List<ReimbursementChangeNotification> l = q.getResultList();
		Set<ReimbursementChangeNotification> set = new HashSet<>();
		set.addAll(l);
		return set;
	}

	@Override
	public ReimbursementChangeNotification addReimbursementChangeNotification(ReimbursementChangeNotification notification) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(notification);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return notification;
	}

	@Override
	public void deleteReimbursementChangeNotification(ReimbursementChangeNotification notification) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(notification);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		
	}

	@Override
	public void updateReimbursementChangeNotification(ReimbursementChangeNotification notification) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(notification);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
