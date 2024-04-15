package com.example.splits.utilities;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.GroupDetail;
import com.example.splits.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DbQueries {
    DatabaseHelper databaseHelper;

    public DbQueries(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    @SuppressLint("Range")
    public List<GroupDetail> getGroupDetails(int userId) {
        List<GroupDetail> groupDetails = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

//        // Query to retrieve total amount owed, total amount paid, and total transactions made by a user in all groups
//        String query1 = "SELECT " +
//                "g.id AS groupId, " +
//                "g.name AS groupName, " +
//                "COUNT(DISTINCT bp." + DatabaseHelper.KEY_USER_ID + ") AS userCount, " +
//                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + " ELSE 0 END), 0) AS totalOwed, " +
//                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + " ELSE 0 END), 0) + COALESCE(SUM(CASE WHEN t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? THEN t." + DatabaseHelper.KEY_TRANSACTION_AMOUNT + " ELSE 0 END), 0) AS totalPaid " +
//                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON g.id = b." + DatabaseHelper.KEY_BILL_GROUP_ID + " " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_BILL_PARTICIPANTS + " bp ON b.id = bp." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_TRANSACTIONS + " t ON g.id = t." + DatabaseHelper.KEY_GROUP_ID + " " +
//                "GROUP BY g.id";

        // Query to retrieve total amount owed, total amount paid, and total transactions made by a user in all groups
//        String query = "SELECT " +
//                "g.id AS groupId, " +
//                "g.name AS groupName, " +
//                "COUNT(DISTINCT bp." + DatabaseHelper.KEY_USER_ID + ") AS userCount, " +
//                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + " ELSE 0 END), 0) AS totalOwed, " +
//                "COALESCE(SUM(CASE WHEN bp." + DatabaseHelper.KEY_USER_ID + " = ? THEN bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + " ELSE 0 END), 0) AS totalPaid, " +
//                "COALESCE(SUM(CASE WHEN t." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? AND t." + DatabaseHelper.KEY_GROUP_ID + " = g.id THEN t." + DatabaseHelper.KEY_TRANSACTION_AMOUNT + " ELSE 0 END), 0) AS userTransactionAmount " +
//                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON g.id = b." + DatabaseHelper.KEY_BILL_GROUP_ID + " " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_BILL_PARTICIPANTS + " bp ON b.id = bp." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " " +
//                "LEFT JOIN " + DatabaseHelper.TABLE_TRANSACTIONS + " t ON g.id = t." + DatabaseHelper.KEY_GROUP_ID + " " +
//                "GROUP BY g.id";

        // Subquery to calculate userTransactionAmount for each group and user
        String subquery = "(SELECT " +
                DatabaseHelper.KEY_GROUP_ID + ", " +
                DatabaseHelper.KEY_TRANSACTION_PAYER_ID + ", " +
                "SUM(" + DatabaseHelper.KEY_TRANSACTION_AMOUNT + ") AS userTransactionAmount " +
                "FROM " + DatabaseHelper.TABLE_TRANSACTIONS +
                " GROUP BY " + DatabaseHelper.KEY_GROUP_ID + ", " + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + ") AS t_sub";


        // Subquery to calculate totalOwed for each group and user
        String subqueryOwed = "(SELECT " +
                DatabaseHelper.KEY_PARTICIPANT_BILL_ID + ", " +
                DatabaseHelper.KEY_USER_ID + ", " +
                "SUM(" + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + ") AS totalOwed " +
                "FROM " + DatabaseHelper.TABLE_BILL_PARTICIPANTS +
                " GROUP BY " + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + ", " + DatabaseHelper.KEY_USER_ID + ") AS bp_sub";

// Subquery to calculate totalPaid for each group and user
        String subqueryPaid = "(SELECT " +
                DatabaseHelper.KEY_PARTICIPANT_BILL_ID + ", " +
                DatabaseHelper.KEY_USER_ID + ", " +
                "SUM(" + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + ") AS totalPaid " +
                "FROM " + DatabaseHelper.TABLE_BILL_PARTICIPANTS +
                " GROUP BY " + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + ", " + DatabaseHelper.KEY_USER_ID + ") AS bp_sub_paid";

        // Subquery to calculate userCount for each group
        String subqueryUserCount = "(SELECT " +
                DatabaseHelper.KEY_GROUP_ID + ", " +
                "COUNT(DISTINCT " + DatabaseHelper.KEY_USER_ID + ") AS userCount " +
                "FROM " + DatabaseHelper.TABLE_GROUP_MEMBERS +
                " GROUP BY " + DatabaseHelper.KEY_GROUP_ID + ") AS ug_sub";

// Main query
        String query1 = "SELECT " +
                "g.id AS groupId, " +
                "g.name AS groupName, " +
                "COUNT(DISTINCT bp_sub." + DatabaseHelper.KEY_USER_ID + ") AS userCount, " +
                "COALESCE(bp_sub.totalOwed, 0) AS totalOwed, " +
                "COALESCE(bp_sub_paid.totalPaid, 0) AS totalPaid, " +
                "COALESCE(t_sub.userTransactionAmount, 0) AS userTransactionAmount " +
                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON g.id = b." + DatabaseHelper.KEY_BILL_GROUP_ID + " " +
                "LEFT JOIN " + subqueryOwed + " ON b.id = bp_sub." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " AND bp_sub." + DatabaseHelper.KEY_USER_ID + " = ? " +
                "LEFT JOIN " + subqueryPaid + " ON b.id = bp_sub_paid." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " AND bp_sub_paid." + DatabaseHelper.KEY_USER_ID + " = ? " +
                "LEFT JOIN " + subquery + " ON g.id = t_sub." + DatabaseHelper.KEY_GROUP_ID + " AND t_sub." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? " +
                "GROUP BY g.id";

        // Main query
        String query = "SELECT " +
                "g.id AS groupId, " +
                "g.name AS groupName, " +
                "COALESCE(ug_sub.userCount, 0) AS userCount, " +
                "COALESCE(bp_sub.totalOwed, 0) AS totalOwed, " +
                "COALESCE(bp_sub_paid.totalPaid, 0) AS totalPaid, " +
                "COALESCE(t_sub.userTransactionAmount, 0) AS userTransactionAmount " +
                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
                "LEFT JOIN " + subqueryUserCount + " ON g.id = ug_sub." + DatabaseHelper.KEY_GROUP_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILLS + " b ON g.id = b." + DatabaseHelper.KEY_BILL_GROUP_ID + " " +
                "LEFT JOIN " + subqueryOwed + " ON b.id = bp_sub." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " AND bp_sub." + DatabaseHelper.KEY_USER_ID + " = ? " +
                "LEFT JOIN " + subqueryPaid + " ON b.id = bp_sub_paid." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " AND bp_sub_paid." + DatabaseHelper.KEY_USER_ID + " = ? " +
                "LEFT JOIN " + subquery + " ON g.id = t_sub." + DatabaseHelper.KEY_GROUP_ID + " AND t_sub." + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? " +
                "WHERE EXISTS (SELECT 1 FROM " + DatabaseHelper.TABLE_GROUP_MEMBERS + " WHERE " + DatabaseHelper.KEY_GROUP_ID + " = g.id AND " + DatabaseHelper.KEY_USER_ID + " = ?) " +
                "GROUP BY g.id";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(userId), String.valueOf(userId), String.valueOf(userId)});

        // Loop through the cursor and add user balances to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupDetail groupDetail = new GroupDetail();
                groupDetail.setGroupId(cursor.getInt(cursor.getColumnIndex("groupId")));
                groupDetail.setGroupName(cursor.getString(cursor.getColumnIndex("groupName")));
                groupDetail.setUserCount(cursor.getInt(cursor.getColumnIndex("userCount")));
                groupDetail.setTotalOwed(cursor.getDouble(cursor.getColumnIndex("totalOwed")));
                groupDetail.setTotalPaid(cursor.getDouble(cursor.getColumnIndex("totalPaid")));
                groupDetail.setUserTransactionAmount(cursor.getDouble(cursor.getColumnIndex("userTransactionAmount")));
                groupDetails.add(groupDetail);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Close database connection
        db.close();

        return groupDetails;
    }



    @SuppressLint("Range")
    public List<Transaction> getallTransactionsofuser(int userId){
        List<Transaction> transactions = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_TRANSACTIONS + " WHERE " + DatabaseHelper.KEY_TRANSACTION_PAYER_ID + " = ? OR " + DatabaseHelper.KEY_TRANSACTION_PAYEE_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId), String.valueOf(userId)});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
                transaction.setPayerId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTION_PAYER_ID)));
                transaction.setPayeeId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTION_PAYEE_ID)));
                transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTION_AMOUNT)));
                transaction.setRemark(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTION_REMARK)));
                transaction.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTION_DATE)));
                transaction.setGroupId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_GROUP_ID)));
                transactions.add(transaction);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return transactions;
    }


}
