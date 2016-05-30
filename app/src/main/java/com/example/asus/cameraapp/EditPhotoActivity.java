package com.example.asus.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Matrix;

public class EditPhotoActivity extends AppCompatActivity {

    private ImageView cropText;
    private ImageView image;
    private ImageButton rotateButton;
    private ImageButton cropButton;
    private ImageButton backButton;
    private ImageButton doneButton;
    private Bitmap bitmap,temp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphoto);

        initComponents();
    }

    private void initComponents() {

        cropText = (ImageView) findViewById(R.id.txt_crop);
        cropText.setImageResource(R.drawable.txt_crop);

        Intent intent = getIntent();
        bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        temp = bitmap;

        image = (ImageView) findViewById(R.id.img_image);
        image.setImageBitmap(bitmap);

        rotateButton = (ImageButton) findViewById(R.id.btn_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {

            Matrix matrix = new Matrix();

            public void onClick(View view) {
                matrix.postRotate(-90);
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                image.setImageBitmap(rotated);
                temp = rotated;
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
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
                temp = rotated;
            }
        });

        doneButton = (ImageButton) findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditPhotoActivity.this, AddStickerActivity.class);
                intent.putExtra("bitmap",temp);
                startActivity(intent);
            }
        });
    }
}
