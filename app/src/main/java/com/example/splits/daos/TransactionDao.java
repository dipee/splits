package com.example.splits.daos;

import com.example.splits.models.SettlementUser;
import com.example.splits.models.Transaction;

import java.util.List;

public interface TransactionDao {

    Transaction addTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);

}
