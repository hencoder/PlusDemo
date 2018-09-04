package com.hencoder.a22.mvc;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hencoder.a22.R;

public class DataView extends LinearLayout implements MvcActivity.IView {
    TextView data1View;
    TextView data2View;

    public DataView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        data1View = findViewById(R.id.data1View);
        data2View = findViewById(R.id.data2View);
    }

    @Override
    public void showData(String data1, String data2) {
        data1View.setText(data1);
        data2View.setText(data2);
    }
}
