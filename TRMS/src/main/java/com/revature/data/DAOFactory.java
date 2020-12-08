package com.revature.data;

public class DAOFactory {
	
	private static DAOFactory self = null;
	private static EmployeeDAO emDAO = null;
	private static EventDAO evDAO = null;
	private static EventTypeDAO evTypeDAO = null;
	

	
	private DAOFactory()
	{
	}
	
	public static DAOFactory getDAOFactory()
	{
		if (self == null)
		{
			self = new DAOFactory();
		}
		return self;
	}
	
	public static EmployeeDAO getEmployeeDAO()
	{
		if (emDAO == null)
			emDAO = new EmployeePostgres();
		
		return emDAO;
	}
	
	public static EventDAO getEventDAO()
	{
		if (evDAO == null)
			evDAO = new EventPostgres();
		
		return evDAO;
	}
	
	public static EventTypeDAO getEventTypeDAO()
	{
		if (evTypeDAO == null)
		{
			evTypeDAO = new EventTypeHibernate();
		}
		return evTypeDAO;
	}
}
