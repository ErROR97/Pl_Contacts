package com.example.pl_contacts.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pl_contacts.R;
import com.example.pl_contacts.adapters.GroupsAdapter;
import com.example.pl_contacts.instances.Group;

import java.util.ArrayList;
import java.util.List;


public class GroupsFragment extends Fragment {
    private View view;

    RecyclerView groupsRv;
    GroupsAdapter groupsAdapter;
    List<Group> groupList;

    private void init() {
        groupsRv = view.findViewById(R.id.rv_groups);
        groupList = new ArrayList<>();

        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));
        groupList.add(new Group("Family Group", 7));

        groupsAdapter = new GroupsAdapter(getActivity(), groupList);
        groupsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        groupsRv.setAdapter(groupsAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_groups, container, false);

        init();

        return view;
    }


}
