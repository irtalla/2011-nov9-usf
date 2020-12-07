package dev.elliman.data;

import java.util.List;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;

public interface ClaimDAO {
	
	//read 
	public List<Claim> getClaimsByPerson(Person person);
	
	//write
	public Claim makeClaim(Claim claim);
}
