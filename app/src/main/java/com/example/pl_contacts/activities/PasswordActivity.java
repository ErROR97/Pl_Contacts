package com.example.pl_contacts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pl_contacts.R;
import com.example.pl_contacts.handlers.DBHelper;
import com.google.android.material.textfield.TextInputEditText;

public class PasswordActivity extends AppCompatActivity {
    RelativeLayout root;
    TextInputEditText passwrodEt;
    ImageView enterImg;
    TextView woringPassTxt;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        root = findViewById(R.id.root);
        passwrodEt = findViewById(R.id.et_password);
        enterImg = findViewById(R.id.img_enter);
        woringPassTxt = findViewById(R.id.txt_wrong_password);

        dbHelper = new DBHelper(this);

//        boolean insert = dbHelper.insertPhoneNumber(1, "09320439", "mobile");
//        dbHelper.insertPhoneNumber(1, "32942", "mobile");
//        dbHelper.insertPhoneNumber(1, "38924", "mobile");

//        boolean insert = dbHelper.insertContacts("matin", "barahouei", "matdb",
//                "zahedan", "zabol", "matingriems.ir", "matingrimes@yahoo.com",
//        "1375/11/21", 0);
//        if (insert) {
//            Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
//        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.light_blue));
            getWindow().setStatusBarColor(getResources().getColor(R.color.light_blue));
        }

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwrodEt.clearFocus();
            }
        });

        enterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwrodEt.getText().toString().equals("1234")) {
                    startActivity(new Intent(PasswordActivity.this, MainActivity.class));
                    finish();
                } else {
                    woringPassTxt.setVisibility(View.VISIBLE);
                }
            }
        });


    }

}
