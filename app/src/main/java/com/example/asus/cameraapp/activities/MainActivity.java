package com.example.asus.cameraapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.Save;
import com.example.asus.cameraapp.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    private ImageView logoImage;
    private ImageButton cameraButton, galleryButton;
    private Button gallAppButton,gallDecButton;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;

    boolean result= Utility.checkPermission(MainActivity.this);

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);

        logoImage = (ImageView) findViewById(R.id.img_logo);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        galleryButton = (ImageButton) findViewById(R.id.galleryButton);
        gallAppButton = (Button) findViewById(R.id.galleryFAppButton);
        gallDecButton = (Button) findViewById(R.id.galleryFDecButton);
        gallDecButton.setVisibility(View.INVISIBLE);
        gallAppButton.setVisibility(View.INVISIBLE);

        initComponents();
    }

    private void initComponents() {

        logoImage.setImageResource(R.drawable.img_logo);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userChoosenTask = "Take Photo";
                if (result)
                    cameraIntent();
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallDecButton.setVisibility(View.VISIBLE);
                gallAppButton.setVisibility(View.VISIBLE);
            }
        });
        gallDecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoosenTask = "Choose from Library";
                if (result)
                    galleryIntent();
            }
        });
        gallAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                }
                break;
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
/**
        File folder = new File("/MyStickers");
        File destination = new File (Environment.getExternalStorageDirectory().getAbsolutePath()+folder,
                                System.currentTimeMillis() + ".jpg");
        if(!folder.exists()) folder.mkdirs();

        FileOutputStream fo;

        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 */
        Save saveFile = new Save();
        saveFile.SaveImage(getApplicationContext(), thumbnail);

        Intent intent = new Intent(this, EditPhotoActivity.class);
        intent.putExtra("BitmapImage", thumbnail);
        startActivity(intent);
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(this, EditPhotoActivity.class);
        intent.putExtra("BitmapImage", bm);
        startActivity(intent);
    }
}