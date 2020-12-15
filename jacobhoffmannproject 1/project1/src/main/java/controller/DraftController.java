package controller;

import java.util.Set;

import io.javalin.http.Context;
import models.Draft;
import models.Draft_Approval;
import models.Pitch_Approval;
import servicespackage.draft.Draft_Service;
import servicespackage.draft.Draft_ServiceImpl;
import servicespackage.draft_approval.Draft_Approval_Service;
import servicespackage.draft_approval.Draft_Approval_ServiceImpl;

public class DraftController {
private static Draft_Service eserv = new Draft_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft u = eserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByAuthorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Draft> draftSet = eserv.getByAuthorId(id);
		if (draftSet != null) {
			ctx.status(200);
			ctx.json(draftSet);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx) throws Exception{
		Draft u = ctx.bodyAsClass(Draft.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft u = ctx.bodyAsClass(Draft.class);
		if (u != null) {
			ctx.status(200);
			eserv.update(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByUsersID(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Draft> paSet = eserv.getByAuthorId(id);
		if (paSet != null) {
			ctx.status(200);
			ctx.json(paSet);
		} else {
			ctx.status(404);
		}
	}
	
}
