package com.example.asus.cameraapp;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.asus.cameraapp.adapters.GridViewAdapter;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

//        gridView = (GridView) findViewById(R.id.gridView);
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, null);
//        //gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
//        gridView.setAdapter(gridAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
//                //Create intent
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                intent.putExtra("title", item.getTitle());
//                intent.putExtra("image", item.getImage());
//
//                //Start details activity
//                startActivity(intent);
//            }
//        });
    }

        private ArrayList<ImageItem> getData() {
            final ArrayList<ImageItem> imageItems = new ArrayList<>();
            ArrayList<String> imgArr = MainActivity.getImageArr();
            for (int i = 0; i < imgArr.size(); i++) {
                Bitmap bitmap = BitmapFactory.decodeFile(imgArr.get(i));
                imageItems.add(new ImageItem(bitmap, "Image#" + i));
            }
            return imageItems;
    }
}
