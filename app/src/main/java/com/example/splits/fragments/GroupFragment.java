package com.example.splits.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.splits.R;
import com.example.splits.activities.HomeActivity;
import com.example.splits.adapters.GroupListAdapter;
import com.example.splits.databinding.FragmentGroupBinding;
import com.example.splits.models.GroupDetail;
import com.example.splits.services.GroupService;
import com.example.splits.utilities.DatabaseHelper;


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

        // get user id from activity
        int userId = ((HomeActivity) getActivity()).getUserId();
        adapter = new GroupListAdapter(groupService.getGroupDetails(userId), new GroupListAdapter.OnGroupClickListener() {
            @Override
            public void onGroupClick(GroupDetail group) {
                Log.d("GroupFragment", "Group clicked: " + group.getGroupName());
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