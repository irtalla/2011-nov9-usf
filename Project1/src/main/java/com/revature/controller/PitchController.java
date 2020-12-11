package com.revature.controller;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Committee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.service.PitchService;
import com.revature.service.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pServ = new PitchServiceImpl();
	
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		if(pitch != null) {
			ctx.status(200);
			ctx.json(pitch);
		}else {
			ctx.status(404);
		}
	}
	
	public static void addPitch(Context ctx) {
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		pServ.addPitch(pitch);
		ctx.status(201);
	}
	
	public static void updatePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		pServ.updatePitch(pitch);
		ctx.status(202);
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		pServ.deletePitch(pitch);
		ctx.status(204);
	}
	
	public static void getPitches(Context ctx) {
		Set<Pitch> pitches = pServ.getPitches();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getCommitteePitches(Context ctx) {
		Person loggedPerson = ctx.sessionAttribute("user");
		Set<Committee> comms = loggedPerson.getCommittees();
		Set<Pitch> commPitches = new HashSet<>();
		for(Committee com : comms) {
			
			commPitches.add (getPitchByCommitteeId(com.getId()));	
		}
		
		if(commPitches !=null) {
			ctx.status(200);
			ctx.json(commPitches);
		}else {
			ctx.status(404);
		}
		System.out.println("they say thats some dead code up there?");
	}

	private static Pitch getPitchByCommitteeId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
