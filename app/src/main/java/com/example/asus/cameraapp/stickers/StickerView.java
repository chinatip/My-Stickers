package com.example.asus.cameraapp.stickers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.asus.cameraapp.R;
import com.example.asus.cameraapp.stickers.state.CancleState;
import com.example.asus.cameraapp.stickers.state.DownState;
import com.example.asus.cameraapp.stickers.state.MotionState;
import com.example.asus.cameraapp.stickers.state.MoveState;
import com.example.asus.cameraapp.stickers.state.UpState;


/**
 * Created by sam on 14-8-14.
 */
public class StickerView extends View {

    private float mScaleSize;

    public static final float MAX_SCALE_SIZE = 3.2f;
    public static final float MIN_SCALE_SIZE = 0.6f;


    private float[] mOriginPoints;
    private float[] mPoints;
    private RectF mOriginContentRect;
    private RectF mContentRect;
    private RectF mViewRect;

    private float mLastPointX, mLastPointY;

    private Bitmap mBitmap;
    private Bitmap mControllerBitmap, mDeleteBitmap;
    private Bitmap mReversalHorBitmap,mReversalVerBitmap;//水平反转和垂直反转bitmap
    private Matrix mMatrix;
    private Paint mPaint, mBorderPaint;
    private float mControllerWidth, mControllerHeight, mDeleteWidth, mDeleteHeight;
    private float mReversalHorWidth,mReversalHorHeight,mReversalVerWidth,mReversalVerHeight;
    private boolean mInController, mInMove;
    private boolean mInReversalHorizontal,mInReversalVertical;

    private boolean mDrawController = true;
    //private boolean mCanTouch;
    private float mStickerScaleSize = 1.0f;

    private OnStickerDeleteListener mOnStickerDeleteListener;

    public StickerView(Context context) {
        this(context, null);
    }

