package com.revature.services;

import java.util.Set;

import com.revature.data.RoleDAO;
import com.revature.data.RoleDAOFactory;
import com.revature.exceptions.NonUniqueRoleException;
import com.revature.models.Role;

public class RoleServiceImpl implements RoleService {
	private RoleDAO roleDao;
	
	public RoleServiceImpl() {
		RoleDAOFactory roleFactory = new RoleDAOFactory();
		roleDao = roleFactory.getRoleDao();
	}
	
	@Override
	public Integer addRole(Role r) throws NonUniqueRoleException {
		return roleDao.add(r).getId();
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleDao.getById(id);
	}

	@Override
	public Role getRolebyName(String name) {
		return roleDao.getByName(name);
	}

	@Override
	public Set<Role> getAllRoles() {
		return roleDao.getAll();
	}

	@Override
	public void updateRole(Role r) throws NonUniqueRoleException {
		roleDao.update(r);
	}

	@Override
	public void deleteRole(Role r) {
		roleDao.delete(r);
	}

}
