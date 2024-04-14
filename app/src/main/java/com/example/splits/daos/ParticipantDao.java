package com.example.splits.daos;

import com.example.splits.models.Participant;

import java.util.List;

public interface ParticipantDao {
    Participant addParticipant(Participant participant);
    List<Participant> getParticipantsOfBill(int billId);

}
