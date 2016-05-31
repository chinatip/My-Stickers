package com.example.asus.cameraapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.asus.cameraapp.models.InfoPopUp;
import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.Save;
import com.example.asus.cameraapp.stickers.StickerView;

import java.util.ArrayList;

public class AddStickerActivity extends AppCompatActivity {
    private Bitmap bitmap;
    private static ImageView image;
    private static ArrayList<Bitmap> stickers = new ArrayList<>();
    public static ImageView sticker1;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton saveButton;
    private ImageButton shareButton;
    private ImageButton reverseButton;
    private ImageButton infoButton;
    private static Resources resources;
    private static StickerView stickerView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsticker);

        resources = getResources();
        bitmap = getIntent().getParcelableExtra("bitmap");
        image = (ImageView)findViewById(R.id.img_image);

        stickerView = new StickerView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.img_image);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.img_image);
        ((ViewGroup)image.getParent()).addView(stickerView, params);

        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initComponents() {
        image.setImageBitmap(bitmap);

        homeButton = (ImageButton) findViewById(R.id.btn_home);

        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addButton = (ImageButton) findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddStickerActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });

        saveButton = (ImageButton) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //fix
                bitmap = mergeToPin(bitmap, stickers.get(0));
                image.setImageBitmap(bitmap);

//                image.buildDrawingCache();
//                Bitmap bmap = image.getDrawingCache();
//                image.setImageBitmap(bmap);

                Save savefile = new Save();
                savefile.SaveImage(getApplicationContext(), bitmap);
            }
        });

        shareButton = (ImageButton) findViewById(R.id.btn_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        reverseButton = (ImageButton) findViewById(R.id.btn_addS_back);
        reverseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bitmap myImg = getIntent().getParcelableExtra("bitmap");
//                Matrix matrix = new Matrix();
//                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
//                        matrix, true);
                image.setImageBitmap(myImg);
                sticker1.setImageBitmap(null);
            }
        });

        infoButton = (ImageButton) findViewById(R.id.btn_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(AddStickerActivity.this, InfoPopUp.class));
            }
        });
    }

    public static Bitmap mergeToPin(Bitmap back, Bitmap front) {
        Bitmap result = Bitmap.createBitmap(back.getWidth(), back.getHeight(), back.getConfig());
        Canvas canvas = new Canvas(result);
        int widthBack = back.getWidth();
        int widthFront = front.getWidth();
        float move = (widthBack - widthFront) / 2;
        canvas.drawBitmap(back, 0f, 0f, null);
        canvas.drawBitmap(front, 0, 0, null);
        return result;
    }

//    public Bitmap combineImages(Bitmap c, Bitmap s) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom
//        Bitmap cs = null;
//
//        int width, height = 0;
//
//        if(c.getWidth() > s.getWidth()) {
//            width = c.getWidth() + s.getWidth();
//            height = c.getHeight();
//        } else {
//            width = s.getWidth() + s.getWidth();
//            height = c.getHeight();
//        }
//
//        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//
//        Canvas comboImage = new Canvas(cs);
//
//        comboImage.drawBitmap(c, 0f, 0f, null);
//        comboImage.drawBitmap(s, c.getWidth(), 0f, null);
//
//        // this is an extra bit I added, just incase you want to save the new image somewhere and then return the location
//    /*String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";
//
//    OutputStream os = null;
//    try {
//      os = new FileOutputStream(loc + tmpImg);
//      cs.compress(CompressFormat.PNG, 100, os);
//    } catch(IOException e) {
//      Log.e("combineImages", "problem combining images", e);
//    }*/
//
//        return cs;
//    }

    public static void setSticker(int id) {
        Bitmap StickerBitmap = BitmapFactory.decodeResource(resources, id);
        stickerView.setWaterMark(StickerBitmap);
    }

}
