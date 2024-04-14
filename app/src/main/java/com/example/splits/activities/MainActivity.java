package com.example.splits.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splits.databinding.ActivityMainBinding;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;
    DatabaseHelper databaseHelper;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();

        setContentView(view);


        databaseHelper = new DatabaseHelper(this);
        userService = new UserService(databaseHelper);

        activityMainBinding.loginPageButton.setOnClickListener(this);
        activityMainBinding.registerPageButton.setOnClickListener(this);
        onLoadCheckLogin();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==activityMainBinding.loginPageButton.getId()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        if(view.getId()==activityMainBinding.registerPageButton.getId()){
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }

    }

    public void onLoadCheckLogin(){
        User user = userService.checkIfLoggedIn();
        if(user != null) {
            // User is logged in, navigate to HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("name", user.getName());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("userId", user.getId());
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back to MainActivity
        }
    }
}