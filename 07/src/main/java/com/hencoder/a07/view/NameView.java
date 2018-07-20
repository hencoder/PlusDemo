package com.hencoder.a07.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.a07.Utils;

public class NameView extends View {
    private static final float TEXT_SIZE = Utils.dpToPixel(50);

    String name = "1-001-京哈线";
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        invalidate();
    }

    public NameView(Context context) {
        super(context);
    }

    public NameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(TEXT_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(name, getWidth() / 2, getHeight() / 2, paint);
    }
}
