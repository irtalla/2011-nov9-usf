package com.revature.service;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.data.PitchDAO;
import com.revature.data.PitchPostgres;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pDao = new PitchPostgres();
	@Override
	public Integer addPitch(Pitch p) {
		return pDao.add(p).getId();
	}

	@Override
	public Pitch getPitchById(Integer id) {
		return pDao.getById(id);
	}


	@Override
	public void updatePitch(Pitch p) {
		pDao.update(p);
	}

	@Override
	public void deletePitch(Pitch p) {
		pDao.delete(p);
	}

}
