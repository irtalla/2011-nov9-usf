package controller;

import io.javalin.http.Context;
import models.Draft_Status_Short;
import models.Editor;
import servicespackage.dss.DSS_Service;
import servicespackage.dss.DSS_ServiceImpl;
import servicespackage.editor.Editor_Service;
import servicespackage.editor.Editor_ServiceImpl;

public class DSSController {
private static DSS_Service eserv = new DSS_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Short u = eserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}

	public static void add(Context ctx){
		Draft_Status_Short u = ctx.bodyAsClass(Draft_Status_Short.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Short u = ctx.bodyAsClass(Draft_Status_Short.class);
		if (u != null) {
			ctx.status(200);
			eserv.update(u);
		} else {
			ctx.status(404);
		}
	}
}
