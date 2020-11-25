package spencer.revature.data;

public class PaymentDAOFactory {
public PaymentDAO getPaymentDAO() {
        
        return new PaymentPostgres();
    }
}
