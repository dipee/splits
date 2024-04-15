package com.example.splits.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.splits.activities.HomeActivity;
import com.example.splits.databinding.FragmentAddSettlementBinding;
import com.example.splits.models.GroupDetail;
import com.example.splits.models.Transaction;
import com.example.splits.services.SettlementService;
import com.example.splits.utilities.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSettlementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSettlementFragment extends Fragment implements View.OnClickListener{
    FragmentAddSettlementBinding binding;
    SettlementService settlementService;

    DatabaseHelper databaseHelper;
    int groupId;
    int userId;

    GroupDetail group;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddSettlementBinding.inflate(inflater, container, false);

        //initialize database helper and service
        databaseHelper = new DatabaseHelper(getContext());
        settlementService = new SettlementService(databaseHelper);

        binding.buttonSave.setOnClickListener(this);
        //get data from bundle
        groupId = getArguments().getInt("groupId");
        double amount = getArguments().getDouble("totalOwed");
        String groupName = getArguments().getString("groupName");

        //get user id from home activity
        userId = ((HomeActivity) getActivity()).getUserId();

        binding.textViewOwedAmount.setText(String.valueOf(amount).format("%.2f", amount));
        binding.textViewGroupName.setText(String.valueOf(groupName));

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.buttonSave.getId()){
            //save settlement to database
            //get data from form
            String amountText = binding.editTextAmount.getText().toString();

            String remark = binding.editTextRemarks.getText().toString();
            //validate fields
            if(validateSettlementFields(amountText, remark)){
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount = Double.parseDouble(amountText);
            //create settlement object
            Transaction transaction = new Transaction();
            transaction.setPayerId(userId);
            transaction.setGroupId(groupId);
            transaction.setAmount(amount);
            transaction.setRemark(remark);
            //save settlement to database
            settlementService.addSettlement(transaction);
            //navigate to group detail fragment
            GroupFragment fragment = new GroupFragment();

            getActivity().getSupportFragmentManager().beginTransaction().replace(com.example.splits.R.id.fragment_container, fragment).addToBackStack(null).commit();




        }
    }

    Boolean validateSettlementFields(String amount, String remark){
        return amount.isEmpty() || remark.isEmpty();
    }
}