package controller;

import java.util.Set;

import io.javalin.http.Context;
import models.Pitch_Approval;
import models.Story;
import servicespackage.pitch_approval.Pitch_Approval_Service;
import servicespackage.pitch_approval.Pitch_Approval_ServiceImpl;
import servicespackage.story.Story_Service;
import servicespackage.story.Story_ServiceImpl;

public class Pitch_ApprovalController {
private static Pitch_Approval_Service paserv = new Pitch_Approval_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Pitch_Approval u = paserv.getById(id);
		//System.out.println(u);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByEditorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Pitch_Approval> paSet = paserv.getByEditorId(id);
		if (paSet != null) {
			ctx.status(200);
			ctx.json(paSet);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Pitch_Approval u = ctx.bodyAsClass(Pitch_Approval.class);
		paserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch_Approval u = ctx.bodyAsClass(Pitch_Approval.class);
		u.setApproved(true);
		//System.out.println(u);

		if (u != null) {
			System.out.println(u);
			ctx.status(200);
			paserv.update(u);
		} else {
			ctx.status(404);
		}
	}
}
