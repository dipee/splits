package com.example.splits.utilities;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.GroupDetail;
import com.example.splits.models.SettlementUser;

import java.util.ArrayList;
import java.util.List;

public class DbQueries {
    DatabaseHelper databaseHelper;

    public DbQueries(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    @SuppressLint("Range")
    public List<GroupDetail> getGroupDetail(int userId) {
        List<GroupDetail> groupDetails = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Query to retrieve total amount owed, total amount paid, and total transactions made by a user in all groups
        String query = "SELECT " +
                "g.id AS groupId, " +
                "g.name AS groupName, " +
                "COUNT(DISTINCT bp." + DatabaseHelper.KEY_USER_ID + ") AS userCount, " +
                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + " ELSE 0 END), 0) AS totalOwed, " +
                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + " ELSE 0 END), 0) + COALESCE(SUM(CASE WHEN t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? THEN t." + DatabaseHelper.KEY_TRANSACTION_AMOUNT + " ELSE 0 END), 0) AS totalPaid " +
                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON g.id = b." + DatabaseHelper.KEY_BILL_GROUP_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILL_PARTICIPANTS + " bp ON b.id = bp." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_TRANSACTIONS + " t ON g.id = t." + DatabaseHelper.KEY_GROUP_ID + " " +
                "GROUP BY g.id";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(userId), String.valueOf(userId)});

        // Loop through the cursor and add user balances to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupDetail groupDetail = new GroupDetail();
                groupDetail.setGroupId(cursor.getInt(cursor.getColumnIndex("groupId")));
                groupDetail.setGroupName(cursor.getString(cursor.getColumnIndex("groupName")));
                groupDetail.setUserCount(cursor.getInt(cursor.getColumnIndex("userCount")));
                groupDetail.setTotalOwed(cursor.getDouble(cursor.getColumnIndex("totalOwed")));
                groupDetail.setTotalPaid(cursor.getDouble(cursor.getColumnIndex("totalPaid")));
                groupDetails.add(groupDetail);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Close database connection
        db.close();

        return groupDetails;
    }

    @SuppressLint("Range")
    public List<SettlementUser> getSettlementUsers(int userId, int groupId) {
        List<SettlementUser> settlementUsers = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Query to retrieve users to whom the supplied userId owes money in a specific group
        String query = "SELECT " +
                "u." + DatabaseHelper.KEY_ID + " AS userId, " +
                "u." + DatabaseHelper.KEY_NAME + " AS userName, " +
                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? AND bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + " > bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + " - COALESCE(SUM(CASE WHEN t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? THEN t." + DatabaseHelper.KEY_TRANSACTION_AMOUNT + " ELSE 0 END), 0) THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + " - bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + " + COALESCE(SUM(CASE WHEN t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? THEN t." + DatabaseHelper.KEY_TRANSACTION_AMOUNT + " ELSE 0 END), 0) ELSE 0 END), 0) AS amountOwed " +
                "FROM " + DatabaseHelper.TABLE_USERS + " u " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILL_PARTICIPANTS + " bp ON u." + DatabaseHelper.KEY_ID + " = bp." + DatabaseHelper.KEY_USER_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON bp." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " = b." + DatabaseHelper.KEY_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_TRANSACTIONS + " t ON b." + DatabaseHelper.KEY_ID + " = t." + DatabaseHelper.KEY_BILL_GROUP_ID + " AND t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? " +
                "WHERE b." + DatabaseHelper.KEY_BILL_GROUP_ID + " = ? " +
                "GROUP BY u." + DatabaseHelper.KEY_ID + " " +
                "HAVING amountOwed > 0";


        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(userId), String.valueOf(userId), String.valueOf(groupId)});

        // Loop through the cursor and add users to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                SettlementUser user = new SettlementUser();
                user.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
                user.setAmountOwed(cursor.getDouble(cursor.getColumnIndex("amountOwed")));
                settlementUsers.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Close database connection
        db.close();

        return settlementUsers;
    }
}
