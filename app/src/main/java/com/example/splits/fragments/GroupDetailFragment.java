package com.example.splits.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.splits.R;
import com.example.splits.adapters.BillAdapter;
import com.example.splits.databinding.FragmentGroupDetailBinding;
import com.example.splits.models.Bill;
import com.example.splits.models.Group;
import com.example.splits.models.GroupDetail;
import com.example.splits.services.BillService;
import com.example.splits.services.GroupService;
import com.example.splits.services.ParticipantService;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;


public class GroupDetailFragment extends Fragment implements View.OnClickListener {

    FragmentGroupDetailBinding binding;
    DatabaseHelper databaseHelper;
    BillService billService;

    ParticipantService participantService;

    BillAdapter billAdapter;

    GroupService groupService;

    Group group;
    GroupDetail groupDetail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroupDetailBinding.inflate(inflater, container, false);
        binding.floatingActionButton.setOnClickListener(this);
        binding.buttonAddSettlement.setOnClickListener(this);

        //initialize database helper and service
        databaseHelper = new DatabaseHelper(getContext());
        billService = new BillService(databaseHelper);
        groupService = new GroupService(databaseHelper);
        participantService = new ParticipantService(databaseHelper);
        //get group data from previous bundle serializable fragment
        groupDetail = (GroupDetail) getArguments().getSerializable("group");
        group = groupService.getGroup(groupDetail.getGroupId());

        //set group info
        binding.textViewTitle.setText(group.getName());
        binding.textViewDescription.setText(group.getDescription());
        binding.textViewUserCount.setText("Users: " + groupDetail.getUserCount());
        binding.textViewOwed.setText("Owed: " + String.format("%.2f",groupDetail.getTotalOwed()));
        binding.textViewPaid.setText("Spent: " + String.format("%.2f",groupDetail.getTotalPaid() + groupDetail.getUserTransactionAmount()));


//        setting adapter

        List<Bill> bills = billService.getBillsOfGroup(group.getId());

        //print participants of each bill
        for (Bill bill : bills) {
            System.out.println("Bill: " + bill.getTitle());
            participantService.getParticipantsOfBill(bill.getId()).forEach(participant -> {
                System.out.println("Participant: " + participant.getUserId() + " " + participant.getPortionOwed() + " " + participant.getPortionPaid());
            });

        }

        billAdapter = new BillAdapter(bills);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(billAdapter);


        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.floatingActionButton.getId()){
            // Navigate to AddGroupFragment
            //add group to fragment
            AddBillFragment fragment = new AddBillFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("group", group);
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(v.getId() == binding.buttonAddSettlement.getId()){
            double totalPaid = groupDetail.getTotalPaid() + groupDetail.getUserTransactionAmount();
            double totalOwed = groupDetail.getTotalOwed();
            if(totalPaid >= totalOwed) {
                //show dialog that no settlement is required
                Toast.makeText(getContext(), "No settlement required", Toast.LENGTH_SHORT).show();
            }
            else {
                // Navigate to SettlementListFragment
                AddSettlementFragment fragment = new AddSettlementFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("groupId", groupDetail.getGroupId());
                bundle.putDouble("totalOwed", groupDetail.getTotalOwed());
                bundle.putDouble("totalPaid", groupDetail.getTotalPaid());
                bundle.putString("groupName", group.getName());
                bundle.putSerializable("group", group);

                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        }

    }
}