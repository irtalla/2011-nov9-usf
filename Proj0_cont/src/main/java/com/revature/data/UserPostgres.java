package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.models.Bike;
import com.revature.models.Inventory;
import com.revature.models.InventoryStatus;
import com.revature.models.InventoryType;
import com.revature.models.Offer;
import com.revature.models.OfferStatus;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public User add(User t) throws NonUniqueUsernameException {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into users values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				u = t;
				u.setId(rs.getInt(1));
				
				for (Role r : t.getRoles()) {
					sql = "insert into users_roles values (default,"
							+ "(select id from users where id = ?), "
							+ "(select id from roles where id = ?)"
							+ ")";
					pstmt.setInt(1, t.getId());
					pstmt.setInt(2, r.getId());
					pstmt.executeUpdate();
				}
				
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return u;
		
	}
	
	@Override
	public User getById(Integer id) {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where id = " + id;
			Set<User> users = getUserInfo(sql, conn);
			for (User user : users) {
				if (user.getId().equals(id)) {
					u = user;
				}
			}
			
			sql = "where users_roles.user_id = " + id;
			Set<Role> roles = getRoleInfo(sql, conn);
			u.setRoles(roles);
			
			sql = "where u.user_id = " + id;			
			Set<Inventory> inventory = getInventoryInfo(sql, conn);
			u.setItemsPurchased(inventory);
			
			sql = "where o.id = " + id;			
			Set<Offer> offers = getOfferInfo(sql, conn);
			u.setOffersMade(offers);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
		
	}
	
	@Override
	public User getByUsername(String username) {
		User u = new User();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "where username = '" + username + "'";
			
			Set<User> users = getUserInfo(sql, conn);
			for (User user : users) {
				if (user.getUsername().equals(username)) {
					u = user;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getById(u.getId());
	}

	@Override
	public Set<User> getByRole(String roleName) {
		Set<User> users = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select id from roles where roles.name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roleName);
			ResultSet rs = pstmt.executeQuery();
			Integer roleId = rs.getInt("id");
			
			sql = "join users_roles on users.id = users_roles.user_id "
					+ "where users_roles.role_id = " + roleId;
			users = getUserInfo(sql, conn);
			for (User user : users) {
				sql = "where users_roles.user_id = " + user.getId();
				Set<Role> roles = getRoleInfo(sql, conn);
				user.setRoles(roles);
				
				sql = "where u.user_id = " + user.getId();				
				Set<Inventory> inventory = getInventoryInfo(sql, conn);
				user.setItemsPurchased(inventory);
				
				sql = "where o.id = " + user.getId();		
				Set<Offer> offers = getOfferInfo(sql, conn);
				user.setOffersMade(offers);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public Set<User> getAll() {
		Set<User> users = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "";
			users = getUserInfo(sql, conn);
			for (User user : users) {
				sql = "where users_roles.user_id = " + user.getId();
				Set<Role> roles = getRoleInfo(sql, conn);
				user.setRoles(roles);
				
				sql = "where u.user_id = " + user.getId();				
				Set<Inventory> inventory = getInventoryInfo(sql, conn);
				user.setItemsPurchased(inventory);
				
				sql = "where o.id = " + user.getId();		
				Set<Offer> offers = getOfferInfo(sql, conn);
				user.setOffersMade(offers);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public void update(User t) {		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			int rowsAffected = 0;
			String sql = "delete from users_roles where users_roles.user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected = pstmt.executeUpdate();
			
			for (Role r : t.getRoles()) {
				sql = "insert into users_roles values (default, "
						+ "(select id from users where id = ?), "
						+ "(select id from roles where id = ?)"
						+ ")";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.setInt(2, r.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			sql = "delete from users_inventory where users_inventory.user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from inventory using users_inventory "
					+ "where inventory.id = users_inventory.inventory_id "
					+ "and users_inventory.user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			for (Inventory i : t.getItemsPurchased()) {
				sql = "insert into users_inventory values ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.setInt(2, i.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			sql = "delete from customer_offer where customer_offer.user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from employee_offer where employee_offer.user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();	
			
			for (Offer o : t.getOffersMade()) {
				sql = "insert into customer_offer ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.setInt(2, o.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			sql = "update users set username = ?, passwd = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "";
			PreparedStatement pstmt = null;
			int rowsAffected = 0;
			
			for (Inventory i : t.getItemsPurchased()) {
				sql = "delete from inventory where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i.getId());
				rowsAffected += pstmt.executeUpdate();
			}
			
			OfferDAO offerDao = new OfferPostgres();
			for (Offer o : t.getOffersMade()) {
				offerDao.delete(o);
			}
			
			sql = "delete from customer_offer where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from employee_offer where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from users_roles where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from users_inventory where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			sql = "delete from users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			rowsAffected += pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Set<User> getUserInfo(String additionalString, Connection conn) throws SQLException {
		Set<User> users = new HashSet<>();
		String sql = "select id as user_id, username, passwd as password from users "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			users.add(user);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return users;
		
	}
	
	private Set<Role> getRoleInfo(String additionalString, Connection conn) throws SQLException {
		Set<Role> roles = new HashSet<>();
		String sql = "select roles.id as role_id, roles.name as role_name "
				+ "from (users_roles join roles on users_roles.role_id = roles.id) "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Role r = new Role();
			r.setId(rs.getInt("role_id"));
			r.setName(rs.getString("role_name"));
			roles.add(r);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return roles;
		
	}
	
	private Set<Inventory> getInventoryInfo(String additionalString, Connection conn) throws SQLException {
		Set<Inventory> inventory = new HashSet<>();
		String sql = "select i.id as inven_id, i.value as inven_value, "
				+ "it.id as type_id, it.name as type_name, "
				+ "ist.id as status_id, ist.name as status_name "
				+ "from (inventory as i join inventory_type as it on i.type_id = it.id) "
				+ "join inventory_status as ist on i.status_id = ist.id "
				+ "join users_inventory as u on i.id = u.inventory_id "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			InventoryType it = new InventoryType();
			it.setId(rs.getInt("type_id"));
			it.setName(rs.getString("type_name"));
			InventoryStatus is = new InventoryStatus();
			is.setId(rs.getInt("status_id"));
			is.setName(rs.getString("status_name"));	
			
			switch (rs.getString("status_name")) {
				case "Bike":
					Bike b = new Bike();
					b.setId(rs.getInt("inven_id"));
					b.setValue(rs.getFloat("value"));
					b.setType(it);
					b.setStatus(is);
					inventory.add(b);
					break;
				default:
					break;
			}
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return inventory;
		
	}
	
	private Set<Offer> getOfferInfo(String additionalString, Connection conn) throws SQLException {
		Set<Offer> offers = new HashSet<>();
		String sql = "select o.id as offer_id, o.item_id, o.value as offer_value, "
				+ "eo.user_id as employee_id, "
				+ "os.id as status_id, os.name as status_name "
				+ "from (offer as o join offer_status as os on o.status_id = os.id) "
				+ "join employee_offer as eo on o.id = eo.offer_id "
				+ additionalString;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Offer o = new Offer();
			o.setId(rs.getInt("offer_id"));
			o.setItemId(rs.getInt("item_id"));
			o.setValue(rs.getFloat("offer_value"));
			o.setEmployeeId(rs.getInt("employee_id"));
			OfferStatus os = new OfferStatus();
			os.setId(rs.getInt("status_id"));
			os.setName(rs.getString("status_name"));
			o.setStatus(os);
			offers.add(o);
		}
		
		try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		
		return offers;
		
	}
	
}
