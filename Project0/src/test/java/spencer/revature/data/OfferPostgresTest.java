package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;

class OfferPostgresTest {

	/*@Test
	void BasicAdd() {
		OfferDAO offDao;
		OfferDAOFactory offDaoFactory = new OfferDAOFactory();
		offDao = offDaoFactory.getOfferDAO();
		Offer o = new Offer();
		o.setAmount(85.99);
		Bicycle b = new Bicycle();
		b.setId(3);
		o.setBicycle(b);
		OfferStatus os = new OfferStatus();
		os.setId(1);
		o.setOfferStatus(os);
		Employee e = new Employee();
		e.setId(1);
		o.setEmployee(e);
		Customer c = new Customer();
		c.setId(1);
		o.setCustomer(c);
		offDao.add(o);
	}*/
	
	@Test
	void AddDeleteGetAll() {
		OfferDAO offDao;
		OfferDAOFactory offDaoFactory = new OfferDAOFactory();
		offDao = offDaoFactory.getOfferDAO();
		
		Set<Offer> offs = offDao.getAll();
		assertTrue(offs.size()>0); //check to see if anything was grabbed
		
		Offer o = new Offer();
		o.setAmount(20.20);
		Bicycle b = new Bicycle();
		b.setId(3);
		o.setBicycle(b);
		OfferStatus os = new OfferStatus();
		os.setId(1);
		o.setOfferStatus(os);
		Employee e = new Employee();
		e.setId(1);
		o.setEmployee(e);
		Customer c = new Customer();
		c.setId(1);
		o.setCustomer(c);
		offDao.add(o);
		assertTrue(offs.size()<offDao.getAll().size());//Check to see if something was added
		offDao.delete(o);
		assertTrue(offs.size()==offDao.getAll().size());//check to see if it's been removed
	}
	
	@Test
	void getID() {
		OfferDAO offDao;
		OfferDAOFactory offDaoFactory = new OfferDAOFactory();
		offDao = offDaoFactory.getOfferDAO();
		Offer o = offDao.getById(3);
		
		assertTrue(o.getAmount()==85.99);
	
	}
	@Test
	void update() {
		OfferDAO offDao;
		OfferDAOFactory offDaoFactory = new OfferDAOFactory();
		offDao = offDaoFactory.getOfferDAO();
		
		Offer off = offDao.getById(2);
		off.setAmount(89.872);
		OfferStatus os = new OfferStatus();
		os.setId(2);
		off.setOfferStatus(os);
		
		offDao.update(off);
		Offer o = offDao.getById(off.getId());
		assertTrue(o.getAmount()==89.87);
	
	}


}
