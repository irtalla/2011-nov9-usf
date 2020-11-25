package JDBC;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import Entity.Status;
import Utils.ConnectionUtil;

public class StatusPostgres implements StatusJDBC {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Status add(Status s) {
		Status t = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into status values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			pstmt.setNString(1, s.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				t=s;
				s.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Status getById(Integer id) {
		Status t = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				t = new Status();
				t.setId(rs.getInt("id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Set<Status> getAll() {
Set<Status> status = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from status";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Status s = new Status();	
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				status.add(s);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	@Override
	public void update(Status s) {
		try (Connection conn = cu.getConnection()) {
			if(!(s.equals(null))) {
				conn.setAutoCommit(false);
				String sql = "update status set name = ? where id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, s.getName());
				pstmt.setInt(2, s.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void remove(Status s) {
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from status where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
