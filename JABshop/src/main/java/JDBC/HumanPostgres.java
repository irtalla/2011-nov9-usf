package JDBC;
import java.util.Set;
import java.sql.*;
import Utils.ConnectionUtil;
import java.util.HashSet;

import Entity.Bike;
import Entity.Human;
import Entity.Role;

public class HumanPostgres implements HumanJDBC{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Human add(Human h) {
		Human t = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into human values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, h.getUser());
			pstmt.setString(2, h.getPass());
			pstmt.setInt(3, h.getPurp().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				t = h;
				t.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Human getById(Integer id) {
		Human h = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select human.id as human_id, human.username as username, human.passwrd as passwrd,\r\n" + 
					"		roles.id as id, roles.names as names from human join roles \r\n" + 
					"		on human.roles_id = roles.id where human.id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				h = new Human();
				h.setId(rs.getInt("human_id"));
				h.setUser(rs.getString("username"));
				h.setPass(rs.getString("passwrd"));
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("names"));
				h.setPurp(role);
				
				h.setBikes(getBikesByHumanId(h.getId(), conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return h;

	}

	@Override
	public Set<Human> getAll() {
Set<Human> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select human.id as human_id, roles.id as roles_id, username, passwrd, "
					+ "roles.name as roles_name from human join roles on roles_id = roles.id";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Human human = new Human();
				human.setId(rs.getInt("human_id"));
				human.setUser(rs.getString("username"));
				human.setPass(rs.getString("passwrd"));
				Role job = new Role();
				job.setId(rs.getInt("roles_id"));
				job.setName(rs.getString("roles_name"));
				human.setPurp(job);
				
				human.setBikes(getBikesByHumanId(human.getId(), conn));
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;

	}

	@Override
	public void update(Human h) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update human set username = ?, passwrd = ?, roles_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, h.getUser());
			pstmt.setString(2, h.getPass());
			pstmt.setInt(3, h.getPurp().getId());
			pstmt.setInt(4, h.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Human h) {
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from human where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, h.getId());

			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public Human getByUsername(String username) {
		Human human = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select human.id as human_id, roles.id as roles_id, username, passwrd, "
					+ "roles.names as roles_name from human "
					+ "join roles on roles_id = roles.id where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				human = new Human();
				human.setUser(rs.getString("username"));
				human.setId(rs.getInt("human_id"));
				human.setPass(rs.getString("passwrd"));
				Role job = new Role();
				job.setId(rs.getInt("roles_id"));
				job.setName(rs.getString("roles_name"));
				human.setPurp(job);
				human.setBikes(getBikesByHumanId(human.getId(), conn));
			}
						
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return human;

	}
	
	private Set<Bike> getBikesByHumanId(Integer id, Connection conn) throws SQLException{
		Set<Bike> bikes = new HashSet<>();
		BikeJDBC bikejdbc = new BikePostgres();
		
		String sql = "select * from human_bike where human_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Bike ride = bikejdbc.getById(rs.getInt("bike_id"));
			bikes.add(ride);
		}
		
		return bikes;

	}

}
