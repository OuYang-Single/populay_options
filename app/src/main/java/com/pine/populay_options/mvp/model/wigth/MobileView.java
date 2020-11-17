package com.pine.populay_options.mvp.model.wigth;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MobileView extends ViewGroup {
    int width;
    int height;
    public MobileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MobileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MobileView(Context context) {
        super(context);
        width=  getWidth();
        height=  getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int fingerCount = event.getPointerCount();
        if (fingerCount > 1) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        } else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return super.onTouchEvent(event);

    }
}
