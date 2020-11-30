package com.revature.data;


import com.revature.beans.PersonBike;

public interface PersonBikeDAO extends GenericDAO<PersonBike>{ 

	public PersonBike add(PersonBike pb);
	
}
