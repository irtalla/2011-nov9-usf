package controller;

import java.util.Set;

import io.javalin.http.Context;
import models.Draft_Approval;
import models.Draft_Status_Short;
import models.Pitch_Status;
import servicespackage.draft_approval.Draft_Approval_Service;
import servicespackage.draft_approval.Draft_Approval_ServiceImpl;
import servicespackage.dss.DSS_Service;
import servicespackage.dss.DSS_ServiceImpl;

public class Draft_ApprovalController {
private static Draft_Approval_Service eserv = new Draft_Approval_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Draft_Approval u = eserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByStoryId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Draft_Approval u = eserv.getByStoryId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByEditorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Draft_Approval> paSet = eserv.getByEditor(id);
		if (paSet != null) {
			ctx.status(200);
			ctx.json(paSet);
		} else {
			ctx.status(404);
		}	
	}
	public static void add(Context ctx){
		Draft_Approval u = ctx.bodyAsClass(Draft_Approval.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void updateDraft_Approval(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Approval u = ctx.bodyAsClass(Draft_Approval.class);
		System.out.println("am in draft update");

		if (u != null) {
			ctx.status(200);
			eserv.updateDraft_Approval(u);
		} else {
			ctx.status(404);
		}
	}
	
}
