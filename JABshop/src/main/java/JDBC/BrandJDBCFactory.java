package JDBC;

public class BrandJDBCFactory {
	
	public BrandJDBC getBrandJDBC() {
		return new BrandPostgres();
	}

}
