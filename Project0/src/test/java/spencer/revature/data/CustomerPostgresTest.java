package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;

class CustomerPostgresTest {

	/*@Test
	void BasicAdd() {
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		Customer b = new Customer();
		b.setUsername("jimmyjam");
		b.setPassword("passsss");
		custDao.add(b);
	}*/
	@Test
	void getId(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		Customer b = custDao.getById(2);
		
		assertEquals("SpencerCampbell", b.getUsername());
	}
	@Test
	void getUser(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		Customer b = custDao.getByUsername("Store");
		
		assertEquals("password", b.getPassword());
	}
	
	@Test
	void getNoUser(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		Customer b = custDao.getByUsername("Storsse");
		
		assertEquals(b, null);
	}
	@Test
	void getAll(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		
		Set<Customer> custs = custDao.getAll();
		assertTrue(custs.size()>0);
		//assertTrue(custs.size()==3);
	}
	
	@Test
	void updateCustomer(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		

		Customer c = new Customer();
		c.setUsername("THE");
		c.setPassword("pass");
		c.setId(2);
		custDao.update(c);
		
		Customer cust =null; 
		cust=custDao.getById(2);
		
		assertTrue("THE".equals(cust.getUsername()));
	}
	
	@Test
	void getByUser(){
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		
		Customer c =custDao.getByUsername("SpencerCampbell");
		assertTrue("pass".equals(c.getPassword()));
	}
	
	@Test
	void CustomerBike() {
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		Customer c = new Customer();
		c.setId(1);
		Set<Bicycle> bikes = custDao.getCustomersBikes(c);
		assertTrue((bikes.size())>3);
	}
	
	
	@AfterAll 
	 public static void returnName() {
		CustomerDAO custDao;
		CustomerDAOFactory custDaoFactory = new CustomerDAOFactory();
		custDao = custDaoFactory.getCustomerDAO();
		

		Customer c = new Customer();
		c.setUsername("SpencerCampbell");
		c.setPassword("pass");
		c.setId(2);
		custDao.update(c);
	}
	
}
