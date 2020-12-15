package com.trms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.trms.beans.Activity;
import com.trms.beans.ApprovalStatus;
import com.trms.beans.EventType;
import com.trms.beans.Person;
import com.trms.beans.Request;
import com.trms.beans.RequestType;
import com.trms.services.ActivityService;
import com.trms.services.ActivityServiceImpl;
import com.trms.services.RequestService;
import com.trms.services.RequestServiceImpl;

import io.javalin.http.Context;

public class RequestController {
	private static RequestService rs = new RequestServiceImpl();
	private static ActivityService as = new ActivityServiceImpl();
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void getMyRequests(Context ctx) {
		Person loggedPerson = ctx.sessionAttribute("user");
		List<Request> rList = rs.getByPersonId(loggedPerson.getId());
		System.out.println(rList.size() + "this is the rlist size");
		if (rList != null) {
			ctx.status(200);
			ctx.json(rList);
		} else {
			ctx.json("Not here");
			ctx.status(404);
		}
	}

	public static void getById(Context ctx) {
		Request r = new Request();
		int id = Integer.valueOf(ctx.pathParam("id"));
		r = rs.getById(id);
		if (r.getId() > 0) {
			System.out.println(r.getId());
			ctx.status(200);
			ctx.json(r);
		} else {
			ctx.json("Not here");
			ctx.status(404);
		}
	}

	public static void updateById(Context ctx) {

	}

	public static void newRequest(Context ctx) {
		objectMapper.registerModule(new JavaTimeModule());
		Request r = new Request();
		r = ctx.bodyAsClass(Request.class);

		LocalDateTime due = LocalDateTime.now().plusDays(7);
		r.setDue(due);

		//used to set projected amount to the percentage of cost auto allowed
		RequestType rt = r.getRequestType();
		switch (rt.getId()) {
			case 1:
				r.setProjectedAmount(r.getCost() * .8f);
				break;
			case 2:
				r.setProjectedAmount(r.getCost() * .6f);
				break;
			case 3:
				r.setProjectedAmount(r.getCost() * .75f);
				break;
			case 4:
				r.setProjectedAmount(r.getCost());
				break;
			case 5:
				r.setProjectedAmount(r.getCost() * .3f);
		}
		List<Request> rList = rs.getByPersonId(r.getRequestor().getId());
		float sum = 0f;
		float remainder = 1000f;
		LocalDateTime ldt = null;
		//getting pending or completed requests and the amount pending/disbursed
		if (rList.size() > 0) {
			for (Request request : rList) {
				if (request.getClosed() == null) {
					ldt = request.getDue();

					//if the request is completed and money disbursed, set to close date
				} else if (request.getDue() == null) {
					ldt = request.getClosed();
				}

				if (ldt.getYear() == LocalDateTime.now().getYear()) {
					sum += request.getAmount();
				}
			}
		}


		//does the math for max $1000 total, sets remaining cash left for this person
		if (sum > 0f && sum < remainder) {
			remainder -= sum;
		} else if (sum > remainder) remainder = 0f;

		if (r.getProjectedAmount() > remainder) r.setProjectedAmount(remainder);


//		ApprovalStatus a = new ApprovalStatus();
//		r.setStatus(a);

		int id = rs.addRequest(r);

		if (id > 0) {
			r.setId(id);
			ctx.status(200);
			ctx.json(r);
			Activity ac = new Activity();
			EventType ev = new EventType();
			ev.setId(1);
			ev.setName("Ticket created");
			ac.setEvent(ev);
			ac.setCreator(r.getRequestor());
			ac.setName(r.getRequestor().getFirst() + " " + r.getRequestor().getLast() + " created the request.");
			ac.setRequestId(r.getId());
			ac.setTimestamp(LocalDateTime.now());
			as.addActivity(ac);

		} else ctx.status(404);

	}

	public static void getByManagerOrDheadId(Context ctx) {
		Person loggedPerson = ctx.sessionAttribute("user");
		List<Request> rList = new ArrayList<>();

		if (loggedPerson.getRole().getId() == 1) {
			rList = rs.getBydHeadId(loggedPerson.getId());

		} else if (loggedPerson.getRole().getId() == 2) {
			rList = rs.getByManagerId(loggedPerson.getId());
		}
		rList.removeIf(request -> request.getClosed() != null);

		if (rList.size() > 0) {
			ctx.status(200);
			ctx.json(rList);
		} else {
			ctx.json("Not here");
			ctx.status(404);
		}
	}

	public static void getBydHeadId(Context ctx) {

	}

