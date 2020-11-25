package com.revature.services;

import java.util.Set;

import com.revature.beans.Purchase;

public interface PurchaseServices {
	
	//create
	public Purchase addPurchase(Purchase p);
	//read
	public Set<Purchase> getAll();
	
	public Set<Purchase> getByUserId(Integer id);
	
	public Purchase getById(Integer id);
	//update
	public void updatePurchase(Purchase p);
	//delete
	public void deletePurchase(Purchase p);
	
	
	

}
