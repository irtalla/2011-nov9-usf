package dev.rev.data;

import java.util.List;
import java.util.Set;

import dev.rev.model.Bicycle;

public interface BicycleDAO extends GenericDAo<Bicycle>{
		public Bicycle getBicyclebybrand(String name);
		public Bicycle getbyID(int id);
		public void updatebikestatus(int id,int person_id,int price);
		public void delbike(int b);
		public List<Bicycle> bicyclebyp(int p_id);
		public void updatepayment(int bike_id,int amount);
}
