package com.revature.data;

import com.revature.beans.Person;
import com.revature.services.PersonServices;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {
    Person person = new Person();

    @Test
    public void createUser() {
    }

    @Test
    public void loginUser() {
        String name = "asdasd";
        String password = "3edc1qaZ";

        assertNotEquals(password, null);
    }

    @Test
    public void getUsername() {
        String name = "palle017";

        assertEquals(name, person.getUserName());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }
}