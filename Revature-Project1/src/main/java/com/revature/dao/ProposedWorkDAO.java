package com.revature.dao;

import com.revature.beans.Status;
import com.revature.beans.Work;

public interface ProposedWorkDAO {
	public Work addProposedWork(Work w);
	public boolean removeProposedWork(Work w);
	public void setProposedWorkStatus(Work w, Status s);
}
