package com.example.splits.services;

import com.example.splits.daos.GroupDao;
import com.example.splits.daos.GroupDaoImpl;
import com.example.splits.models.Group;
import com.example.splits.utilities.DatabaseHelper;

public class GroupService {
    private GroupDao groupDao;

    public GroupService(DatabaseHelper databaseHelper) {
        this.groupDao = new GroupDaoImpl(databaseHelper);
    }

    public Group addGroup(Group group) {
        return groupDao.addGroup(group);
    }

    public Group getGroup(int id) {
        return groupDao.getGroup(id);
    }
}