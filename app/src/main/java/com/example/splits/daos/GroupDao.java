package com.example.splits.daos;

import com.example.splits.models.Group;

public interface GroupDao {
    Group addGroup(Group group);
    Group getGroup(int id);
}
