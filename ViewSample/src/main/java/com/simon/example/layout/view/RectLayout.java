package com.simon.example.layout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Simon Yu
 */
public class RectLayout extends FrameLayout {
    public RectLayout(Context context) {
        this(context, null, 0);
    }

    public RectLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = widthMeasureSpec & ~(0x3 << 30);
        final int heightSize = heightMeasureSpec & ~(0x3 << 30);

        if (widthSize > heightSize) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }
    }
}
