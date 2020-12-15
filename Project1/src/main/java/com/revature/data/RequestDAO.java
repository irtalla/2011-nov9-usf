package com.revature.data;

import com.revature.beans.Person;
//import com.revature.beans.Pitch;
import com.revature.beans.Request;

import java.util.Set;

public interface RequestDAO extends GenericDAO<Request> {
    public Request add(Request r);
    public Set<Request> getRequestsByPerson(Person p);
}