    public StickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4.0f);
        mPaint.setColor(Color.WHITE);

        mBorderPaint = new Paint(mPaint);
        mBorderPaint.setColor(Color.parseColor("#B2ffffff"));
        mBorderPaint.setShadowLayer(DisplayUtil.dip2px(getContext(), 2.0f), 0, 0, Color.parseColor("#33000000"));

        mControllerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sticker_control);
        mControllerWidth = mControllerBitmap.getWidth();
        mControllerHeight = mControllerBitmap.getHeight();

        mDeleteBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sticker_delete);
        mDeleteWidth = mDeleteBitmap.getWidth();
        mDeleteHeight = mDeleteBitmap.getHeight();

        mReversalHorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sticker_reversal_horizontal);
        mReversalHorWidth = mReversalHorBitmap.getWidth();
        mReversalHorHeight = mReversalHorBitmap.getHeight();

        mReversalVerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sticker_reversal_vertical);
        mReversalVerWidth = mReversalVerBitmap.getWidth();
        mReversalVerHeight = mReversalVerBitmap.getHeight();

    }

    public void setWaterMark(@NonNull Bitmap bitmap) {
        mBitmap = bitmap;
        mStickerScaleSize = 1.0f;


        setFocusable(true);
        try {


            float px = mBitmap.getWidth();
            float py = mBitmap.getHeight();


            //mOriginPoints = new float[]{px, py, px + bitmap.getWidth(), py, bitmap.getWidth() + px, bitmap.getHeight() + py, px, py + bitmap.getHeight()};
            mOriginPoints = new float[]{0, 0, px, 0, px, py, 0, py, px / 2, py / 2};
            mOriginContentRect = new RectF(0, 0, px, py);
            mPoints = new float[10];
            mContentRect = new RectF();

            mMatrix = new Matrix();
            float transtLeft = ((float)DisplayUtil.getDisplayWidthPixels(getContext()) - mBitmap.getWidth()) / 2;
            float transtTop = ((float)DisplayUtil.getDisplayWidthPixels(getContext()) - mBitmap.getHeight()) / 2;

            mMatrix.postTranslate(transtLeft, transtTop);

        } catch (Exception e) {
            e.printStackTrace();
        }
        postInvalidate();

    }

    public Matrix getMarkMatrix() {
        return mMatrix;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null || mMatrix == null) {
            return;
        }

        mMatrix.mapPoints(mPoints, mOriginPoints);

        mMatrix.mapRect(mContentRect, mOriginContentRect);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
        if (mDrawController && isFocusable()) {
            canvas.drawLine(mPoints[0], mPoints[1], mPoints[2], mPoints[3], mBorderPaint);
            canvas.drawLine(mPoints[2], mPoints[3], mPoints[4], mPoints[5], mBorderPaint);
            canvas.drawLine(mPoints[4], mPoints[5], mPoints[6], mPoints[7], mBorderPaint);
            canvas.drawLine(mPoints[6], mPoints[7], mPoints[0], mPoints[1], mBorderPaint);
            canvas.drawBitmap(mControllerBitmap, mPoints[4] - mControllerWidth / 2, mPoints[5] - mControllerHeight / 2, mBorderPaint);
            canvas.drawBitmap(mDeleteBitmap, mPoints[0] - mDeleteWidth / 2, mPoints[1] - mDeleteHeight / 2, mBorderPaint);
            canvas.drawBitmap(mReversalHorBitmap,mPoints[2]-mReversalHorWidth/2,mPoints[3]-mReversalVerHeight/2,mBorderPaint);
            canvas.drawBitmap(mReversalVerBitmap,mPoints[6]-mReversalVerWidth/2,mPoints[7]-mReversalVerHeight/2,mBorderPaint);
        }
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mDrawController = false;
        draw(canvas);
        mDrawController = true;
        canvas.save();
        return bitmap;
    }

    public void setShowDrawController(boolean show) {
        mDrawController = show;
    }

    private boolean mInDelete = false;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isFocusable()) {
            return super.dispatchTouchEvent(event);
        }
        if (mViewRect == null) {
            mViewRect = new RectF(0f, 0f, getMeasuredWidth(), getMeasuredHeight());
        }
        float x = event.getX();
        float y = event.getY();
        MotionState state;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                state = new DownState();
                state.dispatchTouchEvent(event, this);
                break;
            case MotionEvent.ACTION_UP:
                state = new UpState();
                state.dispatchTouchEvent(event, this);
                break;
            case MotionEvent.ACTION_CANCEL:
                state = new CancleState();
                state.dispatchTouchEvent(event, this);
                break;
            case MotionEvent.ACTION_MOVE:
                state = new MoveState();
                state.dispatchTouchEvent(event, this);


                return true;

        }
        return true;
    }

    public interface OnStickerDeleteListener {
        public void onDelete();
    }

    public void setOnStickerDeleteListener(OnStickerDeleteListener listener) {
        mOnStickerDeleteListener = listener;
    }

    public float getmScaleSize() {
        return mScaleSize;
    }

    public void setmScaleSize(float mScaleSize) {
        this.mScaleSize = mScaleSize;
    }

    public static float getMaxScaleSize() {
        return MAX_SCALE_SIZE;
    }

    public static float getMinScaleSize() {
        return MIN_SCALE_SIZE;
    }

    public float[] getmOriginPoints() {
        return mOriginPoints;
    }

    public void setmOriginPoints(float[] mOriginPoints) {
        this.mOriginPoints = mOriginPoints;
    }

    public float[] getmPoints() {
        return mPoints;
    }

    public void setmPoints(float[] mPoints) {
        this.mPoints = mPoints;
    }

    public RectF getmOriginContentRect() {
        return mOriginContentRect;
    }

    public void setmOriginContentRect(RectF mOriginContentRect) {
        this.mOriginContentRect = mOriginContentRect;
    }

    public RectF getmContentRect() {
        return mContentRect;
    }

    public void setmContentRect(RectF mContentRect) {
        this.mContentRect = mContentRect;
    }

    public RectF getmViewRect() {
        return mViewRect;
    }

    public void setmViewRect(RectF mViewRect) {
        this.mViewRect = mViewRect;
    }

    public float getmLastPointX() {
        return mLastPointX;
    }

    public void setmLastPointX(float mLastPointX) {
        this.mLastPointX = mLastPointX;
    }

    public float getmLastPointY() {
        return mLastPointY;
    }

    public void setmLastPointY(float mLastPointY) {
        this.mLastPointY = mLastPointY;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public Bitmap getmControllerBitmap() {
        return mControllerBitmap;
    }

    public void setmControllerBitmap(Bitmap mControllerBitmap) {
        this.mControllerBitmap = mControllerBitmap;
    }

    public Bitmap getmDeleteBitmap() {
        return mDeleteBitmap;
    }

    public void setmDeleteBitmap(Bitmap mDeleteBitmap) {
        this.mDeleteBitmap = mDeleteBitmap;
    }

    public Bitmap getmReversalHorBitmap() {
        return mReversalHorBitmap;
    }

    public void setmReversalHorBitmap(Bitmap mReversalHorBitmap) {
        this.mReversalHorBitmap = mReversalHorBitmap;
    }

    public Bitmap getmReversalVerBitmap() {
        return mReversalVerBitmap;
    }

    public void setmReversalVerBitmap(Bitmap mReversalVerBitmap) {
        this.mReversalVerBitmap = mReversalVerBitmap;
    }

    public Matrix getmMatrix() {
        return mMatrix;
    }

    public void setmMatrix(Matrix mMatrix) {
        this.mMatrix = mMatrix;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public Paint getmBorderPaint() {
        return mBorderPaint;
    }

    public void setmBorderPaint(Paint mBorderPaint) {
        this.mBorderPaint = mBorderPaint;
    }

    public float getmControllerWidth() {
        return mControllerWidth;
    }

    public void setmControllerWidth(float mControllerWidth) {
        this.mControllerWidth = mControllerWidth;
    }

    public float getmControllerHeight() {
        return mControllerHeight;
    }

    public void setmControllerHeight(float mControllerHeight) {
        this.mControllerHeight = mControllerHeight;
    }

    public float getmDeleteWidth() {
        return mDeleteWidth;
    }

    public void setmDeleteWidth(float mDeleteWidth) {
        this.mDeleteWidth = mDeleteWidth;
    }

    public float getmDeleteHeight() {
        return mDeleteHeight;
    }

    public void setmDeleteHeight(float mDeleteHeight) {
        this.mDeleteHeight = mDeleteHeight;
    }

    public float getmReversalHorWidth() {
        return mReversalHorWidth;
    }

    public void setmReversalHorWidth(float mReversalHorWidth) {
        this.mReversalHorWidth = mReversalHorWidth;
    }

    public float getmReversalHorHeight() {
        return mReversalHorHeight;
    }

    public void setmReversalHorHeight(float mReversalHorHeight) {
        this.mReversalHorHeight = mReversalHorHeight;
    }

    public float getmReversalVerWidth() {
        return mReversalVerWidth;
    }

    public void setmReversalVerWidth(float mReversalVerWidth) {
        this.mReversalVerWidth = mReversalVerWidth;
    }

    public float getmReversalVerHeight() {
        return mReversalVerHeight;
    }

    public void setmReversalVerHeight(float mReversalVerHeight) {
        this.mReversalVerHeight = mReversalVerHeight;
    }

    public boolean ismInController() {
        return mInController;
    }

    public void setmInController(boolean mInController) {
        this.mInController = mInController;
    }

    public boolean ismInMove() {
        return mInMove;
    }

    public void setmInMove(boolean mInMove) {
        this.mInMove = mInMove;
    }

    public boolean ismInReversalHorizontal() {
        return mInReversalHorizontal;
    }

    public void setmInReversalHorizontal(boolean mInReversalHorizontal) {
        this.mInReversalHorizontal = mInReversalHorizontal;
    }

    public boolean ismInReversalVertical() {
        return mInReversalVertical;
    }

    public void setmInReversalVertical(boolean mInReversalVertical) {
        this.mInReversalVertical = mInReversalVertical;
    }

    public boolean ismDrawController() {
        return mDrawController;
    }

    public void setmDrawController(boolean mDrawController) {
        this.mDrawController = mDrawController;
    }

    public float getmStickerScaleSize() {
        return mStickerScaleSize;
    }

    public void setmStickerScaleSize(float mStickerScaleSize) {
        this.mStickerScaleSize = mStickerScaleSize;
    }

    public OnStickerDeleteListener getmOnStickerDeleteListener() {
        return mOnStickerDeleteListener;
    }

    public void setmOnStickerDeleteListener(OnStickerDeleteListener mOnStickerDeleteListener) {
        this.mOnStickerDeleteListener = mOnStickerDeleteListener;
    }

    public boolean ismInDelete() {
        return mInDelete;
    }

    public void setmInDelete(boolean mInDelete) {
        this.mInDelete = mInDelete;
    }

}
