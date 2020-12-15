package com.trms.data;

import com.trms.beans.Role;

public interface RoleDAO extends GenericDAO<Role> {
	public Role add(Role r);
	public Role getByName(String name);

}
