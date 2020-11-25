package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Person;
import dev.elliman.data.BikeDAO;
import dev.elliman.data.BikeDAOFactory;
import dev.elliman.data.PersonDAO;

public class BikeServiceImpl implements BikeService {
	
	private BikeDAO bikeDAO;
	
	public BikeServiceImpl() {
		bikeDAO = new BikeDAOFactory().getBikeDAO();
	}

	@Override
	public Integer addBike(Bike bike) {
		return bikeDAO.add(bike);
	}

	@Override
	public Bike getById(Integer id) {
		return bikeDAO.getByID(id);
	}

	@Override
	public Set<Bike> getAvalibleBikes() {
		return bikeDAO.getAvalibleBikes();
	}

	@Override
	public Bike getByModel(String model) {
		return bikeDAO.getByModel(model);
	}

	@Override
	public void update(Bike bike) {
		bikeDAO.update(bike);
	}

	@Override
	public void delete(Bike bike) {
		bikeDAO.delete(bike);
	}

	@Override
	public Set<Bike> getAll() {
		return bikeDAO.getAll();
	}

	@Override
	public Set<Bike> getOwnedBikes(Person person) {
		return bikeDAO.getOwnedBikes(person);
	}

}
