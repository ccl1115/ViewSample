package com.simon.example.layout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * @author yulu02
 */
public class CoverFlow extends ViewPager {
    private static final String TAG = "CoverFlow";

    public static final float PAGE_WIDTH = 0.5f;

    /**
     * We need add extra page to make the cover flow loop.
     * CoverFlow
     */
    public static final int PAGE_PADDING = 2;

    private int mAbsLeft;
    private int mWidth;
    private int mHeight;
    private int mPageWidth;
    private int mCurrLeftPos;
    private final int[] location = new int[2];

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CoverFlow(Context context) {
        this(context, null);
    }

    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOffscreenPageLimit(12);

        setOnPageChangeListener(new SimpleOnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int state) {
                // loop the cover flow
                if (state == SCROLL_STATE_IDLE) {
                    final int current = getCurrentItem();
                    if (current <= PAGE_PADDING - 1) {
                        setCurrentItem(getAdapter().getCount() - 1 - PAGE_PADDING, false);
                        invalidate();
                    } else if (current >= getAdapter().getCount() - PAGE_PADDING) {
                        setCurrentItem(PAGE_PADDING, false);
                        invalidate();
                    }
                }
            }
        });

        // For development stage
        DebugCoverFlowAdapter adapter = new DebugCoverFlowAdapter();
        setAdapter(adapter);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        setCurrentItem(adapter.getCount() >> 1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mPageWidth = (int) (mWidth * PAGE_WIDTH + 0.5f);
        mCurrLeftPos = (mWidth - mPageWidth) >> 1;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        final int[] location = new int[2];
        getLocationOnScreen(location);
        mAbsLeft = location[0];
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        ev.offsetLocation(- (mWidth * (1 - PAGE_WIDTH) / 2), 0);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        final long drawingTime = getDrawingTime();
        final int savedCount1 = canvas.save();
        final int dx = (int) (getWidth() * (1 - PAGE_WIDTH) / 2);
        canvas.translate(dx, 0);
        final int count = getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child == null) continue;
            child.getLocationOnScreen(location);
            final int left = location[0] - mAbsLeft;
            final float scale = calculateScale(left + dx);
            final int savedCount2 = canvas.save();
            canvas.scale(scale, scale, left + (mPageWidth >> 1) + getScrollX(), mHeight >> 1);
            drawChild(canvas, child, drawingTime);
            canvas.restoreToCount(savedCount2);
        }
        canvas.restoreToCount(savedCount1);
    }

    private float calculateScale(int left) {
        final int rel = abs(left - mCurrLeftPos);
        return (float) pow(1.3, -(rel / (float) mPageWidth));
    }

    /**
     * For debug only
     */
    class DebugCoverFlowAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 6 + PAGE_PADDING + PAGE_PADDING;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView im = new TextView(container.getContext());
            im.setBackgroundColor(Color.WHITE);
            if (position <= PAGE_PADDING - 1) {
                im.setText("" + (6 - PAGE_PADDING + position));
            } else if (position >= getCount() - PAGE_PADDING) {
                im.setText("" + (position - (6 + PAGE_PADDING)));
            } else {
                im.setText("" + (position - PAGE_PADDING));
            }
            container.addView(im);
            return im;
        }

        @Override
        public float getPageWidth(int position) {
            return PAGE_WIDTH;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
