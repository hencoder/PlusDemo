package com.hencoder.a08;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    private static final String TAG = MaterialEditText.class.getSimpleName();

    private static final float LABEL_SIZE = Utils.dp2Px(12);
    private static final float LABEL_PADDING = Utils.dp2Px(8);
    private static final float LABEL_OFFSET = Utils.dp2Px(4);
    private static final float LABEL_OFFSET_Y = Utils.dp2Px(12);
    private static final float TOTAL_EXTRA_OFFSET = Utils.dp2Px(16);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    boolean labelShown = false;
    float labelFraction = 0;
    private boolean useFloatingLabel = true;
    int colorAccent;

    ObjectAnimator animator;

    public MaterialEditText(Context context) {
        super(context);
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        TypedArray typedArrayColorAccent = context.obtainStyledAttributes(attrs, new int[]{R.attr.colorAccent});
        colorAccent = typedArrayColorAccent.getColor(0, Color.BLACK);

        Log.d(TAG, "MaterialEditText: " + Integer.toHexString(colorAccent));

        boolean useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        typedArray.recycle();
        typedArrayColorAccent.recycle();

        if (useFloatingLabel) {
            this.useFloatingLabel = true;
        } else {
            this.useFloatingLabel = false;
        }
    }

    {
        setBackground(null);
        setPadding(getPaddingLeft() + 200, (int) (getPaddingTop() + LABEL_PADDING + LABEL_SIZE), getPaddingRight(), getPaddingBottom());
        paint.setTextSize(LABEL_SIZE);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !labelShown) {
                    labelShown = true;
                    getAnimator().start();
                } else if (s.length() == 0 && labelShown) {
                    labelShown = false;
                    getAnimator().reverse();
                }
            }
        });
    }

    private ObjectAnimator getAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this, "labelFraction", 0, 1);
        }
        return animator;
    }

    public float getLabelFraction() {
        return labelFraction;
    }

    public void setLabelFraction(float labelFraction) {
        this.labelFraction = labelFraction;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        CharSequence hint = getHint();
        int alpha = paint.getAlpha();
        paint.setAlpha((int) (labelFraction * 0xff));
        float extraOffset = (1 - labelFraction) * TOTAL_EXTRA_OFFSET;
        canvas.drawText(hint, 0, hint.length(), LABEL_OFFSET + 200, LABEL_SIZE + LABEL_OFFSET_Y + extraOffset, paint);
        paint.setAlpha(alpha);

        // 绘制背景
        if (hasFocus()) {
            paint.setColor(colorAccent);
            paint.setStrokeWidth(Utils.dp2Px(2));
        } else {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(Utils.dp2Px(0.75f));
        }
        canvas.drawLine(LABEL_OFFSET + 200,getBottom() - Utils.dp2Px(8), getWidth() - LABEL_OFFSET, getBottom() - Utils.dp2Px(8), paint);
    }
}
