package com.example.splits.services;

import com.example.splits.daos.ParticipantDao;
import com.example.splits.daos.ParticipantDaoImpl;
import com.example.splits.models.Participant;
import com.example.splits.models.User;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

public class ParticipantService {

    DatabaseHelper databaseHelper;
    ParticipantDao participantDao;

    public ParticipantService(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        this.participantDao = new ParticipantDaoImpl(databaseHelper);
    }

    public Participant addParticipant(Participant participant) {
        return participantDao.addParticipant(participant);
    }

    public List<Participant> getParticipantsOfBill(int billId) {
        return participantDao.getParticipantsOfBill(billId);
    }

    public List<Participant> addParticipants(List<User> participants, double totalAmount, int payerUserId, int billId) {
        return participantDao.addParticipants(participants, totalAmount, payerUserId, billId);
    }
}
