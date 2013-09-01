package com.simon.example.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Simon Yu
 */
public class NoUpdateLayout extends ViewGroup {

    private TextView mTextView;

    public NoUpdateLayout(Context context) {
        this(context, null, 0);
    }

    public NoUpdateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoUpdateLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTextView = new TextView(context);
        mTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mTextView.setText("I'm not gonna update");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mTextView.measure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mTextView.getMeasuredWidth(), mTextView.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mTextView.layout(0, 0, r - l, b - t);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        drawChild(canvas, mTextView, getDrawingTime());
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }
}
