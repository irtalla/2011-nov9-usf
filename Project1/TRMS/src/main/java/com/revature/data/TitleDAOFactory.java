package com.revature.data;

public class TitleDAOFactory {
	
	public TitleDAO getTitleDAO() {

		return new TitlePostgres();
	}
}



    
 
