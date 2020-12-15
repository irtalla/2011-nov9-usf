package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Approval;
import com.revature.beans.GenreSpecialization;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.services.ApprovalServices;
import com.revature.services.ApprovalServicesImpl;
import com.revature.services.GenreSpecializationServices;
import com.revature.services.GenreSpecializationServicesImpl;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;

import io.javalin.http.Context;

public class ApprovalController {
	private static ApprovalServices approvalServ = new ApprovalServicesImpl();
	private static PersonServices personServ = new PersonServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	private static GenreSpecializationServices gsServ = new GenreSpecializationServicesImpl();
	
	public static void submitApproval(Context ctx) {
		System.out.println("Submitting an approval in the controller");
		Approval approval = new Approval();
		Integer sid = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(sid);
		if(sp == null) {
					System.out.println("status: 400");
					ctx.status(400);
		}
		else {
			Integer authorID = sp.getPersonID();
			approval.setApprovedID(authorID);
			approval.setStoryApprovedID(sid);
			approval.setApproverID(Integer.valueOf(ctx.queryParam("approverID")));
			if(Integer.valueOf(ctx.queryParam("statusID")) != 7 || sp.getType().getTypeID() == 4) {//checking if this needs committee approval
				System.out.println(Integer.valueOf(ctx.queryParam("statusID")));
				approval.setStatusApproved(Integer.valueOf(ctx.queryParam("statusID")));

				System.out.println(approval);
				approvalServ.addApproval(approval);
				Status status = sp.getStatus();
				status.setStatusID(Integer.valueOf(ctx.queryParam("statusID")));
				switch(Integer.valueOf(ctx.queryParam("statusID"))) {
				case 2:
					status.setName("Pitch Under Review - General Editor Approval Needed");
					break;
				case 3:
					status.setName("Pitch Under Review - Senior Genre Editor Approval Needed");
					break;
				case 6:
					status.setName("Draft Under Review - Comittee Approval Needed");
					break;
				case 7:
					status.setName("Draft Accepted");
					Person author = personServ.getByPersonID(sp.getPersonID());
					author.setPoints(author.getPoints() + 10);
					personServ.updatePerson(author);
					break;
				}
				sp.setStatus(status);
				storyPitchServ.updateStoryPitch(sp);
				System.out.println(approval);
				approvalServ.addApproval(approval);
				ctx.status(200);
			}
			else {
				Set<GenreSpecialization> gspecs = gsServ.getByGenredID(sp.getGenre().getGenreID());
				double numEditors = gspecs.size();
				Set<Approval> approvals = approvalServ.getAll();
				double numApprovals = 1.0; // starting at one since the approval being submitted approves it for this status
				for(Approval a: approvals) {
					if(a.getStoryApprovedID() == sid && a.getStatusApproved() == 7) {//checking if other committee members have approved the story
						numApprovals += 1.0;
					}
				}
				approval.setStatusApproved(7);
				System.out.println(numApprovals);
				System.out.println(numEditors);
				System.out.println(numApprovals/numEditors);
				if(((sp.getType().getTypeID() < 3) && (numApprovals/numEditors > 0.5)) || ((numApprovals > 1) && sp.getType().getTypeID() == 3)) {
					Status status = sp.getStatus();
					status.setStatusID(7);
					status.setName("Draft Accepted");
					storyPitchServ.updateStoryPitch(sp);
					Person author = personServ.getByPersonID(sp.getPersonID());
					if(sp.getType().getTypeID() == 1) {
						author.setPoints(author.getPoints() + 50);
					}
					else {
						author.setPoints(author.getPoints() + 25);
					}
					personServ.updatePerson(author);
				}
			}
			System.out.println(approval);
			approvalServ.addApproval(approval);
			ctx.status(200);
		}
	}
	
}
