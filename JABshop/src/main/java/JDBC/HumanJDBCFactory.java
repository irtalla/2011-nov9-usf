package JDBC;

public class HumanJDBCFactory {
	
	public HumanJDBC getHumanJDBC() {
		return new HumanPostgres();
	}

}
