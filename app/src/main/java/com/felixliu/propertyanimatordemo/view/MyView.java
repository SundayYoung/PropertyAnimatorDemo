package com.felixliu.propertyanimatordemo.view;


import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/3/30 0030.
 * E-Mail：543441727@qq.com
 */

public class MyView extends View {

    private Drawable mDrawable;
    private Bitmap mBitmapDrawable;
    private PointF mPoint = new PointF(200, 200);
    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public void startAnimator() {
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mPoint.y, 2300 - mPoint.y);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PositionEvaluator(), mPoint);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                mPoint.y = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
//        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mPoint.x, mPoint.y, 100, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取view的宽高测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //保存测量高度
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mPoint.x = (int) event.getX();
                mPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }

    private class PositionEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            mPoint.x = 445 * fraction * 3;
            mPoint.y = 0.5f * 480 * (fraction * 3) * (fraction * 3);
            return mPoint;
        }
    }

}
