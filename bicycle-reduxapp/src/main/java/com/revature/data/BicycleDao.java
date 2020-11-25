package com.revature.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.revature.beans.Bicycle;

public class BicycleDao {
    private Connection connection;

    public BicycleDao() {
        this.connection = connection ;
    }

    public List<Bicycle> getAllBicycles() {
        List<Bicycle> list = new ArrayList<Bicycle>();
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BICYCLE;");
            while (rs.next()) {
                Integer id = Integer.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String owner = rs.getString("owner_id");
                list.add(new Bicycle(id, name, owner));
            }
            return list;
        } catch (Exception e) {
            return list;
        }

    }

    public List<Bicycle> getBicyclesForUser(String userName) {
        List<Bicycle> list = new ArrayList<Bicycle>();
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BICYCLE WHERE owner_id = " + userName + ";");
            while (rs.next()) {
                Integer id = Integer.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String ownerName = rs.getString("owner_id");
                list.add(new Bicycle(id, name, ownerName));
            }
            return list;
        } catch (Exception e) {
            return list;
        }

    }

    public Bicycle addBicycle(Integer id, String name, String owner) {
        try {
            String query = "INSERT INTO BICYCLE (id, name, owner_id) "
                    + String.format("VALUES (%d, %s, %s)", id, name, owner);

            this.connection.createStatement().executeQuery(query);
            return new Bicycle(id, name, owner);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean removeBicycle(Integer bicycleId) {
        try {
            this.connection.createStatement()
                    .executeQuery("DELETE from BICYCLE where id = " + bicycleId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updateOwner(Integer bicycleId, String userName) {
        try {
            String query = "UPDATE BICYCLE " + "SET owner_id = " + userName + " WHERE id=" + bicycleId;

            this.connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            return;
        }

    }

	public static Bicycle getById(Integer id) {
		return null;
	}

	public static Set<Bicycle> getMyBicycles() {
		return null;
	}



}
