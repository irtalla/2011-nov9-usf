package com.revature.data;

import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.PitchInfoRequest;
import com.revature.beans.Role;
import com.revature.exceptions.RequestAsAuthorException;
import com.revature.exceptions.UninvolvedRequestTargetException;

public class PitchInfoRequestHibernate extends GenericHibernate<PitchInfoRequest> implements PitchInfoRequestDAO {

	public PitchInfoRequestHibernate() {
		super(PitchInfoRequest.class);
	}

	@Override
	public PitchInfoRequest addPitchInfoRequest(PitchInfoRequest pir)
			throws RequestAsAuthorException, UninvolvedRequestTargetException {
		Person requester = pir.getRequestingEditor();
		Person target = pir.getTargetedPerson();
		Pitch pitch = pir.getPitch();
		System.out.println(requester.toString());
		
		if(requester.getRole().equals(Role.AUTHOR)) {
			throw new RequestAsAuthorException();
		}else if(!target.getRole().equals(Role.AUTHOR) && !pitch.getEditorsThatHaveReacted().contains(target)) {
			//targeting an editor that has not reacted to the pitch
			throw new UninvolvedRequestTargetException();
		}else {
			this.add(pir);
		}
		return pir;
	}

}
