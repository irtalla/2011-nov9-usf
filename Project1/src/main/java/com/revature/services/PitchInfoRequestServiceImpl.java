package com.revature.services;

import com.revature.beans.PitchInfoRequest;
import com.revature.data.PitchInfoRequestDAOFactory;
import com.revature.data.PitchInfoRequestHibernate;

public class PitchInfoRequestServiceImpl extends GenericServiceImpl<PitchInfoRequest> implements PitchInfoRequestService {

	public PitchInfoRequestServiceImpl() {
		super(new PitchInfoRequestDAOFactory());
	}

	@Override
	PitchInfoRequestHibernate getDao() {
		return new PitchInfoRequestHibernate();
	}

}
