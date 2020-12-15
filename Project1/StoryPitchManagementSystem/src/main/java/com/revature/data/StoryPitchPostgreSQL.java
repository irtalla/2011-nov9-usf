package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.StoryPitch;
import com.revature.beans.Genre;
import com.revature.beans.Type;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.util.ConnectionUtil;

public class StoryPitchPostgreSQL implements StoryPitchDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public StoryPitch add(StoryPitch sp) {
		if(sp == null) {
			System.out.println("Cannot add a null response to the database");
			return sp;
		}
		else {
			//System.out.println(sp);
		StoryPitch n = new StoryPitch();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into story_pitches values(default, ?, ?,  default, ?, ?, ?, ?, ?, ?, default, ?, ?)";
			String[] keys = {"story_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  sp.getTitle());
			pstmt.setInt(2, sp.getType().getTypeID());
			pstmt.setDate(3,  sp.getCompletionDate());
			pstmt.setString(4,  sp.getTagline());
			pstmt.setString(5,  sp.getDetailedDescription());
			pstmt.setInt(6,  sp.getPersonID());
			pstmt.setInt(7,  sp.getGenre().getGenreID());
			pstmt.setInt(8,  sp.getStatus().getStatusID());
			//pstmt.setInt(9,  sp.getPriorityID()); //using default
			pstmt.setInt(9,  sp.getChangesRequested());
			pstmt.setString(10,  sp.getDraft());
			
			try {
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("Exception here");
				System.out.println(e);
				return n;
				//e.printStackTrace();
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				n=sp;
				n.setStoryID(rs.getInt(1));
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				n.setSubmissionDate(date);
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
			}
			catch(Exception e) {
				System.out.println("Exception cought");
			e.printStackTrace();
			}
			return n;
		}
	}

	@Override
	public Set<StoryPitch> getAll() {
		Set<StoryPitch> storyPitches = new HashSet<StoryPitch>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id"
					+ " join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				StoryPitch n = new StoryPitch();
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));
				
				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setGenre(g);
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
				storyPitches.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return storyPitches;
	}

	@Override
	public void update(StoryPitch sp) {
		if(sp == null) {
			System.out.println("Cannot update a null story pitch");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update story_pitches "
						+" set title = ?, "
						+" type_id = ?, "
						+" completion_date = ?, "
						+" tag_line = ?, "
						+" detailed_description = ?, "
						+" genre_id = ?, "
						+" status_id = ?, "
						+" priority_id = ?, "
						+" changes_requested = ?, "
						+" draft = ? "
						+" where story_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sp.getTitle());
				pstmt.setInt(2, sp.getType().getTypeID());
				pstmt.setDate(3, sp.getCompletionDate());
				pstmt.setString(4, sp.getTagline());
				pstmt.setString(5, sp.getDetailedDescription());
				pstmt.setInt(6, sp.getGenre().getGenreID());
				pstmt.setInt(7,  sp.getStatus().getStatusID());
				pstmt.setInt(8,  sp.getPriorityID());
				pstmt.setInt(9,  sp.getChangesRequested());
				pstmt.setString(10,  sp.getDraft());
				pstmt.setInt(11,  sp.getStoryID());
				
				int n = pstmt.executeUpdate();
				
				if(n > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(StoryPitch sp) {

		if(sp == null) {
			System.out.println("Cannot delete a null story pitch from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "delete from story_pitches "
						+" where story_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sp.getStoryID());
				
				int nr = pstmt.executeUpdate();
				
				if(nr > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Set<StoryPitch> getByAuthorID(Integer id) {
		Set<StoryPitch> storyPitches = new HashSet<StoryPitch>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id "
					+ " join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				StoryPitch n = new StoryPitch();
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));

				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setGenre(g);
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
				storyPitches.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return storyPitches;
	}

	@Override
	public StoryPitch getByStoryPitchID(Integer id) {
		StoryPitch n = new StoryPitch();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id "
					+ " join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id where story_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));
				n.setGenre(g);

				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));

				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
			}
			else {
				return null;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Set<StoryPitch> getByStatusID(Integer id) {
		Set<StoryPitch> storyPitches = new HashSet<StoryPitch>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id "
					+ "join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id where sp.status_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				StoryPitch n = new StoryPitch();
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));

				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setGenre(g);
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
				storyPitches.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return storyPitches;
	}

	@Override
	public Set<StoryPitch> getByPriorityAndGenreID(Integer pid, Integer gid) {
		Set<StoryPitch> storyPitches = new HashSet<StoryPitch>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id"
					+ " join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id"
					+ " where sp.genre_id = ? and sp.priority_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			pstmt.setInt(2, pid);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				StoryPitch n = new StoryPitch();
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));

				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setGenre(g);
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
				storyPitches.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return storyPitches;
	}

	@Override
	public Set<StoryPitch> getByStatusAndGenreID(Integer sid, Integer gid) {
		Set<StoryPitch> storyPitches = new HashSet<StoryPitch>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from story_pitches sp join genres g on sp.genre_id = g.genre_id "
					+ " join story_types t on sp.type_id = t.type_id join status s on sp.status_id = s.status_id "
					+ " where sp.genre_id = ? and sp.tatus_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			pstmt.setInt(2, sid);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				StoryPitch n = new StoryPitch();
				Genre g = new Genre();
				g.setGenreID(rs.getInt(14));
				g.setGenreName(rs.getString(15));

				Type t = new Type();
				t.setTypeID(rs.getInt(16));
				t.setName(rs.getString(17));
				n.setType(t);
				
				Status s = new Status();
				s.setStatusID(rs.getInt(18));
				s.setName(rs.getString(19));
				n.setStatus(s);
				
				n.setStoryID(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setSubmissionDate(rs.getDate(4));
				n.setCompletionDate(rs.getDate(5));
				n.setTagline(rs.getString(6));
				n.setDetailedDescription(rs.getString(7));
				n.setPersonID(rs.getInt(8));
				n.setGenre(g);
				n.setPriorityID(rs.getInt(11));
				n.setChangesRequested(rs.getInt(12));
				n.setDraft(rs.getString(13));
				storyPitches.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return storyPitches;
	}
	
}

