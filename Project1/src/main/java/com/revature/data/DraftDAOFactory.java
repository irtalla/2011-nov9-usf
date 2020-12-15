package com.revature.data;

public class DraftDAOFactory {
    public DraftDAO getDraftDAO() {
        return new DraftHibernate();
    }
}
