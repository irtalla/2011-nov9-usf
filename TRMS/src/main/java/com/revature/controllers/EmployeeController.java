package com.revature.controllers;


import java.util.Set;

import com.revature.beans.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;

import io.javalin.http.Context;

public class EmployeeController {
	private static EmployeeService empServ = new EmployeeServiceImpl();
	
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Employee emp = ctx.sessionAttribute("user");
		if (emp != null) {
			System.out.println("Logged in as " + emp.getUsername());
			ctx.json(emp);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(401);
		}
	}
	
	public static void getEmployeeById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Employee emp = empServ.getEmployeeById(id);
		if (emp != null) {
			ctx.status(200);
			ctx.json(emp);
		} else {
			ctx.status(404);
		}
	}
	
	public static void login(Context ctx)
	{
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		Employee emp = empServ.getEmployeeByLogin(username, password);
		if (emp != null) {
			ctx.status(200);
			ctx.json(emp);
			ctx.sessionAttribute("user", emp);
		} else {
			ctx.status(400);
		}
	}
	
	public static void logout(Context ctx)
	{
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void getAllEmployees(Context ctx)
	{
		Set<Employee> results = empServ.getEmployees();
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
	
	public static void addEmployee(Context ctx)
	{
		Employee emp = ctx.bodyAsClass(Employee.class);
		empServ.addEmployee(emp);
		ctx.status(201);
	}
	
	public static void updateEmployee(Context ctx)
	{
		//Integer id = Integer.valueOf(ctx.pathParam("id"));
		Employee emp = ctx.bodyAsClass(Employee.class);
		
		if (emp != null) {
			ctx.status(200);
			empServ.updateEmployee(emp);
		} else {
			ctx.status(404);
		}
	}
	
	public static void deleteEmployee(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Employee emp = empServ.getEmployeeById(id);
		if (emp != null) {
			ctx.status(204);
			empServ.removeEmployee(emp);
		} else {
			ctx.status(404);
		}
	}
}
