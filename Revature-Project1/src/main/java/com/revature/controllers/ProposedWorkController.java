package com.revature.controllers;

import com.revature.services.AuthorFunctions;
import com.revature.services.AuthorServicesImpl;

import io.javalin.http.Context;

public class ProposedWorkController {
	private static AuthorFunctions asi = new AuthorServicesImpl();
	
	public static void addProposedWork(Context ctx) {
		String title = ctx.queryParam("title");
		String description = ctx.queryParam("description");
		String typeOfBook = ctx.queryParam("bookType");
		String genre = ctx.queryParam("genre");
		
		//Author whatever = ctx.
		
		
	}
	
	public static void getProposedWorkById(Context ctx) {
		
	}
	
	public static void getProposedWorkByName(Context ctx) {
		
	}
}
