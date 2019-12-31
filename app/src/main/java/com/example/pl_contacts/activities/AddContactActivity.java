package com.example.pl_contacts.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.pl_contacts.R;
import com.example.pl_contacts.fragments.AddNumberFragment;
import com.example.pl_contacts.instances.Contact;
import com.example.pl_contacts.interfaces.EditTextChangeListener;
import com.example.pl_contacts.interfaces.IdUpdateListener;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddContactActivity extends AppCompatActivity implements EditTextChangeListener {

    LinearLayout numbersContainer;
    List<IdUpdateListener> idUpdateListenerList;
    ScrollView scrollView;
    RelativeLayout headerContainer;

    CardView changePhotoContainer;
    FrameLayout dialogFocusContainer;
    ImageView addPicImg, picImg, closeImg;
    TextView takePhotoTxt, choosePhotoTxt, cancelTxt;

    private List<View> viewList;
    private int viewId = 1;
    private int lastId = 0;
    String pathToFile;
    Contact contact;

    private void init() {
        viewList = new ArrayList<>();
        idUpdateListenerList = new ArrayList<>();
        contact = new Contact();

        closeImg = findViewById(R.id.img_close);
        numbersContainer = findViewById(R.id.container_numbers);
        scrollView = findViewById(R.id.scrollview);
        headerContainer = findViewById(R.id.container_header);


        dialogFocusContainer = findViewById(R.id.container_dialog_focus);
        changePhotoContainer = findViewById(R.id.container_change_photo);
        addPicImg = findViewById(R.id.img_add_pic);
        picImg = findViewById(R.id.img_pic);
        takePhotoTxt = findViewById(R.id.txt_take_photo);
        choosePhotoTxt = findViewById(R.id.txt_choose_photo);
        cancelTxt = findViewById(R.id.txt_cancel_renaming);

        addFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        init();

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

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
            }
        });

        addPicImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.VISIBLE);
                changePhotoContainer.setVisibility(View.VISIBLE);
                dialogFocusContainer.setClickable(true);
            }
        });

        takePhotoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        choosePhotoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseFromGallery();
            }
        });

        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFocusContainer.setVisibility(View.INVISIBLE);
                changePhotoContainer.setVisibility(View.INVISIBLE);
                dialogFocusContainer.setClickable(false);
            }
        });


        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
            dialogFocusContainer.setVisibility(View.INVISIBLE);
            changePhotoContainer.setVisibility(View.INVISIBLE);
            dialogFocusContainer.setClickable(false);
            picImg.setVisibility(View.VISIBLE);
            picImg.setImageBitmap(bitmap);
        } else if (resultCode == RESULT_OK && requestCode == 2) {
            dialogFocusContainer.setVisibility(View.INVISIBLE);
            changePhotoContainer.setVisibility(View.INVISIBLE);
            dialogFocusContainer.setClickable(false);
            picImg.setVisibility(View.VISIBLE);
            Uri uri = data.getData();
            picImg.setImageURI(uri);
        }
    }


    private void dispatchPictureTakerAction() {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        if (captureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createPhotoFile();
            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(AddContactActivity.this, "com.example.pl_contacts.fileprovider", photoFile);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(captureIntent, 1);
            }

        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.i("mylog", "exception: " + e.toString());
        }
        return image;
    }


    private void ChooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }




    public int findId(){
        View v = findViewById(viewId);
        while (v != null){
            v = findViewById(++viewId);
        }
        return viewId++;
    }

    public void addFragment() {
        View view = getLayoutInflater().inflate(R.layout.framelayout, null);
        view.setId(findId());
        viewList.add(view);
        numbersContainer.addView(view);

        Bundle bundle = new Bundle();
        bundle.putString("viewId", String.valueOf(lastId++));
        AddNumberFragment addNumberFragment = new AddNumberFragment();
        addNumberFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(view.getId(), addNumberFragment).commit();
    }

    @Override
    public boolean onTextChanged(boolean check, int id) {
        if (check) {
            if (id == lastId - 1) {
                addFragment();
            }
            return false;
        } else {
            if (id == lastId - 2) {
                numbersContainer.removeView(viewList.get(id + 1));
                viewList.remove(id + 1);
                idUpdateListenerList.remove(id + 1);
                lastId--;
                if (viewList.size() == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (viewList.size() == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }

    }

    @Override
    public boolean onForceCloesed(int id) {
        numbersContainer.removeView(viewList.get(id));
        viewList.remove(id);
        idUpdateListenerList.remove(id);
        lastId--;
        for (int i = 0; i < lastId; i++) {
            idUpdateListenerList.get(i).onIdUpdated(id);
        }
        if (viewList.size() == 1) {
            idUpdateListenerList.get(0).onLastOne();
            return true;
        } else {
            return false;
        }
    }

    public void setOnDataListener(AddNumberFragment fragmentInterfaceListener){
        idUpdateListenerList.add(fragmentInterfaceListener);
    }

}
