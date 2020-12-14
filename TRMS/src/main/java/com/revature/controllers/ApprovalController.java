package com.revature.controllers;

import java.io.IOException;
import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.ApprovalFile;
import com.revature.services.ApprovalService;
import com.revature.services.ApprovalServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

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
	
	public static void addApprovalFile(Context ctx)
	{
		UploadedFile f = ctx.uploadedFile(ctx.queryParam("name"));
		
		if(f != null)
		{
			ctx.status(200);
		}
		else
		{
			ctx.status(404);
			System.out.println("File not found!");
			return;
		}
		
		ApprovalFile file = new ApprovalFile();
		file.setName(f.getFilename());
		try {
			file.setData(new byte[f.getContent().available()]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			f.getContent().read(file.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.setFormId(Integer.parseInt(ctx.queryParam("formid")));
		
		Integer newId = appServ.addApprovalFile(file);
		ctx.json(newId);
	}
	
	public static void getApprovalFileByFormId(Context ctx)
	{
		Set<ApprovalFile> result = appServ.getApprovalFilesByFormId(Integer.parseInt(ctx.queryParam("formid")));
		if (result != null)
		{
			ctx.json(result);
			ctx.status(200);
		}
		else
		{
			ctx.status(404);
		}
	}

}
