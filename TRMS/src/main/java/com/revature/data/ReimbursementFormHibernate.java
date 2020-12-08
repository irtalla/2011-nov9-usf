package com.revature.data;

import java.util.Set;

import org.hibernate.Session;

import com.revature.beans.ReimbursementForm;
import com.revature.utils.HibernateUtil;

public class ReimbursementFormHibernate implements ReimbursementFormDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public ReimbursementForm add(ReimbursementForm t) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ReimbursementForm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ReimbursementForm t) {
		// TODO Auto-generated method stub
		
	}

}
