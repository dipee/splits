package com.example.splits.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splits.R;
import com.example.splits.databinding.FragmentGroupBinding;


public class GroupFragment extends Fragment implements View.OnClickListener {

    private FragmentGroupBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGroupBinding.inflate(inflater, container, false);

        // Set Floating Action Button click listener
        binding.floatingActionButton.setOnClickListener(this);


        return binding.getRoot();
    }


    @Override
    public void onClick(View v) {
        Log.d("Clicked", "onClick: floating action button clicked");
        if(v.getId() == binding.floatingActionButton.getId()){
            Log.d("Clicked", "onClick: floating action button clicked");
        }

    }
}