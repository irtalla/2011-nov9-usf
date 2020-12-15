package com.revature.controllers;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.GenreSpecialization;
import com.revature.beans.InfoRequest;
import com.revature.beans.Person;
import com.revature.beans.StoryPitch;
import com.revature.beans.InfoRequest;
import com.revature.beans.Type;
import com.revature.services.GenreSpecializationServices;
import com.revature.services.GenreSpecializationServicesImpl;
import com.revature.services.InfoRequestServices;
import com.revature.services.InfoRequestServicesImpl;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;
import com.revature.services.InfoRequestServices;
import com.revature.services.InfoRequestServicesImpl;

import io.javalin.http.Context;

public class GenreSpecializationController {
	private static InfoRequestServices infoRequestServ = new InfoRequestServicesImpl();
	private static GenreSpecializationServices gsServ = new GenreSpecializationServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	
	public static Set<GenreSpecialization> getByGenreID(Context ctx) {
		System.out.println("Getting genre specializations in the controller");
		Set<GenreSpecialization> gs = new HashSet<GenreSpecialization>();
		Integer gsid = Integer.valueOf(ctx.queryParam("genreSpecializationID"));
		gs = gsServ.getByGenredID(gsid);
		if (gs.size() > 0) {
			ctx.status(200);
			//System.out.println(p);
			ctx.json(gs);
			ctx.sessionAttribute("genreSpecializations", gs);
			return gs;
		} else {
			ctx.status(404);
			return null;
		}
	}
	
}
