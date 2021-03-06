package com.example.pl_contacts.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.adapters.ViewPagerAdapter;
import com.example.pl_contacts.fragments.ContactsFragment;
import com.example.pl_contacts.fragments.GroupsFragment;
import com.example.pl_contacts.interfaces.ShowSelectContainerListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements ShowSelectContainerListener {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CardView addBtn;
    AppBarLayout appBarLayout;

    FrameLayout dialogFocusContainer;
    CardView createGroupContainer;
    TextInputEditText groupEt;
    TextView cancelTxt, OKTxt;

    RelativeLayout multiSelectContainer;
    TextView numberOfSelectedTxt;
    ImageView deleteSelectedImg, selectAllImg, unselectAllImg, closeImg;


    boolean isOnContact = true;

    private void init() {


        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        appBarLayout = findViewById(R.id.appbar);

        addBtn = findViewById(R.id.btn_add);

        dialogFocusContainer = findViewById(R.id.container_dialog_focus);
        createGroupContainer = findViewById(R.id.container_create_group);
        groupEt = findViewById(R.id.et_rename_group);
        cancelTxt = findViewById(R.id.txt_cancel_renaming);
        OKTxt = findViewById(R.id.txt_ok_renaming);

        multiSelectContainer = findViewById(R.id.container_multi_select);
        numberOfSelectedTxt = findViewById(R.id.txt_number_of_selected);
        deleteSelectedImg = findViewById(R.id.img_delete_selected);
        selectAllImg = findViewById(R.id.img_select);
        unselectAllImg = findViewById(R.id.img_unselect);
        closeImg = findViewById(R.id.img_close);

        multiSelectContainer.bringToFront();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ContactsFragment(), "contacts");
        viewPagerAdapter.addFragment(new GroupsFragment(), "groups");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ViewGroup tabs = (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < tabs.getChildCount(); i++) {
            ViewGroup tab = (ViewGroup) tabs.getChildAt(i);
            for (int j = 0; j < tab.getChildCount(); j++) {
                if (tab.getChildAt(j) instanceof TextView) {
                    ((TextView)tab.getChildAt(j)).setTypeface(ResourcesCompat.getFont(this, R.font.productsans_bold));
                }
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    isOnContact = true;
                } else {
                    isOnContact = false;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnContact) {
                    startActivity(new Intent(MainActivity.this, AddContactActivity.class));
                } else {
                    dialogFocusContainer.setVisibility(View.VISIBLE);
                    createGroupContainer.setVisibility(View.VISIBLE);
                    dialogFocusContainer.setClickable(true);
                    groupEt.requestFocus();
                }
            }
        });


        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.INVISIBLE);
                createGroupContainer.setVisibility(View.INVISIBLE);
                dialogFocusContainer.setClickable(false);
            }
        });




        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiSelectContainer.setVisibility(View.INVISIBLE);
                appBarLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onShowSelectContainer() {
        multiSelectContainer.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (multiSelectContainer.getVisibility() == View.VISIBLE) {
            multiSelectContainer.setVisibility(View.INVISIBLE);
            appBarLayout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }

    }
}
