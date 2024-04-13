package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.splits.R;
import com.example.splits.databinding.ActivityRegisterBinding;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding activityRegisterBinding;
    DatabaseHelper databaseHelper;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);

//        set dbhelper and service
        databaseHelper = new DatabaseHelper(this);
        userService = new UserService(databaseHelper);
//        set click listeners
        activityRegisterBinding.registerButton.setOnClickListener(this);
        activityRegisterBinding.bTHbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        if(view.getId() == activityRegisterBinding.registerButton.getId()){
            String name = activityRegisterBinding.nameField.getText().toString();
            String email = activityRegisterBinding.emailField.getText().toString();
            String password = activityRegisterBinding.passwordField.getText().toString();
            String confirmPassword = activityRegisterBinding.confirmPasswordField.getText().toString();
            if(password.equals(confirmPassword)){
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                User newUser = userService.addUser(user);
//                show toast to user
                if(newUser == null){
                    Toast.makeText(this, "Failed to add User", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, newUser.getName() + " added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }

            }
            else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }

        }
        else if(view.getId() == activityRegisterBinding.bTHbutton.getId()){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}