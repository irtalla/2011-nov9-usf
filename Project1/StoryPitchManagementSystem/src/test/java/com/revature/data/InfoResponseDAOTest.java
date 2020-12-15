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
import com.revature.beans.InfoResponse;
import com.revature.data.InfoResponseDAO;
import com.revature.data.InfoResponseDAOFactory;
import com.revature.data.InfoResponsePostgreSQL;

@TestMethodOrder(OrderAnnotation.class)
class InfoResponseDAOTest {
	
	private static InfoResponseDAO infoResponseDao;

	@BeforeAll
	public static void setUp() {
		InfoResponseDAOFactory infoResponseDaoFactory = new InfoResponseDAOFactory();
		infoResponseDao = infoResponseDaoFactory.getInfoResponseDAO();
	}
	
	@Test
	@Order(1)
	void testGetByResponseId() {
		InfoResponse r = null;
		r = infoResponseDao.getByResponseID(1);
		//System.out.println(r);
		assertTrue(r != null);
	}
	
	@Test
	@Order(2)
	void testGetByEditorID() {
		Set<InfoResponse> responses = null;
		responses = infoResponseDao.getByRespondedID(1);
		//System.out.println(responses);
		assertTrue(responses.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<InfoResponse> responses = null;
		responses = infoResponseDao.getAll();
		//System.out.println(responses);
		assertTrue(responses.size() > 0);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		InfoResponse infoResponse = (InfoResponse) infoResponseDao.getByResponseID(1);
		InfoResponse infoResponse2 = new InfoResponse();
		infoResponse2.setResponseID(infoResponse.getResponseID());
		infoResponse2.setResponseDate(infoResponse.getResponseDate());
		infoResponse2.setRequestID(infoResponse.getRequestID());
		infoResponse2.setMessage(infoResponse.getMessage()+"test");
		infoResponse2.setStoryID(infoResponse.getStoryID());
		infoResponse2.setPersonRespondingID(infoResponse.getPersonRespondingID());
		infoResponse2.setPersonRespondedID(infoResponse.getPersonRespondedID());
		//System.out.println(infoResponse);
		//System.out.println(infoResponse2);
		infoResponseDao.update(infoResponse2);
		InfoResponse channgeResponse3 = (InfoResponse) infoResponseDao.getByResponseID(1);
		//System.out.println(channgeResponse3);
		assertTrue(infoResponse2.getMessage().equals(channgeResponse3.getMessage()));
		// undoing update
		infoResponseDao.update(infoResponse);
		//System.out.println(infoResponse);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		InfoResponse n =  new InfoResponse();
		n.setMessage("test");
		n.setStoryID(1);
		n.setRequestID(1);
		n.setPersonRespondingID(33);
		n.setPersonRespondedID(1);
		Integer id = 0;
		InfoResponse r = infoResponseDao.add(n);
		id = r.getResponseID();
		//System.out.println(r);
		//System.out.println(id);
		assertTrue(id != 0);
		n.setResponseID(id);
		infoResponseDao.delete(n);
		//System.out.println(infoResponseDao.getByResponseID(id));
		assertTrue(((InfoResponse) infoResponseDao.getByResponseID(id)).getResponseID() != id);
	}


}