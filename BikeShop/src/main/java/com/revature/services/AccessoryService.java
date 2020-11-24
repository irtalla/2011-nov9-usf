package com.revature.services;

import java.util.Set;

import com.revature.beans.Accessory;


public interface AccessoryService {
	public Integer addAccessory(Accessory a);
	// "read" methods
	public Accessory getAccessoryById(Integer id);
	public Set<Accessory> getAccessories();
	// "update" methods
	public void updateAccessory(Accessory c);
	// "delete" methods
	public void removeAccessory(Accessory c);
}
