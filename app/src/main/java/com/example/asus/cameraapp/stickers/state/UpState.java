package com.example.asus.cameraapp.stickers.state;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.example.asus.cameraapp.stickers.DisplayUtil;
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
    private OnStickerDeleteListener mOnStickerDeleteListener;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    float x,y;
    private float[] mOriginPoints;
    private float[] mPoints;
    private RectF mOriginContentRect;
    private RectF mContentRect;

    public void init() {
        x = event.getX();
        y = event.getY();
        mInDelete = stickerView.ismInDelete();
        mInReversalHorizontal = stickerView.ismInReversalHorizontal();
        mInReversalVertical = stickerView.ismInReversalVertical();
        mBitmap = stickerView.getmBitmap();
        mMatrix = stickerView.getmMatrix();
        mOriginPoints = stickerView.getmOriginPoints();
        mPoints = stickerView.getmPoints();
        mOriginContentRect = stickerView.getmOriginContentRect();
        mContentRect = stickerView.getmContentRect();
    }
    @Override
    public void dispatchTouchEvent(MotionEvent event, StickerView stickerView) {
        this.stickerView = stickerView;
        this.event = event;
        checker = new Checker(stickerView);
        init();
        if (checker.isInDelete(x, y) && mInDelete) {
            doDeleteSticker();
            return;
        }
        if(checker.isInReversalHorizontal(x, y) && mInReversalHorizontal){
            doReversalHorizontal();
            return;
        }
        if (checker.isInReversalVertical(x, y) && mInReversalVertical){
            doReversalVertical();
            return;
        }
    }


    private void doDeleteSticker() {
        stickerView.setWaterMark(null);
        if (mOnStickerDeleteListener != null) {
            mOnStickerDeleteListener.onDelete();
        }
    }

    //图片水平反转
    private void doReversalHorizontal(){
        float[] floats = new float[] { -1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f };
        Matrix tmpMatrix = new Matrix();
        tmpMatrix.setValues(floats);
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
                mBitmap.getHeight(), tmpMatrix, true);
        stickerView.setmBitmap(mBitmap);
        stickerView.invalidate();
        stickerView.setmInReversalHorizontal(false);
    }
    //图片垂直反转
    private void doReversalVertical(){
        float[] floats = new float[] { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f };
        Matrix tmpMatrix = new Matrix();
        tmpMatrix.setValues(floats);
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
                mBitmap.getHeight(), tmpMatrix, true);
        stickerView.setmBitmap(mBitmap);
        stickerView.invalidate();
        stickerView.setmInReversalVertical(false);
    }

    public interface OnStickerDeleteListener {
        public void onDelete();
    }

    public void setOnStickerDeleteListener(OnStickerDeleteListener listener) {
        stickerView.setOnStickerDeleteListener((StickerView.OnStickerDeleteListener) listener);
    }

}
