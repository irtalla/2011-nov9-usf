package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Flower;
import com.revature.beans.Purchase;
import com.revature.data.PurchaseRepository;

@Service
public class PurchaseService {
	private PurchaseRepository purchaseDao;
	
	@Autowired
	public PurchaseService(PurchaseRepository pr) {
		purchaseDao = pr;
	}
	
	public Purchase getPurchase(Integer id) {
		return purchaseDao.findOne(id);
	}
	
	public Set<Purchase> getPurchasesByFlower(Flower f) {
		return purchaseDao.findByFlower(f);
	}
	
	@Transactional
	public void addPurchase(Purchase p) {
		purchaseDao.save(p);
	}
}
