package com.example.asus.cameraapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.asus.cameraapp.models.ImageItem;
import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.adapters.GridViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private ArrayList<ImageItem> imageItems = new ArrayList<>();
    private List<File> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        data = getListFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyStickers"));

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(GalleryActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("index", position);
                intent.putExtra("imagePath", item.getPath());
                startActivity(intent);

            }
        });
    }

    private ArrayList<ImageItem> getData() {
        imageItems = new ArrayList<>();
//        using device file
        for (int i = 0; i < data.size(); i++) {
            imageItems.add(new ImageItem("Image#" + i, data.get(i).getAbsolutePath()));
            Log.e("path",data.get(i).getAbsolutePath());
        }

//        using drawable file
//            imageItems.add(new ImageItem("Image#" + 1, R.drawable.s1 + ""));
//            imageItems.add(new ImageItem("Image#" + 2, R.drawable.s2 + ""));
//            imageItems.add(new ImageItem("Image#" + 3,R.drawable.s3+""));
//            imageItems.add(new ImageItem("Image#" + 4,R.drawable.s4+""));
//            imageItems.add(new ImageItem("Image#" + 5, R.drawable.s5 + ""));
//            imageItems.add(new ImageItem("Image#" + 6, R.drawable.s6 + ""));

        return imageItems;
    }

    private List<File> getListFile(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFile(file));
            } else {
                if(file.getName().endsWith(".jpg")){
                    inFiles.add(file);
                    Log.e("path",file.getAbsolutePath());
                }
            }
        }
        return inFiles;
    }


}
