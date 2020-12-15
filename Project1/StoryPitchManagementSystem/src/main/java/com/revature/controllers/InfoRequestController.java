package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.InfoRequest;
import com.revature.beans.Person;
import com.revature.beans.InfoRequest;
import com.revature.beans.Type;
import com.revature.services.InfoRequestServices;
import com.revature.services.InfoRequestServicesImpl;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;
import com.revature.services.InfoRequestServices;
import com.revature.services.InfoRequestServicesImpl;

import io.javalin.http.Context;

public class InfoRequestController {
	private static InfoRequestServices infoRequestServ = new InfoRequestServicesImpl();
	private static PersonServices personServ = new PersonServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	
	public static void submitInfoRequest(Context ctx) {
		System.out.println("Submitting an info request in the controller");
		InfoRequest infoRequest = new InfoRequest();
		infoRequest.setStoryID(Integer.valueOf(ctx.queryParam("storyID")));
		infoRequest.setPersonRequestingID(Integer.valueOf(ctx.queryParam("personRequestingID")));
		infoRequest.setPersonRequestedID(Integer.valueOf(ctx.queryParam("personRequestedID")));
		infoRequest.setMessage(String.valueOf(ctx.queryParam("message")));
		if(personServ.getByPersonID(Integer.valueOf(ctx.queryParam("personRequestedID"))) == null ||
				personServ.getByPersonID(Integer.valueOf(ctx.queryParam("personRequestingID"))) == null ||
				storyPitchServ.getByStoryPitchID(Integer.valueOf(ctx.queryParam("storyID"))) == null) {
					ctx.status(400);
					System.out.println("status: 400");
				}
		else {
		infoRequestServ.addInfoRequest(infoRequest);
		ctx.status(200);
		}
	}
	
}
