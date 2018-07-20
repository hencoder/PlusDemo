package com.hencoder.a07.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.a07.Utils;

public class AnimatorView extends View {
    private int color = Color.RED;
    private float radius = Utils.dpToPixel(50);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public AnimatorView(Context context) {
        super(context);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(color);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}
