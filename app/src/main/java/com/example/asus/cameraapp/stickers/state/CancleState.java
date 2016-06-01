package com.example.asus.cameraapp.stickers.state;

import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public class CancleState implements MotionState {
    @Override
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView) {
        stickerView.setmLastPointX(0);
        stickerView.setmLastPointY(0);
        stickerView.setmInController(false);
        stickerView.setmInMove(false);
        stickerView.setmInDelete(false);
        stickerView.setmInReversalHorizontal(false);
        stickerView.setmInReversalVertical(false);
    }


}
