package com.revature.controllers;

import java.util.Set;

import com.revature.beans.FurtherInfoRequests;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.services.AuthorFunctions;
import com.revature.services.AuthorServicesImpl;
import com.revature.services.EditorFunctions;
import com.revature.services.EditorServicesImpl;

import io.javalin.http.Context;

public class ApprovalController {
	private static EditorFunctions esi = new EditorServicesImpl();
	private static AuthorFunctions asi = new AuthorServicesImpl();
	
	public static void editorRejectProposedWork(Context ctx) {
		StoryPitch rejectedStoryPitch = ctx.bodyAsClass(StoryPitch.class);
		Status rejectedStatus = new Status();
		//copied directly from the database.
		rejectedStatus.setId(3);
		rejectedStatus.setStatus("rejected");
		rejectedStoryPitch.setStatus(rejectedStatus);
		
		esi.rejectAPitch(rejectedStoryPitch);
		ctx.status(200);
	}
	
	public static void acceptProposedWork(Context ctx) {
		StoryPitch acceptedStoryPitch = ctx.bodyAsClass(StoryPitch.class);
		Status progressStatus = new Status();
		//copied directly from the database.
		
		if (acceptedStoryPitch.getStage().getId() < 4) {
			progressStatus.setId(1);
			progressStatus.setStatus("pending");
			acceptedStoryPitch.setStatus(progressStatus);
		}
		else {
			progressStatus.setId(6);
			progressStatus.setStatus("approved");
			acceptedStoryPitch.setStatus(progressStatus);
		}
		
		esi.acceptAPitch(acceptedStoryPitch);
		ctx.status(200);
	}
	
	public static void addRequestedInfo(Context ctx) {
		FurtherInfoRequests fir = ctx.bodyAsClass(FurtherInfoRequests.class);
		asi.addRequestedInfo(fir);	//if there is time make this general.
		ctx.status(200);
	}
	
	public static void getRequestedInfo(Context ctx) {
		int approvalID = Integer.parseInt(ctx.pathParam("id"));
		int approvalStage = Integer.parseInt(ctx.pathParam("stage"));
		Set<FurtherInfoRequests> firTrees = esi.getRequestedInfo(approvalID, approvalStage);
		ctx.json(firTrees);
		ctx.status(200);
	}
	
	public static void authorRemoveProposedWork(Context ctx) {
		int userID = Integer.parseInt(ctx.pathParam("userID"));
		int proposedWorkID = Integer.parseInt(ctx.pathParam("workID"));
		asi.authorRemoveProposedWork(userID, proposedWorkID);
		ctx.status(200);
	}
	
	public static void getAllNeededApprovals(Context ctx) {
		int editorID = Integer.parseInt(ctx.pathParam("editorID"));
		Set<StoryPitch> editorMustCheck = esi.getPitchesByEditorApproval(editorID);
		ctx.json(editorMustCheck);
		ctx.status(200);
	}
	

}
