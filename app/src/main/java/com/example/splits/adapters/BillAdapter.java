package com.example.splits.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splits.R;
import com.example.splits.models.Bill;
import com.example.splits.models.User;


import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;


    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_list_item, parent, false);
        return new BillAdapter.BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.billNameTextView.setText(bill.getTitle());
        holder.billAmountTextView.setText(String.valueOf(bill.getAmount()));
        holder.billDescriptionTextView.setText(bill.getDescription());
        holder.billDateTextView.setText(bill.getDate());



    }

    @Override
    public int getItemCount() {
        return billList.size();
    }




    public static class BillViewHolder extends RecyclerView.ViewHolder {

        TextView billNameTextView;
        TextView billAmountTextView;

        TextView billDescriptionTextView;

        TextView billDateTextView;


        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            billNameTextView = itemView.findViewById(R.id.textViewBillTitle);
            billAmountTextView = itemView.findViewById(R.id.textViewBillAmount);
            billDescriptionTextView = itemView.findViewById(R.id.textViewBillDescription);
            billDateTextView = itemView.findViewById(R.id.textViewBillDate);

        }
    }
}
