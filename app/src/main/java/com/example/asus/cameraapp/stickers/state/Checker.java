package com.example.asus.cameraapp.stickers.state;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.StickerView;

/**
 * Created by Asus on 1/6/2559.
 */
public class Checker {
    private StickerView stickerView;
    private float mReversalHorWidth,mReversalHorHeight,mReversalVerWidth,mReversalVerHeight;
    private float mControllerWidth, mControllerHeight, mDeleteWidth, mDeleteHeight;
    private float[] mPoints;

    public Checker(StickerView stickerView){
        this.stickerView = stickerView;
        init();
    }

    public void init(){
        mReversalHorWidth = stickerView.getmReversalHorWidth();
        mReversalHorHeight = stickerView.getmReversalHorHeight();
        mReversalVerWidth = stickerView.getmReversalVerWidth();
        mReversalVerHeight = stickerView.getmReversalVerHeight();
        mControllerWidth = stickerView.getmControllerWidth();
        mControllerHeight = stickerView.getmControllerHeight();
        mDeleteWidth = stickerView.getmDeleteWidth();
        mDeleteHeight = stickerView.getmDeleteHeight();
        mPoints = stickerView.getmPoints();
    }

    public boolean isInController(float x, float y) {
        int position = 4;
        //while (position < 8) {
        float rx = mPoints[position];
        float ry = mPoints[position + 1];
        RectF rectF = new RectF(rx - mControllerWidth / 2,
                ry - mControllerHeight / 2,
                rx + mControllerWidth / 2,
                ry + mControllerHeight / 2);
        if (rectF.contains(x, y)) {
            return true;
        }
        //   position += 2;
        //}
        return false;

    }

    public boolean isInDelete(float x, float y) {
        int position = 0;
        //while (position < 8) {
        float rx = mPoints[position];
        float ry = mPoints[position + 1];
        RectF rectF = new RectF(rx - mDeleteWidth / 2,
                ry - mDeleteHeight / 2,
                rx + mDeleteWidth / 2,
                ry + mDeleteHeight / 2);
        if (rectF.contains(x, y)) {
            return true;
        }
        //   position += 2;
        //}
        return false;

    }
    //判断点击区域是否在水平反转按钮区域内
    public boolean isInReversalHorizontal(float x,float y){
        int position = 2;
        float rx = mPoints[position];
        float ry = mPoints[position+1];

        RectF rectF = new RectF(rx - mReversalHorWidth/2,ry-mReversalHorHeight/2,rx+mReversalHorWidth/2,ry+mReversalHorHeight/2);
        if (rectF.contains(x,y))
            return true;

        return false;

    }
    //判断点击区域是否在垂直反转按钮区域内
    public boolean isInReversalVertical(float x,float y){
        int position = 6;
        float rx = mPoints[position];
        float ry = mPoints[position+1];

        RectF rectF = new RectF(rx - mReversalVerWidth/2,ry - mReversalVerHeight/2,rx + mReversalVerWidth/2,ry+mReversalVerHeight/2);
        if (rectF.contains(x,y))
            return true;
        return false;
    }
}
