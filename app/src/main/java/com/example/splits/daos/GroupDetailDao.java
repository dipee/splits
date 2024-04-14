package com.example.splits.daos;

import com.example.splits.models.GroupDetail;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public interface GroupDetailDao {

    List<GroupDetail> getGroupDetails();
}
