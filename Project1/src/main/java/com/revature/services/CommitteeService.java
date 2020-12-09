package com.revature.services;

import java.util.Set;

import com.revature.exceptions.NonUniqueCommitteeException;
import com.revature.models.Committee;
import com.revature.models.Genre;
import com.revature.models.Role;
import com.revature.models.User;

public interface CommitteeService {
	public Integer addCommittee(Committee t) throws NonUniqueCommitteeException;
	public Committee getCommitteeById(Integer id);
	public Committee getComitteeByGenre(Genre genre);
	public Set<User> getCommitteeEditorsByRole(Committee t, Role role);
	public Set<User> getCommitteeEditors(Committee t);
	public Set<Committee> getAllCommittees();
	public void updateCommittee(Committee t) throws NonUniqueCommitteeException;
	public void deleteCommittee(Committee t);
	
}
