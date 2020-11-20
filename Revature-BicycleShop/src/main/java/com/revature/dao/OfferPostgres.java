package com.revature.dao;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {

	ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Set<Offer> getAllOffers() {
		Set<Offer> allOffers = new HashSet<Offer>();
		
		try(Connection conn = cu.getConnection()){
			//this long string means "merge offer and bicycles where the bicycles are being
			//have offers made for them, then add the info of the 
			
			String sql = "select offer_bike.offer_id, offer_bike.offer_maker_id, offer_bike.bike_id, "
					+ "offer_bike.offer, offer_bike.status, offer_bike.bike_model, offer_bike.bike_type, "
					+ "offer_bike.description, offer_bike.seller_id, offer_bike.price, offer_bike.status, "
					+ "offer_bike.owner_id, empl.user_id, empl.user_username, empl.user_password, "
					+ "empl.user_role, usr.user_id, usr.user_username, "
					+ "usr.user_password, usr.user_role from"
					    + "(select offer_id, offer_maker_id, offer, status, bicycles.bike_id, bike_model, "
					    + "bike_type, description, seller_id, price, status, owner_id from offers join bicycles "
					    + "on offer.bike_id = bicycles.bike_id as offer_bike)"
					+ "join bike_shop_users as empl where empl.user_id = offer_bike.seller_id"
					+ "join bike_shop_users as usr where usr.user_id = offer_bike.owner_id)";
			
			
		}
		catch(Exception e) {
			
		}
	}

	@Override
	public boolean addAnOffer(Offer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAnOffer(Offer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Offer o) {
		// TODO Auto-generated method stub
		
	}

}
