package com.revature.controller;

import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;
import com.revature.services.ProductService;
import com.revature.services.ProductServiceImpl;

public class ManagerController extends EmployeeController {
	
	static PersonService personService = null; 
	
	private static EmployeeController managerController = null;
	
	
	protected ManagerController() {
		super(); 
		ManagerController.personService = new PersonServiceImpl();
	}
	public static EmployeeController getEmployeeController() {
		
		if (ManagerController.managerController == null) {
			ManagerController.managerController =  new ManagerController();
		}
		
		return ManagerController.managerController; 
	}
	
	
}

/*


- As a manager, I can make employee accounts.
- As a manager, I can fire employees.
- As a manager, I can view sales history of all offers.

*/