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
		Person person = ctx.bodyAsClass(Person.class);
		PersonServiceImpl personServ = new PersonServiceImpl();
		try {
			person = personServ.getById(person.getId());
		}catch(Exception e) {
			System.out.println("User not found.");
			ctx.status(404);
		}
		
		try {
			Set<Pitch> pitchSet = getServ().getPitchesViewableBy(person);
			ctx.json(pitchSet);
		}catch(Exception e) {
			System.out.println("Null pitch set for user?");
			ctx.status(404);
		}
		ctx.status(200);
	}
}
