package spencer.revature.data;

public class BicycleStatusDAOFactory {
    
    public BicycleStatusDAO getBicycleStatusDAO() {
        
        return new BicycleStatusPostgres();
    }

}