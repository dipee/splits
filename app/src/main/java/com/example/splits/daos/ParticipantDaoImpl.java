package com.example.splits.daos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.splits.models.Participant;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao{

    DatabaseHelper databaseHelper;

    public ParticipantDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Participant addParticipant(Participant participant) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("bilId", participant.getBillId());
        values.put("userId", participant.getUserId());
        values.put("portionOwed", participant.getPortionOwed());
        values.put("portionPaid", participant.getPortionPaid());
        long id = db.insert("Participant", null, values);
        participant.setId((int) id);
        db.close();
        return participant;
    }


    @Override
    public List<Participant> addParticipants(List<User> users, double totalAmount, int payerUserId, int billId){
        // portion owed by each user
        double portionOwed = totalAmount / users.size();

        List<Participant> participants = new ArrayList<>();

        //calculate portion owed and portion paid for each participant
        for (User user : users) {
            Participant participant = new Participant();
            participant.setBillId(billId);
            participant.setUserId(user.getId());
            if (user.getId() == payerUserId) {
                participant.setPortionOwed(portionOwed);
                participant.setPortionPaid(totalAmount);
            } else {
                participant.setPortionOwed(portionOwed);
                participant.setPortionPaid(0);
            }
            participants.add(participant);
        }
        //use transaction to insert multiple participants
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        for (Participant participant : participants) {
            ContentValues values = new ContentValues();
            values.put("billId", billId);
            values.put("userId", participant.getUserId());
            values.put("portionOwed", participant.getPortionOwed());
            values.put("portionPaid", participant.getPortionPaid());
            long id = db.insert("Participant", null, values);
            participant.setId((int) id);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return participants;
    }

    @SuppressLint("Range")
    @Override
    public List<Participant> getParticipantsOfBill(int billId) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Participant WHERE billId = ?", new String[]{String.valueOf(billId)});
        List<Participant> participants = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Participant participant = new Participant();
                participant.setId(cursor.getInt(cursor.getColumnIndex("id")));
                participant.setBillId(cursor.getInt(cursor.getColumnIndex("billId")));
                participant.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                participant.setPortionOwed(cursor.getDouble(cursor.getColumnIndex("portionOwed")));
                participant.setPortionPaid(cursor.getDouble(cursor.getColumnIndex("portionPaid")));
                participants.add(participant);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return participants;


    }
}
