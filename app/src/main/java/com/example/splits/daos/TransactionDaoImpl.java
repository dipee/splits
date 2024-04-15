package com.example.splits.daos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.SettlementUser;
import com.example.splits.models.Transaction;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao{


    DatabaseHelper databaseHelper;


    public TransactionDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public Transaction addTransaction(Transaction transaction) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("payerId", transaction.getPayerId());
        values.put("payeeId", transaction.getPayeeId());
        values.put("remark", transaction.getRemark());
        values.put("amount", transaction.getAmount());
        values.put("date", transaction.getDate());
        long newRowId = db.insert("transactions", null, values);
        transaction.setId(newRowId);
        return transaction;

    }

    @SuppressLint("Range")
    @Override
    public Transaction getTransaction(int transactionId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("transactions", null, "id = ?", new String[]{String.valueOf(transactionId)}, null, null, null);
        cursor.moveToFirst();
        Transaction transaction = new Transaction();
        transaction.setId(cursor.getLong(cursor.getColumnIndex("id")));
        transaction.setPayerId(cursor.getLong(cursor.getColumnIndex("payerId")));
        transaction.setPayeeId(cursor.getLong(cursor.getColumnIndex("payeeId")));
        transaction.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
        transaction.setAmount(cursor.getDouble(cursor.getColumnIndex("amount")));
        transaction.setDate(cursor.getString(cursor.getColumnIndex("date")));
        return transaction;

    }


}
