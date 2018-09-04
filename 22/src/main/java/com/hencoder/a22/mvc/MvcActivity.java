package com.hencoder.a22.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hencoder.a22.R;
import com.hencoder.a22.data.DataCenter;

public class MvcActivity extends AppCompatActivity {
    IView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mvc);

        dataView = findViewById(R.id.dataView);

        String[] data = DataCenter.getData();
        dataView.showData(data[0], data[1]);
    }

    public interface IView {
        void showData(String data1, String data2);
    }
}
