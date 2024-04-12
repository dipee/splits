package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splits.R;
import com.example.splits.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = activityRegisterBinding.getRoot();
        setContentView(view);
        activityRegisterBinding.registerButton.setOnClickListener(this);
        activityRegisterBinding.bTHbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == activityRegisterBinding.bTHbutton.getId()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(view.getId() == activityRegisterBinding.registerButton.getId()){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}