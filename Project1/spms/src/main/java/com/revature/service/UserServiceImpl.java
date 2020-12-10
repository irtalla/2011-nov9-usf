package com.revature.service;

import com.revature.beans.User;
import com.revature.data.UserHibernate;
import com.revature.exception.NonUniqueUsernameException;

public class UserServiceImpl implements UserService{
    private UserHibernate userHibernate = new UserHibernate();

    @Override
    public Integer addUser(User user) throws NonUniqueUsernameException {
        return userHibernate.add(user).getId();
    }

    @Override
    public User getUserById(Integer id) {
        return userHibernate.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userHibernate.getByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userHibernate.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userHibernate.delete(user);
    }
}
