package com.revature.data;

public class EvtReqDAOFactory {
    
    public EvtReqDAO getEvtReqDAO() {
        
        return new EvtReqPostgres();
    }

}
