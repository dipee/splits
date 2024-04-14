package com.example.splits.daos;

import com.example.splits.models.Group;

import java.util.List;

public interface GroupDao {
    Group addGroup(Group group);
    Group getGroup(int id);

    List<Group> getAllGroups();
}
