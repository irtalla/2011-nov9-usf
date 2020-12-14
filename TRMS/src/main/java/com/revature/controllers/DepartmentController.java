package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Department;
import com.revature.services.DepartmentService;
import com.revature.services.DepartmentServiceImpl;

import io.javalin.http.Context;

public class DepartmentController {
	private static DepartmentService depServ = new DepartmentServiceImpl();

	public static void getDepartmentById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Department type = depServ.getDepartmentById(id);
		
		if (type != null) 
		{
			ctx.status(200);
			ctx.json(type);
		} 
		else 
		{
			ctx.status(404);
		}
		
	}
	
	public static void getAllDepartments(Context ctx)
	{
		Set<Department> results = depServ.getDepartments();
		if (results != null)
		{
			ctx.status(200);
			ctx.json(results);
		}
		else
		{
			ctx.status(404);
		}
	}
}
