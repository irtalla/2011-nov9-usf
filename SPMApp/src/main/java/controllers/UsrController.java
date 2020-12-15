package controllers;

import beans.Usr;
import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import services.UsrService; 
import services.UsrServiceImpl;

public class UsrController {
	private static UsrService usrServ = new UsrServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Usr u = ctx.sessionAttribute("user");
		if (u != null) {
			System.out.println("Logged in as " + u.getUsrname());
			ctx.json(u);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	public static void logIn(Context ctx) {

		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		System.out.println("Log in initiated" + username + password);
		
		Usr u = usrServ.getUsrbyUsrname(username);
		if (u != null) {
			if (u.getPasswd().equals(password))
			{
				System.out.println("Logging in as " + u.getUsrname());
				ctx.status(200);
				ctx.json(u);
				ctx.sessionAttribute("user", u);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(404);
		} 
	}
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void registerUsr(Context ctx) {
		Usr newUsr = ctx.bodyAsClass(Usr.class);
		try {
			usrServ.addUsr(newUsr);
		}
		catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken!");
			ctx.status(409);
		}
		ctx.status(200);
	}
public static void getTypes(Context ctx) {
	ctx.json(usrServ.getTypes());
	ctx.status(200);
}
	
	public static void getUsrById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Usr u = usrServ.getUsrById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUsr(Context ctx) {
		Usr tempUsr = ctx.bodyAsClass(Usr.class);
		usrServ.updateUsr(tempUsr);
		ctx.status(202);
	}
	
	public static void deleteUsr(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Usr usr = usrServ.getUsrById(id);
		usrServ.deleteUsr(usr);
		ctx.status(204);
	}
	public static void getUsrByName(Context ctx) {
		String username = String.valueOf(ctx.pathParam("user"));
		Usr usr = usrServ.getUsrbyUsrname(username);
		if (usr != null) {
			ctx.status(200);
			ctx.json(usr);
		}else {
			ctx.status(404);
		}
	}
	public static void getRoles(Context ctx) {
		ctx.json(usrServ.getRoles());
		ctx.status(200);
	}
	public static void getGenres(Context ctx) {
		ctx.json(usrServ.getGenres());
		ctx.status(200);
	}
}	
