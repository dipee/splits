package com.example.splits.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splits.R;
import com.example.splits.databinding.FragmentAddBillBinding;
import com.example.splits.databinding.FragmentAddSettlementBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSettlementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSettlementFragment extends Fragment {
    FragmentAddSettlementBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddSettlementBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}