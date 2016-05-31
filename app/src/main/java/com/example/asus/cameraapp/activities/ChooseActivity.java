package com.example.asus.cameraapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.stickers.StickerAdapter;
import com.example.asus.cameraapp.stickers.StickerRow;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {
    private ArrayList<StickerRow> rows;
    private ListView stickerGallery;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        stickerGallery = (ListView) findViewById(R.id.lvdata);
        rows = new ArrayList<>();
        initComponents();
        stickerGallery.setAdapter(new StickerAdapter(getBaseContext(), rows,this));
    }
    private void initComponents(){
        StickerRow row1 = new StickerRow (R.drawable.stk01,R.drawable.stk02,R.drawable.stk03,R.drawable.stk04);
        StickerRow row2 = new StickerRow (R.drawable.stk05,R.drawable.stk06,R.drawable.stk07,R.drawable.stk08);
        StickerRow row3 = new StickerRow (R.drawable.stk09,R.drawable.stk10,R.drawable.stk11,R.drawable.stk12);
        StickerRow row4 = new StickerRow (R.drawable.stk13,R.drawable.stk14,R.drawable.stk15,R.drawable.stk16);
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
    }


}
