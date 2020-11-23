package dev.rev.data;

import dev.rev.model.Bicycle;

public interface BicycleDAO extends GenericDAo<Bicycle>{
		public Bicycle getBicyclebybrand(String name);
		public Bicycle getbyID(int id);
}
