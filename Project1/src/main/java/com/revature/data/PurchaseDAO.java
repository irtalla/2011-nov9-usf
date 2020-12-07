package com.revature.data;

import java.util.Set;

import com.cross.beans.Purchase;

public interface PurchaseDAO extends GenericDAO<Purchase> {
	public Set<Purchase> getPurchasesByCustomerId(Integer customerId); 
	
}
