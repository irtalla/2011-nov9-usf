package controller;

import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import models.Request_Info;
import models.Users;
import servicespackage.request_info.Request_Info_Service;
import servicespackage.request_info.Request_Info_ServiceImpl;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class Request_InfoController {
private static Request_Info_Service riServ = new Request_Info_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Request_Info u = riServ.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void add(Context ctx) {
		Request_Info u = ctx.bodyAsClass(Request_Info.class);
		riServ.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Request_Info u = ctx.bodyAsClass(Request_Info.class);
		if (u != null) {
			ctx.status(200);
			riServ.update(u);
		} else {
			ctx.status(404);
		}
	}
}
