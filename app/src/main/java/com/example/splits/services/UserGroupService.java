package com.example.splits.services;

import com.example.splits.daos.UserGroupDao;
import com.example.splits.daos.UserGroupDaoImpl;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class UserGroupService {
    UserGroupDao userGroupDao;

    public UserGroupService(DatabaseHelper databaseHelper) {
         userGroupDao = new UserGroupDaoImpl(databaseHelper);
    }

    public void addUserToGroup(int userId, int groupId) {
        userGroupDao.addUserToGroup(userId, groupId);
    }

    public void removeUserFromGroup(int userId, int groupId) {
        userGroupDao.removeUserFromGroup(userId, groupId);
    }

    public List<User> getGroupMembers(int groupId) {
        return userGroupDao.getGroupMembers(groupId);
    }

}
