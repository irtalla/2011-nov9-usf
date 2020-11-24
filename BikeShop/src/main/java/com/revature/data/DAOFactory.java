package com.revature.data;

public class DAOFactory {
	
	private static DAOFactory self = null;
	
	private static AccessoryDAO ADAO = null;
	private static CustomerDAO CDAO = null;
	private static BikeDAO BDAO = null;
	private static OfferDAO ODAO = null;
	private static PaymentDAO PDAO = null;
	
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
	
	public static AccessoryDAO getAccessoryDAO()
	{
		if (ADAO == null)
		{
			ADAO = new AccessoryPostgres();
		}
		return ADAO;
	}
	
	public static OfferDAO getOfferDAO()
	{
		if (ODAO == null)
		{
			ODAO = new OfferPostgres();
		}
		return ODAO;
	}
	
	public static CustomerDAO getCustomerDAO()
	{
		if (CDAO == null)
		{
			CDAO = new CustomerPostgres();
		}
		return CDAO;
	}
	
	public static BikeDAO getBikeDAO()
	{
		if (BDAO == null)
		{
			BDAO = new BikePostgres();
		}
		return BDAO;
	}
	
	public static PaymentDAO getPaymentDAO()
	{
		if (PDAO == null)
		{
			PDAO = new PaymentPostgres();
		}
		return PDAO;
	}
	
}
