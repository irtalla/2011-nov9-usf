package com.revature.data;

import com.revature.beans.Attachment;

public class AttachmentDAOFactory implements GenericDAOFactory<Attachment>{
	@Override
	public AttachmentDAO getDAO() {
		return new AttachmentHibernate();
	}
}
