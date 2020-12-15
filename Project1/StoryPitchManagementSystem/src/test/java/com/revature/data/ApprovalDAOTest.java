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
import com.revature.beans.Approval;
import com.revature.data.ApprovalDAO;
import com.revature.data.ApprovalDAOFactory;
import com.revature.data.ApprovalPostgreSQL;

@TestMethodOrder(OrderAnnotation.class)
class ApprovalDAOTest {
	
	private static ApprovalDAO approvalDao;

	@BeforeAll
	public static void setUp() {
		ApprovalDAOFactory approvalDaoFactory = new ApprovalDAOFactory();
		approvalDao = approvalDaoFactory.getApprovalDAO();
	}
	
	@Test
	@Order(1)
	void testGetById() {
		Approval r = null;
		r = approvalDao.getByApprovalID(1);
		//u = SQLDao.getByApprovalId(1);
		//System.out.println(r);
		assertTrue(r != null);
	}
	
	@Test
	@Order(2)
	void testGetByApprovedID() {
		Set<Approval> approvals = null;
		approvals = approvalDao.getByApprovedID(28); //user 28 is Brandon Sanderson, has approvals hard coded in SQL database
		System.out.println(approvals);
		assertTrue(approvals.size() > 0);
	}
	
	@Test
	@Order(3)
	void testGetAll() {
		Set<Approval> requests = null;
		requests = approvalDao.getAll();
		//System.out.println(requests);
		assertTrue(requests.size() > 1);
	}
	
	@Test
	@Order(4)
	void updateTest() {
		Approval approval = approvalDao.getByApprovalID(1);
		Approval approval2 = new Approval();
		approval2.setApprovalID(approval.getApprovalID());
		approval2.setApprovalDate(approval.getApprovalDate());
		approval2.setStatusApproved(approval.getStatusApproved()+1);
		approval2.setApprovedID(approval.getApprovedID());
		approval2.setApproverID(11);
		approval2.setStoryApprovedID(approval.getStoryApprovedID());
		//System.out.println(approval);
		//System.out.println(approval2);
		approvalDao.update(approval2);
		Approval channgeApproval3 = approvalDao.getByApprovalID(1);
		//System.out.println(channgeApproval3);
		assertTrue(approval2.getApproverID().equals(channgeApproval3.getApproverID()));
		// undoing update
		approvalDao.update(approval);
	}
	
	@Test
	@Order(5)
	void addAndDeleteTest() {
		Approval n =  new Approval();
		n.setStatusApproved(3);
		n.setApprovedID(1);
		n.setApproverID(1);
		n.setStoryApprovedID(5);
		Integer id = 0;
		Approval approval = (Approval) approvalDao.add(n);
		id = approval.getApprovalID();
		//System.out.println(approval);
		//System.out.println(id);
		assertTrue(id != 0);
		n.setApprovalID(id);
		approvalDao.delete(n);
		//System.out.println(approvalDao.getByApprovalID(id));
		assertTrue(approvalDao.getByApprovalID(id) == null);
	}


}