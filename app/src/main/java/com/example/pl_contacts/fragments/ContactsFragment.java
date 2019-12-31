package com.example.pl_contacts.fragments;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.Script;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.pl_contacts.R;
import com.example.pl_contacts.adapters.ContactsAdapter;
import com.example.pl_contacts.instances.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactsFragment extends Fragment {
    private View view;
    private RecyclerView contactsRv;
    private ContactsAdapter contactsAdapter;
    private List<Contact> contactList;

    private void init() {
        contactsRv = view.findViewById(R.id.rv_contacts);


        contactList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Contact contact = new Contact();
            contact.setFirstName("Matin");
            contact.setLastName("Daryanosh Barahouei");
            contactList.add(contact);
        }

        contactsAdapter = new ContactsAdapter(getActivity(), contactList);

        contactsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsRv.setAdapter(contactsAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        init();


        return view;
    }


}
