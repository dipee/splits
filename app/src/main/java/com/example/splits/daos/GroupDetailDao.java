package com.example.splits.daos;

import com.example.splits.models.GroupDetail;

import java.util.List;

public interface GroupDetailDao {

    List<GroupDetail> getGroupDetails(int userId);
}
