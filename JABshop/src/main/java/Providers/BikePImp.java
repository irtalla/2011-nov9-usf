package Providers;
import java.util.Set;
import Entity.Bike;
import Entity.Human;
import Entity.Status;
import JDBC.BikeJDBC;
import JDBC.BikeJDBCFactory;
import JDBC.HumanJDBC;
import JDBC.HumanJDBCFactory;

public class BikePImp implements BikeP{
	private BikeJDBC bikejdbc;
	private HumanJDBC humanjdbc;
	
	public BikePImp() {
		BikeJDBCFactory bikejdbcfactory = new BikeJDBCFactory();
		bikejdbc = bikejdbcfactory.getBikeJDBC();
		
		HumanJDBCFactory humanjdbcfactory = new HumanJDBCFactory();
		humanjdbc = humanjdbcfactory.getHumanJDBC();
	}
	

	@Override
	public Integer addBike(Bike b) {
		return bikejdbc.add(b).getId();
	}
	

	@Override
	public Bike getBikeById(Integer id) {
		return bikejdbc.getById(id);
	}
	

	@Override
	public Set<Bike> getBikes() {
			return bikejdbc.getAll();
	}
	

	@Override
	public Set<Bike> getAvailableBikes() {
		return bikejdbc.getAvailableBikes();
	}
	

	@Override
	public void updateBike(Bike b) {
		bikejdbc.update(b);
	}
	

	@Override
	public void buyBike(Human h, Bike b) {
			Status status = new Status();
			status.setId(2);
			status.setName("sold");
			b.setStatus(status);
			updateBike(b);
			Set<Bike> set = h.getBikeList();
			set.add(b);
			h.setBikes(set);
			System.out.println(h.getBikeList().size());
	}

	@Override
	public void removeBike(Bike b) {
		bikejdbc.remove(b);
	}
	
	

}
