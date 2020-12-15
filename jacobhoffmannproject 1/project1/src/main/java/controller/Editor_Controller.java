package controller;

import io.javalin.http.Context;
import models.Editor;
import models.Employee;
import models.Users;
import servicespackage.editor.Editor_Service;
import servicespackage.editor.Editor_ServiceImpl;
import servicespackage.employee.Employee_Service;
import servicespackage.employee.Employee_ServiceImpl;

public class Editor_Controller {
private static Editor_Service eserv = new Editor_ServiceImpl();
public static void checkLogin(Context ctx) {
	System.out.println("Checking login");
	Editor p = ctx.sessionAttribute("edit");
	if (p != null) {
		ctx.json(p);
		ctx.status(200);
	} else {
		System.out.println("Not logged in");
		ctx.status(400);
	}
}
	public static void getByEditorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Editor u = eserv.getByEditorId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByEmployeeId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Editor u = eserv.getByEmployeeId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
			ctx.sessionAttribute("edit", u);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Editor u = ctx.bodyAsClass(Editor.class);
		eserv.addEditor(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Editor u = ctx.bodyAsClass(Editor.class);
		if (u != null) {
			ctx.status(200);
			eserv.updateEditor(u);
		} else {
			ctx.status(404);
		}
	}
}
