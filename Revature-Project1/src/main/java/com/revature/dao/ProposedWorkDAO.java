package com.revature.dao;

import java.util.Set;

import com.revature.beans.Status;
import com.revature.beans.Work;

public interface ProposedWorkDAO {
	public Work addProposedWork(Work w);
	public Set<Work> getAllWorksOfAuthor(int id);
	public boolean removeProposedWork(Work w);
	public void setProposedWorkStatus(Work w, Status s);
}
