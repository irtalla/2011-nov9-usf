package com.revature.data;

import java.util.Set;

import com.revature.beans.Role;

public interface RoleDAO extends GenericDAO <Role>{
	public Role add(Role r);
	public Set<Role> getAvailableRoles();
}
