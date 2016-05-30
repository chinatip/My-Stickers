package com.example.asus.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EditPhotoActivity extends AppCompatActivity {

    private ImageView cropText;
    private ImageView image;
    private ImageButton rotateButton;
    private ImageButton cropButton;
    private ImageButton backButton;
    private ImageButton doneButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphoto);

        initComponents();
    }

    private void initComponents() {

        cropText = (ImageView) findViewById(R.id.txt_crop);
        cropText.setImageResource(R.drawable.txt_crop);

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");

        image = (ImageView) findViewById(R.id.img_image);
        image.setImageBitmap(bitmap);

        rotateButton = (ImageButton) findViewById(R.id.btn_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {

            Intent intent = getIntent();
            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
            Matrix matrix = new Matrix();

            public void onClick(View view) {
                matrix.postRotate(-90);
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        cropButton = (ImageButton) findViewById(R.id.btn_crop);
        cropButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        backButton = (ImageButton) findViewById(R.id.btn_crop_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = getIntent();
                Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        doneButton = (ImageButton) findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditPhotoActivity.this, AddStickerActivity.class);
                startActivity(intent);
            }
        });
    }
}
