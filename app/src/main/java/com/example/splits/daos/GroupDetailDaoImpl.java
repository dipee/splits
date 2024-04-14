package com.example.splits.daos;


import com.example.splits.models.GroupDetail;
import com.example.splits.utilities.DatabaseHelper;
import com.example.splits.utilities.DbQueries;

import java.util.List;

public class GroupDetailDaoImpl implements GroupDetailDao{
    DatabaseHelper databaseHelper;
    GroupDetailDaoImpl(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }
    @Override
    public List<GroupDetail> getGroupDetails(DatabaseHelper databaseHelper) {
        DbQueries dbQueries = new DbQueries(databaseHelper);
       return getGroupDetails(databaseHelper);
    }
}
