package com.example.asus.cameraapp.stickers.state;

import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public interface MotionState {
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView);
}
