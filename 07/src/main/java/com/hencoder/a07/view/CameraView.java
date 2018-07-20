package com.hencoder.a07.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.a07.Utils;

public class CameraView extends View {
    private static final float IMAGE_SIZE = Utils.dpToPixel(250);
    private static final float OFFSET = Utils.dpToPixel(80);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_SIZE);
        camera.rotateX(45);
        camera.setLocation(0, 0, Utils.getCameraZ()); // 8 * 72 = 576
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate( (OFFSET + IMAGE_SIZE / 2),  (OFFSET + IMAGE_SIZE / 2));
        camera.applyToCanvas(canvas);
        canvas.translate(- (OFFSET + IMAGE_SIZE / 2), - (OFFSET + IMAGE_SIZE / 2));
        canvas.drawBitmap(bitmap, OFFSET, OFFSET, paint);
        canvas.restore();
    }
}
