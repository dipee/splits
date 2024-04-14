package com.example.splits.daos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.GroupDetail;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GroupDetailDaoImpl {
//    private static final String TABLE_GROUPS = "Groups";
//    private static final String TABLE_GROUP_MEMBERS = "UserGroup";
//    private static final String TABLE_BILL_PARTICIPANTS = "Participant";
//
//    private static final String KEY_ID = "id";
//    private static final String KEY_GROUP_NAME = "name";
//    private static final String KEY_USER_ID = "userId";
//    private static final String KEY_PARTICIPANT_BILL_ID = "billId";
//    private static final String KEY_PARTICIPANT_PORTION_PAID = "portionPaid";
//    private static final String KEY_PARTICIPANT_PORTION_OWED = "portionOwed";
//
//    private static  final  String KEY_GROUP_ID = "groupId";
//    DatabaseHelper databaseHelper;
//    GroupDetailDaoImpl(DatabaseHelper databaseHelper){
//        this.databaseHelper = databaseHelper;
//    }
//    @Override
//    public List<GroupDetail> getGroupDetails() {
//        List<GroupDetail> groupDetailsList = new ArrayList<>();
//
//        SQLiteDatabase db = this.databaseHelper.getReadableDatabase();
//
//        // Query to retrieve group details along with user counts, total amount owed, and total amount paid
//        String query = "SELECT " +
//                "g." + KEY_ID + ", " +
//                "g." + KEY_GROUP_NAME + ", " +
//                "COUNT(ug." + KEY_USER_ID + ") AS UserCount, " +
//                "SUM(bp." + KEY_PARTICIPANT_PORTION_OWED + ") AS TotalOwed, " +
//                "SUM(bp." + KEY_PARTICIPANT_PORTION_PAID + ") AS TotalPaid " +
//                "FROM " + TABLE_GROUPS + " g " +
//                "LEFT JOIN " + TABLE_GROUP_MEMBERS + " ug ON g." + KEY_ID + " = ug." + KEY_GROUP_ID + " " +
//                "LEFT JOIN " + TABLE_BILL_PARTICIPANTS + " bp ON g." + KEY_ID + " = bp." + KEY_PARTICIPANT_BILL_ID + " " +
//                "GROUP BY g." + KEY_ID;
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        // Loop through the cursor and add group details to the list
//        if (cursor.moveToFirst()) {
//            do {
//                GroupDetail groupDetails = new GroupDetail();
//                groupDetails.setGroupId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//                groupDetails.setGroupName(cursor.getString(cursor.getColumnIndex(KEY_GROUP_NAME)));
//                groupDetails.setUserCount(cursor.getInt(cursor.getColumnIndex("UserCount")));
//                groupDetails.setTotalOwed(cursor.getDouble(cursor.getColumnIndex("TotalOwed")));
//                groupDetails.setTotalPaid(cursor.getDouble(cursor.getColumnIndex("TotalPaid")));
//                groupDetailsList.add(groupDetails);
//            } while (cursor.moveToNext());
//        }
//
//        // Close cursor and database connection
//        cursor.close();
//        db.close();
//
//        return groupDetailsList;
//    }
}
