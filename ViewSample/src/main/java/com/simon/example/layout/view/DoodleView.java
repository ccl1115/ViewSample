package com.simon.example.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleView extends View {

    private final Path mDoodlePath;
    private final Paint mPaint;

    public DoodleView(Context context) {
        this(context, null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDoodlePath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFF000000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = MotionEventCompat.getX(event, 0);
        final float y = MotionEventCompat.getY(event, 0);
        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDoodlePath.reset();
                mDoodlePath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mDoodlePath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mDoodlePath, mPaint);
    }
}
