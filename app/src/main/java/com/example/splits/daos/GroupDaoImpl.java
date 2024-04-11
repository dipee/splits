package com.example.splits.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.Group;
import com.example.splits.utilities.DatabaseHelper;

public class GroupDaoImpl implements GroupDao {
    DatabaseHelper databaseHelper;

    public GroupDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public Group addGroup(Group group) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", group.getName());

        long result = db.insert("Group", null, contentValues);

        if (result == -1) {
            return null;
        } else {
            group.setId((int) result);
            return group;
        }
    }

    @Override
    public Group getGroup(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("Group", new String[]{"id", "name"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Group group = new Group();
            group.setId(cursor.getInt(0));
            group.setName(cursor.getString(1));
            cursor.close();
            return group;
        }

        return null;
    }
}
