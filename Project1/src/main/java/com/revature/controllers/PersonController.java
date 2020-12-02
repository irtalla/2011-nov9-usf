package com.revature.controllers;

public class PersonController {
	public static void logIn(Context ctx) {
		System.out.println("Logging in");
		String username = ctx.queryParam("username");
	}
}
