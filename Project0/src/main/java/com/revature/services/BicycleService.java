package com.revature.services;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Finance;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.data.BicyclePostgres;
import com.revature.data.FinancePostgres;

public class BicycleService {
	private BicyclePostgres bicyclePostgres;
	private FinancePostgres financePostgres;
	
	public BicycleService() {
		bicyclePostgres = new BicyclePostgres();
		financePostgres = new FinancePostgres();
	}
	
	public Bicycle getBicycleById(Integer id) {
		return bicyclePostgres.getById(id);
	}
	
	public Set<Bicycle> getAll(){
		return bicyclePostgres.getAll();
	}
	
	public void update(Bicycle b) {
		bicyclePostgres.update(b);
	}
	
	public void delete(Bicycle b) {
		bicyclePostgres.delete(b);
	}
	
	public Integer addBicycle(Bicycle b) {
		return bicyclePostgres.add(b).getId();
	}
	
	public Set<Bicycle> getAvaliableBicycles(){
		return bicyclePostgres.getAvailableBicycles();
	}
	
	public Set<Bicycle> getOwnedBicycles(Person p){
		return bicyclePostgres.getOwnedBicycles(p);
	}
	
	public void registerOwner(Bicycle b, Person p) {
		Status status = new Status();
		status.changeToUnavaliable();
		b.setStatus(status);
		bicyclePostgres.update(b);
		bicyclePostgres.updateOwner(b, p);
	}
	
	public Double ownedAmount(Bicycle b) {
		Finance finance = financePostgres.getByBicycle(b);
		return finance.getFinancedAmount();
	}
}
