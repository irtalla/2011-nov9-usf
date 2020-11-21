package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Product;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private ProductFeaturePostgres prodPostgres= new ProductFeaturePostgres(); 
	private OfferPostgres offerPostgres = new OfferPostgres(); 
	
	@Override
	public Person add(Person t) {
		
		return null; 
	}

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
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
		return false;
		// TODO Auto-generated method stub

	}

}
