package com.revature.utils;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.revature.utils.ConnectionUtil;

public class ConnectionUtil_Tests {
	
	
	@DisplayName("Simple postgres connectivity test")
	@Test
	public void testPostgresConnectivity() {
		
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil(); 
		Connection conn = cu.getConnection(); 
	
		Assertions.assertNotEquals(null, conn);
		
		System.out.println( conn.toString() ); 
		
	}
	

}
