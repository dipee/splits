package com.example.splits.services;

import com.example.splits.daos.TransactionDao;
import com.example.splits.daos.TransactionDaoImpl;
import com.example.splits.models.Transaction;
import com.example.splits.utilities.DatabaseHelper;
import com.example.splits.utilities.DbQueries;

public class SettlementService {

    DatabaseHelper databaseHelper;
    TransactionDao transactionDao;

    DbQueries dbQueries;

    public SettlementService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        this.transactionDao = new TransactionDaoImpl(databaseHelper);
        this.dbQueries = new DbQueries(databaseHelper);
    }

    public Transaction addSettlement(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }



}
