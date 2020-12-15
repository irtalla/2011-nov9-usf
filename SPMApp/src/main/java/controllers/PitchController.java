package controllers;

import java.util.Set;

import beans.Usr;
import io.javalin.http.Context;
import beans.Approval;
import beans.Draft;
import beans.Pitch;
import services.UsrService;
import services.UsrServiceImpl;
import services.PitchService;
import services.PitchServiceImpl;

public class PitchController {
	private static PitchService pitchServ = new PitchServiceImpl();
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if (pitch != null) {
			ctx.status(200);
			ctx.json(pitch);
		} else {
			ctx.status(404);
		}
	}
	public static void getApprovalById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Approval approval = pitchServ.getApprovalById(id);
		if (approval != null) {
			ctx.status(200);
			ctx.json(approval);
			} else {
				ctx.status(404);
			}
	}
	
	public static void getAllPitches(Context ctx) {
		Set<Pitch> pitches = pitchServ.getPitches();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPendingPitches(Context ctx) {
		System.out.println("Getting available pitches!");
		Set<Pitch> pitchSet = pitchServ.getPendingPitches();
		if (pitchSet != null) {
			ctx.status(200);
			ctx.json(pitchSet);
		} else {
			ctx.status(404);
		}
	}
	public static void addPitch(Context ctx) {
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		pitchServ.addPitch(pitch);
		ctx.status(201);
	}
	public static void getDrafts(Context ctx) {
		Set<Draft> draftSet = pitchServ.getDrafts();
		if (draftSet != null) {
			ctx.status(200);
			ctx.json(draftSet);
		} else {
			ctx.status(404);
		}
	}
	public static void acceptApproval(Context ctx) {
		Draft draft = ctx.bodyAsClass(Draft.class);
		pitchServ.acceptDraft(draft);
		ctx.status(201);
	}
	public static void updatePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		if (pitch != null) {
			ctx.status(200);
			pitchServ.updatePitch(pitch);
		} else {
			ctx.status(404);
		}
	}
	public static void updateApproval(Context ctx) {
		Approval approval = ctx.bodyAsClass(Approval.class);
		if (approval != null) {
			ctx.status(200);
			pitchServ.updateApproval(approval);
		} else {
			ctx.status(404);
		}
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if (pitch != null) {
			pitchServ.removePitch(pitch);
			ctx.status(204);
		} else {
			ctx.status(204);
		}
	}
	
	public static void acceptPitch(Context ctx) {
		Usr loggedUsr = ctx.sessionAttribute("user");
		Pitch pitch = pitchServ.getPitchById(Integer.valueOf(ctx.pathParam("id")));
		if (loggedUsr != null) {
			if (pitch != null) {
				pitchServ.approvePitch(loggedUsr, pitch);
				ctx.status(200);
			} else {
				ctx.status(409);
			}
		} else {
			ctx.status(401);
		}
	}
	public static void getApprovals(Context ctx) {
		System.out.println("getApprovals reached");
		Set<Approval> approvalSet = pitchServ.getApprovals();
			if (approvalSet != null) {
				ctx.status(200);
				ctx.json(approvalSet);
			} else {
				ctx.status(404);
			}
	}
	public static void approvePitch(Context ctx) {
		Approval approval = ctx.bodyAsClass(Approval.class);
		pitchServ.addApproval(approval);
		ctx.status(201);
	}
	//TODO 
	public static void makeSuggestion(Context ctx) { /* We need to make a Suggestion bean to create the object which we send to DB; just content and 
												     ID fkeys */

	}
	
}