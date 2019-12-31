package com.example.pl_contacts.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.adapters.NumbersAdapter;
import com.example.pl_contacts.handlers.DBHelper;
import com.example.pl_contacts.instances.Contact;
import com.example.pl_contacts.instances.Number;

import java.util.ArrayList;
import java.util.List;

public class ContactDetailsActivity extends AppCompatActivity {
    RecyclerView numbersRv;
    NumbersAdapter numbersAdapter;
    List<Number> numberList;
    ScrollView scrollView;
    RelativeLayout headerContainer;
    TextView fullnameTxt, fullnameHeaderTxt;
    ImageView deleteImg, editImg;
    int id = 0;


    FrameLayout dialogFocusContainer;
    CardView deleteCotactContainer;
    TextView deleteTxt, cancelTxt;

    DBHelper dbHelper;

    public void init() {

        dbHelper = new DBHelper(this);

        headerContainer = findViewById(R.id.container_header);
        fullnameHeaderTxt = findViewById(R.id.txt_fullname_header);
        deleteImg = findViewById(R.id.img_delete);
        editImg = findViewById(R.id.img_edit);

        scrollView = findViewById(R.id.scrollview);
        numbersRv = findViewById(R.id.rv_numbers);

        fullnameTxt = findViewById(R.id.txt_fullname);

        dialogFocusContainer = findViewById(R.id.container_dialog_focus);
        deleteCotactContainer = findViewById(R.id.container_delete_contact);
        deleteTxt = findViewById(R.id.txt_delete);
        cancelTxt = findViewById(R.id.txt_cancel);

        numberList = new ArrayList<>();
        numberList.add(new Number("Mobile", "09363303477"));
        numberList.add(new Number("Mobile", "09363303477"));
        numberList.add(new Number("Mobile", "09363303477"));
        numberList.add(new Number("Mobile", "09363303477"));
        numberList.add(new Number("Mobile", "09363303477"));
        numberList.add(new Number("Mobile", "09363303477"));

        numbersAdapter = new NumbersAdapter(this, numberList);
        numbersRv.setLayoutManager(new LinearLayoutManager(this));
        numbersRv.setAdapter(numbersAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        init();

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(headerContainer,"elevation", 0);
                    objectAnimator.setDuration(50);
                    objectAnimator.start();
                } else {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(headerContainer,"elevation", 40);
                    objectAnimator.setDuration(50);
                    objectAnimator.start();
                }

                Rect scrollBounds = new Rect();
                scrollView.getHitRect(scrollBounds);
                if (fullnameTxt.getLocalVisibleRect(scrollBounds) && fullnameHeaderTxt.getVisibility() == View.VISIBLE) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                    alphaAnimation.setDuration(200);
                    fullnameHeaderTxt.startAnimation(alphaAnimation);
                    fullnameHeaderTxt.setVisibility(View.INVISIBLE);
                } else if (!fullnameTxt.getLocalVisibleRect(scrollBounds) && fullnameHeaderTxt.getVisibility() == View.INVISIBLE) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(200);
                    fullnameHeaderTxt.startAnimation(alphaAnimation);
                    fullnameHeaderTxt.setVisibility(View.VISIBLE);
                }
            }
        });

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.VISIBLE);
                deleteCotactContainer.setVisibility(View.VISIBLE);
                dialogFocusContainer.setClickable(true);
            }
        });

        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.INVISIBLE);
                deleteCotactContainer.setVisibility(View.INVISIBLE);
                dialogFocusContainer.setClickable(false);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (dialogFocusContainer.getVisibility() == View.VISIBLE) {
            dialogFocusContainer.setVisibility(View.INVISIBLE);
            deleteCotactContainer.setVisibility(View.INVISIBLE);
            dialogFocusContainer.setClickable(false);
        } else {
            super.onBackPressed();
        }
    }
}
