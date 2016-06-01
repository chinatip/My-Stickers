package com.example.asus.cameraapp.stickers.state;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public class DownState implements MotionState {
    private StickerView stickerView;
    private MotionEvent event;
    private Checker checker;
    private float mLastPointX, mLastPointY;
    private boolean mInController, mInMove;
    private boolean mInReversalHorizontal,mInReversalVertical;
    private boolean mInDelete;
    private RectF mContentRect;
    float x,y;

    public void init() {
        mLastPointX = stickerView.getmLastPointX();
        mLastPointY = stickerView.getmLastPointY();
        mInController = stickerView.ismInController();
        mInMove = stickerView.ismInMove();
        mInReversalHorizontal = stickerView.ismInReversalHorizontal();
        mInReversalVertical = stickerView.ismInReversalVertical();
        mInDelete = stickerView.ismInDelete();
        mContentRect = stickerView.getmContentRect();
        x = event.getX();
        y = event.getY();
    }

    @Override
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView) {
        this.stickerView = stickerView;
        this.event = event;
        checker = new Checker(stickerView);
        init();

        if (checker.isInController(x, y)) {
            stickerView.setmInController(true);
            stickerView.setmLastPointY(y);
            stickerView.setmLastPointX(x);
            return;
        }

        if (checker.isInDelete(x, y)) {
            stickerView.setmInDelete(true);
            return;
        }

        if(checker.isInReversalHorizontal(x, y)){
            stickerView.setmInReversalHorizontal(true);
            return;
        }

        if(checker.isInReversalVertical(x,y)){
            stickerView.setmInReversalVertical(true);
            return;
        }

        if (mContentRect.contains(x, y)) {
            stickerView.setmLastPointY(y);
            stickerView.setmLastPointX(x);
            stickerView.setmInMove(true);
        }
        return;
    }
}
