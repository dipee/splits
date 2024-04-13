package com.example.splits.daos;

import com.example.splits.models.User;

public interface LoginInfoDao {

    User addLogin(User user);
    void addLogout(String email);

    User checkIfLoggedIn();
}
