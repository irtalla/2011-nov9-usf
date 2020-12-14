package com.revature.controllers;

import java.util.Set;

import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.services.ReimbursementFormService;
import com.revature.services.ReimbursementFormServiceImpl;

import io.javalin.http.Context;

public class ReimbursementFormController {
	private static ReimbursementFormService serv = new ReimbursementFormServiceImpl();
	
	public static void getReimbursementFormById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		ReimbursementForm evt = serv.getReimbursementFormById(id);
		
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
	
	public static void getStageById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Stage st = serv.getStageById(id);
		//System.out.println("Stage request id = " + id);
		
		if (st != null)
		{
			ctx.status(200);
			ctx.json(st);
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void getStatusById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Status st = serv.getStatusById(id);
		
		if (st != null)
		{
			ctx.status(200);
			ctx.json(st);
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void getGradingFormatById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		GradingFormat gf = serv.getGradingFormatById(id);
		
		if (gf != null)
		{
			ctx.status(200);
			ctx.json(gf);
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void getAllReimbursementForms(Context ctx)
	{
		Set<ReimbursementForm> results = serv.getReimbursementForms();
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
	
	public static void getAllGradingFormats(Context ctx)
	{
		Set<GradingFormat> results = serv.getGradingFormats();
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
	
	public static void addReimbursementForm(Context ctx)
	{
		ReimbursementForm evt = ctx.bodyAsClass(ReimbursementForm.class);
		int id = serv.addReimbursementForm(evt);
		evt.setStage(serv.getStageById(evt.getStage().getId()));
		evt.setStatus(serv.getStatusById(evt.getStatus().getId()));
		ctx.json(id);
		ctx.status(201);
	}
	
	public static void updateReimbursementForm(Context ctx)
	{
		ReimbursementForm form = ctx.bodyAsClass(ReimbursementForm.class);
		form.setStage(serv.getStageById(form.getStage().getId()));
		form.setStatus(serv.getStatusById(form.getStatus().getId()));
		System.out.println(form);
		serv.updateReimbursementForm(form);
		ctx.status(200);
	}
	
	public static void deleteReimbursementForm(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		ReimbursementForm evt = serv.getReimbursementFormById(id);
		serv.removeReimbursementForm(evt);
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
