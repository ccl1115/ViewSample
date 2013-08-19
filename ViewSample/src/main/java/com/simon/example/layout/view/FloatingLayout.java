package com.simon.example.layout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Simon Yu
 */
public class FloatingLayout extends LinearLayout {

    public FloatingLayout(Context context) {
        this(context, null, 0);
    }

    public FloatingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int orientation = getOrientation();
        if (orientation == HORIZONTAL) {
            layoutHorizontal();
        } else if (orientation == VERTICAL) {
            layoutVertical();
        }
    }

    private void layoutHorizontal() {

        final int count = getChildCount();
        final int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        int left = getPaddingLeft();
        int top = getPaddingTop();

        View child;
        MarginLayoutParams mlp;
        int cW, cH;
        int maxRowHeight = 0;

        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child != null) {
                mlp = (MarginLayoutParams) child.getLayoutParams();
                if (mlp != null) {
                    // 开始其中一个子View的布局

                    // 预先计算宽高，如果大于当前剩下的空间则从新的一行开始布局。

                    cW = mlp.leftMargin + mlp.rightMargin + child.getMeasuredWidth();
                    cH = mlp.topMargin + mlp.bottomMargin + child.getMeasuredHeight();

                    if (width - left < cW) {
                        // 空间不足，从新的一行开始布局
                        left = getPaddingLeft();
                        top += maxRowHeight;
                    }

                    // 布局的时候要考虑到子View的margin，padding是由子View自己控制的。
                    child.layout(left + mlp.leftMargin,
                            top + mlp.topMargin,
                            left + mlp.leftMargin + child.getMeasuredWidth(),
                            top + mlp.topMargin + child.getMeasuredHeight());

                    left += cW;
                    maxRowHeight = cH > maxRowHeight ? cH : maxRowHeight;

                }
            }
        }
    }

    private void layoutVertical() {

        final int count = getChildCount();
        final int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        int left = getPaddingLeft();
        int top = getPaddingTop();

        View child;
        MarginLayoutParams mlp;
        int cW, cH;
        int maxColumnWidth = 0;

        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child != null) {
                mlp = (MarginLayoutParams) child.getLayoutParams();
                if (mlp != null) {

                    cW = mlp.leftMargin + mlp.rightMargin + child.getMeasuredWidth();
                    cH = mlp.topMargin + mlp.bottomMargin + child.getMeasuredHeight();

                    if (height - top < cH) {
                        left += maxColumnWidth;
                        top = getPaddingTop();
                    }

                    child.layout(left + mlp.leftMargin,
                            top + mlp.topMargin,
                            left + mlp.leftMargin + child.getMeasuredWidth(),
                            top + mlp.topMargin + child.getMeasuredHeight());

                    top += cH;
                    maxColumnWidth = cW > maxColumnWidth ? cW : maxColumnWidth;
                }
            }
        }
    }
}
