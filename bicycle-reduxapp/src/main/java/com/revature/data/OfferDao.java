package com.revature.data;

import com.revature.beans.Offer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfferDao {
    private static Connection connection;

    public OfferDao(Connection c) {
        this.connection = c;
    }

    public static String acceptOffer(Integer offerId, Integer bicycleId) {
        try {
            connection.createStatement()
                    .executeQuery("DELETE from Offer where bicycle_id = " + bicycleId + " AND id <> " + offerId);
            final ResultSet rs = connection.createStatement()
                    .executeQuery("UPDATE Offer SET complete = TRUE WHERE id == " + offerId);
            String userName = null;
            while (rs.next()) {
                userName = rs.getString("user_id");
            }
            return userName;
        } catch (final Exception e) {
            return null;
        }
    }

    public static boolean addOffer(final Offer offer) {
        try {
            final String query = "INSERT INTO Offer (id, user_id, bicycle_id, price, complete) "
                    + String.format("VALUES (%d, %s, %s, %d, %b)", offer.getId(), offer.getUserName(), offer.getBicycleId(), offer.getPrice(), offer.getComplete());

            connection.createStatement().executeQuery(query);
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    public static List<Offer> getOffersForBicycle(final Integer bicycleId) {
        final List<Offer> list = new ArrayList<Offer>();
        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM Offer WHERE bicycle_id=" + bicycleId);
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (final Exception e) {
            return list;
        }

    }

    public static List<Offer> getCompleteOffers() {
        final List<Offer> list = new ArrayList<Offer>();
        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM Offer WHERE complete = TRUE");
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (final Exception e) {
            return list;
        }

    }

    public static List<Offer> getOffersForUser(final String userName) {
        final List<Offer> list = new ArrayList<Offer>();

        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM Offer WHERE user_id = " + userName);
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (final Exception e) {
            return list;
        }

    }

    public static List<Offer> rejectOffer(final String userName) {
        final List<Offer> list = new ArrayList<Offer>();

        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM Offer WHERE user_id = " + userName);
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (final Exception e) {
            return list;
        }

    }

    public static List<Offer> rejectAllOffer(final String userName) {
        final List<Offer> list = new ArrayList<Offer>();

        try {
            final Statement stmt = connection.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM Offer WHERE user_id = " + userName);
            while (rs.next()) {
                list.add(new Offer(rs.getInt("id"), rs.getString("user_id"), rs.getInt("bicycle_id"), rs.getInt("price"), rs.getBoolean("complete")));
            }
            return list;
        } catch (final Exception e) {
            return list;
        }

    }

}
