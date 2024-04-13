package com.example.splits.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

public class LoginInfoDaoImpl implements LoginInfoDao {

    DatabaseHelper databaseHelper;

    public LoginInfoDaoImpl(DatabaseHelper db){
        this.databaseHelper = db;
    }
    @Override
    public User addLogin(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("name", user.getName());
        contentValues.put("loggedIn", 1);
        db.insert("Core", null, contentValues);
        return user;


    }

    @Override
    public void addLogout() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", "");
        contentValues.put("name", "");
        contentValues.put("loggedIn", 0);
        db.insert("Core", null, contentValues);

    }

    @Override
    public User checkIfLoggedIn() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("Core", new String[]{"email", "name", "loggedIn"}, "loggedIn=?", new String[]{String.valueOf(1)}, null, null, null);
        User user = new User();
//        get first row to query
        if (cursor != null && cursor.moveToFirst()) {
            user.setEmail(cursor.getString(0));
            user.setName(cursor.getString(1));
            cursor.close();
        }
        return user;
    }
}
