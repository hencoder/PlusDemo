package com.hencoder.a09.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TagLayout extends ViewGroup {
    Rect[] childrenBounds;

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int lineWidthUsed = 0;
        int heightUsed = 0;
        int childCount = getChildCount();
        int maxHeight = 0;

        if (childrenBounds == null) {
            childrenBounds = new Rect[childCount];
        } else if (childrenBounds.length < childCount) {
            childrenBounds = Arrays.copyOf(childrenBounds, childCount);
        }

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds[i];
            measureChildWithMargins(child, widthMeasureSpec, lineWidthUsed, heightMeasureSpec, heightUsed);

            // 判断另起一行
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED &&
                    child.getMeasuredWidth() + lineWidthUsed + getPaddingStart() + getPaddingEnd() > MeasureSpec.getSize(widthMeasureSpec)) {
                lineWidthUsed = 0;
                heightUsed += maxHeight;
                maxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, lineWidthUsed, heightMeasureSpec, heightUsed);
            }

            if (childBounds == null) {
                childBounds = childrenBounds[i] = new Rect();
            }
            childBounds.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            lineWidthUsed += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        int width = widthUsed;
        int height = heightUsed + maxHeight;

        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0),
                resolveSizeAndState(height,  heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect childBounds = childrenBounds[i];
            child.layout(childBounds.left, childBounds.top, childBounds.right, childBounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
