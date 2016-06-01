package com.example.asus.cameraapp.stickers.state;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public class UpState implements MotionState {
    private Checker checker;
    private MotionEvent event;
    private StickerView stickerView;
    private boolean mInDelete;
    private boolean mInReversalHorizontal,mInReversalVertical;
    float x,y;

    public void init() {
        x = event.getX();
        y = event.getY();
        mInDelete = stickerView.ismInDelete();
        mInReversalHorizontal = stickerView.ismInReversalHorizontal();
        mInReversalVertical = stickerView.ismInReversalVertical();
    }
    @Override
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView) {
        this.stickerView = stickerView;
        this.event = event;
        checker = new Checker(stickerView);
        init();
        if (checker.isInDelete(x, y) && mInDelete) {
            stickerView.doDeleteSticker();
            return;
        }
        if(checker.isInReversalHorizontal(x, y) && mInReversalHorizontal){
            stickerView.doReversalHorizontal();
            return;
        }
        if (checker.isInReversalVertical(x, y) && mInReversalVertical){
            stickerView.doReversalVertical();
            return;
        }
    }

}
