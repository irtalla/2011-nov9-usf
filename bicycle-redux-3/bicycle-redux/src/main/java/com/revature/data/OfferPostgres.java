package com.revature.data;

import com.revature.beans.Offer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferPostgres implements OfferDAO {
    // Table schema
    // Int id, String user_id, Int bicycle_id, Int price, Boolean complete

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    static String tableName = "offers";

    public boolean acceptOffer(Integer offerId, Integer bicycleId) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("DELETE from %s where bicycle_id=? AND id<>?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bicycleId);
            stmt.setInt(2, offerId);
            stmt.executeUpdate();

            String query2 = String.format("UPDATE %s SET complete=? WHERE id=?", tableName);
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setBoolean(1, true);
            stmt2.setInt(2, offerId);
            stmt2.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addOffer(Offer offer) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("INSERT INTO %s (id, user_id, bicycle_id, price, complete) VALUES (?, ?, ?, ?, ?)", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, offer.getId());
            stmt.setString(2, offer.getUserName());
            stmt.setInt(2, offer.getBicycleId());
            stmt.setInt(2, offer.getPrice());
            stmt.setBoolean(2, offer.getComplete());

            stmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Offer> getOffersForBicycle(Integer bicycleId) {
        List<Offer> list = new ArrayList<Offer>();
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s WHERE bicycle_id=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bicycleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    public List<Offer> getCompleteOffers() {
        List<Offer> list = new ArrayList<Offer>();
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s WHERE complete=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    public List<Offer> getOffersForUser(String userName) {
        List<Offer> list = new ArrayList<Offer>();

        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s WHERE user_id=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    public String getOfferOwner(Integer offerId) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT user_id FROM %s WHERE offerId=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, offerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("user_id");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
