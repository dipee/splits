package com.example.splits.daos;


import com.example.splits.models.GroupDetail;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class GroupDetailDaoImpl implements GroupDetailDao{
    DatabaseHelper databaseHelper;
    public GroupDetailDaoImpl(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }
    @Override
    public List<GroupDetail> getGroupDetails(int userId) {
//        DbQueries dbQueries = new DbQueries(databaseHelper);
       return databaseHelper.getGroupDetail(userId);
    }
}
