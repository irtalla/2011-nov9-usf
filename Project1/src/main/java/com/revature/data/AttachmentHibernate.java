package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.revature.beans.Attachment;

public class AttachmentHibernate extends GenericHibernate<Attachment> implements AttachmentDAO{

	public AttachmentHibernate() {
		super(Attachment.class, "attachment");
	}

	@Override
	public Attachment getByIdEagerly(Integer id) {
//		// TODO Auto-generated method stub
//		Attachment newAttch = this.getByIdLazily(id);
//		newAttch.setPitch(new PitchHibernate().getByIdLazily(newAttch.getPitchId()));
//		return newAttch;
		//this will never be eagerly retrieved
		return this.getByIdLazily(id);
	}
	@Override
	public Set<Attachment> getAllEagerly() {
//		Session s = hu.getSession();
//		String sql = "SELECT * from " + this.tableName;
//        NativeQuery<Attachment> query = s.createNativeQuery(sql, this.type);
//        List<Attachment> attchs = query.getResultList();
//        for(Attachment attch : attchs) {
//        	attch.setPitch(new PitchHibernate().getByIdLazily(attch.getPitchId()));
//        }
//        return new HashSet<>(attchs);
		//these will never be eagerly retrieved
		return this.getAllLazily();
	}
	
	@Override
	public Set<Attachment> getAllEagerlyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		// This will never be retrieved eagerly
		return this.getAllLazilyWhereOwnerIdIs(ownerIdName, ownerId);
	}
}
