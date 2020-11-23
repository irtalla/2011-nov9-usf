package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Bicycle;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.utils.ConnectionUtil;

public class BicyclePostgre implements BicycleDAO{


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bicycle add(Bicycle t) throws Exception {
		
		Bicycle bicycle=null;
		try(Connection conn= cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getBrand());
			pstmt.setInt(2, t.getPrice());
			pstmt.setString(3, t.getColor());
			pstmt.setInt(4,t.getQuantity());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				bicycle = t;
				bicycle.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bicycle;

		
	}

	@Override
	public Bicycle getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bicycle> getAll() {
Set<Bicycle> Bicycle = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql="Select * from Bicycle";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Bicycle bicycles = new Bicycle();
				bicycles.setId(rs.getInt("bicycle_id"));
				bicycles.setBrand(rs.getString("Brand"));
				bicycles.setColor(rs.getString("Color"));
				bicycles.setPrice(rs.getInt("Price"));
				bicycles.setQuantity(rs.getInt("Quantity"));
				Bicycle.add(bicycles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Bicycle;
		

	}

	@Override
	public void update(Bicycle t) {
		
		Bicycle update=new Bicycle();
		try(Connection conn=cu.getConnection()){
			String sql="UPDATE Bicycle SET Brand = ?  WHERE kind = 'Drama';";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Bicycle t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bicycle getBicyclebybrand(String name) {
		Bicycle get=new Bicycle();
		try(Connection conn=cu.getConnection()){
			String sql="Select * from Bicycle where Brand = ?";
			PreparedStatement ps=conn.prepareCall(sql);	
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				get.setBrand(rs.getString("Brand"));
				get.setColor(rs.getString("Color"));
				get.setPrice(rs.getInt("Price"));
				get.setQuantity(rs.getInt("Quantity"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return get;
	}

	@Override
	public Bicycle getbyID(int id) {
		Bicycle get=new Bicycle();
		try(Connection conn=cu.getConnection()){
			String sql="Select * from Bicycle where bicycle_id = ?";
			PreparedStatement ps=conn.prepareCall(sql);	
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				get.setBrand(rs.getString("Brand"));
				get.setColor(rs.getString("Color"));
				get.setPrice(rs.getInt("Price"));
				get.setQuantity(rs.getInt("Quantity"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return get;
		
		// TODO Auto-generated method stub
	}

}
