package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Person add(Person t) throws NullPointerException, NonUniqueUsernameException{
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			//default is id, userName, password, role_id
			String sql ="insert into person values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2,  t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				p=t;
				p.setId(rs.getInt("id"));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			System.out.println("Looks like that user name already exists\n"
					+ "try again, maybe add a number at the end.");
			
			//e.printStackTrace();
			if(e.getMessage().contains("violates unique"))
				throw new NonUniqueUsernameException();
		}
		return p;
	}

	@Override
	public Person getById(Integer id) throws NullPointerException{
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.id, username, passwd, person.user_role_id, user_role.role_name "
					+ "from person "
					+ "join user_role on "
					+ "user_role_id = user_role.id "
					+ "where person.id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Person();
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("user_role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
				p.setBikes(getBikesByPersonId(p.getId(),conn));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

	private Set<Bicycle> getBikesByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Bicycle> bikes = new HashSet<>();
		BicycleDAO BikeDao = new BicyclePostgres();
		
		String sql = "select * from person_bike where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bicycle owned = BikeDao.getById(rs.getInt("bike_id"));
			bikes.add(owned);
		}
		
		return bikes;
	}

	
	
	@Override
	public Set<Person> getAll() {
		Set<Person> peeps = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.id, username, passwd, person.user_role_id, user_role.role_name "
					+ "from person "
					+ "join user_role on "
					+ "user_role_id = user_role.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("user_role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
				
				p.setBikes(getBikesByPersonId(p.getId(), conn));
				peeps.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return peeps;
	}

	@Override
	public void update(Person t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql ="update person set username = ?, passwd = ?, user_role_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2,  t.getPassword());
			pstmt.setInt(3, t.getRole().getId());
			pstmt.setInt(4,  t.getId());
			
			
			int rowsChanged = pstmt.executeUpdate();
			if(rowsChanged > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Person t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from person_bike where person_id = ?";
						//delete from person_bike where person_id = 6;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsChanged = pstmt.executeUpdate(); //0 or 1
			String sql2 = "delete from person where person.id = ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, t.getId());
			rowsChanged += pstmt2.executeUpdate();


			if(rowsChanged > 0) {
				conn.commit();
			}else {
				System.out.println("rolling back!");
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Person getByUsername(String username)throws NullPointerException{
		Person p = null;
		
		try(Connection conn = cu.getConnection()){
			String sql = "select person.id, username, passwd, person.user_role_id , user_role.role_name "
					+ "from person "
					+ "join user_role on "
					+ "user_role_id = user_role.id "
					+ "where person.username = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Person();
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("passwd"));
				Role r = new Role();
				r.setId(rs.getInt("user_role_id"));
				r.setName(rs.getString("role_name"));
				p.setRole(r);
				p.setBikes(getBikesByPersonId(p.getId(), conn));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

}
