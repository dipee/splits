package com.example.splits.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.splits.R;
import com.example.splits.models.User;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}