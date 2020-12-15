package controller;

import io.javalin.http.Context;
import models.Employee;
import models.Genre;
import servicespackage.Genre.Genre_Service;
import servicespackage.Genre.Genre_ServiceImpl;
import servicespackage.employee.Employee_Service;
import servicespackage.employee.Employee_ServiceImpl;

public class EmployeeController {
private static Employee_Service eserv = new Employee_ServiceImpl();
	
	public static void getByEmployeeId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Employee u = eserv.getByEmployeeId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByUserId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Employee u = eserv.getByUserId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
			ctx.sessionAttribute("emp", u);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Employee u = ctx.bodyAsClass(Employee.class);
		eserv.addEmployee(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Employee u = ctx.bodyAsClass(Employee.class);
		if (u != null) {
			ctx.status(200);
			eserv.updateEmployee(u);
		} else {
			ctx.status(404);
		}
	}
}
