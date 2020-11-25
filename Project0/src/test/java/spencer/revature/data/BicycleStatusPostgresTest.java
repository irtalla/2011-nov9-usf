package spencer.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import spencer.revature.beans.BicycleStatus;

class BicycleStatusPostgresTest {


	@Test
	void BasicAdd() {
		BicycleStatusDAO bikeDao;
		BicycleStatusDAOFactory bikeStatusDaoFactory = new BicycleStatusDAOFactory();
		bikeDao = bikeStatusDaoFactory.getBicycleStatusDAO();
		BicycleStatus s= new BicycleStatus();
		s.setStatus("Available");
		bikeDao.add(s);
		
	}

}
