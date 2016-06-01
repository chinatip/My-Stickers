package com.example.asus.cameraapp.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.cameraapp.models.ImageItem;
import com.example.asus.cameraapp.OnSwipeTouchListener;
import com.example.asus.cameraapp.R;

/**
 * Created by Asus on 28/5/2559.
 */
public class DetailsActivity extends AppCompatActivity {

    private ImageView stickerButton,shareButton;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        stickerButton = (ImageView) findViewById(R.id.stickerButton);
        shareButton = (ImageView) findViewById(R.id.shareButton);

        String title = getIntent().getStringExtra("title");
        String path = getIntent().getStringExtra("imagePath");
        index = getIntent().getIntExtra("index",0);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        //using device file
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        //using drawable
        imageView.setImageResource(Integer.parseInt(path));

//        imageView.setOnTouchListener(new OnSwipeTouchListener(DetailsActivity.this) {
//            public void onSwipeTop() {
//                Toast.makeText(DetailsActivity.this, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                if(index>0){
//                    Intent intent = new Intent(DetailsActivity.this, DetailsActivity.class);
//                    ImageItem item = GalleryActivity.getImageItems().get(index-1);
//                    intent.putExtra("title", item.getTitle());
//                    intent.putExtra("index", index-1);
//                    intent.putExtra("imagePath", item.getPath());
//                    startActivity(intent);
//                    finish();
//                }
//            }
//            public void onSwipeLeft() {
//                if(GalleryActivity.getImageItems().size()>index+1){
//                    Intent intent = new Intent(DetailsActivity.this, DetailsActivity.class);
//                    ImageItem item = GalleryActivity.getImageItems().get(index+1);
//                    intent.putExtra("title", item.getTitle());
//                    intent.putExtra("index", index+1);
//                    intent.putExtra("imagePath", item.getPath());
//                    startActivity(intent);
//                    finish();
//                }
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(DetailsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//            }
//
//        });

    }
}