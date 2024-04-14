package com.example.splits.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.splits.activities.HomeActivity;
import com.example.splits.databinding.FragmentAddBillBinding;
import com.example.splits.models.Group;
import com.example.splits.services.BillService;
import com.example.splits.utilities.DatabaseHelper;

import com.example.splits.models.Bill;

import java.time.LocalDate;


public class AddBillFragment extends Fragment implements View.OnClickListener {

    FragmentAddBillBinding binding;
    DatabaseHelper databaseHelper;

    BillService billService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBillBinding.inflate(inflater, container, false);
        //initialize database helper and service
        databaseHelper = new DatabaseHelper(getContext());
        billService = new BillService(databaseHelper);
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
            double amount = Double.parseDouble(binding.editAmountText.getText().toString());

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
        }
    }
}