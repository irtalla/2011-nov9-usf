package Providers;

import Entity.Human;
import JDBC.HumanJDBC;
import JDBC.HumanJDBCFactory;

public class HumanPImp implements HumanP{
	private HumanJDBC humanjdbc;
	
	public HumanPImp() {
		HumanJDBCFactory humanjdbcfactory = new HumanJDBCFactory();
		humanjdbc = humanjdbcfactory.getHumanJDBC();
	}
	
	@Override
	public Integer addHuman(Human h) {
		return humanjdbc.add(h).getId();
	}

	@Override
	public Human getHumanById(Integer id) {
		return humanjdbc.getById(id);
	}

	@Override
	public Human getHumanByUsername(String username) {
		return humanjdbc.getByUsername(username);
	}

	@Override
	public void updateHuman(Human h) {
		humanjdbc.update(h);
	}

	@Override
	public void removeHuman(Human h) {
		humanjdbc.remove(h);
	}
	
}
