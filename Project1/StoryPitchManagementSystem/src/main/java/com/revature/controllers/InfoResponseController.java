package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.InfoRequest;
import com.revature.beans.Person;
import com.revature.beans.InfoResponse;
import com.revature.beans.Type;
import com.revature.services.InfoRequestServices;
import com.revature.services.InfoRequestServicesImpl;
import com.revature.services.InfoResponseServices;
import com.revature.services.InfoResponseServicesImpl;

import io.javalin.http.Context;

public class InfoResponseController {
	private static InfoResponseServices infoResponseServ = new InfoResponseServicesImpl();
	private static InfoRequestServices infoRequestServ = new InfoRequestServicesImpl();
	
	public static void submitInfoResponse(Context ctx) {
		//System.out.println("Submitting a story in the controller");
		Integer infoRequestID = Integer.valueOf(ctx.queryParam("infoRequestID"));
		InfoRequest infoRequest = infoRequestServ.getByRequestID(infoRequestID);
		InfoResponse infoResponse = new InfoResponse();
		infoResponse.setStoryID(infoRequest.getStoryID());
		infoResponse.setRequestID(infoRequestID);
		infoResponse.setPersonRespondingID(infoRequest.getPersonRequestedID());
		infoResponse.setPersonRespondedID(infoRequest.getPersonRequestingID());
		infoResponse.setMessage(String.valueOf(ctx.queryParam("message")));
		infoResponseServ.addInfoResponse(infoResponse);
	}
	
}
