package com.revature.services;

import java.util.Set;

import com.revature.data.CommitteeDAO;
import com.revature.data.CommitteeDAOFactory;
import com.revature.exceptions.NonUniqueCommitteeException;
import com.revature.models.Committee;
import com.revature.models.Genre;
import com.revature.models.Role;
import com.revature.models.User;

public class CommitteeServiceImpl implements CommitteeService {
	private CommitteeDAO committeeDao;
	
	public CommitteeServiceImpl() {
		CommitteeDAOFactory committeeFactory = new CommitteeDAOFactory();
		committeeDao = committeeFactory.getCommitteeDao();
	}
	
	@Override
	public Integer addCommittee(Committee t) throws NonUniqueCommitteeException {
		return committeeDao.add(t);
	}

	@Override
	public Committee getCommitteeById(Integer id) {
		return committeeDao.getById(id);
	}

	@Override
	public Committee getComitteeByGenre(Genre genre) {
		return committeeDao.getByGenre(genre);
	}

	@Override
	public Set<User> getCommitteeEditorsByRole(Role role) {
		return committeeDao.getCommitteeEditorsByRole(role);
	}

	@Override
	public Set<User> getCommitteeEditors() {
		return committeeDao.getCommitteeEditors();

	}

	@Override
	public Set<Committee> getAllCommittees() {
		return committeeDao.getAll();
	}

	@Override
	public void updateCommittee(Committee t) throws NonUniqueCommitteeException {
		committeeDao.update(t);
	}

	@Override
	public void deleteCommittee(Committee t) {
		committeeDao.delete(t);
	}

}
