package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;


import spencer.revature.utils.ConnectionUtil;

import spencer.revature.beans.BicycleStatus;

public class BicycleStatusPostgres implements BicycleStatusDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	
	@Override
	public BicycleStatus add(BicycleStatus t) {
		BicycleStatus s = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bicycle_status values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getStatus());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				s=t;
				s.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public BicycleStatus getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BicycleStatus> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BicycleStatus t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BicycleStatus t) {
		// TODO Auto-generated method stub
		
	}

}
