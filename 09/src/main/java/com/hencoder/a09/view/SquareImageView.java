package com.hencoder.a09.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.hencoder.a09.Utils;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int size = Math.min(width, height);

        setMeasuredDimension(size, size);
    }
}
