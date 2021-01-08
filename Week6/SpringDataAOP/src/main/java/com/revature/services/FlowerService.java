package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Flower;
import com.revature.data.FlowerRepository;

@Service
@Transactional(readOnly=true)
public class FlowerService {
	private FlowerRepository flowerDao;
	
	@Autowired
	public FlowerService(FlowerRepository fr) {
		flowerDao = fr;
	}
	
	public Flower getFlower(Integer id) {
		return flowerDao.findOne(id);
	}
	
	public Flower getByName(String name) {
		return flowerDao.findByName(name);
	}
	
	public List<Flower> getAll() {
		return flowerDao.findAll();
	}
	
	@Transactional(readOnly=false, propagation=Propagation.SUPPORTS)
	public void addFlower(Flower f) {
		flowerDao.save(f);
	}
	
	public void throwExceptionFlower(String s) {
		if (s.equals(""))
			System.out.println("it didn't throw an exception");
	}
}
