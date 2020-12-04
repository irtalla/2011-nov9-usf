package dev.elliman.services;

import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;

public interface ClaimService {
	
	//read
	public Set<Claim> getClaimsByPerson(Person person);
	public Set<Claim> getClaimsByPerson(Integer id);
	
	//write
	public Integer makeClaim(Claim claim);
}
