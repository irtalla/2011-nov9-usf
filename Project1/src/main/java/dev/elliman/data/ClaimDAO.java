package dev.elliman.data;

import java.util.List;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;

public interface ClaimDAO {
	
	//read 
	public List<Claim> getClaimsByPerson(Person person);
	public List<Claim> getDSUnapprovedClaims();
	public List<Claim> getApprovedClaims(Person person);
	public Claim getClaimByID(Integer id);
	
	//write
	public Claim makeClaim(Claim claim);
	public Boolean update(Claim claim);
}
