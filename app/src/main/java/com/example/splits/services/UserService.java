package com.example.splits.services;

import com.example.splits.daos.LoginInfoDao;
import com.example.splits.daos.LoginInfoDaoImpl;
import com.example.splits.daos.UserDao;
import com.example.splits.daos.UserDaoImpl;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class UserService {
    private UserDao userDao;
    private LoginInfoDao loginInfoDao;

    public UserService(DatabaseHelper databaseHelper) {
        this.userDao = new UserDaoImpl(databaseHelper);
        this.loginInfoDao = new LoginInfoDaoImpl(databaseHelper);
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

    public User loginUser(String email, String password) {
        User user =  userDao.loginUser(email, password);
        if(user != null) {

            return loginInfoDao.addLogin(user);

        }
        return null;
    }

    public boolean logoutUser(String email) {
        loginInfoDao.addLogout(email);
        return true;
    }

    public User checkIfLoggedIn() {
        return loginInfoDao.checkIfLoggedIn();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}