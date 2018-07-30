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

public class MultiTouchView3 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(300);
    Bitmap bitmap;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float[] point1 = new float[2];
    float[] point2 = new float[2];

    public MultiTouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        paint.setStrokeWidth(Utils.dp2px(20));
        paint.setStrokeCap(Paint.Cap.ROUND);
        point1[0] = 0;
        point1[1] = 0;
        point2[0] = 0;
        point2[1] = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                System.out.println("ACTIVE: " + event.getActionIndex() + "-" + event.getPointerId(event.getActionIndex()));
                point1[0] = event.getX(0);
                point1[1] = event.getY(0);
                if (event.getPointerCount() > 1) {
                    point2[0] = event.getX(1);
                    point2[1] = event.getY(1);
                }
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPoint(point1[0], point1[1], paint);
        canvas.drawPoint(point2[0], point2[1], paint);
    }
}
