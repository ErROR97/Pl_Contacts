package com.example.pl_contacts.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.adapters.ContactsAdapter;
import com.example.pl_contacts.instances.Contact;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.List;

public class GroupDetailsActivity extends AppCompatActivity {
    private RelativeLayout headerContainer;
    private ImageView deleteImg, editImg, addContactImg, backImg;
    private RecyclerView contactsRv;

    private List<Contact> contactList;
    private ContactsAdapter contactsAdapter;
    private LinearLayoutManager linearLayoutManager;

    private FrameLayout dialogFocusContainer;

    private CardView renameGroupContainer;
    private TextInputEditText renameGroupEt;
    private TextView okRenamingTxt, cancelRenamingTxt;

    private CardView deleteGroupContainer;
    private TextView deleteGroupTxt, cancelDeletingTxt;

    private void init() {

        contactsRv = findViewById(R.id.rv_contacts);

        headerContainer = findViewById(R.id.container_header);
        deleteImg = findViewById(R.id.img_delete);
        editImg = findViewById(R.id.img_edit);
        addContactImg = findViewById(R.id.img_add_contact);
        backImg = findViewById(R.id.img_back);

        dialogFocusContainer = findViewById(R.id.container_dialog_focus);

        renameGroupContainer = findViewById(R.id.container_rename_group);
        renameGroupEt = findViewById(R.id.et_rename_group);
        okRenamingTxt = findViewById(R.id.txt_ok_renaming);
        cancelRenamingTxt = findViewById(R.id.txt_cancel_renaming);

        deleteGroupContainer = findViewById(R.id.container_delete_group);
        deleteGroupTxt = findViewById(R.id.txt_delete);
        cancelDeletingTxt = findViewById(R.id.txt_cancel_deleting);

        contactList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Contact contact = new Contact();
            contact.setFirstName("Matin");
            contact.setLastName("Daryanosh Barahouei");
            contactList.add(contact);
        }

        contactsAdapter = new ContactsAdapter(this, contactList);

        linearLayoutManager = new LinearLayoutManager(this);
        contactsRv.setLayoutManager(linearLayoutManager);
        contactsRv.setAdapter(contactsAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        init();

        contactsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int pastVisibleItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (pastVisibleItems == 0) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(headerContainer, "elevation", 0);
                    objectAnimator.setDuration(50);
                    objectAnimator.start();
                } else {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(headerContainer,"elevation", 40);
                    objectAnimator.setDuration(50);
                    objectAnimator.start();
                }
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.VISIBLE);
                renameGroupContainer.setVisibility(View.VISIBLE);
                dialogFocusContainer.setClickable(true);
                renameGroupEt.requestFocus();
            }
        });

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.VISIBLE);
                deleteGroupContainer.setVisibility(View.VISIBLE);
                dialogFocusContainer.setClickable(true);
            }
        });

        cancelRenamingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.INVISIBLE);
                renameGroupContainer.setVisibility(View.INVISIBLE);
                dialogFocusContainer.setClickable(false);
            }
        });

        cancelDeletingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.INVISIBLE);
                deleteGroupContainer.setVisibility(View.INVISIBLE);
                dialogFocusContainer.setClickable(false);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (dialogFocusContainer.getVisibility() == View.VISIBLE) {
            dialogFocusContainer.setVisibility(View.INVISIBLE);
            deleteGroupContainer.setVisibility(View.INVISIBLE);
            renameGroupContainer.setVisibility(View.VISIBLE);
            dialogFocusContainer.setClickable(false);
        } else {
            super.onBackPressed();
        }

    }
}
