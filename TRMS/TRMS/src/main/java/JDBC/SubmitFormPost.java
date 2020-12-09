package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import Util.ConnectionUtil;
import Entity.ApproveType;
import Entity.Employee;
import Entity.EventType;
import Entity.Status;
import Entity.SubmitForm;

public class SubmitFormPost implements SubmitFormDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	@Override
	public SubmitForm add(SubmitForm t) {
		// TODO Auto-generated method stub
		SubmitForm sub = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into form_submit values(?, ?,default, default, ?, default)";
			String[] keys = {"emp_id","event_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getEmp().getId());
			pstmt.setInt(2, t.getEt().getId());
			pstmt.setString(3, t.getDescription());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				sub = t;
				
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				System.out.println("you're already taking this class");
			}
			e.printStackTrace();
		}
		
		return sub;
	}

	@Override
	public SubmitForm getById(Integer id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SubmitForm> getAll() {
		// TODO Auto-generated method stub
		Set<SubmitForm> forms = new HashSet<SubmitForm>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.full_name as fname, event_type.e_name as ename, "
					+ "event_type.por as por, event_type.ecost as ecost, approve_type.aname as aname, "
					+ "status.s_name as sname, form_submit.lgrade as lgrade, form_submit.descript as descript, "
					+ "form_submit.sub_date as subdate from employee join form_submit on "
					+ "form_submit.emp_id = employee.id join event_type on "
					+ "form_submit.event_id = event_type.id join approve_type on "
					+ "event_type.appr_id = approve_type.id join status on form_submit.status_id = status.id";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				SubmitForm sf = new SubmitForm();
				Employee emp = new Employee();
				EventType et = new EventType();
				Status stat = new Status();
				ApproveType at = new ApproveType();
				emp.setFullName(rs.getString("fname"));
				et.setName(rs.getString("ename"));
				et.setPor(rs.getDouble("por"));
				et.setCost(rs.getInt("ecost"));
				at.setName(rs.getString("aname"));
				et.setAppr(at);
				stat.setName(rs.getString("sname"));
				sf.setGrade(rs.getString("lgrade"));
				sf.setDescription(rs.getString("descript"));
				sf.setDate(rs.getString("subdate"));
				sf.setEmp(emp);
				sf.setEt(et);
				sf.setStat(stat);
				forms.add(sf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forms;
	}

	@Override
	public void update(SubmitForm t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update form_submit set status_id = ?, lgrade = ?, descript = ? where emp_id = ? and event_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getStat().getId());
			pstmt.setString(2, t.getGrade());
			pstmt.setString(3, t.getDescription());
			pstmt.setInt(4, t.getEmp().getId());
			pstmt.setInt(5, t.getEt().getId());
			
			
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
	public void delete(SubmitForm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubmitForm getByIds(Integer emp_id, Integer event_id) {
		// TODO Auto-generated method stub
		SubmitForm sf = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.full_name as fname, event_type.e_name as ename, event_type.por as por, event_type.ecost as ecost, approve_type.aname as aname,\r\n" + 
					"status.s_name as sname, form_submit.lgrade as lgrade, form_submit.descript as descript, form_submit.sub_date as subdate\r\n" + 
					"from employee join form_submit on form_submit.emp_id = employee.id join event_type on form_submit.event_id = \r\n" + 
					"event_type.id join approve_type on event_type.appr_id = approve_type.id join status on form_submit.status_id = status.id\r\n" + 
					"where form_submit.emp_id = ? and form_submit.event_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, event_id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sf = new SubmitForm();
				Employee emp = new Employee();
				EventType et = new EventType();
				Status stat = new Status();
				ApproveType at = new ApproveType();
				emp.setFullName(rs.getString("fname"));
				et.setName(rs.getString("ename"));
				et.setPor(rs.getDouble("por"));
				et.setCost(rs.getInt("ecost"));
				at.setName(rs.getString("aname"));
				stat.setName(rs.getString("sname"));
				sf.setGrade(rs.getString("lgrade"));
				sf.setDescription(rs.getString("descript"));
				sf.setDate(rs.getString("subdate"));
				sf.setEmp(emp);
				sf.setEt(et);
				sf.setStat(stat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sf;
	}

	}


