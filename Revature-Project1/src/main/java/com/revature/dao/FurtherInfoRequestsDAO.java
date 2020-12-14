package com.revature.dao;

import java.util.Set;

import com.revature.beans.FurtherInfoRequests;

public interface FurtherInfoRequestsDAO {
	public void addFurtherInfoToAPitch(FurtherInfoRequests fir);
	public Set<FurtherInfoRequests> getFurtherInfo(int id, int stage);
}
