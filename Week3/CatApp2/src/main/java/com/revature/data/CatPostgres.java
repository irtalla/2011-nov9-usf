package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.SpecialNeed;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class CatPostgres implements CatDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Cat add(Cat t) {
		Cat c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into cat values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getAge());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getBreed().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				if (t.getSpecialNeeds().size() > 0) {
					if (addSpecialNeeds(t, conn)) {
						c = t;
						c.setId(rs.getInt(1));
						conn.commit();
					} else {
						conn.rollback();
					}
				} else {
					c = t;
					c.setId(rs.getInt(1));
					conn.commit();
				}
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Cat getById(Integer id) {
		Cat cat = null;

		try (Connection conn = cu.getConnection()) {			
			// Get the cat object
			String sql = "select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, "
					+ "breed.name as breed_name from "
					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from "
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join breed on breed_id = breed.id where cat_status.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				cat.setSpecialNeeds(getSpecialNeedsByCatId(cat.getId(), conn));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cat;
	}

	@Override
	public Set<Cat> getAll() {
		Set<Cat> cats = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, "
					+ "breed.name as breed_name from "
					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from "
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join breed on breed_id = breed.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Cat cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				
				cat.setSpecialNeeds(getSpecialNeedsByCatId(cat.getId(), conn));
				
				cats.add(cat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cats;
	}
	
	@Override
	public Set<Cat> getAvailableCats() {
		Set<Cat> cats = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from (select cat_status.id, cat_status.name, age, "
					+ "status_id, status_name, breed_id, " 
					+ "breed.name as breed_name from "
					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as "
					+ "status_name from " 
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join breed on breed_id = breed.id) as available where"
					+ " available.status_name = 'Available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Cat cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				
				cat.setSpecialNeeds(getSpecialNeedsByCatId(cat.getId(), conn));
				
				cats.add(cat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cats;
		
	}

	@Override
	public void update(Cat t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update cat set name = ?, age = ?, status_id = ?, breed_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getAge());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getBreed().getId());
			pstmt.setInt(5, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				if (updateSpecialNeeds(t, conn))
					conn.commit();
				else
					conn.rollback();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Cat t) {
		try (Connection conn = cu.getConnection()) {
			// first, we need to delete the cat's special needs
			String sql = "delete from special_need where cat_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected == t.getSpecialNeeds().size()) {
				// next, we need to delete the associates between the cat
				// and its owner(s)
				sql = "delete from person_cat where cat_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.executeUpdate();
				
				// then, we can delete the cat
				sql = "delete from cat where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				
				rowsAffected = pstmt.executeUpdate();
				if(rowsAffected > 0)
					conn.commit();		
				else
					conn.rollback();	
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void adoptCat(Person p, Cat c) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "call adopt_cat(?,?)";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, p.getId());
			cstmt.setInt(2, c.getId());
			cstmt.executeUpdate();
			conn.commit();
//			if (rowsAffected > 0) {
//				conn.commit();
//			} else {
//				conn.rollback();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean addSpecialNeeds(Cat c, Connection conn) throws SQLException {
		String sql = "insert into cat_special_need values";
		for (int i = 0; i < c.getSpecialNeeds().size(); i++) {
			sql += " (?,?)";
			if (i < c.getSpecialNeeds().size() - 1)
				sql += ",";
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 0;
		for (SpecialNeed sn : c.getSpecialNeeds()) {
			pstmt.setInt(++i, c.getId());
			pstmt.setInt(++i, sn.getId());
		}
		int rowsAffected = pstmt.executeUpdate();
		
		return (rowsAffected == c.getSpecialNeeds().size());
	}
	
	private Set<SpecialNeed> getSpecialNeedsByCatId(Integer id, Connection conn) throws SQLException {
		Set<SpecialNeed> needs = new HashSet<>();
		
		String sql = "select special_need_id, name from cat_special_need join special_need on "
				+ "special_need_id = id where cat_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rsSn = pstmt.executeQuery();
		
		while (rsSn.next()) {
			SpecialNeed sn = new SpecialNeed();
			sn.setId(rsSn.getInt("special_need_id"));
			sn.setName(rsSn.getString("name"));
			needs.add(sn);
		}
		
		return needs;
	}

	// this is just ONE way of handling this problem, and it isn't
	// necessarily the best way! :) just want to show different options.
	private boolean updateSpecialNeeds(Cat c, Connection conn) throws SQLException {
		int needsInDatabase = 0;
		
		String sql = "select cat_id, special_need_id, name "
				+ "from cat_special_need join special_need on "
				+ "special_need_id = special_need.id where cat_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, c.getId());
		
		ResultSet rs = pstmt.executeQuery();
		
		// this set will end up including needs that are in
		// the database that are no longer associates with this
		// cat anymore (after the update)
		Set<SpecialNeed> needsToDelete = new HashSet<>();
		
		// this set will end up including needs that are not
		// yet in the database, but need to be added as they
		// are part of the updated cat
		Set<SpecialNeed> updatedNeeds = c.getSpecialNeeds();
		
		while (rs.next()) {
			SpecialNeed sn = new SpecialNeed();
			sn.setId(rs.getInt("special_need_id"));
			sn.setName(rs.getString("name"));
			
			// if the cat's current special needs do not include this,
			// it must be deleted from the database
			if (!updatedNeeds.contains(sn)) {
				needsToDelete.add(sn);
			} else {
				// if it does contain the special need, we don't
				// need to add it
				updatedNeeds.remove(sn);
			}
			
			needsInDatabase++;
		}
		
		for (SpecialNeed sn : needsToDelete) {
			sql = "delete from cat_special_need where cat_id = ? and "
					+ "special_need_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			pstmt.setInt(2, sn.getId());
			int rowsAffected = pstmt.executeUpdate();
			needsInDatabase -= rowsAffected;
		}
		
		for (SpecialNeed sn : updatedNeeds) {
			sql = "insert into cat_special_need values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			pstmt.setInt(2, sn.getId());
			pstmt.executeUpdate();
			needsInDatabase++;
		}
		
		return (needsInDatabase == c.getSpecialNeeds().size());
	}
}
