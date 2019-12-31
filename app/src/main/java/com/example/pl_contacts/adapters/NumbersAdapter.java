package com.example.pl_contacts.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.instances.Contact;
import com.example.pl_contacts.instances.Number;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    private Activity activity;
    private List<Number> list;

    public NumbersAdapter(Activity activity, List<Number> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_number, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.numberTxt.setText(list.get(position).getNumber());
            holder.typeTxt.setText(list.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconImg, meessageImg;
        private TextView numberTxt, typeTxt;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            meessageImg = itemView.findViewById(R.id.img_message);
            numberTxt = itemView.findViewById(R.id.txt_number);
            typeTxt = itemView.findViewById(R.id.txt_type);
        }
    }

}
