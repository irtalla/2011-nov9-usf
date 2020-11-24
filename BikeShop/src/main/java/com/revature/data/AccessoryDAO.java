package com.revature.data;

import com.revature.beans.Accessory;

public interface AccessoryDAO extends GenericDAO<Accessory> {
	public void purchase(Accessory t, Integer customerID, Integer num);
}
