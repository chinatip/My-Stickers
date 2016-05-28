package com.example.asus.cameraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.asus.cameraapp.adapters.GridViewAdapter;

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

    //    private ArrayList<ImageItem> getData() {
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
//        return imageItems;
//    }
}
