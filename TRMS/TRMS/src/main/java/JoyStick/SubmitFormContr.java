package JoyStick;

import java.util.Set;

import Entity.SubmitForm;
import Service.SubmitFormServ;
import Service.SubmitFormServImp;
import io.javalin.http.Context;

public class SubmitFormContr {
	
private static SubmitFormServ submitFormServ = new SubmitFormServImp();
	
	public static void getFormById(Context ctx) {
		Integer emp_id = Integer.valueOf(ctx.pathParam("id"));
		System.out.println("1");
		SubmitForm sf = submitFormServ.getById(emp_id);
		if (sf != null) {
			
			ctx.status(200);
			ctx.json(sf);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAll(Context ctx) {
		Set<SubmitForm> forms = submitFormServ.getAll();
		if (forms != null) {
			ctx.status(200);
			ctx.json(forms);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getDS(Context ctx) {
		Set<SubmitForm> forms = submitFormServ.getDS();
		if (forms != null) {
			ctx.status(200);
			ctx.json(forms);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getDH(Context ctx) {
		Set<SubmitForm> forms = submitFormServ.getDH();
		if (forms != null) {
			ctx.status(200);
			ctx.json(forms);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getHY(Context ctx) {
		Set<SubmitForm> forms = submitFormServ.getHY();
		if (forms != null) {
			ctx.status(200);
			ctx.json(forms);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPile(Context ctx) {
		Set<SubmitForm> forms = submitFormServ.getPile();
		if (forms != null) {
			ctx.status(200);
			ctx.json(forms);
		} else {
			ctx.status(404);
		}
	}
	
	
	public static void addForm(Context ctx) {
		SubmitForm sf = ctx.bodyAsClass(SubmitForm.class);
		submitFormServ.add(sf);
		ctx.status(201);
	}
	
	public static void updateForm(Context ctx) {
		System.out.println("4");
		SubmitForm sf = ctx.bodyAsClass(SubmitForm.class);
		if (sf != null) {
			ctx.status(200);
			submitFormServ.update(sf);
		} else {
			ctx.status(404);
		}
	}
}
