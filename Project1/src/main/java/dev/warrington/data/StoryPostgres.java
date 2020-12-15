package dev.warrington.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.nio.file.Path;
import java.nio.file.Paths;

import dev.warrington.utils.ConnectionUtil;

import dev.warrington.beans.Story;


public class StoryPostgres implements StoryDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Set<Story> getMyStories(Integer id, Integer roleId) {

		Set<Story> stories = new HashSet<Story>();
		
		switch(roleId) {
		
			case 1:
				try (Connection conn = cu.getConnection()) {
					String sql = "select * from story join person on author_id = person_id where person_id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();
					
					while (rs.next()) {
						Story s = new Story();
						s.setId(rs.getInt("story_id"));
						s.setTitle(rs.getString("title"));
						s.setFirstName(rs.getString("first_name"));
						s.setLastName(rs.getString("last_name"));
						s.setGenre(rs.getInt("genre"));
						s.setStoryType(rs.getInt("story_type"));
						s.setTagline(rs.getString("tagline"));
						s.setDescription(rs.getString("description"));
						s.setStatus(rs.getInt("status"));
						s.setPriority(rs.getInt("priority"));
						s.setFilePath(rs.getString("file_path"));
						s.setNote(rs.getString("notes"));
						s.setAuthorId(rs.getInt("author_id"));
						s.setSubmissionDate(LocalDate.parse(rs.getString("date_submitted")));
						s.setCompletionDate(rs.getString("completion_date"));
						if (rs.getInt("need_approval") > 0) s.setRequest(true);
						else s.setRequest(false);
						stories.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try (Connection conn = cu.getConnection()) {
					Set<Integer> genreIds = new HashSet<Integer>();
					String sql = "select genre_id from committees where employee_id = " + id;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						genreIds.add(rs.getInt("genre_id"));
					}
					
					sql = "select * from story join person on author_id = person_id";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						if (genreIds.contains(rs.getInt("genre")) && rs.getInt("status") == 2) {
							Story s = new Story();
							s.setId(rs.getInt("story_id"));
							s.setTitle(rs.getString("title"));
							s.setFirstName(rs.getString("first_name"));
							s.setLastName(rs.getString("last_name"));
							s.setGenre(rs.getInt("genre"));
							s.setStoryType(rs.getInt("story_type"));
							s.setTagline(rs.getString("tagline"));
							s.setDescription(rs.getString("description"));
							s.setStatus(rs.getInt("status"));
							s.setPriority(rs.getInt("priority"));
							s.setFilePath(rs.getString("file_path"));
							s.setNote(rs.getString("notes"));
							s.setAuthorId(rs.getInt("author_id"));
							s.setSubmissionDate(LocalDate.parse(rs.getString("date_submitted")));
							s.setCompletionDate(rs.getString("completion_date"));
							
							stories.add(s);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try (Connection conn = cu.getConnection()) {
					Set<Integer> genreIds = new HashSet<Integer>();
					String sql = "select genre_id from committees where employee_id = " + id;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						genreIds.add(rs.getInt("genre_id"));
					}
					
					sql = "select * from story join person on author_id = person_id";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						if (!genreIds.contains(rs.getInt("genre")) && rs.getInt("status") == 4) {
							Story s = new Story();
							s.setId(rs.getInt("story_id"));
							s.setTitle(rs.getString("title"));
							s.setFirstName(rs.getString("first_name"));
							s.setLastName(rs.getString("last_name"));
							s.setGenre(rs.getInt("genre"));
							s.setStoryType(rs.getInt("story_type"));
							s.setTagline(rs.getString("tagline"));
							s.setDescription(rs.getString("description"));
							s.setStatus(rs.getInt("status"));
							s.setPriority(rs.getInt("priority"));
							s.setFilePath(rs.getString("file_path"));
							s.setNote(rs.getString("notes"));
							s.setAuthorId(rs.getInt("author_id"));
							s.setSubmissionDate(LocalDate.parse(rs.getString("date_submitted")));
							s.setCompletionDate(rs.getString("completion_date"));
	
							stories.add(s);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try (Connection conn = cu.getConnection()) {
					Set<Integer> genreIds = new HashSet<Integer>();
					String sql = "select genre_id from committees where employee_id = " + id;
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						genreIds.add(rs.getInt("genre_id"));
					}
					
					sql = "select * from story join person on author_id = person_id";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						if (genreIds.contains(rs.getInt("genre")) && rs.getInt("status") == 6) {
							Story s = new Story();
							s.setId(rs.getInt("story_id"));
							s.setTitle(rs.getString("title"));
							s.setFirstName(rs.getString("first_name"));
							s.setLastName(rs.getString("last_name"));
							s.setGenre(rs.getInt("genre"));
							s.setStoryType(rs.getInt("story_type"));
							s.setTagline(rs.getString("tagline"));
							s.setDescription(rs.getString("description"));
							s.setStatus(rs.getInt("status"));
							s.setPriority(rs.getInt("priority"));
							s.setFilePath(rs.getString("file_path"));
							s.setNote(rs.getString("notes"));
							s.setAuthorId(rs.getInt("author_id"));
							s.setSubmissionDate(LocalDate.parse(rs.getString("date_submitted")));
							s.setCompletionDate(rs.getString("completion_date"));
	
							stories.add(s);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		
		}
		
		return stories;
	}

	@Override
	public void addStory(Story s) {
		Path path = Paths.get(s.getFilePath());
		Path file = path.getFileName();
		s.setFilePath(file.toString());
		Boolean isOnHold = false;
		
		Integer value = 0;
		switch(s.getStoryType()) {
			case 1:
				value = 50;
				break;
			case 2:
				value = 25;
				break;
			case 3:
				value = 20;
				break;
			case 4:
				value = 10;
				break;
		}
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into story values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"story_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, s.getTitle());
			pstmt.setInt(2, s.getAuthorId());
			pstmt.setInt(3, s.getGenre());
			pstmt.setInt(4, s.getStoryType());
			pstmt.setString(5, s.getTagline());
			pstmt.setString(6, s.getDescription());
			pstmt.setInt(7, 2);
			pstmt.setInt(8, 0);
			pstmt.setString(9, s.getFilePath());
			pstmt.setString(10, s.getSubmissionDate().toString());
			pstmt.setString(11, s.getCompletionDate());
			pstmt.setString(12, null);
			pstmt.setInt(13, 0);
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				s.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (Connection conn = cu.getConnection()) {
			Integer total = 0;
			String sql = "select available_points from author where author_id = " + s.getAuthorId();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				total = rs.getInt("available_points") - value;
			}
			
			if (total <= 0) total = 0;
			
			sql = "update author set available_points = " + total + "where author_id = " + s.getAuthorId();
			stmt = conn.createStatement();
			Integer rowsAffected = stmt.executeUpdate(sql);
			
			if (total == 0) {
				sql = "update story set status = " + 1 + "where story_id = " + s.getId();
				stmt = conn.createStatement();
				rowsAffected = stmt.executeUpdate(sql);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void approve(Integer id) {
		Integer status = 0;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select status_id from story join status on status_id = story.status where story_id = " + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				status = rs.getInt("status_id");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		if (status != 0 && status < 8 && status % 2 == 0) {
			status += 2;
		} else if (status != 0 && status < 8 && status % 2 == 1) {
			status += 1;
		}
		
		if (status != 0) {
			try (Connection conn = cu.getConnection()) {
				String sql = "update story set status = " + status + " where story_id = " + id;
				Statement stmt = conn.createStatement();
				Integer rowsAffected = stmt.executeUpdate(sql);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deny(Integer id) {
		
		try (Connection conn = cu.getConnection()) {
			String sql = "update story set status = 9 where story_id = " + id;
			Statement stmt = conn.createStatement();
			Integer rowsAffected = stmt.executeUpdate(sql);			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void req(Integer id) {
		Integer status = 0;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select status_id from story join status on status_id = story.status where story_id = " + id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				status = rs.getInt("status_id");
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		
		if (--status != 0) {
			try (Connection conn = cu.getConnection()) {
				String sql = "update story set status = " + status + " where story_id = " + id;
				Statement stmt = conn.createStatement();
				Integer rowsAffected = stmt.executeUpdate(sql);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Integer id) {
		try (Connection conn = cu.getConnection()) {
			
			String sql = "delete from story where story_id = " + id;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
