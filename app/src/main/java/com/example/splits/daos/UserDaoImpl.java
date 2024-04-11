package com.example.splits.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

public class UserDaoImpl implements UserDao {
    private DatabaseHelper databaseHelper;

    public UserDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public User addUser(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getPassword());

        long result = db.insert("User", null, contentValues);

        if (result == -1) {
            return null;
        } else {
            user.setId((int) result);
            return user;
        }
    }

    @Override
    public User getUser(String email) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("User", new String[]{"id", "name", "email", "password"}, "email=?", new String[]{email}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            cursor.close();
            return user;
        }

        return null;
    }

    @Override
    public User getUser(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("User", new String[]{"id", "name", "email", "password"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            cursor.close();
            return user;
        }

        return null;
    }

    @Override
    public Boolean loginUser(String email, String password) {
        User user = getUser(email);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

}
