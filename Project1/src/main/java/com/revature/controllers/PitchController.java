package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.beans.Person;
import com.revature.services.PersonServiceImpl;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController extends GenericController<Pitch>{
	public PitchController() {
		super(Pitch.class);
	}

	@Override
	PitchServiceImpl getServ() {
		return new PitchServiceImpl();
	}
	
	
	public void getPitchesViewableBy(Context ctx) {
		System.out.println("Getting viewable pitches");
		Person person = new PersonServiceImpl().getById(Integer.valueOf(ctx.pathParam("id")));
		
		try {
			Set<Pitch> pitchSet = getServ().getPitchesViewableBy(person);
			ctx.json(pitchSet);
		}catch(Exception e) {
			System.out.println("Null pitch set for user?");
			ctx.status(404);
			e.printStackTrace();
		}
		ctx.status(200);
	}
}
