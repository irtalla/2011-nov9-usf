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
			System.out.println("ffff"+forms);
			
			ctx.status(200);
			ctx.json(forms);
			
		}else {
			ctx.status(404);
		}
	}
	
	public static void getformbyid(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		System.out.println(id+"id is");
		reimbForm cat = fs.getbyid(id);
		System.out.println("fomr:"+cat);
		if (cat != null) {
			ctx.status(200);
			ctx.json(cat);
		} else {
			ctx.status(404);
		}
	} 
	public static void getempforms(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		System.out.println(id+"id is");
		List<reimbForm> cat = fs.getempforms(id);
		System.out.println("fomr:"+cat);
		if (cat != null) {
			ctx.status(200);
			ctx.json(cat);
		} else {
			ctx.status(404);
		}
	} 
	
	
	
	public static void updateform(Context ctx) {
		
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		reimbForm cat = ctx.bodyAsClass(reimbForm.class);
		if (cat != null) {
			ctx.status(200);
			fs.update_form(cat);
		} else {
			ctx.status(404);
		}
		
	}
}
