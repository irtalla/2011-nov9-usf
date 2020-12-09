package com.revature.data;

import java.util.Set;

import com.revature.exceptions.NonUniqueCommitteeException;
import com.revature.models.Committee;
import com.revature.models.Genre;
import com.revature.models.Role;
import com.revature.models.User;

public interface CommitteeDAO extends GenericDAO<Committee> {
	public Integer add(Committee t) throws NonUniqueCommitteeException;
	public Committee getByGenre(Genre genre);
	public Set<User> getCommitteeEditorsByRole(Committee t, Role role);
	public Set<User> getCommitteeEditors(Committee t);
	public void update(Committee t) throws NonUniqueCommitteeException;
}
