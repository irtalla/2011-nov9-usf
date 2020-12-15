package com.revature.services;

import java.time.LocalDateTime;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.revature.data.RequestDAO;
import com.revature.data.RequestDAOFactory;
import com.revature.data.ReviewStatusDAO;
import com.revature.data.ReviewStatusDAOFactory;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOFactory;
import com.revature.models.Requests;
import com.revature.models.ReviewStatus;
import com.revature.models.User;

public class RequestServiceImpl implements RequestService {
	private RequestDAO reqDao;
	private UserDAO uDao;
	private ReviewStatusDAO rsDao;
	
	public RequestServiceImpl() {
		RequestDAOFactory reqFactory = new RequestDAOFactory();
		reqDao = reqFactory.getRequestDao();
		UserDAOFactory uFactory = new UserDAOFactory();
		uDao = uFactory.getUserDao();
		ReviewStatusDAOFactory rsFactory = new ReviewStatusDAOFactory();
		rsDao = rsFactory.getReviewStatusDao();
	}
	
	@Override
	public Integer addRequest(Requests t) throws Exception {
		return reqDao.add(t);
	}

	@Override
	public Requests getRequestById(Integer id) {
		return reqDao.getById(id);
	}

	@Override
	public Set<Requests> getRequestByRequester(Integer id) {
		User u = uDao.getById(id);
		return reqDao.getByRequester(u);
	}

	@Override
	public Set<Requests> getRequestByRequestee(Integer id) {
		User u = uDao.getById(id);
		return reqDao.getByRequestee(u);
	}

	@Override
	public Set<Requests> getAllRequests() {
		return reqDao.getAll();
	}

	@Override
	public void updateRequests(Requests t) throws Exception{
		reqDao.update(t);
	}

	@Override
	public void deleteRequests(Requests t) {
		reqDao.delete(t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Requests parseContext(String ctx) {
		System.out.println(ctx);
		ctx = "[" + ctx + "]";
		JSONParser parser = new JSONParser();
		Requests r = new Requests();
		try {
			Object obj = parser.parse(ctx);
			JSONArray array = (JSONArray)obj;
			JSONObject jObj = (JSONObject)array.get(0);
			jObj.keySet().forEach( key -> {
				switch (key.toString() ) {
					case "answer":
						r.setAnswer(jObj.get(key).toString());
						break;
					case "question":
						r.setQuestion(jObj.get(key).toString());
						break;
					case "requestee":
						JSONObject requesteeObj = (JSONObject) jObj.get(key);
						requesteeObj.keySet().forEach( uKey -> {
							if (uKey.toString().equals("id")) {
								Integer uId = Integer.parseInt(requesteeObj.get(uKey).toString());
								User u = uDao.getById(uId);
								r.setRequestee(u);
							}
						});
						break;
					case "requester":
						JSONObject requesterObj = (JSONObject) jObj.get(key);
						requesterObj.keySet().forEach( uKey -> {
							if (uKey.toString().equals("id")) {
								Integer uId = Integer.parseInt(requesterObj.get(uKey).toString());
								User u = uDao.getById(uId);
								r.setRequester(u);
							}
						});
						break;
					case "requestMadeAt":
						LocalDateTime ldt = LocalDateTime.parse(jObj.get(key).toString());
						r.setRequestMadeAt(ldt);
						break;
					case "requestStatus":
						JSONObject rsObj = (JSONObject) jObj.get(key);
						System.out.println(rsObj.toString());
						rsObj.keySet().forEach( rsKey -> {
							if (rsKey.toString().equals("id")) {
								Integer rsId = Integer.parseInt(rsObj.get(rsKey).toString());
								System.out.println(rsId);
								ReviewStatus rs = rsDao.getById(rsId);
								System.out.println(rs);
								r.setRequestStatus(rs);;
							}
						});
						break;
				}
				
			});
		} catch (ParseException pe) {
			System.out.println("Position: " + pe.getPosition());
			pe.printStackTrace();
		}
		
		System.out.println(r);
		
		return r;
	}

}
