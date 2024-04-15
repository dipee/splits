package com.example.splits.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.splits.R;
import com.example.splits.databinding.ActivityHomeBinding;
import com.example.splits.fragments.GroupFragment;
import com.example.splits.fragments.ProfileFragment;
import com.example.splits.services.UserService;
import com.example.splits.utilities.DatabaseHelper;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private ActionBarDrawerToggle mToggle;
    private DatabaseHelper databaseHelper;
    private UserService userService;
    private String userName;
    private String email;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigationDrawer();

        databaseHelper = new DatabaseHelper(this);
        userService = new UserService(databaseHelper);

        // get email, name from intent
        email = getIntent().getStringExtra("email");
        userName = getIntent().getStringExtra("name");
        userId = getIntent().getIntExtra("userId", 0);

        TextView titleText = binding.navView.getHeaderView(0).findViewById(R.id.header_text_view);
        titleText.setText(String.format("Hi!, %s", userName));

        loadFragment(new GroupFragment());
    }

    private void initNavigationDrawer() {
        mToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navDrawerTextOpen, R.string.navDrawerTextClose);
        binding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.navView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.navLogout) {
            userService.logoutUser(email);
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.navGroups) {
            loadFragment(new GroupFragment());
            binding.drawerLayout.closeDrawers();
            return true;
        } else if (itemId == R.id.navProfile) {
            loadFragment(new ProfileFragment());
            binding.drawerLayout.closeDrawers();
            return true;
        }
        binding.drawerLayout.closeDrawers();

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContainer.getId(), fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public String getEmail() {
        return email;
    }
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}