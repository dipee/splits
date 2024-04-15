package com.example.splits.utilities;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.splits.models.GroupDetail;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

     static final String DATABASE_NAME = "splits.db";
     static final int DATABASE_VERSION = 1;

    // Table Names
     static final String TABLE_USERS = "User";
     static final String TABLE_GROUPS = "Groups";
     static final String TABLE_GROUP_MEMBERS = "UserGroup";
     static final String TABLE_BILLS = "Bill";
     static final String TABLE_BILL_PARTICIPANTS = "Participant";
     static final String TABLE_TRANSACTIONS = "Transactions";

     static final String TABLE_CORE = "LoginInfo";

    // Common column names
     static final String KEY_ID = "id";

    // Users Table - column names
     static final String KEY_NAME = "name";
     static final String KEY_EMAIL = "email";
     static final String KEY_PASSWORD = "password";


    // Groups Table - column names
     static final String KEY_GROUP_NAME = "name";
     static final String KEY_DESCRIPTION = "description";
     static final String KEY_CREATOR_ID = "creatorId";
     static final String KEY_CREATION_DATE = "creationDate";

    // User Group Table - column names
     static final String KEY_GROUP_ID = "groupId";
     static final String KEY_USER_ID = "userId";


    // Bill Table - column names
     static final String KEY_BILL_TITLE = "title";
     static final String KEY_BILL_DESCRIPTION = "description";
     static final String KEY_BILL_PAYER_ID = "payerUserId";
     static final String KEY_BILL_GROUP_ID = "groupId";
     static final String KEY_BILL_AMOUNT = "amount";
     static final String KEY_BILL_DATE = "date";

    // Bill Participants Table - column names
     static final String KEY_PARTICIPANT_BILL_ID = "billId";
     static final String KEY_PARTICIPANT_PORTION_PAID = "portionPaid";
     static final String KEY_PARTICIPANT_PORTION_OWED = "portionOwed";

    // Transactions Table - column names
     static final String KEY_TRANSACTION_PAYER_ID= "payerId";
     static final String KEY_TRANSACTION_PAYEE_ID = "payeeId";

     static final String KEY_TRANSACTION_REMARK = "remark";
     static final String KEY_TRANSACTION_AMOUNT = "amount";
     static final String KEY_TRANSACTION_DATE = "date";

    //Table Core - column names
     static final String KEY_CORE_USER_NAME = "name";
     static final String KEY_CORE_USER_EMAIL = "email";
     static final String KEY_CORE_IS_LOGGED_IN = "loggedIn";
     static final  String KEY_CORE_USER_ID = "userId";


    // User table create statement
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_EMAIL + " TEXT UNIQUE,"
            + KEY_PASSWORD + " TEXT"
            + ")";

    // Group table create statement
    private static final String CREATE_TABLE_GROUPS = "CREATE TABLE " + TABLE_GROUPS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_GROUP_NAME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_CREATOR_ID + " INTEGER,"
            + KEY_CREATION_DATE + " DATETIME"
            + ")";

    // User Group table create statement
    private static final String CREATE_TABLE_GROUP_MEMBERS = "CREATE TABLE " + TABLE_GROUP_MEMBERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_GROUP_ID + " INTEGER,"
            + KEY_USER_ID + " INTEGER"
            + ")";

    // Bill table create statement
    private static final String CREATE_TABLE_BILLS = "CREATE TABLE " + TABLE_BILLS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_BILL_TITLE + " TEXT,"
            + KEY_BILL_DESCRIPTION + " TEXT,"
            + KEY_BILL_PAYER_ID + " INTEGER,"
            + KEY_BILL_GROUP_ID + " INTEGER,"
            + KEY_BILL_AMOUNT + " REAL,"
            + KEY_BILL_DATE + " DATETIME"
            + ")";

    // Bill Participants table create statement
    private static final String CREATE_TABLE_BILL_PARTICIPANTS = "CREATE TABLE " + TABLE_BILL_PARTICIPANTS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PARTICIPANT_BILL_ID + " INTEGER,"
            + KEY_USER_ID + " INTEGER,"
            + KEY_PARTICIPANT_PORTION_PAID + " REAL,"
            + KEY_PARTICIPANT_PORTION_OWED + " REAL"
            + ")";

    // Transaction table create statement
    private static final String CREATE_TABLE_TRANSACTIONS = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TRANSACTION_PAYER_ID + " INTEGER,"
            + KEY_TRANSACTION_PAYEE_ID + " INTEGER,"
            + KEY_TRANSACTION_REMARK + " TEXT,"
            + KEY_TRANSACTION_AMOUNT + " REAL,"
            + KEY_TRANSACTION_DATE + " DATETIME,"
            + KEY_GROUP_ID + " INTEGER"
            + ")";


    // Core table create statement
    private static final String CREATE_TABLE_CORE = "CREATE TABLE " + TABLE_CORE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CORE_USER_NAME + " TEXT,"
            + KEY_CORE_USER_EMAIL + " TEXT,"
            + KEY_CORE_IS_LOGGED_IN + " INTEGER,"
            + KEY_CORE_USER_ID + " INTEGER"
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_GROUPS);
        db.execSQL(CREATE_TABLE_GROUP_MEMBERS);
        db.execSQL(CREATE_TABLE_BILLS);
        db.execSQL(CREATE_TABLE_BILL_PARTICIPANTS);
        db.execSQL(CREATE_TABLE_TRANSACTIONS);
        db.execSQL(CREATE_TABLE_CORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_MEMBERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL_PARTICIPANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CORE);

        // create new tables
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<GroupDetail> getGroupDetail(int userId) {
        List<GroupDetail> groupDetails = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query1 = "SELECT " +
                "g.*, " +
                "(SELECT COALESCE(SUM(bp.portionPaid), 0) " +
                "FROM " + TABLE_BILL_PARTICIPANTS + " bp " +
                "JOIN " + TABLE_BILLS + " b ON bp." + KEY_PARTICIPANT_BILL_ID + " = b." + KEY_ID + " " +
                "WHERE b." + KEY_BILL_GROUP_ID + " = g." + KEY_ID + " " +
                "AND bp." + KEY_USER_ID + " = ?) AS total_amount_paid, " +
                "(SELECT COALESCE(SUM(t." + KEY_TRANSACTION_AMOUNT + "), 0) " +
                "FROM " + TABLE_TRANSACTIONS + " t " +
                "WHERE t." + KEY_GROUP_ID + " = g." + KEY_ID + " " +
                "AND t." + KEY_TRANSACTION_PAYER_ID + " = ?) AS total_transaction_amount_paid, " +
                "(SELECT COALESCE(SUM(bp." + KEY_PARTICIPANT_PORTION_OWED + "), 0) " +
                "FROM " + TABLE_BILL_PARTICIPANTS + " bp " +
                "JOIN " + TABLE_BILLS + " b ON bp." + KEY_PARTICIPANT_BILL_ID + " = b." + KEY_ID + " " +
                "WHERE b." + KEY_BILL_GROUP_ID + " = g." + KEY_ID + " " +
                "AND bp." + KEY_USER_ID + " = ?) AS total_owed_amount, " +
                "(SELECT COUNT(DISTINCT ug." + KEY_USER_ID + ") " +
                "FROM " + TABLE_GROUP_MEMBERS + " ug " +
                "WHERE ug." + KEY_GROUP_ID + " = g." + KEY_ID + ") AS total_user_count " +
                "FROM " + TABLE_GROUPS + " g " +
                "JOIN " + TABLE_GROUP_MEMBERS + " ug ON g." + KEY_ID + " = ug." + KEY_GROUP_ID + " " +
                "WHERE ug." + KEY_USER_ID + " = ?";


        String query = "SELECT " +
                "g." + KEY_ID + " AS groupId, " +
                "g." + KEY_GROUP_NAME + " AS groupName, " +
                "(SELECT COUNT(DISTINCT ug." + KEY_USER_ID + ") " +
                "FROM " + TABLE_GROUP_MEMBERS + " ug " +
                "WHERE ug." + KEY_GROUP_ID + " = g." + KEY_ID + ") AS userCount, " +
                "(SELECT COALESCE(SUM(bp." + KEY_PARTICIPANT_PORTION_OWED + "), 0) " +
                "FROM " + TABLE_BILL_PARTICIPANTS + " bp " +
                "JOIN " + TABLE_BILLS + " b ON bp." + KEY_PARTICIPANT_BILL_ID + " = b." + KEY_ID + " " +
                "WHERE b." + KEY_BILL_GROUP_ID + " = g." + KEY_ID + " " +
                "AND bp." + KEY_USER_ID + " = ?) AS totalOwed, " +
                "(SELECT COALESCE(SUM(bp." + KEY_PARTICIPANT_PORTION_PAID + "), 0) " +
                "FROM " + TABLE_BILL_PARTICIPANTS + " bp " +
                "JOIN " + TABLE_BILLS + " b ON bp." + KEY_PARTICIPANT_BILL_ID + " = b." + KEY_ID + " " +
                "WHERE b." + KEY_BILL_GROUP_ID + " = g." + KEY_ID + " " +
                "AND bp." + KEY_USER_ID + " = ?) AS totalPaid, " +
                "(SELECT COALESCE(SUM(t." + KEY_TRANSACTION_AMOUNT + "), 0) " +
                "FROM " + TABLE_TRANSACTIONS + " t " +
                "WHERE t." + KEY_GROUP_ID + " = g." + KEY_ID + " " +
                "AND t." + KEY_TRANSACTION_PAYER_ID + " = ?) AS userTransactionAmount " +
                "FROM " + TABLE_GROUPS + " g " +
                "JOIN " + TABLE_GROUP_MEMBERS + " ug ON g." + KEY_ID + " = ug." + KEY_GROUP_ID + " " +
                "WHERE ug." + KEY_USER_ID + " = ?";

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

        // Close the database connection
        db.close();

        // Return the list of groups
        return groupDetails;
    }
}