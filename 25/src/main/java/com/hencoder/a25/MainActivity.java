package com.hencoder.a25;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hencoder.a25_annotations.BindView;
import com.hencoder.a25_lib.Bind;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bind.bind(this);

        textView.setText("嘿哈");
    }

    class Inner {
        @BindView(R.id.textView) TextView textView;
    }
}
