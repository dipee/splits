package com.example.splits.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.Bill;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao{

    DatabaseHelper databaseHelper;

    public BillDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public Bill addBill(Bill bill) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", bill.getTitle());
        values.put("amount", bill.getAmount());
        values.put("date", bill.getDate());
        values.put("description", bill.getDescription());
        values.put("payerUserId", bill.getPayerUserId());
        values.put("groupId", bill.getGroupId());

        long id = db.insert("Bill", null, values);
        db.close();
        if(id == -1){
            return null;
        }
        bill.setId((int) id);
        return bill;

    }

    @Override
    public List<Bill> getBillsOfGroup(int groupId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bill WHERE groupId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(groupId)});
        if(cursor.moveToFirst()){
            do{
                Bill bill = new Bill();
                bill.setId(cursor.getInt(0));
                bill.setTitle(cursor.getString(1));
                bill.setDescription(cursor.getString(2));
                bill.setPayerUserId(cursor.getInt(3));
                bill.setGroupId(cursor.getInt(4));
                bill.setAmount(cursor.getInt(5));
                bill.setDate(cursor.getString(6));
                bills.add(bill);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bills;

    }

    @Override
    public List<Bill> getBillOfGroupOfUser(int groupId, int userId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bill WHERE groupId = ? AND payerUserId = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(groupId), String.valueOf(userId)});
        if(cursor.moveToFirst()){
            do{
                Bill bill = new Bill();
                bill.setId(cursor.getInt(0));
                bill.setTitle(cursor.getString(1));
                bill.setDescription(cursor.getString(2));
                bill.setPayerUserId(cursor.getInt(3));
                bill.setGroupId(cursor.getInt(4));
                bill.setAmount(cursor.getInt(5));
                bill.setDate(cursor.getString(6));
                bills.add(bill);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bills;
    }
}
