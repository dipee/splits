package com.example.splits.daos;

import com.example.splits.models.User;

import java.util.List;

public interface UserGroupDao {
    void addUserToGroup(int userId, int groupId);
    void removeUserFromGroup(int userId, int groupId);
    List<User> getGroupMembers(int groupId);

}
