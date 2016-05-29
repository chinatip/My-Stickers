package com.example.asus.cameraapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.asus.cameraapp.adapters.GridViewAdapter;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
            /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
                }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(GalleryActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("imagePath", item.getPath());
                startActivity(intent);


                //open from gallery
//                Intent i = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                final int ACTIVITY_SELECT_IMAGE = 1234;
//                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);

//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(new File(item.getPath())), "image/*");
//                startActivity(intent);

            }
        });
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        //using device file
//        for (int i = 0; i < MainActivity.getImgCount(); i++) {
//            imageItems.add(new ImageItem("Image#" + i,"sdcard/camera_app/"+i+".jpg"));
//        }
        //using drawable file

            imageItems.add(new ImageItem("Image#" + 1,R.drawable.s1+""));
            imageItems.add(new ImageItem("Image#" + 2,R.drawable.s2+""));
            imageItems.add(new ImageItem("Image#" + 3,R.drawable.s3+""));
            imageItems.add(new ImageItem("Image#" + 4,R.drawable.s4+""));
            imageItems.add(new ImageItem("Image#" + 5,R.drawable.s5+""));
            imageItems.add(new ImageItem("Image#" + 6,R.drawable.s6+""));


        return imageItems;
    }

//    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {
//
//        final float densityMultiplier = context.getResources().getDisplayMetrics().density;
//
//        int h= (int) (newHeight*densityMultiplier);
//        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));
//
//        photo=Bitmap.createScaledBitmap(photo, w, h, true);
//
//        return photo;
//    }
}
