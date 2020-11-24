package dev.rev.services;

import java.util.Set;

import dev.rev.data.BicycleDAO;
import dev.rev.data.BicyclesDAOFactory;
import dev.rev.model.Bicycle;

public class BicycleServiceImp implements BicycleService{
	private BicycleDAO bd;
	
	public BicycleServiceImp () {
	BicyclesDAOFactory bdf= new BicyclesDAOFactory();
	bd=bdf.getBicycleDAO();
	}
	
	@Override
	public int addBicycle(Bicycle b) throws Exception {
		// TODO Auto-generated method stub
		
		return bd.add(b).getId();
	}

	@Override
	public Bicycle getBicyclebyBrand(String brand) {
		// TODO Auto-generated method stub
		return bd.getBicyclebybrand(brand);
	}

	@Override
	public Set<Bicycle> getallBicyles() {
		// TODO Auto-generated method stub
		return bd.getAll();
	}

	@Override
	public void updateBicycle(Bicycle b) {
		// TODO Auto-generated method stub
		bd.update(b);
	}

	@Override
	public void deleteBicycle(Bicycle id) {
		// TODO Auto-generated method stub
		bd.delete(id);
	}

	@Override
	public Bicycle getbyID(int id) {
		
		// TODO Auto-generated method stub
		return bd.getbyID(id);
	}

	@Override
	public void updateBikeStatus(int id,int person_id) {
		// TODO Auto-generated method stub
		bd.updatebikestatus(id,person_id);
	}

	@Override
	public Set<Bicycle> bikes(int p_id) {
		// TODO Auto-generated method stub
		return bd.bicyclebyp(p_id);
	}

}
