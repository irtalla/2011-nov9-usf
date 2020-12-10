package dev.rev.controllers;

import java.util.List;

import dev.rev.beans.reimbForm;
import dev.rev.services.formservice;
import dev.rev.services.formserviceimp;
import io.javalin.http.Context;

public class formcontroller {

	private static formservice fs= new formserviceimp();
	
	public static void addform(Context ctx) {
		reimbForm from=ctx.bodyAsClass(reimbForm.class);
		System.out.println(from+" formss");
		fs.addform(from);
		ctx.status(201);
	}
	
	public static void getallforms(Context ctx) {
		List<reimbForm> forms=fs.getforms();
		if(forms != null) {
			ctx.status(200);
			ctx.json(forms);
			
		}else {
			ctx.status(404);
		}
	}
}
