package dev.elliman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Stage;
import dev.elliman.utils.DBConnectionUtil;

public class ClaimJDBCPostgres implements ClaimDAO {
	private DBConnectionUtil cu = DBConnectionUtil.getDBConnectionUtil();

	@Override
	public Set<Claim> getClaimsByPerson(Integer personID) {
		Set<Claim> claims = null;
		
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from claim join stage on claim.approval_stage = stage.id where person = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, personID);
			
			ResultSet rs = pstmt.executeQuery();
			
			claims = new HashSet<Claim>();
			while(rs.next()) {
				Claim c = new Claim();
				c.setId(rs.getInt("id"));
				c.setPersonID(rs.getInt("person"));
				c.setEventID(rs.getInt("event_type"));
				c.setGradingID(rs.getInt("grading"));
				
				LocalDateTime localDate = rs.getObject("event_timestamp", LocalDateTime.class);
				c.setEventDate(localDate);
				
				c.setEventLocation(rs.getString("event_location"));
				c.setDescription(rs.getString("description"));
				c.setPrice(rs.getDouble("price"));
				c.setJustification(rs.getString("justification"));
				c.setHoursMissed(rs.getInt("time_missed"));
				
				Stage s = new Stage();
				s.setId(rs.getInt("approval_stage"));
				s.setName(rs.getString("stage_name"));
				c.setApprovalStage(s);
				
				c.setDsaID(rs.getInt("dsa"));
				c.setDhaID(rs.getInt("dha"));
				c.setBcaID(rs.getInt("bca"));
				c.setDenialReason(rs.getString("denial_reason"));
				
				claims.add(c);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return claims;
	}
	
	public Integer makeClaim(Claim claim) {
		Integer generatedID = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "insert into claim values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setInt(1, claim.getPersonID());
			pstmt.setInt(2, claim.getEventID());
			pstmt.setInt(3, claim.getGradingID());
			pstmt.setObject(4, claim.getEventDate());
			pstmt.setString(5, claim.getEventLocation());
			pstmt.setString(6, claim.getDescription());
			pstmt.setDouble(7, claim.getPrice());
			pstmt.setString(8, claim.getJustification());
			pstmt.setInt(9, claim.getHoursMissed());
			pstmt.setInt(10, claim.getApprovalStage().getId());
//			pstmt.setInt(11, claim.getDsaID());
//			pstmt.setInt(12, claim.getDhaID());
//			pstmt.setInt(13, claim.getBcaID());
			pstmt.setNull(11, java.sql.Types.INTEGER);
			pstmt.setNull(12, java.sql.Types.INTEGER);
			pstmt.setNull(13, java.sql.Types.INTEGER);
			pstmt.setString(14, claim.getDenialReason());
			
			
			Integer rowsChanged = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedID = rs.getInt("id");
				claim.setId(generatedID);
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedID;
	}

}
