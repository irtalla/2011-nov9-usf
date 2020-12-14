package com.revature.controllers;

import java.io.IOException;
import java.util.Set;

import com.revature.beans.GradePresentationFile;
import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementChangeNotification;
import com.revature.beans.ReimbursementForm;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.services.ReimbursementFormService;
import com.revature.services.ReimbursementFormServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

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
		serv.updateReimbursementForm(form);
		ctx.status(200);
	}
	
	public static void deleteReimbursementForm(Context ctx)
	{
		ReimbursementForm evt = ctx.bodyAsClass(ReimbursementForm.class);
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
	
	public static void addGradePresentationFile(Context ctx)
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
		
		GradePresentationFile file = new GradePresentationFile();
		file.setFileName(f.getFilename());
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
		
		Integer newId = serv.addGradePresentationFile(file);
		ctx.json(newId);
		
		
	}
	
	public static void getGradePresentationFileByFormId(Context ctx)
	{
		Set<GradePresentationFile> result = serv.getGradePresentionFileByFormId(Integer.parseInt(ctx.queryParam("formid")));
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
	
	public static void addReimbursementChangeNotification(Context ctx)
	{
		ReimbursementChangeNotification notif = ctx.bodyAsClass(ReimbursementChangeNotification.class);
		int id = serv.addReimbursementChangeNotification(notif);
		ctx.json(id);
		ctx.status(200);
	}
	
	public static void updateReimbursementChangeNotification(Context ctx)
	{
		ReimbursementChangeNotification notif = ctx.bodyAsClass(ReimbursementChangeNotification.class);
		notif.setStatus(serv.getStatusById(notif.getStatus().getId()));
		serv.updateReimbursementChangeNotification(notif);
		ctx.status(200);
	}
	
	public static void getReimbursementChangeNotificationByFormId(Context ctx)
	{
		Set<ReimbursementChangeNotification> result = serv.getReimbursementChangeNotificationByFormId(Integer.parseInt(ctx.pathParam("id")));
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
