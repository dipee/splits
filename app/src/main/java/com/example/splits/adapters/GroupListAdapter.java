package com.example.splits.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splits.R;
import com.example.splits.models.Group;
import com.example.splits.models.GroupDetail;

import java.util.List;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {

    private List<Group> groups;

    private OnGroupClickListener onGroupClickListener;

    public GroupListAdapter(List<Group> groups, OnGroupClickListener onGroupClickListener) {
        this.groups = groups;
        this.onGroupClickListener = onGroupClickListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        Group group = groups.get(position);
        holder.bind(group);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
    public interface OnGroupClickListener {
        void onGroupClick(Group group);
    }

     class GroupViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView descriptionTextView;

        TextView paidAmountTextView;
        TextView owedAmountTextView;




        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onGroupClickListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            onGroupClickListener.onGroupClick(groups.get(position));
                        }
                    }
                }
            });

        }

        public void bind(Group group) {
            nameTextView.setText(group.getName());
            descriptionTextView.setText(group.getDescription());

        }


    }
}
