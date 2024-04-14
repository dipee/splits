package com.example.splits.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.splits.R;
import com.example.splits.databinding.FragmentAddGroupBinding;
import com.example.splits.models.Group;
import com.example.splits.services.GroupService;
import com.example.splits.utilities.DatabaseHelper;

public class AddGroupFragment extends Fragment implements View.OnClickListener {

    private FragmentAddGroupBinding binding;
    DatabaseHelper databaseHelper;

    GroupService groupService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddGroupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.buttonSubmit.setOnClickListener(this);
//        set db and service
        databaseHelper = new DatabaseHelper(getContext());
        groupService = new GroupService(databaseHelper);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == binding.buttonSubmit.getId()){
            String groupName = binding.editTextGroupName.getText().toString();
            String groupDescription = binding.editTextGroupDescription.getText().toString();
            // add group to database
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            Group group = new Group();
            group.setName(groupName);
            group.setDescription(groupDescription);
            groupService.addGroup(group);


            // redirect to group fragment

            GroupFragment groupFragment = new GroupFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, groupFragment).commit();
        }

    }
}