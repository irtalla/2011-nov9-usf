package controller;

import io.javalin.http.Context;
import models.Draft_Status_Other;
import models.Draft_Status_Short;
import servicespackage.dso.DSO_Service;
import servicespackage.dso.DSO_ServiceImpl;
import servicespackage.dss.DSS_Service;
import servicespackage.dss.DSS_ServiceImpl;

public class DSOController {
private static DSO_Service eserv = new DSO_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Other u = eserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}

	public static void add(Context ctx){
		Draft_Status_Other u = ctx.bodyAsClass(Draft_Status_Other.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Other u = ctx.bodyAsClass(Draft_Status_Other.class);
		if (u != null) {
			ctx.status(200);
			eserv.update(u);
		} else {
			ctx.status(404);
		}
	}
}
