package com.example.splits.services;

import com.example.splits.daos.GroupDao;
import com.example.splits.daos.GroupDaoImpl;
import com.example.splits.daos.GroupDetailDao;
import com.example.splits.daos.GroupDetailDaoImpl;
import com.example.splits.models.Group;
import com.example.splits.models.GroupDetail;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class GroupService {
    private GroupDao groupDao;

    private GroupDetailDao groupDetailDao;

    public GroupService(DatabaseHelper databaseHelper) {
        this.groupDao = new GroupDaoImpl(databaseHelper);
        this.groupDetailDao = new GroupDetailDaoImpl(databaseHelper);
    }

    public Group addGroup(Group group) {
        return groupDao.addGroup(group);
    }

    public Group getGroup(int id) {
        return groupDao.getGroup(id);
    }

    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    public List<GroupDetail> getGroupDetails() {
        return groupDetailDao.getGroupDetails();
    }
}