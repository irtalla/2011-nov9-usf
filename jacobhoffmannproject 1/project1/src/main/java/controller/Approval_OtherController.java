package controller;

import io.javalin.http.Context;
import models.Approval_Other;
import models.Author;
import servicespackage.Approval.Approval_Other_Service;
import servicespackage.Approval.Approval_Other_ServiceImpl;
import servicespackage.Author.Author_Service;
import servicespackage.Author.Author_ServiceImpl;

public class Approval_OtherController {
	private static Approval_Other_Service eserv = new Approval_Other_ServiceImpl();

	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval_Other u = eserv.getApprovalById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByEditorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval_Other u = eserv.getByEditorId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Approval_Other u = ctx.bodyAsClass(Approval_Other.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval_Other u = ctx.bodyAsClass(Approval_Other.class);
		if (u != null) {
			ctx.status(200);
			eserv.updateApproval(u);
		} else {
			ctx.status(404);
		}
	}
}
