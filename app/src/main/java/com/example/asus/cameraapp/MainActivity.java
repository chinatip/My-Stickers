package com.example.asus.cameraapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ImageButton cameraButton, stickerButton, shareButton, galleryButton;
    //ImageView imageView;
    ImageView imageView2;
    static final int CAM_REQUEST = 1;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        shareButton = (ImageButton) findViewById(R.id.shareButton);
        galleryButton = (ImageButton) findViewById(R.id.galleryButton);
        stickerButton = (ImageButton) findViewById(R.id.stickerButton);
        //imageView = (ImageView) findViewById(R.id.image_view);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
//        if(imageArr.size()>=1)
//        imageView2.setImageURI(Uri.fromFile(new File(imageArr.get(0))));
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
                addImageGallery(file);
            }
        });
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

    }

    private File getFile() {
        File folder = new File("sdcard/camera_app");
        if(!folder.exists()) {
            folder.mkdir();
        }
        File image_file = new File(folder,getImgCount()+".jpg");
        return image_file;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String path = "sdcard/camera_app/cam_image"+countImage+".jpg";
//        //imageView.setImageDrawable(Drawable.createFromPath(path));
//    }

    private void addImageGallery( File file ) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//delete
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }

    static public int getImgCount() {
//        File folder = new File("sdcard/camera_app");
//        int imgCount = 0;
//        if(!folder.exists()) {
//            folder.mkdir();
//        }
//        else {
//            File file = new File(folder,imgCount+".jpg");
//            while(file.exists()) {
//                imgCount++;
//                file = new File(folder,imgCount+".jpg");
//            }
//        }
//        return imgCount;
        return 1;
    }

}
