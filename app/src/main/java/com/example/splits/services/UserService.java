package com.example.splits.services;

import com.example.splits.daos.UserDao;
import com.example.splits.daos.UserDaoImpl;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(DatabaseHelper databaseHelper) {
        this.userDao = new UserDaoImpl(databaseHelper);
    }

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUser(String email) {
        return userDao.getUser(email);
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    public Boolean loginUser(String email, String password) {
        return userDao.loginUser(email, password);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}