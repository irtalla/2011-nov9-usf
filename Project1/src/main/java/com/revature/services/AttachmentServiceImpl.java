package com.revature.services;

import com.revature.beans.Attachment;
import com.revature.data.AttachmentDAOFactory;
import com.revature.data.AttachmentHibernate;
import com.revature.data.GenericDAO;

public class AttachmentServiceImpl extends GenericServiceImpl<Attachment> implements AttachmentService{

	public AttachmentServiceImpl() {
		super(new AttachmentDAOFactory());
		// TODO Auto-generated constructor stub
	}

	@Override
	GenericDAO<Attachment> getDao() {
		return new AttachmentHibernate();
	}

	@Override
	public Attachment getByIdEagerly(Integer id) {
		return getDao().getByIdEagerly(id);
	}

}
