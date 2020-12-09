package dev.elliman.services;

import java.util.List;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;

public interface ClaimService {
	
	//read
	public List<Claim> getClaimsByPerson(Person person);
	public List<Claim> getDSUnapprovedClaims();
	public Claim getClaimByID(Integer id);
	
	//write
	public Claim makeClaim(Claim claim);
	public boolean accept(Claim claim);
}
