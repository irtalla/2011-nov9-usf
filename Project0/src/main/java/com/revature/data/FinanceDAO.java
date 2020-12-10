package com.revature.data;

import com.revature.beans.Bicycle;
import com.revature.beans.Finance;

public interface FinanceDAO extends GenericDAO<Finance>{
	public Finance add(Finance finance);
	public Finance getByBicycle(Bicycle b);
}
