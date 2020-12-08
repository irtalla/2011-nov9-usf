package JDBC;

public class SubmitFormDAOFactory {
	
	public SubmitFormDAO getSubmitFormDAO() {
		return new SubmitFormPost();
	}

}
