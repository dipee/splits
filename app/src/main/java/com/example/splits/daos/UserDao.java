package com.example.splits.daos;

import com.example.splits.models.User;

import java.util.List;

public interface UserDao {
    User addUser(User user);
    User getUser(String email);
    User getUser(int id);
    Boolean loginUser(String email, String password);

    List<User> getAllUsers();
}
