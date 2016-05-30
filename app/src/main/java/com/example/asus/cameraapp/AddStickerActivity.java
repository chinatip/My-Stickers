package com.example.asus.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddStickerActivity extends AppCompatActivity {

    private ImageView image;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton saveButton;
    private ImageButton shareButton;
    private ImageButton reverseButton;
    private ImageButton infoButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsticker);
        initComponents();
    }

    private void initComponents() {
        image = (ImageView)findViewById(R.id.img_image);
        image.setImageResource(R.drawable.monkey);

        homeButton = (ImageButton) findViewById(R.id.btn_home);

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton = (ImageButton) findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });

        saveButton = (ImageButton) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Save savefile = new Save();
                savefile.SaveImage(getApplicationContext() ,bitmap);
            }
        });

        shareButton = (ImageButton) findViewById(R.id.btn_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        reverseButton = (ImageButton) findViewById(R.id.btn_addS_back);
        reverseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        infoButton = (ImageButton) findViewById(R.id.btn_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(AddStickerActivity.this,InfoPopUp.class));
            }
        });
    }
}
