package com.example.splits.daos;

import com.example.splits.models.Transaction;

public interface TransactionDao {

    Transaction addTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);

}
