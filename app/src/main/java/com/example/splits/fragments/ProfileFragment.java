package com.example.splits.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.splits.activities.HomeActivity;
import com.example.splits.activities.LoginActivity;
import com.example.splits.databinding.FragmentProfileBinding;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    UserService userService;
    DatabaseHelper databaseHelper;

    FragmentProfileBinding binding;

    int userId;
    String email;

    String userName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.updateUserButton.setOnClickListener(this);

        //initialize database helper and service
        databaseHelper = new DatabaseHelper(getContext());
        userService = new UserService(databaseHelper);

        //get user id, email from activity
        userId = ((HomeActivity) getActivity()).getUserId();
         email = ((HomeActivity) getActivity()).getEmail();
        userName = ((HomeActivity) getActivity()).getUserName();

        //set user info
        binding.username.setText(userName);
        binding.email.setText(email);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.updateUserButton.getId()){
            //save user to database
            //get data from form
            String newName = binding.username.getText().toString();
            String newEmail = binding.email.getText().toString();

            if(validateProfileFields(newName, newEmail)){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User();
            user.setName(newName);
            user.setEmail(newEmail);
            user.setId(userId);
            //update user to database
            userService.updateUser(user);
            userService.logoutUser(email);

            //go Intent to login Activity
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);



        }

    }

   Boolean validateProfileFields(String name, String email){
        if(name.isEmpty() || email.isEmpty()){
            return true;
        }
        return false;
    }
}