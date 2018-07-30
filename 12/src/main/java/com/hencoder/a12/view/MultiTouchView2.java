package com.hencoder.a12.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hencoder.a12.Utils;

public class MultiTouchView2 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    Bitmap bitmap;

    float offsetX;
    float offsetY;
    float oldOffsetX;
    float oldOffsetY;

    float downX;
    float downY;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;

        boolean pointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        int skippedPointer = pointerUp ? event.getActionIndex() : -1; // 计算焦点时跳过抬起的手指
        int count = event.getPointerCount();
        for (int i = 0; i < count; i++) {
            if (skippedPointer != i) { // 跳过抬起的手指
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        int divisor = pointerUp ? count - 1 : count; // 如果是 ACTION_POINTER_UP 事件，除数减一
        float focusX = sumX / divisor;
        float focusY = sumY / divisor;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = focusX;
                downY = focusY;
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                downX = focusX;
                downY = focusY;
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                downX = focusX;
                downY = focusY;
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = focusX - downX;
                float deltaY = focusY - downY;
                offsetX = oldOffsetX + deltaX;
                offsetY = oldOffsetY + deltaY;
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }
}
