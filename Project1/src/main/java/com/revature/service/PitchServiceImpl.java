package com.revature.service;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.data.PitchDAO;
import com.revature.data.PitchHibernate;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pDao = new PitchHibernate();
	@Override
	public Integer addPitch(Pitch p) {
		return pDao.add(p).getId();
	}

	@Override
	public Pitch getPitchById(Integer id) {
		return pDao.getById(id);
	}
	
	@Override
	public Set<Pitch> getPitches() {
		return pDao.getAll();
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
