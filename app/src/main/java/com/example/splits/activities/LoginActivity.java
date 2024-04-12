package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splits.R;
import com.example.splits.databinding.ActivityLoginBinding;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

import java.util.List;

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
            if(userService.loginUser(email, password)){
                Intent intent = new Intent(this, HomeActivity.class);
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
        }
    }
}