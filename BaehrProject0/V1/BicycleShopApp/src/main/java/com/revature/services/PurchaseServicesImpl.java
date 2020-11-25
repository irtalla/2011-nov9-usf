package com.revature.services;

import java.util.Set;

import com.revature.beans.Purchase;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchaseDAOFactory;

public class PurchaseServicesImpl implements PurchaseServices{
	
	private PurchaseDAO purchaseDao;
	
	public PurchaseServicesImpl() {
		PurchaseDAOFactory purchaseDaoFactory = new PurchaseDAOFactory();
		purchaseDao = purchaseDaoFactory.getPurchaseDAO();
	}

	@Override
	public Purchase addPurchase(Purchase p) {
		return purchaseDao.add(p);
	}

	@Override
	public Set<Purchase> getAll() {
		return purchaseDao.getAll();
	}

	@Override
	public Set<Purchase> getByUserId(Integer id) {
		return purchaseDao.getByUserId(id);
	}

	@Override
	public Purchase getById(Integer id) {
		return purchaseDao.getById(id);
	}

	@Override
	public void updatePurchase(Purchase p) {
		purchaseDao.update(p);;
	}

	@Override
	public void deletePurchase(Purchase p) {
		purchaseDao.delete(p);
		
	}

}
