package com.hencoder.a07.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.a07.Utils;

public class MorphView extends View {
    private static final float IMAGE_SIZE = Utils.dpToPixel(150);
    private static final float OFFSET_X = Utils.dpToPixel(200);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Matrix matrix = new Matrix();

    public MorphView(Context context) {
        super(context);
    }

    public MorphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MorphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = Utils.getAvatar(getResources(), (int) IMAGE_SIZE);
        matrix.postTranslate(OFFSET_X, 0);
        matrix.postRotate(-45, OFFSET_X + IMAGE_SIZE / 2, IMAGE_SIZE / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }
}
