package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Person;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private OfferPostgres offerPostgres = new OfferPostgres(); 
	
	@Override
	public Person add(Person t) {
		
		Person c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " insert into person values (default, ?, ?, ?) ";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername() );
			pstmt.setString(2, t.getPassword() );
			pstmt.setInt(3, t.getRole().getId() );

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if ( rs.next() ) {
				c = t;
				t.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
				System.out.println("Got results");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Person getById(Integer id) {
		
		Person returnedPerson = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " select "
						+ "	person.id as person_id, "
						+ "	person.username as person_username, "
						+ "	person.passwd as person_password, "
						+ "	person.user_role_id as role_id, "
						+ "	user_role.name as role_name "
						+ "	from person "
						+ "		join user_role "
						+ "		on person.user_role_id = user_role.id "
						+ "	where person.id = ? ";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery(); 
			
			if ( rs.next() ) {
				
				try {
					returnedPerson = deserializeProduct(rs); 
					returnedPerson.setOffers( this.offerPostgres.getOffersByCustomerId(returnedPerson.getId()) );
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnedPerson; 
	}

	@Override
	public Set<Person> getAll() {
		
		Set<Person> persons = new HashSet<Person>();
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " select "
						+ "	person.id as person_id, "
						+ "	person.username as person_username, "
						+ "	person.passwd as person_password, "
						+ "	person.user_role_id as role_id, "
						+ "	user_role.name as role_name "
						+ "	from person "
						+ "		join user_role "
						+ "		on person.user_role_id = user_role.id ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(); 
			
			Person returnedPerson = null; 
			while ( rs.next() ) {
				
				try {
					returnedPerson = deserializeProduct(rs); 
					persons.add(returnedPerson);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persons; 
	}
	
	@Override
	public Person getByUsername(String username) {
		
		Person returnedPerson = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " select "
						+ "	person.id as person_id, "
						+ "	person.username as person_username, "
						+ "	person.passwd as person_password, "
						+ "	person.user_role_id as role_id, "
						+ "	user_role.name as role_name "
						+ "	from person "
						+ "		join user_role "
						+ "		on person.user_role_id = user_role.id "
						+ "	where person.username = ? ";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery(); 
			
			if ( rs.next() ) {
				
				try {
					returnedPerson = deserializeProduct(rs); 
					returnedPerson.setOffers( this.offerPostgres.getOffersByCustomerId(returnedPerson.getId()) );
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnedPerson; 
		
	}

	private Person deserializeProduct(ResultSet rs) throws SQLException {
		
		Person returnedPerson = new Person(); 
		returnedPerson.setId( rs.getInt("person_id") );
		returnedPerson.setUsername( rs.getString("person_username") );
		returnedPerson.setPassword( rs.getString("person_password") );
		returnedPerson.getRole().setId( rs.getInt("role_id") );
		returnedPerson.getRole().setName( rs.getString("role_name") );
		return returnedPerson; 
	}

	@Override
	public boolean update(Person t) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Person t) {
		
		// Current implementation only supports firing employees by
		// removing persons with employee role. It is also assumed
		// that employees cannot buy products or make offers, so there
		// will be no anomalies
		if ( ! t.getRole().getName().equalsIgnoreCase("employee") ) {
			return false;
		}
		
		Integer rowsUpdated = 0; 
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " delete from person "
						+ " where "
						+ " person.id = ? and "
						+ " person.user_role_id = 2 "; 
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId() ); 

			rowsUpdated = pstmt.executeUpdate();
			
			if (rowsUpdated > 1) {
				conn.rollback();
				// TODO : throw custom exception and rollback 
			}
			
			conn.commit(); 

		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return rowsUpdated == 1 ? true : false;

	}

}
