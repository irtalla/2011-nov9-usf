package JDBC;
import Entity.Brand;

public interface BrandJDBC extends GenericJDBC<Brand> {
	public Brand add(Brand b);
}
