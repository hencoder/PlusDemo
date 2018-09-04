package com.hencoder.a22.mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hencoder.a22.R;

public class MvvmActivity extends AppCompatActivity {
    TextView data1View;
    TextView data2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data1View = findViewById(R.id.data1View);
        data2View = findViewById(R.id.data2View);

        new ViewModel(new ViewBinder(), data1View, data2View).load();
    }
}
