package JDBC;
import Entity.Human;

public interface HumanJDBC extends GenericJDBC<Human> {
	public Human add(Human h); //throws NonUniqueUsernameException;
	public Human getByUsername(String username);
	
}
