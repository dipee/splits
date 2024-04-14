package com.example.splits.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splits.databinding.ActivityLoginBinding;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding activityLoginBinding ;

    DatabaseHelper db;
    UserService userService;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();


        setContentView(view);
//        set service and database
        db = new DatabaseHelper(this);
        userService = new UserService(db);


        activityLoginBinding.loginButton.setOnClickListener(this);

        activityLoginBinding.homeText.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //    get credential from the form
        email = activityLoginBinding.emailField.getText().toString();
        password = activityLoginBinding.passwordField.getText().toString();
        if(view.getId() == activityLoginBinding.loginButton.getId()){
            User user = userService.loginUser(email, password);
            if(user != null){
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email", user.getEmail());
                intent.putExtra("name", user.getName());
                intent.putExtra("userId", user.getId());
                startActivity(intent);

                finish();
            }
            else{
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();

            };
        }
        else if(view.getId() == activityLoginBinding.homeText.getId()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}