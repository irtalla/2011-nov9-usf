package com.revature.controllers;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class ReimbursementController {
	private static ReimbursementService reimbursementServ = new ReimbursementServiceImpl();

	public static Set<Reimbursement> getAll(Context ctx){
		Set<Reimbursement> allReimbursements = new HashSet<>();
		allReimbursements = reimbursementServ.getAll();
		if (allReimbursements != null) {
			ctx.status(200);
			ctx.json(allReimbursements);
		} else {
			ctx.status(404);
		}
		return allReimbursements;
	}; // get all reimbursements
	
	public static Integer addReimbursement(Context ctx) {
		Integer retValue = 0;
		Reimbursement rToAdd = new Reimbursement();
		
		rToAdd = ctx.bodyAsClass(Reimbursement.class);
		rToAdd.setBenco_approved(false);
		rToAdd.setDepartment_head_approved(false);
		rToAdd.setSupervisor_approved(false);
		
		try{
			retValue = reimbursementServ.addReimbursement(rToAdd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		if (retValue > 0) {
				ctx.status(200);
				ctx.sessionAttribute("id", retValue);
				ctx.json(retValue);
				return retValue;
			}
			else
			{
				ctx.status(400);
				return retValue;
			}
	}; // add a new reimbursement

	public static Set<Reimbursement> getById(Context ctx){
		Set<Reimbursement> userReimbursements = new HashSet<>();
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		
		userReimbursements = reimbursementServ.getReimbursementById(id);
		
		if (userReimbursements != null) {
			ctx.status(200);
			ctx.json(userReimbursements);
			return userReimbursements;
		} else {
			ctx.status(404);
			return userReimbursements;
		}
	}; 

	
	public static Set<String> getAttachmentsById(Context ctx){
		Set<String> attachments = new HashSet<>();
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		
		attachments = reimbursementServ.getAttachmentsById(id);
		
		if (attachments != null) {
			ctx.status(200);
			ctx.json(attachments);
			return attachments;
		} else {
			ctx.status(404);
			return attachments;
		}
	}
	public static Reimbursement addMessage(Context ctx) {
		Reimbursement reimbursement = new Reimbursement();
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		String message = ctx.queryParam("message");
		
		reimbursement = reimbursementServ.addMessageToReimbursement(id, message);
		
		if (reimbursement != null) {
			ctx.status(200);
			ctx.json(reimbursement);
			return reimbursement;
		} else {
			ctx.status(404);
			return reimbursement;
		}
	}
	public static Reimbursement getReimbursementById(Context ctx){
		Reimbursement reimbursement = new Reimbursement();
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		
		reimbursement = reimbursementServ.getSingleReimbursementById(id);
		
		if (reimbursement != null) {
			ctx.status(200);
			ctx.json(reimbursement);
			return reimbursement;
		} else {
			ctx.status(404);
			return reimbursement;
		}
	};
	
	public static void supapprove(Context ctx){
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Integer retValue;
		retValue = reimbursementServ.approve(id, 1);

		if (retValue > 0) {
			ctx.status(200);
			ctx.json(retValue);
		} else {
			ctx.status(404);
		}
	}
	public static void approve(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Integer retValue;
		retValue = reimbursementServ.approve(id, 4);

		if (retValue > 0) {
			ctx.status(200);
			ctx.json(retValue);
		} else {
			ctx.status(404);
		}
	}
	public static void benapprove(Context ctx){
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Integer retValue;
		retValue = reimbursementServ.approve(id, 2);

		if (retValue > 0) {
			ctx.status(200);
			ctx.json(retValue);
		} else {
			ctx.status(404);
		}
	}
	public static void headapprove(Context ctx){
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Integer retValue;
		retValue = reimbursementServ.approve(id, 3);

		if (retValue > 0) {
			ctx.status(200);
			ctx.json(retValue);
		} else {
			ctx.status(404);
		}
	}
}