	public static void requestUpdate(Context ctx) {
//		Person p = ctx.sessionAttribute("user");
		Request u = ctx.bodyAsClass(Request.class);
		Activity ac = u.getActivity().get(0);
		ApprovalStatus s = u.getStatus();
//		Activity ac = ctx.sessionAttribute("activity");
//		ApprovalStatus s = ctx.sessionAttribute("status");
		Request r = new Request();
//		JSONPObject j = ctx.bodyAsClass(JSONPObject.class);
//		System.out.println(j.toString());
//		System.out.println((String) ctx.sessionAttribute("activity"));
		System.out.println(ac.getRequestId() + " is the request id");
		r = rs.getById(ac.getRequestId());
		r.setStatus(s);

		EventType ev = new EventType();
		ev.setId(2);
		ev.setName("Status updated");
		ac.setEvent(ev);
		ac.setTimestamp(LocalDateTime.now());
		ac.setName(ac.getCreator().getFirst() + " " + ac.getCreator().getLast() + " " +
				"updated the status to: " + s.getName());


		//set function to update amount to projected amount or amount set by benco
		if (u.getAmount() > 0) {
			r.setAmount(u.getAmount());
		}

		if (r.getStatus().getId() > 13) {
			if (r.getAmount() == 0) {
				r.setAmount(r.getProjectedAmount());
			}
		}

		//checks if the status is denied, cancelled, complete, or failed, sets due to null and closed to now
		if (s.getId() == 2 || s.getId() == 3 || s.getId() == 20 || s.getId() == 21 || s.getId() == 22) {
			r.setDue(null);
			r.setClosed(LocalDateTime.now());
		}
		rs.update(r);

		//if update r is successful, add activity
		Activity b = as.addActivity(ac);
//		System.out.println("this is the b id " + b.getId());

		if (b.getId() > 0) {
			ctx.status(200);
			ctx.json(b);
		} else ctx.status(404);

	}

	public static void activityAddComment(Context ctx) {
		Activity ac = new Activity();
		ac = ctx.bodyAsClass(Activity.class);
//		System.out.println(ac.toString());
		EventType ev = new EventType();
		ev.setId(4);
		ev.setName("Comment added");
		ac.setEvent(ev);
		ac.setTimestamp(LocalDateTime.now());
		ac.setName(ac.getCreator().getFirst() + " " + ac.getCreator().getLast() + " " + "commented: ");
		Activity b = as.addActivity(ac);
		System.out.println("this is the b id " + b.getId());
		if (b.getId() > 0) {
			ctx.status(200);
			ctx.json(b);
		} else ctx.status(404);

	}

	public static void activityAttachmentAdd(Context ctx) {
		Activity ac = new Activity();
		ac = ctx.bodyAsClass(Activity.class);
//		System.out.println(ac.toString());


		EventType ev = new EventType();
		ev.setId(3);
		ev.setName("Attachment added");
		ac.setEvent(ev);
		ac.setTimestamp(LocalDateTime.now());
		ac.setName(ac.getCreator().getFirst() + " " + ac.getCreator().getLast() + " added an attachment.");
		Activity b = as.addActivity(ac);
//		System.out.println("this is the b id " + b.getId());
		if (b.getId() > 0) {
			ctx.status(200);
			ctx.json(b);
		} else ctx.status(404);
	}

	public static void activityGetAttachment(Context context) {
	}

	public static void HRGetOpen(Context ctx) {
		List<Request> rList = rs.getAll();
		List<Request> rList2 = new ArrayList<>();
		for (Request request : rList) {
//			System.out.println(request.getClosed());
//			if (request.getClosed() != null) {
//				rList.remove(request);
//			}
			if (request.getStatus().getId() == 8 || request.getStatus().getId() == 13 ||
					request.getStatus().getId() == 18 || request.getStatus().getId() == 19) {
				rList2.add(request);
			}
		}
		if (rList2.size() > 0) {
			ctx.status(200);
			ctx.json(rList2);
		} else {
			ctx.json("Not here");
			ctx.status(404);
		}
	}

	public static void autoApprove() {
		List<Request> rList = rs.getAll();
		for (Request r : rList) {
			LocalDateTime dueDate = r.getDue();
			LocalDateTime now = LocalDateTime.now();
			if (dueDate.isBefore(now)) {
				if (r.getStatus().getId() == 1) {
					r.setDue(now.plusDays(7));
					ApprovalStatus s = new ApprovalStatus();
					s.setId(5);
					s.setName("Manager approved, awaiting dhead");
					r.setStatus(s);
					rs.update(r);
				} else if (r.getStatus().getId() == 5) {
					r.setDue(now.plusDays(7));
					ApprovalStatus s = new ApprovalStatus();
					s.setId(8);
					s.setName("Dhead approval, awaiting benco");
					r.setStatus(s);
					rs.update(r);

				}
			}
		}
	}
}
