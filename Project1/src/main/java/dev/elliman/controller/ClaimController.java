package dev.elliman.controller;

import java.util.List;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Event;
import dev.elliman.beans.Person;
import dev.elliman.beans.Stage;
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
	
	public static void getEventTypes(Context ctx) {
		List<Event> events = cs.getEventTypes();
		
		if(events != null) {
			ctx.status(200);
			ctx.json(events);
		} else {
			ctx.status(500);
		}
	}
	
	public static void getDSUnapprovedClaims(Context ctx) {
		//Person p = ctx.sessionAttribute("user");
		List<Claim> claims = cs.getDSUnapprovedClaims();
		
		if(claims != null) {
			ctx.status(200);
			ctx.json(claims);
		} else {
			ctx.status(500);
		}
	}
	
	public static void getDHUnapprovedClaims(Context ctx) {
		//Person p = ctx.sessionAttribute("user");
		List<Claim> claims = cs.getDHUnapprovedClaims();
		
		if(claims != null) {
			ctx.status(200);
			ctx.json(claims);
		} else {
			ctx.status(500);
		}
	}
	
	public static void getBCUnapprovedClaims(Context ctx) {
		//Person p = ctx.sessionAttribute("user");
		List<Claim> claims = cs.getBCUnapprovedClaims();
		
		if(claims != null) {
			ctx.status(200);
			ctx.json(claims);
		} else {
			ctx.status(500);
		}
	}
	
	public static void accept(Context ctx) {
		Claim claim = cs.getClaimByID(Integer.valueOf(ctx.pathParam("id")));
		Person person = ctx.sessionAttribute("user");
		
		if(person.getRole().getId() == 3) {
			claim.setDsa(person);
			Stage s = new Stage();
			s.setId(2);
			s.setName("Pending department head review");
			claim.setApprovalStage(s);
		} else if(person.getRole().getId() == 2) {
			if(claim.getDsa() == null) {
				claim.setDsa(person);
			}
			claim.setDha(person);
			Stage s = new Stage();
			s.setId(3);
			s.setName("Pending benifits coordinator review");
			claim.setApprovalStage(s);
		} else if(person.getRole().getId() == 1) {
			claim.setBca(person);
			Stage s = new Stage();
			s.setId(4);
			s.setName("Accepted");
			claim.setApprovalStage(s);
		}
		
		boolean accepted = cs.accept(claim);
		
		if(accepted) {
			ctx.status(200);
		} else {
			ctx.status(500);
		}
	}
}
