package com.example.splits.daos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class UserGroupDaoImpl implements UserGroupDao{
    DatabaseHelper databaseHelper;

    public UserGroupDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public void addUserToGroup(int userId, int groupId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("groupId", groupId);
        long result = db.insert("UserGroup", null, contentValues);



    }

    @Override
    public void removeUserFromGroup(int userId, int groupId) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("UserGroup", "userId = ? AND groupId = ?", new String[]{String.valueOf(userId), String.valueOf(groupId)});

    }

    @SuppressLint("Range")
    @Override
    public List<User> getGroupMembers(int groupId) {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String query = "SELECT User.id, User.name, User.email FROM User INNER JOIN UserGroup ON User.id = UserGroup.userId WHERE UserGroup.groupId = ?";


        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(groupId)});

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return users;
    }
}
