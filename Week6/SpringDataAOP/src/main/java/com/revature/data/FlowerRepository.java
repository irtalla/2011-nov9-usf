package com.revature.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {
	// by using certain naming guidelines, we can have Spring write implementations of more specific methods
	Flower findByName(String name);
	// Person findByUsernameAndPassword(String username, String password)
	Set<Flower> findByCurrentPrice(Double currentPrice);
}
