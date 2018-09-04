package com.hencoder.a17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hencoder.a17liba.AUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("lib a name: " + AUtils.getName());
        System.out.println("lib a combined name: " + AUtils.getCombinedName());
    }
}
