package com.revature.data;

import com.revature.models.InventoryType;

public interface InventoryTypeDAO extends GenericDAO<InventoryType> {
	public InventoryType add(InventoryType t);
}
