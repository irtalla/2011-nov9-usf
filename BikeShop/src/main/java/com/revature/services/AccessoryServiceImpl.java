package com.revature.services;

import java.util.Set;

import com.revature.beans.Accessory;
import com.revature.data.AccessoryDAO;
import com.revature.data.DAOFactory;

public class AccessoryServiceImpl implements AccessoryService {
	
	private AccessoryDAO dao = DAOFactory.getAccessoryDAO();
	
	public Integer addAccessory(Accessory a) {
		return dao.add(a).getID();
	}

	public Accessory getAccessoryById(Integer id) {
		return dao.getById(id);
	}

	public Set<Accessory> getAccessories() {
		return dao.getAll();
	}

	public void updateAccessory(Accessory c) {
		dao.update(c);

	}

	public void removeAccessory(Accessory c) {
		dao.delete(c);

	}

	@Override
	public void purchase(Accessory a, Integer customerID, Integer num) {
		dao.purchase(a, customerID, num);
		
	}

}
