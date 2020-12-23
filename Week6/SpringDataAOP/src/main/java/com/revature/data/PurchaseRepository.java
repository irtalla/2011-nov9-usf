package com.revature.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Flower;
import com.revature.beans.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	Set<Purchase> findByFlower(Flower flower);
}
