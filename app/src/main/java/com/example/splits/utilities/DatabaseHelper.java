package com.example.splits.utilities;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "splits.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USERS = "User";
    private static final String TABLE_GROUPS = "Groups";
    private static final String TABLE_GROUP_MEMBERS = "UserGroup";
    private static final String TABLE_BILLS = "Bill";
    private static final String TABLE_BILL_PARTICIPANTS = "Participant";
    private static final String TABLE_TRANSACTIONS = "Transactions";

    private static final String TABLE_CORE = "LoginInfo";

    // Common column names
    private static final String KEY_ID = "id";

    // Users Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    // Groups Table - column names
    private static final String KEY_GROUP_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CREATOR_ID = "creatorId";
    private static final String KEY_CREATION_DATE = "creationDate";

    // User Group Table - column names
    private static final String KEY_GROUP_ID = "groupId";
    private static final String KEY_USER_ID = "userId";


    // Bill Table - column names
    private static final String KEY_BILL_TITLE = "title";
    private static final String KEY_BILL_DESCRIPTION = "description";
    private static final String KEY_BILL_PAYER_ID = "payerUserId";
    private static final String KEY_BILL_GROUP_ID = "groupId";
    private static final String KEY_BILL_AMOUNT = "amount";
    private static final String KEY_BILL_DATE = "date";

    // Bill Participants Table - column names
    private static final String KEY_PARTICIPANT_BILL_ID = "billId";
    private static final String KEY_PARTICIPANT_PORTION_PAID = "portionPaid";
    private static final String KEY_PARTICIPANT_PORTION_OWED = "portionOwed";

    // Transactions Table - column names
    private static final String KEY_TRANSACTION_PAYER_ID= "payerId";
    private static final String KEY_TRANSACTION_PAYEE_ID = "payeeId";

    private static final String KEY_TRANSACTION_REMARK = "remark";
    private static final String KEY_TRANSACTION_AMOUNT = "amount";
    private static final String KEY_TRANSACTION_DATE = "date";

    //Table Core - column names
    private static final String KEY_CORE_USER_NAME = "name";
    private static final String KEY_CORE_USER_EMAIL = "email";
    private static final String KEY_CORE_IS_LOGGED_IN = "loggedIn";


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
            + KEY_TRANSACTION_DATE + " DATETIME"
            + ")";


    // Core table create statement
    private static final String CREATE_TABLE_CORE = "CREATE TABLE " + TABLE_CORE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CORE_USER_NAME + " TEXT,"
            + KEY_CORE_USER_EMAIL + " TEXT,"
            + KEY_CORE_IS_LOGGED_IN + " INTEGER"
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
}