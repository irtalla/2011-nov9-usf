package com.revature.services;

import java.util.Set;

import com.revature.exceptions.NonUniqueRoleException;
import com.revature.models.Role;

public interface RoleService {
	public Integer addRole(Role r) throws NonUniqueRoleException;
	public Role getRoleById(Integer id);
	public Role getRolebyName(String name);
	public Set<Role> getAllRoles();
	public void updateRole(Role r) throws NonUniqueRoleException;
	public void deleteRole(Role r);
}
