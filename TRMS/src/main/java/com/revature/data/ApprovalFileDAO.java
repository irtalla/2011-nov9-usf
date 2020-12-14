package com.revature.data;

import java.util.Set;

import com.revature.beans.ApprovalFile;

public interface ApprovalFileDAO extends GenericDAO<ApprovalFile> {
	public Set<ApprovalFile> getApprovalFileByFormId(Integer id);
}
