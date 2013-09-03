package com.simon.example.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Simon Yu
 */
public class SameCanvasLayout extends LinearLayout {
    private boolean mSet;
    private TextView mTextView;

    public SameCanvasLayout(Context context) {
        this(context, null, 0);
    }

    public SameCanvasLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SameCanvasLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTextView = new TextView(context);
        addView(mTextView);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!mSet) {
            mTextView.setText(String.valueOf(canvas.hashCode()));
            mSet = true;
        }
    }
}
