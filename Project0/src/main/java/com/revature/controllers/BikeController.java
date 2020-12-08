package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.services.BikeService;
import com.revature.services.BikeServiceImpl;
import com.revature.services.OfferService;
import com.revature.services.OfferServiceImpl;

import io.javalin.http.Context;

public class BikeController {
	private static BikeService bikeServ;
	private static OfferService offerServ;
	
	static {
		bikeServ = new BikeServiceImpl();
		offerServ = new OfferServiceImpl();
	}
	
	public static void getBikeById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Bike bike = bikeServ.getBikeById(id);
		if (bike != null) {
			ctx.status(200);
			ctx.json(bike);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllBikes(Context ctx) {
		
	}
	
	public static void getAvailableBikes(Context ctx) {
		
	}
	
	public static void addBike(Context ctx) {
		Bike bike = ctx.bodyAsClass(Bike.class);
		
		//System.out.println(bike.toString());
		Bike addedBike = bikeServ.addBike(bike);
		if(addedBike != null) {
			ctx.status(200);
			ctx.json(bike);
		}else {
			ctx.status(400);
		}
	}
	
	public static void updateBike(Context ctx) {
		
	}
	
	public static void deleteBike(Context ctx) {
		
	}
	
	public static void makeOfferToBuyBike(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Bike bike = bikeServ.getBikeById(id);
		
		Person offerer = ctx.bodyAsClass(Person.class);
		
		if(offerer != null) {
			if(bike.getStatus().getName() == "Adopted") {
				Set<Bike> purchasable = bikeServ.getAvailableBikes();
				ctx.json(purchasable);
			}else {		
				Offer newOffer = new Offer();
				newOffer.setBike(bike);
				newOffer.setPerson(offerer);
				offerServ.makeOffer(newOffer);
				ctx.json(newOffer);
				ctx.status(200);
			}
		}else {
			ctx.status(401);
		}
	}
}
