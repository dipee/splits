package com.example.splits.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.splits.databinding.FragmentAddBillBinding;
import com.example.splits.utilities.DatabaseHelper;


public class AddBillFragment extends Fragment {

    FragmentAddBillBinding binding;
    DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBillBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}