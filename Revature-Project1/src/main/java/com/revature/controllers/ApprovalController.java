package com.revature.controllers;

import com.revature.services.AuthorFunctions;
import com.revature.services.AuthorServicesImpl;
import com.revature.services.EditorFunctions;
import com.revature.services.EditorServicesImpl;

import io.javalin.http.Context;

public class ApprovalController {
	private static EditorFunctions esi = new EditorServicesImpl();
	private static AuthorFunctions asi = new AuthorServicesImpl();
	
	public static void editorRejectProposedWork(Context ctx) {
		
	}
	
	public static void acceptProposedWork(Context ctx) {
		
	}
	
	public static void addRequestedInfo(Context ctx) {
		
	}
	
	public static void getRequestedInfo(Context ctx) {
		
	}
	
	public static void authorRemoveProposedWork(Context ctx) {
		
	}
}
