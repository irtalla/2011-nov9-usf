package com.revature.data;

import java.util.Set;

import com.revature.beans.Purchase;

public interface PurchaseDAO extends GenericDAO<Purchase>{
	public Set<Purchase> getAll();
	public Purchase add(Purchase p);
	public Set<Purchase> getByUserId(Integer id);

}
