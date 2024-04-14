package com.example.splits.daos;

import com.example.splits.models.Participant;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao{

    DatabaseHelper databaseHelper;

    ParticipantDaoImpl(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Participant addParticipant(Participant participant) {
        return null;
    }

    @Override
    public List<Participant> getParticipantsOfBill(int billId) {
        return null;
    }
}
