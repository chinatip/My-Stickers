package com.example.asus.cameraapp.stickers;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.activities.AddStickerActivity;

public class StickerAdapter extends BaseAdapter {
    private ArrayList<StickerRow> list;
    private LayoutInflater mInflater;

    public StickerAdapter(Context context, ArrayList<StickerRow> list) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_sticker, null);
            holder = new ViewHolder();
            holder.iv1 = (ImageView) convertView.findViewById(R.id.iv1);
            holder.iv2 = (ImageView) convertView.findViewById(R.id.iv2);
            holder.iv3 = (ImageView) convertView.findViewById(R.id.iv3);
            holder.iv4 = (ImageView) convertView.findViewById(R.id.iv4);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        final int s1 = list.get(position).getSticker1();
        final int s2 = list.get(position).getSticker2();
        final int s3 = list.get(position).getSticker3();
        final int s4 = list.get(position).getSticker4();

        holder.iv1.setImageResource(s1);
        holder.iv2.setImageResource(s2);
        holder.iv3.setImageResource(s3);
        holder.iv4.setImageResource(s4);

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStickerActivity.setSticker(s1);
            }
        });
        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStickerActivity.setSticker(s2);
            }
        });
        holder.iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStickerActivity.setSticker(s3);
            }
        });
        holder.iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStickerActivity.setSticker(s4);
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        private ImageView iv1, iv2, iv3, iv4;
    }
}
