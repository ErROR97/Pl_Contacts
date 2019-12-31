package com.example.pl_contacts.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.activities.ContactDetailsActivity;
import com.example.pl_contacts.activities.GroupDetailsActivity;
import com.example.pl_contacts.instances.Contact;
import com.example.pl_contacts.instances.Group;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {
    private Activity activity;
    private List<Group> list;

    public GroupsAdapter(Activity activity, List<Group> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameTxt.setText(list.get(position).getName());
        holder.numberTxt.setText(String.valueOf(list.get(position).getContactNumbers()));

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, GroupDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root;
        private TextView nameTxt, numberTxt;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            nameTxt = itemView.findViewById(R.id.txt_name);
            numberTxt = itemView.findViewById(R.id.txt_number);
        }
    }

}
