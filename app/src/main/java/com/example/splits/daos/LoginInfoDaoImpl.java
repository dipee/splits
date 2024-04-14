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
        contentValues.put("userId", user.getId());
        db.insert("LoginInfo", null, contentValues);
        return user;


    }

    @Override
    public void addLogout(String email) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        delete login info
        db.delete("LoginInfo", "email=?", new String[]{email});
        

    }

    @Override
    public User checkIfLoggedIn() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("LoginInfo", new String[]{"email", "name", "loggedIn", "userId"}, "loggedIn=?", new String[]{String.valueOf(1)}, null, null, null);
        User user = new User();
//        get first row to query
        if (cursor != null && cursor.moveToFirst()) {
            user.setEmail(cursor.getString(0));
            user.setName(cursor.getString(1));

            user.setId(cursor.getInt(3));
            cursor.close();
            return user;
        }
        return null;

    }
}
