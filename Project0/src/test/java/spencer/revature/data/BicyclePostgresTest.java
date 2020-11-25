package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;

class BicyclePostgresTest {
//Lots of manual checking with these postgres methods
	/*@Test
	void BasicAdd() {
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		Bicycle b = new Bicycle();
		b.setModel("MountainMan");
		Customer o= new Customer();
		o.setId(1);
		b.setOwner(o);
		BicycleStatus s= new BicycleStatus();
		s.setId(1);
		b.setAvailable(s);
		bikeDao.add(b);
		
	}*/
	
	@BeforeAll
	public static void SetDelete() {
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		Bicycle b = new Bicycle();
		b.setModel("Gorgejumperxqxq");
		Customer o= new Customer();
		o.setId(1);
		b.setOwner(o);
		BicycleStatus s= new BicycleStatus();
		s.setId(1);
		b.setAvailable(s);
		bikeDao.add(b);
	}
	
	@Test
	void delete() {
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		Bicycle deleter= null;
		Set<Bicycle> bikes = bikeDao.getAll();
		for(Bicycle b : bikes){
			if(b.getModel().contentEquals("Gorgejumperxqxq"))
				deleter=b;
		}
		assertTrue(deleter!=null);
		bikeDao.delete(deleter);
		assertTrue(bikes.size()-1==bikeDao.getAll().size());
	}
	
	@Test
	void getId(){
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		
		Bicycle b = bikeDao.getById(3);
		
		assertEquals("Thunder", b.getModel());
	}
	
	@Test
	void getAllBikes(){
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		
		Set<Bicycle> bikes = bikeDao.getAll();
		assertTrue(bikes.size()>0);
	}
	
	@Test
	void getAvailableBikes(){
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		
		Set<Bicycle> bikes = bikeDao.getAvailableBicycles();
		assertTrue(bikes.size()>0);
	}
	@Test
	void updateBike(){
		BicycleDAO bikeDao;
		BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
		bikeDao = bikeDaoFactory.getBicycleDAO();
		

		Bicycle b = new Bicycle();
		b.setModel("mountainMEN");
		b.setId(2);
		BicycleStatus s = new BicycleStatus();
		s.setId(1);
		b.setAvailable(s);
		Customer c = new Customer();
		c.setId(1);
		b.setOwner(c);
		bikeDao.update(b);
		
		Bicycle bike =null; 
		bike=bikeDao.getById(2);
		
		assertTrue("mountainMEN".equals(bike.getModel()));
	}
	
	 @AfterAll 
	 public static void returnName() {
		 BicycleDAO bikeDao;
			BicycleDAOFactory bikeDaoFactory = new BicycleDAOFactory();
			bikeDao = bikeDaoFactory.getBicycleDAO();
			

			Bicycle b = new Bicycle();
			b.setModel("Mountainman");
			b.setId(2);
			BicycleStatus s = new BicycleStatus();
			s.setId(1);
			b.setAvailable(s);
			Customer c = new Customer();
			c.setId(1);
			b.setOwner(c);
			bikeDao.update(b);
   }
	
}

