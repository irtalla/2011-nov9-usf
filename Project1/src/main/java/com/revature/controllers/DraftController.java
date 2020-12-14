package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.Person;
import com.revature.services.DraftServiceImpl;

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
		Person p = ctx.bodyAsClass(Person.class);
		Set<Draft> drafts = getServ().getDraftsViewableBy(p);
		if(drafts != null) {
			ctx.json(drafts);
			ctx.status(200);
		}else {
			ctx.status(404);
		}
	}
}
