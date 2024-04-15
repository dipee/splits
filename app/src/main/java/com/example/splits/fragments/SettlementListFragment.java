package com.example.splits.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splits.R;
import com.example.splits.activities.HomeActivity;
import com.example.splits.adapters.SettlementUserAdapter;
import com.example.splits.databinding.FragmentSettlementListBinding;
import com.example.splits.models.Group;
import com.example.splits.models.SettlementUser;
import com.example.splits.services.SettlementService;
import com.example.splits.utilities.DatabaseHelper;

public class SettlementListFragment extends Fragment {

    FragmentSettlementListBinding binding;

    SettlementUserAdapter adapter;

    SettlementService settlementService;

    DatabaseHelper databaseHelper;

    int groupId;

    int userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettlementListBinding.inflate(inflater, container, false);

        // Set database and service
        databaseHelper = new DatabaseHelper(getContext());
        settlementService = new SettlementService(databaseHelper);

        // Setup RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // get user id from activity
         userId = ((HomeActivity) getActivity()).getUserId();

        //get group from previous fragment
        // Get the value
         groupId =  getArguments().getInt("groupId", 0);


        adapter = new SettlementUserAdapter(settlementService.getUserSettlements(userId, groupId), new SettlementUserAdapter.OnGroupClickListener() {
            @Override
            public void onGroupClick(SettlementUser settlementUser) {
                // Handle click event
                AddSettlementFragment fragment = new AddSettlementFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("groupId", groupId);
                bundle.putInt("userId", userId);
                bundle.putInt("settlementUserId", settlementUser.getUserId());
                bundle.putDouble("settlementAmount", settlementUser.getAmountOwed());
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}