package controller;

import java.util.Set;

import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import models.Pitch_Approval;
import models.Pitch_Status;
import models.Users;
import servicespackage.pitch_status.Pitch_Status_Service;
import servicespackage.pitch_status.Pitch_Status_ServiceImpl;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class Pitch_StatusController {
private static Pitch_Status_Service psServ = new Pitch_Status_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Pitch_Status u = psServ.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByAuthorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Pitch_Status> cats = psServ.getByAuthorId(id);
		System.out.println("yo");
		if (cats != null) {
			System.out.println("yo");
			ctx.status(200);
			ctx.json(cats);
		} else {
			ctx.status(404);
		}
	}
	public static void getByEditorId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Pitch_Status> paSet = psServ.getByEditor(id);
		if (paSet != null) {
			ctx.status(200);
			ctx.json(paSet);
		} else {
			ctx.status(404);
		}	
	}
	public static void add(Context ctx) {
		Pitch_Status u = ctx.bodyAsClass(Pitch_Status.class);
		psServ.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch_Status u = ctx.bodyAsClass(Pitch_Status.class);
		if (u != null) {
			ctx.status(200);
			psServ.update(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getAll(Context ctx) {
		Set<Pitch_Status> cats = psServ.getAll();
		System.out.println("yo");
		if (cats != null) {
			System.out.println("yo");
			ctx.status(200);
			ctx.json(cats);
		} else {
			ctx.status(404);
			
	}
}
}
