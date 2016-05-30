package com.example.asus.cameraapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ChooseActivity extends AppCompatActivity {
    private ImageButton skButton01;
    private ImageButton skButton02;
    private ImageButton skButton03;
    private ImageButton skButton04;
    private ImageButton skButton05;
    private ImageButton skButton06;
    private ImageButton skButton07;
    private ImageButton skButton08;
    private ImageButton skButton09;
    private ImageButton skButton10;
    private ImageButton skButton11;
    private ImageButton skButton12;
    private ImageButton skButton13;
    private ImageButton skButton14;
    private ImageButton skButton15;
    private ImageButton skButton16;
    private ImageButton skButton17;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        skButton01 = (ImageButton) findViewById(R.id.StickerButton01);
        skButton02 = (ImageButton) findViewById(R.id.StickerButton02);
        skButton03 = (ImageButton) findViewById(R.id.StickerButton03);
        skButton04 = (ImageButton) findViewById(R.id.StickerButton04);
        skButton05 = (ImageButton) findViewById(R.id.StickerButton05);
        skButton06 = (ImageButton) findViewById(R.id.StickerButton06);
        skButton07 = (ImageButton) findViewById(R.id.StickerButton07);
        skButton08 = (ImageButton) findViewById(R.id.StickerButton08);
        skButton09 = (ImageButton) findViewById(R.id.StickerButton09);
        skButton10 = (ImageButton) findViewById(R.id.StickerButton10);
        skButton11 = (ImageButton) findViewById(R.id.StickerButton11);
        skButton12 = (ImageButton) findViewById(R.id.StickerButton12);
        skButton13 = (ImageButton) findViewById(R.id.StickerButton13);
        skButton14 = (ImageButton) findViewById(R.id.StickerButton14);
        skButton15 = (ImageButton) findViewById(R.id.StickerButton15);
        skButton16 = (ImageButton) findViewById(R.id.StickerButton16);
        skButton17 = (ImageButton) findViewById(R.id.StickerButton17);

        initComponents();
    }
    private void initComponents(){
        skButton01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk01);
                finish();
            }
        });
        skButton02.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk02);
                finish();
            }
        });
        skButton03.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk03);
                finish();
            }
        });
        skButton04.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk04);
                finish();
            }
        });
        skButton05.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk05);
                finish();
            }
        });
        skButton06.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk06);
                finish();
            }
        });
        skButton07.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk07);
                finish();
            }
        });
        skButton08.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk08);
                finish();
            }
        });
        skButton09.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk09);
                finish();
            }
        });
        skButton10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk10);
                finish();
            }
        });
        skButton11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk11);
                finish();
            }
        });
        skButton12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk12);
                finish();
            }
        });
        skButton13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk13);
                finish();
            }
        });
        skButton14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk14);
                finish();
            }
        });
        skButton15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk15);
                finish();
            }
        });
        skButton16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk16);
                finish();
            }
        });
        skButton17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddStickerActivity.setSticker(R.drawable.stk17);
                finish();
            }
        });
    }
}
