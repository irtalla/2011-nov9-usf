package com.revature.ReimbursementTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

import com.revature.beans.Event;
import com.revature.beans.EventType;
import com.revature.beans.Individual;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.controllers.IndividualController;
import com.revature.services.IndividualService;
import com.revature.services.IndividualServiceImpl;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

import io.javalin.http.Context;

public class ReimbursementTests {
	ReimbursementService rServ = new ReimbursementServiceImpl();
	IndividualService iServ = new IndividualServiceImpl();

	Integer newItemId;

	@Test
    public void test_addReimbursement() throws Exception {
		
//		Reimbursement r = new Reimbursement();
//		r.setAmount(999);
//		r.setBenco_approved(false);
//		r.setSupervisor_approved(false);
//		r.setDepartment_head_approved(false);
//		Status status = new Status();
//		status.setId(1);
//		status.setName("");
//		r.setStatus(status);
//		Individual emp = new Individual();
//		emp = iServ.getIndividualById(1);
//		r.setEmployee(emp);
//		Event event = new Event();
//		event.setBegin_date("");
//		event.setCost(r.getAmount());
//		event.setDescription("");
//		event.setGrading_format("A-F");
//		event.setLocation("");
//		event.setTime("");
//		EventType type = new EventType();
//		type.setId(1);
//		type.setName("university");
//		event.setType(type);
//		r.setEvent(event);
//		
//		//public Integer addReimbursement(Reimbursement r)
//		newItemId = rServ.addReimbursement(r);
//		
//		
//		//Set<String> getAttachmentsById(Integer id)
//		Set<String> attachments = rServ.getAttachmentsById(41);
//		assertTrue(attachments.size() > 0);
//		
//		//getSingleReimbursementById(Integer id) {
//		Reimbursement r2 = rServ.getSingleReimbursementById(newItemId);
//		assertEquals(r2.getAmount(), r.getAmount());
//		
//		//getReimbursementById(Integer id)
//		Set<Reimbursement> r3 = rServ.getReimbursementById(1);
//		assertTrue(r3 != null);
//		
//
////		public Set<Reimbursement> getAll() {
//		Set<Reimbursement> reimbursements = rServ.getAll();
//		assertTrue(reimbursements.size() > 0);
//
////		public Integer approve(Integer id, Integer n) {
//		rServ.approve(newItemId, 1);//supervisor approval
//		
//		//getSingleReimbursementById(Integer id) {
//		r = rServ.getSingleReimbursementById(newItemId);
//		
//		
//		assertTrue(r.getSupervisor_approved());	
    }

	
}
