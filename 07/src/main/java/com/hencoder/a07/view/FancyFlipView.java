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

public class FancyFlipView extends View {
    private static final float IMAGE_SIZE = Utils.dpToPixel(150);
    private static final float PADDING = Utils.dpToPixel(50);
    private static final float FLIP_ANGLE = 45;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();

    private float leftFlip;
    private float rightFlip;
    private float flipRotation;

    public float getLeftFlip() {
        return leftFlip;
    }

    public void setLeftFlip(float leftFlip) {
        this.leftFlip = leftFlip;
        invalidate();
    }

    public float getRightFlip() {
        return rightFlip;
    }

    public void setRightFlip(float rightFlip) {
        this.rightFlip = rightFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public FancyFlipView(Context context) {
        super(context);
    }

    public FancyFlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FancyFlipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_SIZE);
        camera.setLocation(0, 0, Utils.getCameraZ());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float offset = IMAGE_SIZE / 2 + PADDING;

        // 左半边
        camera.save();
        camera.rotateY(leftFlip * FLIP_ANGLE);

        canvas.save();
        canvas.translate(offset, offset);
        canvas.rotate(-flipRotation);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0, -IMAGE_SIZE, -IMAGE_SIZE, IMAGE_SIZE);
        canvas.rotate(flipRotation);
        canvas.translate(-offset, -offset);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        canvas.restore();

        camera.restore();

        // 右半边
        camera.save();
        camera.rotateY(- rightFlip * FLIP_ANGLE);

        canvas.save();
        canvas.translate(offset, offset);
        canvas.rotate(-flipRotation);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0, -IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE);
        canvas.rotate(flipRotation);
        canvas.translate(-offset, -offset);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        canvas.restore();

        camera.restore();


    }
}
