package com.revature.data;

import com.revature.exceptions.NonUniqueRoleException;
import com.revature.models.Role;

public interface RoleDAO extends GenericDAO<Role> {
	public Role add(Role t) throws NonUniqueRoleException;
	public Role getByName(String name);
	public void update(Role t) throws NonUniqueRoleException;
}
