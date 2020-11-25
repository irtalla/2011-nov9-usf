package spencer.revature.data;

public class CustomerDAOFactory {
    
    public CustomerDAO getCustomerDAO() {
        
        return new CustomerPostgres();
    }

}