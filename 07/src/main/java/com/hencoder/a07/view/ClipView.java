package com.hencoder.a07.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.a07.Utils;

public class ClipView extends View {
    private static final float IMAGE_SIZE = Utils.dpToPixel(150);
    private static final float CLIP_PADDING = Utils.dpToPixel(30);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Path path = new Path();

    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_SIZE);
        path.addCircle(IMAGE_SIZE / 2, IMAGE_SIZE / 2, IMAGE_SIZE / 2, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.clipOutPath(path);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }
}
