package com.revature.controllers;

import com.revature.beans.Attachment;
import com.revature.services.AttachmentServiceImpl;

public class AttachmentController extends GenericController<Attachment>{

	public AttachmentController() {
		super(Attachment.class);
	}

	@Override
	AttachmentServiceImpl getServ() {
		return new AttachmentServiceImpl();
	}

}
