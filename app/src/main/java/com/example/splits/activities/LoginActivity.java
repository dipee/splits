package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

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
        Button loginButton = findViewById(R.id.Loginbutton);

        TextView BacktoHome = findViewById(R.id.homeText);
        loginButton.setOnClickListener(this);
        BacktoHome.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}