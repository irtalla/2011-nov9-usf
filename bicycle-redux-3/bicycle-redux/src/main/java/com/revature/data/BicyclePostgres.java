package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Bicycle;
import com.revature.utils.ConnectionUtil;

public class BicyclePostgres implements BicycleDAO {
    // Table schema
    // Int id, String name, String user_id

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    private static String tableName = "bicycles";

    public List<Bicycle> getAllBicycles() {
        List<Bicycle> list = new ArrayList<Bicycle>();
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = Integer.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String owner = rs.getString("user_id");
                list.add(new Bicycle(id, name, owner));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    public List<Bicycle> getBicyclesForUser(String userName) {
        List<Bicycle> list = new ArrayList<Bicycle>();
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s WHERE user_id=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = Integer.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String ownerName = rs.getString("user_id");
                list.add(new Bicycle(id, name, ownerName));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    public Bicycle addBicycle(Integer id, String name, String owner) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("INSERT INTO %s (id, name, user_id) VALUES (?, ?, ?)", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, owner);

            stmt.executeUpdate();

            return new Bicycle(id, name, owner);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean removeBicycle(Integer bicycleId) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("DELETE from %s where id=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bicycleId);

            stmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateOwner(Integer bicycleId, String userName) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("UPDATE %s SET user_id=? WHERE id=?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setInt(2, bicycleId);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

}
