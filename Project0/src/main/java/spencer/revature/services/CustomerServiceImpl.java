package spencer.revature.services;

import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;
import spencer.revature.data.BicycleDAO;
import spencer.revature.data.BicycleDAOFactory;
import spencer.revature.data.CustomerDAO;
import spencer.revature.data.CustomerDAOFactory;

public class CustomerServiceImpl implements CustomerService {


		private BicycleDAO bikeDao;
		private CustomerDAO customerDao;
		
		public CustomerServiceImpl() {
			BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
			bikeDao = bikeDaoFactory.getBicycleDAO();
			
			CustomerDAOFactory customerDaoFactory = new CustomerDAOFactory();
			customerDao = customerDaoFactory.getCustomerDAO();
			
		}

		@Override
		public Integer addCustomer(Customer c) {
			return customerDao.add(c).getId();
		}

		@Override
		public Customer getCustomerById(Integer id) {
			
			return customerDao.getById(id);
		}

		@Override
		public Set<Customer> getCustomers() {

			return customerDao.getAll();
		}
		@Override
		public void updateCustomer(Customer c) {

			 customerDao.update(c);
			
		}

		@Override
		public void removeCustomer(Customer c) {
			customerDao.delete(c);
			
		}

		@Override
		public Customer getCustomerByUsername(String username) {
			return customerDao.getByUsername(username);
		}
		@Override
		public Set<Bicycle> getCustomerBikes(Customer c){
			return customerDao.getCustomersBikes(c);
		}
		

}
