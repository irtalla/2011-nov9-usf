package dev.elliman.controller;

import java.util.List;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.services.ClaimService;
import dev.elliman.services.ClaimServiceImpl;
import io.javalin.http.Context;

public class ClaimController {
	private static ClaimService cs = new ClaimServiceImpl();
	
	public static void getClaimsByPerson(Context ctx) {
		Person p = ctx.sessionAttribute("user");
		List<Claim> claims = cs.getClaimsByPerson(p);
		
		if(claims != null) {
			ctx.status(200);
			ctx.json(claims);
		} else {
			ctx.status(400);
		}
	}
}
