package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.splits.R;
import com.example.splits.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding activityLoginBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);
       activityLoginBinding.Loginbutton.setOnClickListener(this);
       activityLoginBinding.homeText.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == activityLoginBinding.homeText.getId()){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }
}