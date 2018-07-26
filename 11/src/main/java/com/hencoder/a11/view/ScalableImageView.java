package com.hencoder.a11.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.hencoder.a11.Utils;

public class ScalableImageView extends View {
    private static final float IMAGE_SIZE = Utils.dp2px(200);
    private static final float SCALE_OVER_FACTOR = 2;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float imageWidth = IMAGE_SIZE;
    float imageHeight;
    Bitmap bitmap;
    float offsetX;
    float offsetY;
    float originalOffsetX;
    float originalOffsetY;
    float smallScale;
    float bigScale;
    boolean big;
    float scalingFraction;
    ObjectAnimator scalingAnimator;

    GestureDetector gestureDetector;
    GestureDetector.OnGestureListener gestureListener = new HenGestureListener();
    GestureDetector.OnDoubleTapListener doubleTapListener = new HenDoubleTapListener();
    Runnable flingRunner = new FlingRunner();
    OverScroller scroller;

    public ScalableImageView(Context context) {
        super(context);
        init(context);
    }

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        bitmap = Utils.getAvatar(getResources(), (int) imageWidth);
        imageHeight = bitmap.getHeight();

        gestureDetector = new GestureDetector(context, gestureListener);
        gestureDetector.setOnDoubleTapListener(doubleTapListener);
        scroller = new OverScroller(context);
    }

    public float getScalingFraction() {
        return scalingFraction;
    }

    public void setScalingFraction(float scalingFraction) {
        this.scalingFraction = scalingFraction;
        invalidate();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);

        originalOffsetX = (getWidth() - imageWidth) / 2;
        originalOffsetY = (getHeight() - imageHeight) / 2;

        if (imageWidth / imageHeight > (float) getWidth() / getHeight()) {
            smallScale = getWidth() / imageWidth;
            bigScale = getHeight() / imageHeight * SCALE_OVER_FACTOR;
        } else {
            smallScale = getHeight() / imageHeight;
            bigScale = getWidth() / imageWidth * SCALE_OVER_FACTOR;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX * scalingFraction, offsetY * scalingFraction);
        float scale = smallScale + (bigScale - smallScale) * scalingFraction;
        canvas.scale(scale, scale, getWidth() / 2, getHeight() / 2);
        canvas.translate(originalOffsetX, originalOffsetY);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    ObjectAnimator getScalingAnimator() {
        if (scalingAnimator == null) {
            scalingAnimator = ObjectAnimator.ofFloat(this, "scalingFraction", 0f, 1f);
            scalingAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation, boolean isReverse) {
                    if (isReverse) {
                        offsetX = offsetY = 0;
                    }
                }
            });
        }
        return scalingAnimator;
    }

    class HenGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent down, MotionEvent event, float distanceX, float distanceY) {
            if (big) {
                offsetX -= distanceX;
                offsetX = Math.min(offsetX, (imageWidth * bigScale - getWidth()) / 2);
                offsetX = Math.max(offsetX, - (imageWidth * bigScale - getWidth()) / 2);
                offsetY -= distanceY;
                offsetY = Math.min(offsetY, (imageHeight * bigScale - getHeight()) / 2);
                offsetY = Math.max(offsetY, - (imageHeight * bigScale - getHeight()) / 2);
                invalidate();
            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent down, MotionEvent event, float velocityX, float velocityY) {
            scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                    (int) (getWidth() - imageWidth * bigScale) / 2, (int) (imageWidth * bigScale - getWidth()) / 2,
                    (int) (getHeight() - imageHeight * bigScale) / 2, (int) (imageHeight * bigScale - getHeight()) / 2);
            postOnAnimation(flingRunner);
            return false;
        }
    }

    class HenDoubleTapListener implements GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                offsetX = getWidth() / 2 - e.getX();
                offsetY = getHeight() / 2 - e.getY();
                getScalingAnimator().start();
            } else {
                getScalingAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }

    class FlingRunner implements Runnable {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                offsetX = scroller.getCurrX();
                offsetY = scroller.getCurrY();
                invalidate();

                postOnAnimation(this);
            }
        }
    }
}