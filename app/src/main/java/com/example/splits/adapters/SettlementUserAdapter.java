package com.example.splits.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splits.R;
import com.example.splits.models.GroupDetail;
import com.example.splits.models.SettlementUser;

import java.util.List;

public class SettlementUserAdapter extends RecyclerView.Adapter<SettlementUserAdapter.SettlementViewHolder> {

    private List<SettlementUser> settlementUsers;

    private OnGroupClickListener onGroupClickListener;

    public SettlementUserAdapter(List<SettlementUser> settlementUsers, OnGroupClickListener onGroupClickListener) {
        this.settlementUsers = settlementUsers;
        this.onGroupClickListener = onGroupClickListener;
    }

    @NonNull
    @Override
    public SettlementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settlement_user_list_item, parent, false);
        return new SettlementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettlementViewHolder holder, int position) {
        SettlementUser settlementUser  = settlementUsers.get(position);
        holder.bind(settlementUser);
    }

    @Override
    public int getItemCount() {
        return settlementUsers.size();
    }
    public interface OnGroupClickListener {
        void onGroupClick(SettlementUser settlementUser);
    }

    class SettlementViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView owedAmountTextView;




        public SettlementViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.userName);
            owedAmountTextView = itemView.findViewById(R.id.userAmount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onGroupClickListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            onGroupClickListener.onGroupClick(settlementUsers.get(position));
                        }
                    }
                }
            });

        }

        public void bind(SettlementUser settlementUser) {
            nameTextView.setText(settlementUser.getUserName());

            owedAmountTextView.setText("Total Paid: " + String.format("%.2f",settlementUser.getAmountOwed()));


        }


    }
}
