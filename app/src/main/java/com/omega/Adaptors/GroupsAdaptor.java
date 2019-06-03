package com.omega.Adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omega.Database.Groups;
import com.omega.R;

import java.util.List;

public class GroupsAdaptor extends RecyclerView.Adapter<GroupsAdaptor.GroupsViewHolder> {

    LayoutInflater layoutInflater;
    List<Groups> groupsList;
    String TAG = GroupsAdaptor.class.getSimpleName();
    GroupsAdaptorListenerInterface itemListener;

    public GroupsAdaptor(Context context,GroupsAdaptorListenerInterface adaptorListenerInterface){
        layoutInflater = LayoutInflater.from(context);
        itemListener = adaptorListenerInterface;
    }

    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mainView = layoutInflater.inflate(R.layout.item_checkout_groups, parent, false);
        GroupsViewHolder groupsViewHolder = new GroupsViewHolder(mainView);
        return groupsViewHolder;
    }

    @Override
    public void onBindViewHolder(GroupsViewHolder holder, int position) {
        if (groupsList != null) {
            String name = groupsList.get(position).getGroupName();
            String description = groupsList.get(position).getGroupDescription();
            holder.tvGroupName.setText(name);
            holder.tvGroupDescription.setText(description);
        }
        else{
            holder.tvGroupDescription.setText("No Group Found!");
        }
    }

    @Override
    public int getItemCount() {
        if (groupsList != null) {
            return groupsList.size();
        }
        else{
            return 0;
        }
    }

    public void setDataSet(List<Groups> dataSet) {
        groupsList = dataSet;
        notifyDataSetChanged();
    }

    public Groups deleteRowFromDataSet(int pos) {
        Groups group = groupsList.get(pos);
        groupsList.remove(pos);
        notifyItemRemoved(pos);
        return group;
    }

    public interface GroupsAdaptorListenerInterface {
        void onItemClick(View view, String groupName);
    }

    public class GroupsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvGroupName;
        TextView tvGroupDescription;

        public GroupsViewHolder(View itemView) {
            super(itemView);
            tvGroupName = itemView.findViewById(R.id.text_checkout_group_name);
            tvGroupDescription = itemView.findViewById(R.id.text_checkout_group_description);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());

            v.animate().translationXBy(1500).setDuration(200).withEndAction(() -> {
                itemListener.onItemClick(v, tvGroupName.getText().toString());
            });




        }
    }

}
