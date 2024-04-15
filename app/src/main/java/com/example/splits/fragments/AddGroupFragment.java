package com.example.splits.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.splits.R;
import com.example.splits.adapters.UserAdapter;
import com.example.splits.databinding.FragmentAddGroupBinding;
import com.example.splits.models.Group;
import com.example.splits.models.User;
import com.example.splits.services.GroupService;
import com.example.splits.services.UserGroupService;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

import java.time.LocalDate;
import java.util.List;

public class AddGroupFragment extends Fragment implements View.OnClickListener {

    private FragmentAddGroupBinding binding;
    DatabaseHelper databaseHelper;

    GroupService groupService;

    UserAdapter userAdapter;

    UserService userService;

    UserGroupService userGroupService;
    List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddGroupBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.buttonSubmit.setOnClickListener(this);
//        set db and service
        databaseHelper = new DatabaseHelper(getContext());
        groupService = new GroupService(databaseHelper);
        userService = new UserService(databaseHelper);
        userGroupService = new UserGroupService(databaseHelper);

        userList = userService.getAllUsers();
        userAdapter = new UserAdapter(userList);

        binding.userRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.userRecyclerView.setAdapter(userAdapter);
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
            Group group = new Group();
            group.setName(groupName);
            group.setDescription(groupDescription);

            //get email from intent
            String email = getActivity().getIntent().getStringExtra("email");
            group.setCreatorId(email);
            //set current date as creation date
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                group.setCreationDate(LocalDate.now().toString());
            }
            List<User> selectedUsers = userAdapter.getSelectedUsers();

            if(validateGroupFields(groupName, groupDescription, selectedUsers)){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            Group createdGroup = groupService.addGroup(group);

            // add users to group

            for (User user : selectedUsers) {
                userGroupService.addUserToGroup(user.getId(), createdGroup.getId());
            }

            //toast for success group create
            Toast.makeText(getContext(), "Group created successfully", Toast.LENGTH_SHORT).show();

            // redirect to group fragment

            GroupFragment groupFragment = new GroupFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, groupFragment).commit();
        }

    }

    Boolean validateGroupFields(String groupName, String groupDescription, List<User> userList){
        return groupName.isEmpty() || groupDescription.isEmpty() || userList.isEmpty();
    }
}