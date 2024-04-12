package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splits.R;
import com.example.splits.databinding.ActivityLoginBinding;
import com.example.splits.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        activityMainBinding.loginPageButton.setOnClickListener(this);
        activityMainBinding.registerPageButton.setOnClickListener(this);

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
}