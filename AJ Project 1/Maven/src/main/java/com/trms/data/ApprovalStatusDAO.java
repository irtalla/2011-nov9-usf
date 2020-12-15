package com.trms.data;

import com.trms.beans.ApprovalStatus;

public interface ApprovalStatusDAO extends GenericDAO<ApprovalStatus> {
	public ApprovalStatus add (ApprovalStatus a);
	public ApprovalStatus getByName(String name);

}
