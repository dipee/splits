package com.example.splits.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splits.R;
import com.example.splits.adapters.GroupListAdapter;
import com.example.splits.databinding.FragmentGroupBinding;
import com.example.splits.models.Group;
import com.example.splits.services.GroupService;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;


public class GroupFragment extends Fragment implements View.OnClickListener {

    private FragmentGroupBinding binding;
    private GroupListAdapter adapter;

    DatabaseHelper databaseHelper;
    GroupService groupService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGroupBinding.inflate(inflater, container, false);

        // Set Floating Action Button click listener
        binding.floatingActionButton.setOnClickListener(this);

        // Set database and service
        databaseHelper = new DatabaseHelper(getContext());
        groupService = new GroupService(databaseHelper);



        // Setup RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GroupListAdapter(groupService.getAllGroups(), new GroupListAdapter.OnGroupClickListener() {
            @Override
            public void onGroupClick(Group group) {
                Log.d("GroupFragment", "Group clicked: " + group.getName());
                // Navigate to GroupDetailFragment
                // You can pass the clicked group data to the fragment using bundle
                GroupDetailFragment fragment = new GroupDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("group", group);
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });
        binding.recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == binding.floatingActionButton.getId()){
//            got to add group fragment
            AddGroupFragment addGroupFragment = new AddGroupFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addGroupFragment).addToBackStack(null).commit();
        }

    }
}