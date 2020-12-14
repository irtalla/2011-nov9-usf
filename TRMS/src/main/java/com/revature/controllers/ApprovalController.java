package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.services.ApprovalService;
import com.revature.services.ApprovalServiceImpl;

import io.javalin.http.Context;

public class ApprovalController {
	private static ApprovalService appServ = new ApprovalServiceImpl();
	
	public static void getApprovalById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval evt = appServ.getApprovalById(id);
		
		if (evt != null) 
		{
			ctx.status(200);
			ctx.json(evt);
		} 
		else 
		{
			ctx.status(404);
		}
	}
	
	public static void getAllApprovals(Context ctx)
	{
		Set<Approval> results = appServ.getApprovals();
		if (results != null)
		{
			ctx.status(200);
			ctx.json(results);
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void addApproval(Context ctx)
	{
		Approval evt = ctx.bodyAsClass(Approval.class);
		int id = appServ.addApproval(evt);
		ctx.json(id);
		ctx.status(201);
	}
	
	public static void deleteApproval(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval evt = appServ.getApprovalById(id);
		appServ.removeApproval(evt);
		if (evt != null) 
		{
			ctx.status(200);
			ctx.json(evt);
		} 
		else 
		{
			ctx.status(404);
		}
	}

}
