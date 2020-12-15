package controller;

import io.javalin.http.Context;
import models.Draft_Status_Article;
import models.Draft_Status_Short;
import servicespackage.dsa.DSA_Service;
import servicespackage.dsa.DSA_ServiceImpl;
import servicespackage.dss.DSS_Service;
import servicespackage.dss.DSS_ServiceImpl;

public class DSAController {
private static DSA_Service eserv = new DSA_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Article u = eserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}

	public static void add(Context ctx){
		Draft_Status_Article u = ctx.bodyAsClass(Draft_Status_Article.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Draft_Status_Article u = ctx.bodyAsClass(Draft_Status_Article.class);
		if (u != null) {
			ctx.status(200);
			eserv.update(u);
		} else {
			ctx.status(404);
		}
	}
}
