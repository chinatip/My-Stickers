package com.example.asus.cameraapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Matrix;

import com.example.asus.cameraapp.R;

import java.io.ByteArrayOutputStream;

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
                Bitmap rotated = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight(), matrix, true);
                image.setImageBitmap(rotated);
                temp = rotated;
            }
        });

        cropButton = (ImageButton) findViewById(R.id.btn_crop);
        cropButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent imageDownload = new Intent("com.android.camera.action.CROP");
                imageDownload.setDataAndType(getImageUri(image.getContext(), temp),"image/*");
                imageDownload.putExtra("crop", "true");
                imageDownload.putExtra("aspectX", 1);
                imageDownload.putExtra("aspectY", 1);
                imageDownload.putExtra("outputX", 280);
                imageDownload.putExtra("outputY", 280);
                imageDownload.putExtra("return-data", true);
                startActivityForResult(imageDownload, 2);
            }
        });

        backButton = (ImageButton) findViewById(R.id.btn_crop_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Matrix matrix = new Matrix();
//                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
//                        matrix, true);
                image.setImageBitmap(bitmap);
                temp = bitmap;
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap cropImage = extras.getParcelable("data");
            image.setImageBitmap(cropImage);
            temp = cropImage;
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
