package com.example.splits.daos;

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

    @Override
    public List<User> getGroupMembers(int groupId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM User WHERE id IN (SELECT user_id FROM GroupMember WHERE group_id = ?)", new String[]{String.valueOf(groupId)}
        );
        if(cursor != null && cursor.moveToFirst()) {
            List<User> users = new ArrayList<>();
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                users.add(user);
            } while (cursor.moveToNext());
            cursor.close();
            return users;
        }
        return null;
    }
}
