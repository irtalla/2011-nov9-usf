package dev.rev.services;

import dev.rev.beans.employee;
import dev.rev.data.employeeDAO;
import dev.rev.data.employeeDAOFactory;
import dev.rev.exception.NonUniqueUsernameException;

public class employeeServiceImp implements employeeService
{
	private employeeDAO edao;
	
	public employeeServiceImp() {
		employeeDAOFactory efac=new employeeDAOFactory();
		edao=efac.getemployeeDAO();
	}

	@Override
	public Integer addPerson(employee p) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		try {
			return edao.add(p).getEmp_id();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return null;
		}
		
	}

	@Override
	public employee getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return edao.getById(id);
	}

	@Override
	public employee getPersonByUsername(String username) {
		// TODO Auto-generated method stub
		System.out.println("asdasdasd");
		return edao.getbyusername(username);
	}

	@Override
	public void updateemployee(employee emp) {
		// TODO Auto-generated method stub
		edao.update(emp);
	}

}
