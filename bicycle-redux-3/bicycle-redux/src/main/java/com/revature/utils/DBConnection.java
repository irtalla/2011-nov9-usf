package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection(String dbUrl, String userName, String password) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(dbUrl, userName, password);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to db");
            return c;
        }
    }
}
