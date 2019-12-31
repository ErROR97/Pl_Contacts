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
import com.example.pl_contacts.instances.Contact;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
    private Activity activity;
    private List<Contact> list;
    private int[] colors = {R.drawable.background_blue, R.drawable.background_green, R.drawable.background_orange, R.drawable.background_pink,
    R.drawable.background_red, R.drawable.background_yellow, R.drawable.background_purple};
    private String currentLetter = "";

    public ContactsAdapter(Activity activity, List<Contact> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!currentLetter.equals(String.valueOf(list.get(position).getFirstName().toCharArray()[0]))) {
            currentLetter = String.valueOf(list.get(position).getFirstName().toCharArray()[0]);
            holder.alphabetTxt.setText(currentLetter);
        }

        if (list.get(position).getImage() == 0) {
            holder.colorImg.setVisibility(View.VISIBLE);
            holder.colorImg.setBackgroundResource(colors[new Random().nextInt(6)]);
            holder.firstLetterTxt.setVisibility(View.VISIBLE);
            holder.firstLetterTxt.setText(String.valueOf(list.get(position).getFirstName().toCharArray()[0]));
        }
        holder.fullNametxt.setText(list.get(position).getFirstName() + " " + list.get(position).getLastName());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, ContactDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout root;
        private ImageView coverImg, colorImg;
        private TextView alphabetTxt, fullNametxt, firstLetterTxt;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            coverImg = itemView.findViewById(R.id.img_cover);
            colorImg = itemView.findViewById(R.id.img_color);
            alphabetTxt = itemView.findViewById(R.id.txt_alphabet);
            fullNametxt = itemView.findViewById(R.id.txt_fullname);
            firstLetterTxt = itemView.findViewById(R.id.txt_first_letter);
        }
    }

}
