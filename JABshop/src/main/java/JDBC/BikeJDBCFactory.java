package JDBC;

public class BikeJDBCFactory {

		public BikeJDBC getBikeJDBC() {
			return new BikePostgres();
		}
}
