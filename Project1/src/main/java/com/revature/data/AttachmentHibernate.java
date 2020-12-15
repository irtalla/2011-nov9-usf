package com.revature.data;

import java.util.Set;

import com.revature.beans.Attachment;

public class AttachmentHibernate extends GenericHibernate<Attachment> implements AttachmentDAO{

	public AttachmentHibernate() {
		super(Attachment.class, "attachment");
	}

	@Override
	public Attachment getByIdEagerly(Integer id) {
		// TODO Auto-generated method stub
		Attachment newAttch = this.getByIdLazily(id);
		newAttch.setPitch(new PitchHibernate().getByIdLazily(newAttch.getPitchId()));
		return newAttch;
	}
	@Override
	public Set<Attachment> getAllEagerly() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Attachment> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
