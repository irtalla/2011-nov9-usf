package com.revature.service;

import com.revature.beans.Author;
import com.revature.beans.User;
import com.revature.data.AuthorHibernate;
import com.revature.data.EditorHibernate;
import com.revature.data.UserHibernate;
import com.revature.exception.NonUniqueUsernameException;

public class UserServiceImpl implements UserService{
    private final UserHibernate userHibernate = new UserHibernate();
    private final AuthorHibernate authorHibernate = new AuthorHibernate();
    private final EditorHibernate editorHibernate = new EditorHibernate();

    @Override
    public User addUser(User user) throws NonUniqueUsernameException {
        return userHibernate.add(user);
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


    // Returns Author ID on successful completion
    public Integer registerAuthor(String username, String password, String firstName, String lastName) throws NonUniqueUsernameException {
        Author author = new Author();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        author.setUser(userHibernate.add(user));
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setPoints(100);
        return authorHibernate.add(author).getId();
    }

    // Returns 3 if login credentials are correct. If username doesnt exist, return 1. If password is incorrect, return 2.
    public Integer authenticate(String username, String password){
        User user = new User();
        System.out.println("User Password: " + user.getPassword() + "\nEntered Password: " + password);
        try{
            user = userHibernate.getByUsername(username);
        } catch (Exception e) {
            if (e.getMessage().contains("No entity found for query")) {
                return 1;
            }
            e.printStackTrace();
        }
         if (!user.getPassword().equals(password)){
            return 2;
        }
        return 3;
    }
}
