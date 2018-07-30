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

public class MultiTouchView1 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);

    Bitmap bitmap;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float offsetX;
    float offsetY;
    float oldOffsetX;
    float oldOffsetY;
    private float downX;
    private float downY;
    int controllingIndex;

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                controllingIndex = 0;
                downX = event.getX();
                downY = event.getY();
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                controllingIndex = event.getActionIndex();
                downX = event.getX(controllingIndex);
                downY = event.getY(controllingIndex);
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                controllingIndex = 0;
                int preControllingIndex = event.getActionIndex() == 0 ? 1 : 0;
                downX = event.getX(preControllingIndex);
                downY = event.getY(preControllingIndex);
                oldOffsetX = offsetX;
                oldOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = oldOffsetX + event.getX(controllingIndex) - downX;
                offsetY = oldOffsetY + event.getY(controllingIndex) - downY;
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