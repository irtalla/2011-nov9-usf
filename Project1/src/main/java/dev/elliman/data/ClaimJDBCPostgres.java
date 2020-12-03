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
				c.setEventDate(rs.getDate("date"));
				c.setEventTime(rs.getTime("time"));
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
				c.setDsaID(rs.getInt("dha"));
				c.setDsaID(rs.getInt("bca"));
				c.setDenialReason(rs.getString("denial_reason"));
				
				claims.add(c);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return claims;
	}

}
