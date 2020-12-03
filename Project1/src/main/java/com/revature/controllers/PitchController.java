package com.revature.controllers;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.Form;
import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Status;
import com.revature.services.PitchService;

import io.javalin.http.Context;

public class PitchController {
	
	private static PitchService pitchServ = new PitchService(); 
	
	public static void getPendingPitches(Context ctx) { /* TODO : implement */ }
	public static void getPitchById(Context ctx) { /* TODO : implement */ }
	public static void updatePitch(Context ctx) { /* TODO : implement */ }
	public static void getAllPitches(Context ctx) { /* TODO : implement */ }
	public static void rejectPitch(Context ctx) { /* TODO : implement */ }
	public static void acceptPitch(Context ctx) { /* TODO : implement */ }
	
	
	public static void deletePitch(Context ctx) { 
		
		Integer id = Integer.parseInt( ctx.pathParam("id") ); 
		System.out.println(id); 
		ctx.status(200); 
	}
	
	public static void addPitch(Context ctx) {
		
		System.out.println( ctx.body() );
	    GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    Gson gson = builder.create(); 

	    
	    try {
		    Pitch deserializedPitch;	    
		    deserializedPitch = gson.fromJson( ctx.body(), Pitch.class);
		    deserializedPitch.setId(3);
		    ctx.json( gson.toJson(deserializedPitch) );
			ctx.status(200);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ctx.status(500);
	    }
	
	}
	
	public static void getPitchByAuthorId(Context ctx) { 
		
	    GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    Gson gson = builder.create(); 
	    Person deserializedPerson, queriedPerson; 
		
		Pitch dummyPitchA = new Pitch(); 
		dummyPitchA.setId(1);
		dummyPitchA.setTitle("On the Joys of Academic Philosophy");
		dummyPitchA.setTagLine("A tale of one nigga's harrowing escape from cognitive fiefdom"); 
		
		Genre dummyGenre = new Genre(); 
		dummyGenre.setId(-1);
		dummyGenre.setName("Nonfiction");
		dummyPitchA.setGenre(dummyGenre);
		Form dummyForm = new Form(); 
		dummyForm.setId(-1);
		dummyForm.setName("Article");
		dummyPitchA.setForm(dummyForm);
		Status dummyStatus = new Status();
		dummyStatus.setId(-1);
		dummyStatus.setName("pending");
		dummyPitchA.setStatus(dummyStatus);
		
		
		Pitch dummyPitchB = new Pitch(); 
		dummyPitchB.setId(2);
		dummyPitchB.setTitle("Building a Dream");
		dummyPitchB.setTagLine("One nigga's journey from philosophy to software engineering"); 
		
		Genre dummyGenreB = new Genre(); 
		dummyGenreB.setId(-1);
		dummyGenreB.setName("Nonfiction");
		dummyPitchB.setGenre(dummyGenreB);
		Form dummyFormB = new Form(); 
		dummyFormB.setId(-1);
		dummyFormB.setName("Article");
		dummyPitchB.setForm(dummyFormB);
		Status dummyStatusB = new Status();
		dummyStatusB.setId(-1);
		dummyStatusB.setName("pending");
		dummyPitchB.setStatus(dummyStatusB);
		
		Pitch pitches[] = { dummyPitchA, dummyPitchB}; 
		
	    try {
		    ctx.json( gson.toJson(pitches) );
			ctx.status(200);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ctx.status(500);
	    }
		
	}
	
	
	
}



/*


			// all requests to /cats go to this handler
			path ("api/pitches", () -> {
				get(PitchController::getPendingPitches); // get available cats is the default
				post(PitchController::addPitch); // add a cat
				path(":id", () -> {
					get(PitchController::getPitchById); // get a cat by id
					put(PitchController::updatePitch); // update a cat
					delete(PitchController::deletePitch); // delete a cat
				});
				path ("authorId/:id", () -> {
					get(PitchController::getPitchByAuthorId); // get a cat by id

				});
				path ("all", () -> {
					get(PitchController::getAllPitches); // get all cats
				});
				path ("reject/:id", () -> {
					put(PitchController::rejectPitch); // adopt a cat by its id
				});
				path ("accept/:id", () -> {
					put(PitchController::acceptPitch); // adopt a cat by its id
				});
			});

*/




