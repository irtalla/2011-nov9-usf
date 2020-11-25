package JDBC;

public class StatusJDBCFactory {
	
	public StatusJDBC getStatusJDBC() {
		return new StatusPostgres();
	}

}
