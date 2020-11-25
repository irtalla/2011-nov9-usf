package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.beans.*;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
    // Table schema
    // String id, String type

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    private static String tableName = "users";

    public Person createUser(String userName, String type) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("INSERT INTO %s (id, type) VALUES (?, ?)", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, type);

            stmt.executeUpdate();
            return new Person(userName, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person loginUser(String userName) {
        try {
            Connection conn = cu.getConnection();
            String query = String.format("SELECT * FROM %s WHERE id = ?", tableName);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(rs.getString("id"), rs.getString("type"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
