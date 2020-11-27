package com.revature.data;

import com.revature.models.InventoryStatus;

public interface InventoryStatusDAO extends GenericDAO<InventoryStatus> {
	public InventoryStatus add(InventoryStatus t);
}
