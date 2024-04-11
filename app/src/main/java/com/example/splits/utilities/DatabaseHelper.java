package com.example.splits.utilities;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "splits.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)";
        String CREATE_GROUP_TABLE = "CREATE TABLE 'Group' (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        String CREATE_USER_GROUP_TABLE = "CREATE TABLE UserGroup (user_id INTEGER, group_id INTEGER, FOREIGN KEY(user_id) REFERENCES User(id), FOREIGN KEY(group_id) REFERENCES 'Group'(id))";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_GROUP_TABLE);
        db.execSQL(CREATE_USER_GROUP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS 'Group'");
        db.execSQL("DROP TABLE IF EXISTS serGroup");

        onCreate(db);
    }
}