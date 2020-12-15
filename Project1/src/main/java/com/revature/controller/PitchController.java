package com.revature.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.revature.app.SpmsAppJavalin;
import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.core.util.FileUtil;
import io.javalin.http.Context;

public class PitchController {
	private static PitchService pitchServ = new PitchServiceImpl();
	
	public static void submitPitch(Context ctx) {
		System.out.println("Submitting a pitch");
		Pitch newPitch = pitchServ.parseContext(ctx.body());
		try {
			Integer newId = pitchServ.addPitch(newPitch);
			if (!pitchServ.checkShouldHold(newId)) {
				newPitch.getPitchStage().setId(2);
				newPitch.getReviewStatus().setId(2);
				pitchServ.updatePitch(newPitch);
			}
		} catch (Exception e) {
			System.out.println("An exception occurred");
			ctx.status(500);
			return;
		}
		ctx.status(200);
	}
	
	public static void acceptPitch(Context ctx) {
		
	}
	
	public static void rejectPitch(Context ctx) {
	}
	
	public static void uploadFile(Context ctx) {
		List<String> uploadedFiles = new ArrayList<>();
		ctx.uploadedFiles("files[]").forEach(file -> {
			FileUtil.streamToFile(file.getContent(), SpmsAppJavalin.USER_FILE_LOC + "/temp/" + file.getFilename());
			String newPath = pitchServ.updateFilePaths(file.getFilename());
			uploadedFiles.add(newPath);
		});
		if (uploadedFiles.isEmpty() || uploadedFiles.equals(null)) {
			ctx.status(500);
		} else {
			ctx.status(200);
		}

	}
	
	public static void downloadFile(Context ctx) {
		String pitchId = ctx.pathParam("id");
		String fileName = ctx.pathParam("name");
		String filePath = SpmsAppJavalin.USER_FILE_LOC + "/pitch_" + pitchId + "/pitch/" + fileName;
		System.out.println(filePath);
		File f = new File(filePath);
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String mimeType = fileNameMap.getContentTypeFor(f.getName());
		
		try {
			ctx.result(new FileInputStream(f))
			.contentType(mimeType)
			.header("Content-Disposition", "attachment; filename=" + f.getName());
			ctx.status(200);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(500);
		}
	}
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch p = pitchServ.getPitchById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByAuthor(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Pitch> p = pitchServ.getPitchesByAuthor(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchByGenre(Context ctx) {
		String[] stringIds = ctx.pathParam("ids").replaceAll("[^0-9,]","").split(",");
		Integer[] genreIds = new Integer[stringIds.length];
		for (int i = 0; i < stringIds.length; i++) {
			genreIds[i] = Integer.parseInt(stringIds[i]);
//			System.out.println(genreIds[i]);
		}
		Boolean withinGenre = Boolean.valueOf(ctx.pathParam("within_genre"));
		System.out.println("getting pitches by genre");
		Set<Pitch> p = pitchServ.getPitchesByGenre(withinGenre, genreIds);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByStoryType(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Pitch> p = pitchServ.getPitchesByStoryType(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByPitchStage(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Pitch> p = pitchServ.getPitchesByPitchStage(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByReviewStatus(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Pitch> p = pitchServ.getPitchesByReviewStatus(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByPriority(Context ctx) {
		String priority = String.valueOf(ctx.pathParam("priority_pitch"));
		Set<Pitch> p = pitchServ.getPitchesByPriority(priority);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllPitches(Context ctx) {
		Set<Pitch> pitches = pitchServ.getAllPitches();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updatePitch(Context ctx) {
		Pitch tempPitch = pitchServ.parseContext(ctx.body());
		try {
			pitchServ.updatePitch(tempPitch);
			if (tempPitch.getPitchStage().getId() == 5 && tempPitch.getReviewStatus().getId() >= 4) pitchServ.releaseHold(tempPitch);
		} catch (Exception e) {
			System.out.println("An exception has occured");
			ctx.status(200);
			return;
		}
		ctx.status(202);
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		pitchServ.deletePitch(pitch);
		ctx.status(204);
	}
	
	// Fetching additional classes for front-end
	public static void getGenres(Context ctx) {
		List<Genre> genres = pitchServ.getAllGenre();
		if (genres != null) {
			ctx.status(200);
			ctx.json(genres);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getStoryTypes(Context ctx) {
		List<StoryType> types = pitchServ.getAllStoryType();
		if (types != null) {
			ctx.status(200);
			ctx.json(types);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchStages(Context ctx) {
		List<PitchStage> stages = pitchServ.getAllPitchStage();
		if (stages != null) {
			ctx.status(200);
			ctx.json(stages);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getReviewStatus(Context ctx) {
		List<ReviewStatus> status = pitchServ.getAllReviewStatus();
		if (status != null) {
			ctx.status(200);
			ctx.json(status);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPriorities(Context ctx) {
		List<String> priorities = pitchServ.getPriorities();
		if (priorities != null) {
			ctx.status(200);
			ctx.json(priorities);
		} else {
			ctx.status(404);
		}
	}
}
