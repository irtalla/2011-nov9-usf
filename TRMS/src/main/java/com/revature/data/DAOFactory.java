package com.revature.data;

public class DAOFactory {
	
	private static DAOFactory self = null;
	private static EmployeeDAO emDAO = null;
	private static EventDAO evDAO = null;
	private static EventTypeDAO evTypeDAO = null;
	private static ReimbursementFormDAO formDAO = null;
	private static DepartmentDAO depDAO = null;
	private static ApprovalDAO appDAO = null;
	private static InformationRequestDAO infoDAO = null;
	private static ApprovalFileDAO appFileDAO = null;
	private static AttatchmentDAO evAttDAO = null;
	

	
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
	
	public static ReimbursementFormDAO getReimbursementFormDAO()
	{
		if (formDAO == null)
		{
			formDAO = new ReimbursementFormHibernate();
		}
		return formDAO;
	}
	
	public static DepartmentDAO getDepartmentDAO()
	{
		if (depDAO == null)
		{
			depDAO = new DepartmentHibernate();
		}
		return depDAO;
	}
	
	public static ApprovalDAO getApprovalDAO()
	{
		if (appDAO == null)
		{
			appDAO = new ApprovalHibernate();
		}
		return appDAO;
	}
	
	public static InformationRequestDAO getInformationRequestDAO()
	{
		if (infoDAO == null)
		{
			infoDAO = new InformationRequestHibernate();
		}
		return infoDAO;
	}
	
	public static ApprovalFileDAO getApprovalFileDAO()
	{
		if (appFileDAO == null)
		{
			appFileDAO = new ApprovalFileHibernate();
		}
		return appFileDAO;
	}
	
	public static AttatchmentDAO getAttatchmentDAO()
	{
		if (evAttDAO == null)
		{
			evAttDAO = new AttatchmentHibernate();
		}
		return evAttDAO;
		
	}
}
