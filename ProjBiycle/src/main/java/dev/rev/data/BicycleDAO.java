package dev.rev.data;

import java.util.Set;

import dev.rev.model.Bicycle;

public interface BicycleDAO extends GenericDAo<Bicycle>{
		public Bicycle getBicyclebybrand(String name);
		public Bicycle getbyID(int id);
		public void updatebikestatus(int id,int person_id);
		public Set<Bicycle> bicyclebyp(int p_id);
}
