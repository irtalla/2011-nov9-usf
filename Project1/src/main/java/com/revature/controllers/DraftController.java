package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.services.DraftServiceImpl;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class DraftController extends GenericController<Draft> {

	public DraftController() {
		super(Draft.class);
	}

	@Override
	DraftServiceImpl getServ() {
		return new DraftServiceImpl();
	}
	
	public void getDraftsViewableBy(Context ctx){
		Person person = new PersonServiceImpl().getById(Integer.valueOf(ctx.pathParam("id")));
		
		try {
			Set<Draft> pitchSet = getServ().getDraftsViewableBy(person);
			ctx.json(pitchSet);
		}catch(Exception e) {
			System.out.println("Null draft set for user?");
			ctx.status(404);
		}
		ctx.status(200);
	}
	
	
}
