package com.revature.data;

import com.revature.beans.PitchInfoRequest;
import com.revature.exceptions.RequestAsAuthorException;
import com.revature.exceptions.UninvolvedRequestTargetException;

public interface PitchInfoRequestDAO extends GenericDAO<PitchInfoRequest> {
	PitchInfoRequest addPitchInfoRequest(PitchInfoRequest pir) throws RequestAsAuthorException, UninvolvedRequestTargetException;
	//not updatable
}
