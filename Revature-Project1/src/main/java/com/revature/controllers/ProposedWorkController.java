package com.revature.controllers;

import java.sql.Date;
import java.util.Set;

import com.revature.beans.Author;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.Work;
import com.revature.services.AuthorFunctions;
import com.revature.services.AuthorServicesImpl;

import io.javalin.http.Context;

public class ProposedWorkController {
	private static AuthorFunctions asi = new AuthorServicesImpl();
	
	public static void addProposedWork(Context ctx) {
		Work literaryWork = ctx.bodyAsClass(Work.class);
		StoryPitch storyPitch = new StoryPitch();
		storyPitch.setProposedWork(literaryWork);
		
		Status startingStatus = new Status();
		Stage startingStage = new Stage();
		if (literaryWork.getLengthOfWork().getAssociatedPoints() < literaryWork.getAuthor().getPointsRemaining()){
			startingStatus.setId(1);
			startingStatus.setStatus("pending");
			startingStage.setId(1);
			startingStage.setStageName("assistant editor");
		}
		else {
			startingStatus.setId(2);
			startingStatus.setStatus("on hold");
			startingStage.setId(5);
			startingStage.setStageName("before");
		}
		
		storyPitch.setStatus(startingStatus);
		storyPitch.setStage(startingStage);
		
		storyPitch = asi.addProposedWork(storyPitch);
		
		ctx.json(storyPitch);
		ctx.status(200);
	}
	
	public static void getAllProposedWorksByAuthor(Context ctx) {
		Author author = ctx.sessionAttribute("user");
		Set<Work> authorsProposedWorks = asi.getAllOfAuthorsWorks(author);
		
		if (authorsProposedWorks.size() > 0) {
			ctx.json(authorsProposedWorks);
		}
		else {
			ctx.json("You have not proposed any literary work to be reviewed.");
		}
		
		ctx.status(200);
	}
	
	public static void getProposedWorkById(Context ctx) {
		
	}
	
	public static void getProposedWorkByName(Context ctx) {
		
	}
}
