package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import com.revature.beans.*;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.DBConnection;

public abstract class PersonDao {

	//private ConnectionUtil cu=ConnectionUtil.getConnectionUtil();

    private static Connection connection;

    public PersonDao(Connection c) {
        connection = c;
    }

    public static Person createUser(String userName, String type) {
        // ConnectionUtil cu;
        System.out.println("usernmae" + userName + "asdasd" + type);

        try {
            // String query = "INSERT INTO usertable VALUES (" + userName + ", " + type +
            // ")";
            String query = "insert into userTable values (?,?)";
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, type);
            ps.executeUpdate();
            System.out.println("usernmae" + userName + "asdasd" + type);
            ResultSet rs = ps.getGeneratedKeys();

            connection.createStatement().executeUpdate(query);
            connection.commit();
            return new Person(userName, type);
            
        } catch (Exception e) 
        {
            return null;
        }

    }

    public static Person loginUser(String userId) {
       Person p=new Person();
    	try {
            String query = "SELECT * FROM usertable WHERE id = ?" ;

            PreparedStatement ps= connection.prepareStatement(query);
            ps.setString(1, userId);
         //   Statement stmt = this.connection.createStatement();
           
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
                String userName = rs.getString("id");
                String type = rs.getString("type");
                connection.commit();
                System.out.println(userName+"ppppp"+type);
                p.setUserName(userName);
                p.setType(type);
                return p;
               // return new Person(userName, type);
            }
        } catch (Exception e) {

        }

        return null;
     
    }


    public abstract Person getUsername(String username);

    public abstract Set<Person> getAll();

    public abstract void update(Person t);
}
