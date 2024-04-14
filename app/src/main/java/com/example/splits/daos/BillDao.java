package com.example.splits.daos;

import com.example.splits.models.Bill;

import java.util.List;

public interface BillDao {
    Bill addBill(Bill bill);
    List<Bill> getBillsOfGroup(int groupId);
    List<Bill> getBillOfGroupOfUser(int groupId, int userId);

}
