package JoyStick;

import java.util.Set;

import Entity.SubmitForm;
import Service.SubmitFormServ;
import Service.SubmitFormServImp;
import io.javalin.http.Context;

public class SubmitFormContr {
	
private static SubmitFormServ submitFormServ = new SubmitFormServImp();
	
	public static void getFormByIds(Context ctx) {
		Integer emp_id = Integer.valueOf(ctx.queryParam("emp_id"));
		Integer event_id = Integer.valueOf(ctx.queryParam("event_id"));
		SubmitForm sf = submitFormServ.getByIds(emp_id, event_id);
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
	
	
	public static void addForm(Context ctx) {
		SubmitForm sf = ctx.bodyAsClass(SubmitForm.class);
		submitFormServ.add(sf);
		ctx.status(201);
	}
	
	public static void updateForm(Context ctx) {
		SubmitForm sf = ctx.bodyAsClass(SubmitForm.class);
		if (sf != null) {
			ctx.status(200);
			submitFormServ.update(sf);
		} else {
			ctx.status(404);
		}
	}
}
