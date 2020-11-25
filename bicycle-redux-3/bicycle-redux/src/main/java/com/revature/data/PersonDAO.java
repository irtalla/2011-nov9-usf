package com.revature.data;

import com.revature.beans.Person;

public interface PersonDAO {
    Person loginUser(String userName);

    Person createUser(String userName, String type);
}
