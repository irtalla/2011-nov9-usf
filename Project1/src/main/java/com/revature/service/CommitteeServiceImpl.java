package com.revature.service;

import com.revature.beans.Committee;
import com.revature.beans.Genre;
import com.revature.data.CommitteeDAO;
import com.revature.data.CommitteePostgres;

public class CommitteeServiceImpl implements CommitteeService {
	private CommitteeDAO comDao = new CommitteePostgres();
	@Override
	public Integer addCommittee(Committee c) {
		return comDao.add(c).getId();
	}

	@Override
	public Committee getCommitteeById(Integer id) {
		return comDao.getById(id);
	}

	@Override
	public Committee getCommitteeByGenre(Genre g) {
		return comDao.getByGenre(g);
	}

	@Override
	public void updateCommittee(Committee c) {
		comDao.update(c);

	}

	@Override
	public void deleteCommittee(Committee c) {
		comDao.delete(c);
	}

}
