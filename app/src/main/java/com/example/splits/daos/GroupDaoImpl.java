package com.example.splits.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.Group;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    DatabaseHelper databaseHelper;

    public GroupDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    @Override
    public Group addGroup(Group group) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", group.getName());
        values.put("description", group.getDescription());

        long id = db.insert("Groups", null, values);
        group.setId((int) id);

        return group;
    }

    @Override
    public Group getGroup(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query("Groups", new String[]{"id", "name", "description"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Group group = new Group();
            group.setId(cursor.getInt(0));
            group.setName(cursor.getString(1));
            group.setDescription(cursor.getString(2));
            cursor.close();
            return group;
        }

        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query("Groups", new String[]{"id", "name", "description"}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Group group = new Group();
                group.setId(cursor.getInt(0));
                group.setName(cursor.getString(1));
                groups.add(group);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return groups;
    }


}
