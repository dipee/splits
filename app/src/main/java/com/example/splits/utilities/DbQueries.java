package com.example.splits.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.GroupDetail;

import java.util.ArrayList;
import java.util.List;

public class DbQueries {
    DatabaseHelper databaseHelper;

    public DbQueries(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @SuppressLint("Range")
    public  List<GroupDetail> getGroupDetails() {
        List<GroupDetail> groupDetailsList = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Query to retrieve group details along with user counts, total amount owed, and total amount paid
        String query = "SELECT " +
                "g.id AS groupId, " +
                "g.name AS groupName, " +
                "COUNT(ug." + DatabaseHelper.KEY_USER_ID + ") AS userCount, " +
                "COALESCE(SUM(bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_OWED + "), 0) AS totalOwed, " +
                "COALESCE(SUM(bp." + DatabaseHelper.KEY_PARTICIPANT_PORTION_PAID + "), 0) AS totalPaid " +
                "FROM " + DatabaseHelper.TABLE_GROUPS + " g " +
                "LEFT JOIN " + DatabaseHelper.TABLE_GROUP_MEMBERS + " ug ON g.id = ug." + DatabaseHelper.KEY_GROUP_ID + " " +
                "LEFT JOIN " + DatabaseHelper.TABLE_BILL_PARTICIPANTS + " bp ON g.id = bp." + DatabaseHelper.KEY_PARTICIPANT_BILL_ID + " " +
                "GROUP BY g.id";

        Cursor cursor = db.rawQuery(query, null);

        // Loop through the cursor and add group details to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                GroupDetail groupDetail = new GroupDetail();
                groupDetail.setGroupId(cursor.getInt(cursor.getColumnIndex("groupId")));
                groupDetail.setGroupName(cursor.getString(cursor.getColumnIndex("groupName")));
                groupDetail.setUserCount(cursor.getInt(cursor.getColumnIndex("userCount")));
                groupDetail.setTotalOwed(cursor.getDouble(cursor.getColumnIndex("totalOwed")));
                groupDetail.setTotalPaid(cursor.getDouble(cursor.getColumnIndex("totalPaid")));
                groupDetailsList.add(groupDetail);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Close database connection
        db.close();

        return groupDetailsList;
    }
}
