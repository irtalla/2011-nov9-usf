package com.revature.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import com.cross.beans.Purchase;


public class PurchasePostgres_Test {

	PurchasePostgres testPurchasePostgres = new PurchasePostgres(); 
	
	@DisplayName("Test add()") 
	@Test
	public void addTest() {
		
		Purchase newPurchase = new Purchase(); 
		newPurchase.setCustomerId(2);
		newPurchase.setProductId(7);
//		Assertions.assertNotEquals(null, testPurchasePostgres.add(newPurchase) ); 
//		Assertions.assertTrue(
//				testPurchasePostgres.getPurchasesByCustomerId(2)
//				.removeIf( purchase -> purchase.getProductId() == 7)
//				);
		
		Assertions.assertThrows(Exception.class, () -> {
			testPurchasePostgres.add(newPurchase);
		}); 
	}
	
	
}
