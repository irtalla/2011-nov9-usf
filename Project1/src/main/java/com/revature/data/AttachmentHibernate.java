package com.revature.data;

import com.revature.beans.Attachment;

public class AttachmentHibernate extends GenericHibernate<Attachment> implements AttachmentDAO{

	public AttachmentHibernate() {
		super(Attachment.class);
	}

}
