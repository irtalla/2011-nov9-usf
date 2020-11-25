package com.revature.data;

import com.revature.beans.Bicycle;

import java.util.List;

public interface BicycleDAO {
    List<Bicycle> getAllBicycles();
    List<Bicycle> getBicyclesForUser(String userName);
    Bicycle addBicycle(Integer id, String name, String owner);
    boolean removeBicycle(Integer bicycleId);
    void updateOwner(Integer bicycleId, String userName);
}
