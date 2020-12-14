package com.revature.ReimbursementTests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

import com.revature.beans.Event;
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
	/*


public Set<String> getAttachmentsById(Integer id){
public Set<Reimbursement> getAll() {
public Reimbursement getSingleReimbursementById(Integer id) {
public Reimbursement addMessageToReimbursement(Integer id, String message) {
public Integer approve(Integer id, Integer n) {

	 * 
	 * 
	 * 	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="event")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="employee")
	private Individual employee;
	
	private Integer amount;
	private Boolean benco_approved;
	private Boolean supervisor_approved;
	private Boolean head_approved;
	private String message;
	 * 
	 * */
	Integer newItemId;
	//public Integer addReimbursement(Reimbursement r) throws Exception {
	@Test
    public void test_addReimbursement() throws Exception {
		Reimbursement r = new Reimbursement();
		r.setAmount(999999999);
		r.setBenco_approved(false);
		r.setSupervisor_approved(false);
		r.setDepartment_head_approved(false);
		Status status = new Status();
		status.setId(1);
		status.setName("");
		
		newItemId = rServ.addReimbursement(r);
    }
	
	//public Set<Reimbursement> getReimbursementById(Integer id) {
//	@Test
//    public void test_getReimbursementById() {
//		rServ.getReimbursementById(id)
//    }
	
}
