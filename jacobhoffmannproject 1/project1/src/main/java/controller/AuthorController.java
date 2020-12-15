package controller;

import java.util.Set;

import io.javalin.http.Context;
import models.Author;
import models.Commitee;
import models.Users;
import servicespackage.Author.Author_Service;
import servicespackage.Author.Author_ServiceImpl;
import servicespackage.commitee.Commitee_Service;
import servicespackage.commitee.Commitee_ServiceImpl;

public class AuthorController {
	private static Author_Service eserv = new Author_ServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Author p = ctx.sessionAttribute("author");
		if (p != null) {
			ctx.json(p);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Author u = eserv.getAuthorById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByUserId(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Author u = eserv.getByUserId(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
			ctx.sessionAttribute("author", u);
		} else {
			ctx.status(404);
		}
	}
	public static void add(Context ctx){
		Author u = ctx.bodyAsClass(Author.class);
		eserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Author u = ctx.bodyAsClass(Author.class);
		if (u != null) {
			ctx.status(200);
			eserv.updateAuthor(u);
		} else {
			ctx.status(404);
		}
	}
}
