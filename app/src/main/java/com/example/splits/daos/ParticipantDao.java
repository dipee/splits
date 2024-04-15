package com.example.splits.daos;

import com.example.splits.models.Participant;
import com.example.splits.models.User;

import java.util.List;

public interface ParticipantDao {
    Participant addParticipant(Participant participant);
    List<Participant> getParticipantsOfBill(int billId);

    List<Participant> addParticipants(List<User> participants, double totalAmount, int payerUserId, int billId);

}
