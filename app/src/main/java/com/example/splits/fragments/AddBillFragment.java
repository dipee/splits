package com.example.splits.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.splits.activities.HomeActivity;
import com.example.splits.databinding.FragmentAddBillBinding;
import com.example.splits.models.Bill;
import com.example.splits.models.Group;
import com.example.splits.models.User;
import com.example.splits.services.BillService;
import com.example.splits.services.ParticipantService;
import com.example.splits.services.UserGroupService;
import com.example.splits.utilities.DatabaseHelper;

import java.time.LocalDate;
import java.util.List;


public class AddBillFragment extends Fragment implements View.OnClickListener {

    FragmentAddBillBinding binding;
    DatabaseHelper databaseHelper;

    BillService billService;

    UserGroupService userGroupService;

    ParticipantService participantService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBillBinding.inflate(inflater, container, false);
        //initialize database helper and service
        databaseHelper = new DatabaseHelper(getContext());
        billService = new BillService(databaseHelper);
        userGroupService = new UserGroupService(databaseHelper);
        participantService = new ParticipantService(databaseHelper);
        binding.buttonSubmit.setOnClickListener(this);



        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v == binding.buttonSubmit) {
            // Add bill to database
            // Get data from the form
            String title = binding.editTitleText.getText().toString();
            String description = binding.editDescriptionText.getText().toString();
            String textAmount = binding.editAmountText.getText().toString();


            if(validateBillFields(title, description, textAmount)){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount = Double.parseDouble(textAmount);

            //get group id and user id from previous  fragment
            Group group = (Group) getArguments().getSerializable("group");

            //get user id from activity intent
            int userId = ((HomeActivity) getActivity()).getUserId();
            int groupId = group.getId();

            // Create a bill object
            Bill bill = new Bill();
            bill.setTitle(title);
            bill.setDescription(description);
            bill.setAmount((int) amount);
            bill.setGroupId(groupId);
            bill.setPayerUserId(userId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                bill.setDate(LocalDate.now().toString());
            }

            // Add bill to database
            billService.addBill(bill);

            addParticipants(bill, userId, groupId);

            // Navigate to group detail fragment
            GroupFragment fragment = new GroupFragment();


            getActivity().getSupportFragmentManager().beginTransaction().replace(com.example.splits.R.id.fragment_container, fragment).addToBackStack(null).commit();
        }
    }

    Boolean validateBillFields(String title, String description, String amount){
        return title.isEmpty() || description.isEmpty() || amount.isEmpty();
    }

    void addParticipants(Bill bill, int payerUserId, int groupId){
// Add participants to database
        // Get the list of participants
        // Get the list of participants
        List<User> participants = userGroupService.getGroupMembers(groupId);
        // Calculate the portion owed by each participant
        double totalAmount =(double) bill.getAmount();
        participantService.addParticipants(participants, totalAmount, payerUserId, bill.getId());


    }
}