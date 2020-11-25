package spencer.revature.data;

public class EmployeeDAOFactory {
    
    public EmployeeDAO getEmployeeDAO() {
        
        return new EmployeePostgres();
    }
}
