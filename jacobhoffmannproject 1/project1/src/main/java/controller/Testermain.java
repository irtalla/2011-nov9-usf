package controller;

import data.Pitch_ApprovalDAO;
import data.Pitch_ApprovalHibernate;
import data.Pitch_Approval_DAOFactory;
import exceptions.NonUniqueUsernameException;
import models.Employee;
import models.Pitch_Approval;
import models.Users;
import servicespackage.employee.Employee_Service;
import servicespackage.employee.Employee_ServiceImpl;
import servicespackage.pitch_approval.Pitch_Approval_Service;
import servicespackage.pitch_approval.Pitch_Approval_ServiceImpl;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class Testermain {
	/*
	public static void main(String[] args) throws NonUniqueUsernameException {
	Pitch_ApprovalHibernate h = new Pitch_ApprovalHibernate();
	Pitch_Approval_DAOFactory pfactory = new Pitch_Approval_DAOFactory();
	Pitch_Approval_Service ps = new Pitch_Approval_ServiceImpl();
	Pitch_ApprovalDAO pdao = pfactory.getDao();
		Pitch_Approval pa = ps.getById(1);
		System.out.println(pa);
		pa.setApproved(false);
		ps.update(pa);
		//Users newuser  = new Users(1, "testqqqqq", "test","test@test.test","837-887-3801");
	///User_Service userv = new User_ServiceImpl();
	//newuser.setId(userv.addUser(newuser));
	//userv.updateUser(newuser);
	//Users dumb = userv.getUserById(1);
	//System.out.println(dumb);
	//	Employee e = new Employee();
	//	Employee_Service empv = new Employee_ServiceImpl();
		//empv.updateEmployee(e);
		//e = empv.getByEmployeeId(1);
		//e.setId(empv.addEmployee(e));
		//Employee en = empv.getByEmployeeId(e.getId());
		//System.out.println();
	}*/
}
