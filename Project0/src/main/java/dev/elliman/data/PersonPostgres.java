package dev.elliman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Person;
import dev.elliman.beans.Role;
import dev.elliman.utils.DBConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private DBConnectionUtil cu = DBConnectionUtil.getDBConnectionUtil();

	@Override
	public Integer add(Person p) {
		Integer generatedID = null;

		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike_shop.person values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"person_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, p.getFirstName());
			pstmt.setString(2, p.getLastName());
			pstmt.setString(3, p.getUsername());
			pstmt.setString(4, p.getPassword());
			pstmt.setInt(5, p.getRole().getID());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				generatedID = rs.getInt(1);
				p.setID(generatedID);
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return generatedID;
	}

	@Override
	public Person getByID(Integer id) {
		Person p = null;

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.person where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				Role role = new Role();
				role.setLevel(this, rs.getInt("role_id"));
				p = new Person(
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_name"),
						rs.getString("passwrd"),
						role);
				p.setID(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> people = new HashSet<>();

		try(Connection conn = cu.getConnection()){

			String sql = "select * from bike_shop.person";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Role role;
			Person p;
			while(rs.next()) {
				role = new Role();
				role.setLevel(this, rs.getInt("role_id"));
				p = new Person(
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_name"),
						rs.getString("passwrd"),
						role);
				people.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public void update(Person p) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update bike_shop.person set first_name = ?, last_name = ?, user_name = ?, passwrd = ?, role_id = ? where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getFirstName());
			pstmt.setString(2, p.getLastName());
			pstmt.setString(3, p.getUsername());
			pstmt.setString(4, p.getPassword());
			pstmt.setInt(5, p.getRole().getID());
			pstmt.setInt(6, p.getID());

			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person p) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from bike_shop.person where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getID());

			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Person getByUsername(String username) {
		Person p = null;

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.person where user_name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				Role role = new Role();
				role.setLevel(this, rs.getInt("role_id"));
				p = new Person(
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("user_name"),
						rs.getString("passwrd"),
						role);
				p.setID(rs.getInt("person_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

}
