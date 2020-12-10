package com.revature.controller;

import java.util.Set;

import com.revature.beans.Committee;
import com.revature.service.CommitteeService;
import com.revature.service.CommitteeServiceImpl;

import io.javalin.http.Context;

public class CommitteeController {
	private static CommitteeService comServ = new CommitteeServiceImpl();
	
	public static void getAllCommittees(Context ctx) {
		System.out.println("im in the getall!");
		Set<Committee> coms = comServ.getCommittees();
		if(coms != null) {
			System.out.println("coms was not null!");
			System.out.println(coms.size());
			for(Committee c : coms) {
				System.out.println(c.getcommittee_name());
				
			}
			ctx.status(200);
			ctx.json(coms);
		}else {
			System.out.println("Oh my gosh there are no committees!?");
			ctx.status(404);
		}
	}


}
