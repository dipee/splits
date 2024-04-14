package com.example.splits.services;

import com.example.splits.daos.BillDao;
import com.example.splits.daos.BillDaoImpl;
import com.example.splits.models.Bill;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class BillService {

    DatabaseHelper databaseHelper;

    BillDao billDao;

    public BillService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        billDao = new BillDaoImpl(databaseHelper);
    }

    public Bill addBill(Bill bill) {
       return  billDao.addBill(bill);
    }

    public List<Bill> getBillsOfGroup(int groupId) {
        return billDao.getBillsOfGroup(groupId);
    }

    public List<Bill> getBillsOfGroupOfUser(int groupId, int userId) {
        return billDao.getBillOfGroupOfUser(groupId, userId);
    }
}
