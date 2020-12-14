package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.InformationRequest;

public class InformationRequestDAOTest {
	private InformationRequestDAO dao = DAOFactory.getInformationRequestDAO();
	@Test
	public void testAdd()
	{
		InformationRequest i = new InformationRequest();
		
		i.setFormId(1);
		i.setMessage("a message");
		i.setResponse("a response");
		i.setTargetId(1);
		i.setRequestorId(2);
		i.setStatus(DAOFactory.getReimbursementFormDAO().getStatusById(1));
		
		dao.add(i);
		
		assertTrue(i.getId() != -1);
		
		dao.delete(i);
		
		Set<InformationRequest> set = dao.getAll();
		
		assertFalse(set.contains(i));
	}
	
	@Test
	public void testUpdate()
	{
		InformationRequest i = new InformationRequest();
		
		i.setFormId(1);
		i.setMessage("a message");
		i.setResponse("a response");
		i.setRequestorId(2);
		i.setTargetId(1);
		i.setStatus(DAOFactory.getReimbursementFormDAO().getStatusById(1));
		
		dao.add(i);
		
		i.setResponse("another response");
		dao.update(i);
		
		assertEquals(i.getResponse(),"another response");
		dao.delete(i);
	}

}
