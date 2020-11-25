package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Bicycle;
import dev.rev.model.Payment;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.services.PamentServiceImp;
import dev.rev.services.PaymentService;
import dev.rev.utils.ConnectionUtil;

public class BicyclePostgre implements BicycleDAO{


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bicycle add(Bicycle t) throws Exception {
		
		Bicycle bicycle=null;
		try(Connection conn= cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?,?)";
			String[] keys = {"bicycle_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getBrand());
			pstmt.setInt(2, t.getPrice());
			pstmt.setString(3, t.getColor());
			pstmt.setInt(4,t.getQuantity());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				bicycle = t;
				bicycle.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bicycle;

		
	}

	@Override
	public Bicycle getById(Integer id) {
	try(Connection connection=cu.getConnection()){
		
		String sql="Select * from bicycle where bicycle_id=?";
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}
	@Override
	public void update(Bicycle t) {
		
		Bicycle update=new Bicycle();
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="UPDATE Bicycle SET Brand = ? , price = ?, color = ? ,Bicycle_status= ? WHERE bicycle_id= ?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, t.getBrand());
			ps.setInt(2, t.getPrice());
			ps.setString(3, t.getColor());
			ps.setString(4, t.getBicycle_status());
			ps.setInt(5, t.getId());
			
			int row = ps.executeUpdate();
			if(row>0) {
				System.out.println("Bicycle updated");
				conn.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Bicycle t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bicycle getBicyclebybrand(String name) {
		Bicycle get=new Bicycle();
		try(Connection conn=cu.getConnection()){
			String sql="Select * from Bicycle where Brand = ?";
			PreparedStatement ps=conn.prepareCall(sql);	
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				get.setBrand(rs.getString("Brand"));
				get.setColor(rs.getString("Color"));
				get.setPrice(rs.getInt("Price"));
				get.setQuantity(rs.getInt("Quantity"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return get;
	}

	@Override
	public Bicycle getbyID(int id) {
		Bicycle get=new Bicycle();
		try(Connection conn=cu.getConnection()){
			String sql="Select * from Bicycle where bicycle_id = ?";
			PreparedStatement ps=conn.prepareCall(sql);	
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				get.setBrand(rs.getString("Brand"));
				get.setColor(rs.getString("Color"));
				get.setPrice(rs.getInt("Price"));
				get.setQuantity(rs.getInt("Quantity"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return get;
		
		// TODO Auto-generated method stub
	}

	@Override
	public void updatebikestatus(int id,int person_id,int price) {
		// TODO Auto-generated method stub
		String status="Owned";
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="update bicycle set bicycle_status = ? , person_id = ? , price = ? where bicycle_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, person_id);
			ps.setInt(3, price);
			ps.setInt(4, id);
			int rows=ps.executeUpdate();
			
			if(rows>0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Set<Bicycle> getAll() {
Set<Bicycle> Bicycle = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql="Select * from Bicycle";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Bicycle bicycles = new Bicycle();
				bicycles.setId(rs.getInt("bicycle_id"));
				bicycles.setBrand(rs.getString("Brand"));
				bicycles.setColor(rs.getString("Color"));
				bicycles.setPrice(rs.getInt("Price"));
				bicycles.setQuantity(rs.getInt("Quantity"));
				bicycles.setBicycle_status(rs.getString("bicycle_status"));
				Bicycle.add(bicycles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Bicycle;
		

	}



	@Override
	public List<Bicycle> bicyclebyp(int p_id) {
		// TODO Auto-generated method stub
		List<Bicycle> bikes= new ArrayList();

		try(Connection conn= cu.getConnection()){
			String sql="Select * from bicycle where person_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				Bicycle b= new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setBrand(rs.getString("brand"));
				b.setColor(rs.getString("color"));
				b.setPrice(rs.getInt("price"));
				b.setBicycle_status(rs.getString("bicycle_status"));
				bikes.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bikes;
	}

	@Override
	public void delbike(int b) {
		// TODO Auto-generated method stub
		System.out.println("Bicycle id delete :"+b);
		try(Connection connection= cu.getConnection()){
			connection.setAutoCommit(false);
			System.out.println("here");
			String stat="owned";
			String sql ="Delete from offer where bicycle_id = ?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, b);
			//ps.setString(2, stat);
			int ros= ps.executeUpdate();
				//connection.commit();
				System.out.println("offer deleted ");
				connection.commit();
				sql="DELETE FROM Bicycle WHERE bicycle_id = ?";
			
				ps=connection.prepareStatement(sql);
				ps.setInt(1, b);
			//ps.setString(2, stat);
				ros=ps.executeUpdate();
				if(ros>0) {

					System.out.println("Bicycle  deleted ");
					connection.commit();
				}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

	@Override
	public void updatepayment(int bike_id,int amount) {
		// TODO Auto-generated method stub
		
		try(Connection con=cu.getConnection()){
			String sql="select * from bicycle where bicycle_id = ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,bike_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int next=rs.getInt("price");
				int personid=rs.getInt("person_id");
				
				System.out.println(next);
				int remaining=next-amount;
				sql="update bicycle set price = ? where bicycle_id=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, remaining);
				ps.setInt(2, bike_id);
				int rows =ps.executeUpdate();
				if(rows>0) {
					System.out.println("Payment done");
					PaymentService p=new PamentServiceImp();
					Payment pay=new Payment();
					pay.setActual_payment(next);
					pay.setBicycle_id(bike_id);
					pay.setPerson_id(personid);
					pay.setRemaining_payment(remaining);
					System.out.println("Remaining Payment is "+pay.getRemaining_payment()+"");
					try {
						//pay.setPayment_id(p.addpayment(pay));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
