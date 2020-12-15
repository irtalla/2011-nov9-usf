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
import com.revature.beans.ChangeRequest;
import com.revature.data.ChangeRequestDAO;
import com.revature.data.ChangeRequestDAOFactory;
import com.revature.data.ChangeRequestPostgreSQL;


class ChangeRequestDAOTest {
	
	private static ChangeRequestDAO changeRequestDao;

	@BeforeAll
	public static void setUp() {
		ChangeRequestDAOFactory changeRequestDaoFactory = new ChangeRequestDAOFactory();
		changeRequestDao = changeRequestDaoFactory.getChangeRequestDAO();
	}
	
//	@Test
//	@Order(1)
//	void testGetById() {
//		ChangeRequest r = null;
//		r = changeRequestDao.getChangeRequestByID(1);
//		//u = SQLDao.getByChangeRequestId(1);
//		//System.out.println(u);
//		assertTrue(u != null);
//	}
	
	@Test
	@Order(2)
	void testGetByRequestedID() {
		Set<ChangeRequest> requests = null;
		requests = changeRequestDao.getByRequestedID(33);
		//System.out.println(u);
		assertTrue(requests.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<ChangeRequest> requests = null;
		requests = changeRequestDao.getAll();
		//System.out.println(requests);
		assertTrue(requests.size() > 1);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		ChangeRequest changeRequest = (ChangeRequest) changeRequestDao.getByRequestID(1);
		ChangeRequest changeRequest2 = new ChangeRequest();
		changeRequest2.setRequestID(changeRequest.getRequestID());
		changeRequest2.setMessage(changeRequest.getMessage()+"test");
		changeRequest2.setRequestDate(changeRequest.getRequestDate());
		changeRequest2.setStoryID(changeRequest.getStoryID());
		changeRequest2.setPersonRequestedID(changeRequest2.getPersonRequestedID());
		changeRequest2.setPersonRequestingID(changeRequest.getPersonRequestingID());
		//System.out.println(changeRequest);
		//System.out.println(changeRequest2);
		changeRequestDao.update(changeRequest2);
		ChangeRequest channgeRequest3 = (ChangeRequest) changeRequestDao.getByRequestID(1);
		//System.out.println(channgeRequest3);
		assertTrue(changeRequest2.getMessage().equals(channgeRequest3.getMessage()));
		// undoing update
		changeRequestDao.update(changeRequest);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		ChangeRequest n =  new ChangeRequest();
		n.setMessage("test");
		n.setStoryID(1);
		n.setPersonRequestingID(1);
		n.setPersonRequestedID(33);
		Integer id = 0;
		ChangeRequest cr = (ChangeRequest) changeRequestDao.add(n);
		id = cr.getRequestID();
		//System.out.println(cr);
		//System.out.println(id);
		assertTrue(id != 0);
		n.setRequestID(id);
		changeRequestDao.delete(n);
		//System.out.println(changeRequestDao.getByRequestID(id));
		assertTrue(((ChangeRequest) changeRequestDao.getByRequestID(id)).getRequestID() != id);
	}


}
