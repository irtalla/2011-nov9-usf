package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import com.revature.beans.Rejection;
import com.revature.data.RejectionDAO;
import com.revature.data.RejectionDAOFactory;
import com.revature.data.RejectionPostgreSQL;

@TestMethodOrder(OrderAnnotation.class)
class RejectionDAOTest {
	
	private static RejectionDAO rejectionDao;

	@BeforeAll
	public static void setUp() {
		RejectionDAOFactory rejectionDaoFactory = new RejectionDAOFactory();
		rejectionDao = rejectionDaoFactory.getRejectionDAO();
	}
	
	@Test
	@Order(1)
	void testGetById() {
		Rejection r = null;
		r = rejectionDao.getByRejectionID(1);
		//System.out.println(r);
		assertTrue(r != null);
	}
	
	@Test
	@Order(2)
	void testGetByRejectedID() {
		Set<Rejection> rejections = null;
		rejections = rejectionDao.getByRejectedID(16); //user 16 is Charles Dickens, has rejections hard coded in SQL database
		//System.out.println(rejections);
		assertTrue(rejections.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<Rejection> rejections = null;
		rejections = rejectionDao.getAll();
		//System.out.println(rejections);
		assertTrue(rejections.size() > 0);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		Rejection rejection = rejectionDao.getByRejectionID(1);
		Rejection rejection2 = new Rejection();
		rejection2.setRejectionID(rejection.getRejectionID());
		rejection2.setRejectionDate(rejection.getRejectionDate());
		rejection2.setReason(rejection.getReason()+"test");
		rejection2.setRejectedID(rejection.getRejectedID());
		rejection2.setRejectorID(11);
		rejection2.setStoryRejectedID(rejection.getStoryRejectedID());
		//System.out.println(rejection);
		//System.out.println(rejection2);
		rejectionDao.update(rejection2);
		Rejection channgeRejection3 = rejectionDao.getByRejectionID(1);
		//System.out.println(channgeRejection3);
		assertTrue(rejection2.getRejectorID().equals(channgeRejection3.getRejectorID()));
		// undoing update
		rejectionDao.update(rejection);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		Rejection n =  new Rejection();
		n.setReason("Test");
		n.setRejectedID(1);
		n.setRejectorID(1);
		n.setStoryRejectedID(5);
		Integer id = 0;
		Rejection rejection = (Rejection) rejectionDao.add(n);
		id = rejection.getRejectionID();
		//System.out.println(rejection);
		//System.out.println(id);
		assertTrue(id != 0);
		n.setRejectionID(id);
		rejectionDao.delete(n);
		//System.out.println(rejectionDao.getByRejectionID(id));
		assertTrue(rejectionDao.getByRejectionID(id) == null);
	}


}