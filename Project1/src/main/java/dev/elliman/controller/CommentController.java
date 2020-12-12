package dev.elliman.controller;

import java.util.List;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.beans.RFC;
import dev.elliman.services.ClaimService;
import dev.elliman.services.ClaimServiceImpl;
import dev.elliman.services.RFCService;
import dev.elliman.services.RFCServiceImpl;
import io.javalin.http.Context;

public class CommentController {
	private static  ClaimService cs = new ClaimServiceImpl();
	private static RFCService rfcs = new RFCServiceImpl();
	
	public static void makeRFC(Context ctx) {
		String rfcString = ctx.body().substring(1, ctx.body().length()-1);
		String[] rfcParts = rfcString.split(",");
		
		RFC rfc = new RFC();
		
		Person user = ctx.sessionAttribute("user");
		rfc.setCommenter(user);
		
		for(String part : rfcParts) {
			String field = part.split(":")[0];
			String value = part.split(":")[1];
			//remove "
			field = field.substring(1, field.length()-1);
			value = value.substring(1, value.length()-1);
			
			if("claim".equals(field)) {
				Integer id = Integer.valueOf(value);
				Claim c = cs.getClaimByID(id);
				rfc.setClaim(c);
			} else if("description".equals(field)) {
				rfc.setDescription(value);
			}
		}
		
		rfcs.makeRFC(rfc);
	}
	
	public static void getCommentsForClaim(Context ctx) {
		Integer claimID = Integer.valueOf(ctx.pathParam("id"));
		List<RFC> rfcList = rfcs.getRFCByClaim(claimID);
		
		ctx.json(rfcList);
		ctx.status(200);

	}
	
	public static void answerComment(Context ctx) {
		String body = ctx.body();
		body = body.substring(1, body.length()-1);
		String[] rfcAnswerParts = body.split(",");
		Integer id = null;
		String answer = null;
		for(String part : rfcAnswerParts) {
			String field = part.split(":")[0];
			field = field.substring(1,field.length()-1);
			String value = part.split(":",2)[1];
			value = value.substring(1, value.length()-1);
			if("id".equals(field)) {
				id = Integer.valueOf(value);
			} else if("answer".equals(field)) {
				answer = value;
			}
		}
		
		Boolean success = rfcs.answerRFC(id,answer);
		if(success) {
			ctx.status(200);
		} else {
			ctx.status(500);
		}
	}
}
