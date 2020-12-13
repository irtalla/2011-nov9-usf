package com.revature.data;

import com.revature.beans.PitchInfoRequest;

public class PitchInfoRequestDAOFactory implements GenericDAOFactory<PitchInfoRequest>{
	@Override
	public PitchInfoRequestDAO getDAO() {
		return new PitchInfoRequestHibernate();
	}
}
