package com.example.asus.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AddStickerActivity extends AppCompatActivity {
    private Bitmap bitmap;
    private ImageView image;
    private static ArrayList<Bitmap> stickers = new ArrayList<>();
    public static ImageView sticker1;
    private ImageButton homeButton;
    private ImageButton addButton;
    private ImageButton saveButton;
    private ImageButton shareButton;
    private ImageButton reverseButton;
    private ImageButton infoButton;
    private ViewGroup mRrootLayout;
    private int _xDelta;
    private int _yDelta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsticker);

        bitmap = getIntent().getParcelableExtra("bitmap");

        mRrootLayout = (ViewGroup) findViewById(R.id.root);
        sticker1 = (ImageView) findViewById(R.id.sticker1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        sticker1.setLayoutParams(layoutParams);
        sticker1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = -250;
                        layoutParams.bottomMargin = -250;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mRrootLayout.invalidate();
                return true;
            }
        });
        initComponents();
    }

    private void initComponents() {
        image = (ImageView)findViewById(R.id.img_image);
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
                bitmap = overlay(bitmap, stickers.get(0));
                image.setImageBitmap(bitmap);
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
                Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        infoButton = (ImageButton) findViewById(R.id.btn_info);
        infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(AddStickerActivity.this, InfoPopUp.class));
            }
        });
    }

    public static void setSticker(Bitmap bitmap) {
        sticker1.setImageBitmap(bitmap);
        stickers.add(bitmap);
    }

//    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
//        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
////        Bitmap scaledBitmap = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, filter);
//        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp1, new Matrix(), null);
//        canvas.drawBitmap(bmp2, 0, 0, null);
//        return bmOverlay;
//    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        bmOverlay.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator
                + "test.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmOverlay;
    }


}
