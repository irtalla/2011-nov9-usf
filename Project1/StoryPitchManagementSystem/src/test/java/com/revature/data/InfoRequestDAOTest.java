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
import com.revature.beans.InfoRequest;
import com.revature.data.InfoRequestDAO;
import com.revature.data.InfoRequestDAOFactory;
import com.revature.data.InfoRequestPostgreSQL;

@TestMethodOrder(OrderAnnotation.class)
class InfoRequestDAOTest {
	
	private static InfoRequestDAO infoRequestDao;

	@BeforeAll
	public static void setUp() {
		InfoRequestDAOFactory infoRequestDaoFactory = new InfoRequestDAOFactory();
		infoRequestDao = infoRequestDaoFactory.getInfoRequestDAO();
	}
	
//	@Test
//	@Order(1)
//	void testGetById() {
//		InfoRequest r = null;
//		r = infoRequestDao.getInfoRequestByID(1);
//		//u = SQLDao.getByInfoRequestId(1);
//		//System.out.println(u);
//		assertTrue(u != null);
//	}
	
	@Test
	@Order(2)
	void testGetByAuthorID() {
		Set<InfoRequest> requests = null;
		requests = infoRequestDao.getByRequestedID(33);
		//System.out.println(u);
		assertTrue(requests.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<InfoRequest> requests = null;
		requests = infoRequestDao.getAll();
		//System.out.println(requests);
		assertTrue(requests.size() > 1);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		InfoRequest infoRequest = (InfoRequest) infoRequestDao.getByRequestID(1);
		InfoRequest infoRequest2 = new InfoRequest();
		infoRequest2.setRequestID(infoRequest.getRequestID());
		infoRequest2.setMessage(infoRequest.getMessage()+"test");
		infoRequest2.setRequestDate(infoRequest.getRequestDate());
		infoRequest2.setStoryID(infoRequest.getStoryID());
		infoRequest2.setPersonRequestedID(infoRequest.getPersonRequestedID());
		infoRequest2.setPersonRequestingID(infoRequest.getPersonRequestingID());
		//System.out.println(infoRequest);
		//System.out.println(infoRequest2);
		infoRequestDao.update(infoRequest2);
		InfoRequest channgeRequest3 = (InfoRequest) infoRequestDao.getByRequestID(1);
		//System.out.println(channgeRequest3);
		assertTrue(infoRequest2.getMessage().equals(channgeRequest3.getMessage()));
		// undoing update
		infoRequestDao.update(infoRequest);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		InfoRequest n =  new InfoRequest();
		n.setMessage("test");
		n.setStoryID(1);
		n.setPersonRequestingID(1);
		n.setPersonRequestedID(33);
		Integer id = 0;
		InfoRequest cr = (InfoRequest) infoRequestDao.add(n);
		id = cr.getRequestID();
		//System.out.println(cr);
		//System.out.println(id);
		assertTrue(id != 0);
		n.setRequestID(id);
		infoRequestDao.delete(n);
		//System.out.println(infoRequestDao.getByRequestID(id));
		assertTrue(((InfoRequest) infoRequestDao.getByRequestID(id)).getRequestID() != id);
	}


}