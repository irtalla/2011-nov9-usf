package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Approval;

public class ApprovalDAOTest {
	ApprovalDAO dao = DAOFactory.getApprovalDAO();
	
	@Test
	public void testAddApproval()
	{
		Approval a = new Approval();
		a.setApproverId(2);
		a.setFormId(1);
		a.setMessage("what?");
		a.setStatusId(2);
		
		Approval b = dao.add(a);
		System.out.println(a);
		System.out.println(b);
		assertTrue(b.getId() != -1);
		
		dao.delete(b);
		
		Set<Approval> s = dao.getAll();
		
		assertFalse(s.contains(b));
		
		
	}

}
