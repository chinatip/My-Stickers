package com.example.asus.cameraapp.stickers.state;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public class MoveState implements MotionState {
    public static final float MAX_SCALE_SIZE = 3.2f;
    public static final float MIN_SCALE_SIZE = 0.6f;
    private float mStickerScaleSize = 1.0f;
    private StickerView stickerView;
    private MotionEvent event;
    private boolean mInMove,mInController;
    private float[] mPoints;
    private Matrix mMatrix;
    private float mLastPointX, mLastPointY;
    private RectF mViewRect;
    float x,y;

    public void init() {
        x = event.getX();
        y = event.getY();
        mStickerScaleSize = stickerView.getmStickerScaleSize();
        mInMove = stickerView.ismInMove();
        mInController = stickerView.ismInController();
        mPoints = stickerView.getmPoints();
        mMatrix = stickerView.getmMatrix();
        mLastPointX = stickerView.getmLastPointX();
        mLastPointY = stickerView.getmLastPointY();
        mViewRect = stickerView.getmViewRect();
    }

    @Override
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView) {
        this.stickerView = stickerView;
        this.event = event;
        init();
        if (mInController) {

            mMatrix.postRotate(rotation(event), mPoints[8], mPoints[9]);
            float nowLenght = calculateLength(mPoints[0], mPoints[1]);
            float touchLenght = calculateLength(event.getX(), event.getY());
            if ((float)Math.sqrt((nowLenght - touchLenght) * (nowLenght - touchLenght)) > 0.0f) {
                float scale = touchLenght / nowLenght;
                float nowsc = mStickerScaleSize * scale;
                if (nowsc >= MIN_SCALE_SIZE && nowsc <= MAX_SCALE_SIZE) {
                    mMatrix.postScale(scale, scale, mPoints[8], mPoints[9]);
                    stickerView.setmStickerScaleSize(nowsc);
                }
            }

            stickerView.invalidate();
            stickerView.setmLastPointX(x);
            stickerView.setmLastPointY(y);
            return;

        }

        if (mInMove == true) { //拖动的操作
            float cX = x - mLastPointX;
            float cY = y - mLastPointY;
            stickerView.setmInController(false);
            //Log.i("MATRIX_OK", "ma_jiaodu:" + a(cX, cY));

            if ((float)Math.sqrt(cX * cX + cY * cY) > 2.0f  && canStickerMove(cX, cY)) {
                //Log.i("MATRIX_OK", "is true to move");
                mMatrix.postTranslate(cX, cY);
                stickerView.postInvalidate();
                stickerView.setmLastPointX(x);
                stickerView.setmLastPointY(y);
            }
            return;
        }
    }

    private boolean canStickerMove(float cx, float cy) {
        float px = cx + mPoints[8];
        float py = cy + mPoints[9];
        if (mViewRect.contains(px, py)) {
            return true;
        } else {
            return false;
        }
    }


    private float calculateLength(float x, float y) {
        float ex = x - mPoints[8];
        float ey = y - mPoints[9];
        return (float)Math.sqrt(ex * ex + ey * ey);
    }


    private float rotation(MotionEvent event) {
        float  originDegree = calculateDegree(mLastPointX, mLastPointY);
        float nowDegree = calculateDegree(event.getX(), event.getY());
        return nowDegree - originDegree;
    }

    private float calculateDegree(float x, float y) {
        double delta_x = x - mPoints[8];
        double delta_y = y - mPoints[9];
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

}
