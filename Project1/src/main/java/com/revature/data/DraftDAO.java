package com.revature.data;

import com.revature.beans.Person;
import com.revature.beans.Draft;

import java.util.Set;

public interface DraftDAO extends GenericDAO<Draft> {
    public Draft add(Draft d);
    public Set<Draft> getPending();
    public Set<Draft> getByAuthor(Person author);
}
