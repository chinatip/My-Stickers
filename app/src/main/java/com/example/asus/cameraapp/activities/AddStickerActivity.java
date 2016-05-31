package com.example.asus.cameraapp.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private Bitmap bitmap, temp;
    private static ImageView image;
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
        temp = bitmap;
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
        image.setImageBitmap(temp);

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
                Bitmap sticker = stickerView.getBitmap();
                temp = mergeBitmap(temp, sticker);
                image.setImageBitmap(temp);

//                image.buildDrawingCache();
//                Bitmap bmap = image.getDrawingCache();
//                image.setImageBitmap(bmap);

                Save saveFile = new Save();
                saveFile.SaveImage(getApplicationContext(), temp);
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
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
                temp = rotated;
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
        result = Bitmap.createBitmap(front.getWidth(), front.getHeight(), front.getConfig());
        canvas.setBitmap(result);
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

    public Bitmap mergeBitmap(Bitmap back, Bitmap front){
        Bitmap bitmap = null;
        try {

            bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(bitmap);
            Resources res = resources;

            Drawable drawable1 = new BitmapDrawable(back);
            Drawable drawable2 = new BitmapDrawable(front);

            int backLeftRight = (c.getWidth() - back.getWidth())/2;
            int backUpDown = (c.getHeight() - back.getHeight())/2;
            Log.e("left-right",back.getScaledWidth(c)/2 + back.getWidth()/2+"");
            Log.e("up-down", back.getScaledHeight(c) / 2 + back.getHeight() / 2 + "");


            drawable1.setBounds(100, 100, 400, 400);
            drawable2.setBounds(150, 150, 350, 350);
            drawable1.draw(c);
            drawable2.draw(c);


        } catch (Exception e) {
        }
        return bitmap;
    }

    public static void setSticker(int id) {
        Bitmap StickerBitmap = BitmapFactory.decodeResource(resources, id);
        stickerView.setWaterMark(StickerBitmap);
    }

}
