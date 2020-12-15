package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Rejection;
import com.revature.beans.Person;
import com.revature.beans.Rejection;
import com.revature.beans.Type;
import com.revature.services.RejectionServices;
import com.revature.services.RejectionServicesImpl;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;
import com.revature.services.RejectionServices;
import com.revature.services.RejectionServicesImpl;

import io.javalin.http.Context;

public class RejectionController {
	private static RejectionServices rejectionServ = new RejectionServicesImpl();
	private static PersonServices personServ = new PersonServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	
	public static void submitRejection(Context ctx) {
		System.out.println("Submitting a rejection in the controller");
		Rejection r = new Rejection();
		Integer storyID = Integer.valueOf(ctx.queryParam("storyID"));
		if(storyPitchServ.getByStoryPitchID(Integer.valueOf(ctx.queryParam("storyID"))) == null) {
					ctx.status(400);
					System.out.println("status: 400");
				}
		else {
			Integer authorID = storyPitchServ.getByStoryPitchID(storyID).getPersonID();
			r.setRejectorID(Integer.valueOf(ctx.queryParam("rejector")));
			r.setRejectedID(authorID);
			r.setReason(String.valueOf(ctx.queryParam("message")));
			r.setStoryRejectedID(storyID);
			rejectionServ.addRejection(r);
			ctx.status(200);
		}
	}
	
}
