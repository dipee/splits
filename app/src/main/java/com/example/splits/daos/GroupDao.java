package com.example.splits.daos;

import com.example.splits.models.Group;
import com.example.splits.models.User;

import java.util.List;

public interface GroupDao {
    Group addGroup(Group group);
    Group getGroup(int id);

}
