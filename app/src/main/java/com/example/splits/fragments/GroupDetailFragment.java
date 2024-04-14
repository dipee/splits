package com.example.splits.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.splits.R;
import com.example.splits.databinding.FragmentGroupDetailBinding;


public class GroupDetailFragment extends Fragment implements View.OnClickListener {

    FragmentGroupDetailBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGroupDetailBinding.inflate(inflater, container, false);
        binding.floatingActionButton.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.floatingActionButton.getId()){
            // Navigate to AddGroupFragment
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddBillFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }
}