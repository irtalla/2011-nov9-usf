package dev.rev.controllers;


import dev.rev.beans.employee;
import dev.rev.exception.NonUniqueUsernameException;
import dev.rev.services.employeeService;
import dev.rev.services.employeeServiceImp;
import io.javalin.http.Context;

public class employeecontroller {

	private static employeeService eservice =new employeeServiceImp();
	
	public static void checklogin(Context ctx){
		System.out.println(ctx.sessionAttribute("user")+"asdads");
		employee p = ctx.sessionAttribute("user");
		if (p != null) {
			System.out.println("Logged in as " + p.getEmp_name());
			ctx.json(p);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	
	public static void updateUser(Context ctx) {
		employee emp=ctx.bodyAsClass(employee.class);
		eservice.updateemployee(emp);
		ctx.status(200);
		
	}
	
	public static void register(Context ctx) {
		
		employee newem=ctx.bodyAsClass(employee.class);
		try {
			
			eservice.addPerson(newem);
		}catch(NonUniqueUsernameException e) {
			System.out.println(e);
			ctx.status(409);
		}
		ctx.status(200);
		
	}
	
	public static void logout(Context ctx) {
		System.out.println("Logging out herer");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void getuserbyid(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		employee p = eservice.getPersonById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	

	public static void logIn(Context ctx) {
		System.out.println("Logging in");
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		
		employee p = eservice.getPersonByUsername(username);
		//System.out.println("username: "+username +"  password: "+ password+" person"+ p);
		
		if (p != null) {
			if (p.getPassword().equals(password))
			{
				System.out.println("Logged in as " + p.getEmp_name());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
				System.out.println("user "+p);
			}
			else
			{
				System.out.println(username+"::user" + password+"::pass");
				// password mismatch
				ctx.status(400);
			}
		}
		else
		{
			// username not found
			ctx.status(404);
		}
	}
	

}
