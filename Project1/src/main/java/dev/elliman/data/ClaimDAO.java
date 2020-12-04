package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;

public interface ClaimDAO {
	
	//read 
	public Set<Claim> getClaimsByPerson(Integer personID);
	
	//write
	public Integer makeClaim(Claim claim);
}
