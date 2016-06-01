package com.example.asus.cameraapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.adapters.StickerAdapter;
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
        StickerRow row5 = new StickerRow (R.drawable.stk17,R.drawable.stk18,R.drawable.stk19,R.drawable.stk20);
        StickerRow row6 = new StickerRow (R.drawable.stk21,R.drawable.stk22,R.drawable.stk23,R.drawable.stk24);
        StickerRow row7 = new StickerRow (R.drawable.stk25,R.drawable.stk26,R.drawable.stk27,R.drawable.stk28);
        StickerRow row8 = new StickerRow (R.drawable.stk29,R.drawable.stk30,R.drawable.stk31,R.drawable.stk32);
        StickerRow row9 = new StickerRow (R.drawable.stk33,R.drawable.stk34,R.drawable.stk35,R.drawable.stk36);
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);
        rows.add(row7);
        rows.add(row8);
        rows.add(row9);
    }


}
