package controller;

import java.util.Set;

import io.javalin.http.Context;
import models.Commitee;
import models.Draft;
import models.Pitch_Status;
import servicespackage.commitee.Commitee_Service;
import servicespackage.commitee.Commitee_ServiceImpl;
import servicespackage.draft.Draft_Service;
import servicespackage.draft.Draft_ServiceImpl;

public class CommiteeController {
	private static Commitee_Service eserv = new Commitee_ServiceImpl();

	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Commitee u = eserv.getCommiteeById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getAll(Context ctx) {
		Set<Commitee> cats = eserv.getAll();
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
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Commitee> cSet = eserv.getByEditorId(id);
		if (cSet != null) {
			ctx.status(200);
			ctx.json(cSet);
		} else {
			ctx.status(404);
		}
	}
	public static void getByGenreId(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Commitee> cSet = eserv.getByGenreID(id);
		if (cSet != null) {
			ctx.status(200);
			ctx.json(cSet);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Commitee u = ctx.bodyAsClass(Commitee.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Commitee u = ctx.bodyAsClass(Commitee.class);
		if (u != null) {
			ctx.status(200);
			eserv.updateCommitee(u);
		} else {
			ctx.status(404);
		}
	}
	
}
