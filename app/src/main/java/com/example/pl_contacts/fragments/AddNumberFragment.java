package com.example.pl_contacts.fragments;

import android.os.Bundle;
import android.text.BoringLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.activities.AddContactActivity;
import com.example.pl_contacts.interfaces.EditTextChangeListener;
import com.example.pl_contacts.interfaces.IdUpdateListener;
import com.google.android.material.textfield.TextInputEditText;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddNumberFragment extends Fragment implements IdUpdateListener {
    private View view;
    private NiceSpinner niceSpinner;
    private TextInputEditText numberTxt;
    private EditTextChangeListener editTextChangeListener;
    private ImageView closeImg;

    private boolean textIsEmpty = true;
    private int id;


    private void init() {
        ((AddContactActivity)getActivity()).setOnDataListener(this);

        id = Integer.valueOf(getArguments().getString("viewId"));
        editTextChangeListener = (EditTextChangeListener) getActivity();
        niceSpinner = view.findViewById(R.id.spinner_type);
        numberTxt = view.findViewById(R.id.txt_number);
        closeImg = view.findViewById(R.id.img_close);

        niceSpinner.attachDataSource(new LinkedList<>(Arrays.asList("Mobile", "Work", "Home", "Work Fax", "Home Fax", "Other")));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_number, container, false);
        init();


        numberTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && textIsEmpty) {

                    if (!editTextChangeListener.onTextChanged(true, id)) {
                        closeImg.setVisibility(View.VISIBLE);
                    } else {
                        closeImg.setVisibility(View.INVISIBLE);
                    }
                    textIsEmpty = false;
                } else if (s.length() == 0 && !textIsEmpty) {
                    if (!editTextChangeListener.onTextChanged(false, id)) {
                        closeImg.setVisibility(View.VISIBLE);
                    }else {
                        closeImg.setVisibility(View.INVISIBLE);
                    }
                    textIsEmpty = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextChangeListener.onForceCloesed(id);
            }
        });

        return view;
    }



    @Override
    public void onIdUpdated(int id) {
        if (id < this.id) {
            this.id--;
        }
    }

    @Override
    public void onLastOne() {
        if (this.id == 0) {
            closeImg.setVisibility(View.INVISIBLE);
        }
    }
}
