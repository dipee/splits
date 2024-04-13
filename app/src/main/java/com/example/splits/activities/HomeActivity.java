package com.example.splits.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;

import com.example.splits.R;
import com.example.splits.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding activityHomeBinding ;
    ActionBarDrawerToggle mToggle = null;

    DatabaseHelper databaseHelper;
    UserService userService;

    String userName;
    String email;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = activityHomeBinding.getRoot();
        setContentView(view);
        initNavigationDrawer();
        databaseHelper = new DatabaseHelper(this);
        userService = new UserService(databaseHelper);
        // get email, name from intent
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("name");

//        set navigation view
        navigationView = activityHomeBinding.navView;
       TextView titleText =  navigationView.getHeaderView(0).findViewById(R.id.header_text_view);
        titleText.setText(String.format("Hi!, %s", userName));



    }
    private void initNavigationDrawer() {

        mToggle = new ActionBarDrawerToggle(this, activityHomeBinding.drawerLayout, activityHomeBinding.materialToolbar, R.string.navDrawerTextOpen, R.string.navDrawerTextClose);
        activityHomeBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(activityHomeBinding.materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationDrawer();
    }
    private void setNavigationDrawer() {
        activityHomeBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // selected item ID
                int itemId = item.getItemId();

                // Logout item ID
                int logoutItemId = activityHomeBinding.navView.getMenu().findItem(R.id.navLogout).getItemId();
                if(itemId == logoutItemId){

                    userService.logoutUser(email);
//                    go to login scree
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                    return true;
                }



                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}